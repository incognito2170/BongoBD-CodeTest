# Source code for Video Player problem with Local Unit Test code.

* Unit test code is placed under "BongoBD-CodeTest/VideoPlayer/app/src/test/java/com/bongotest/videoplayer/".

## Q3. Write a video player application with ‘Play’, ‘Forward’ , ‘Rewind’ functionalities. Please write pseudocode for this program and explain the design pattern you will use to develop these three functionalities.

### Answer:

Several design patterns can be used to solve this problem. For example, Facade pattern, Comand pattern, etc. I will be using **Facade design pattern** to build this application. It might seem that Command pattern would be more suited as the play/pause functionality is under the same button, but I believe Facade will be more suited to implement a smooth solution.

Our layout file will consist of one VideoView, one SeekBar, two TextViews to show current video progress and remaining time, and three Button widgets for Play, Rewind and Forward functionalities.

* activity_video_player.xml:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorPrimary"
    tools:context=".activities.VideoPlayerActivity">

    <VideoView
        android:id="@+id/videoView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintDimensionRatio="4:3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

    <SeekBar
        android:id="@+id/seekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@+id/videoView"/>

    <RelativeLayout
        android:id="@+id/time_tv_ll"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@+id/seekbar">

        <TextView
            android:id="@+id/progress_time_tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/initial_progress_time"
            android:textColor="@android:color/white"
            android:layout_marginStart="5dp"
            android:layout_alignParentStart="true"/>

        <TextView
            android:id="@+id/remaining_time_tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginEnd="5dp"
            android:text="@string/initial_remaining_time"
            android:textColor="@android:color/white"
            android:layout_alignParentEnd="true"/>
    </RelativeLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginTop="5dp"
        app:layout_constraintTop_toBottomOf="@+id/time_tv_ll"
        android:weightSum="3">

        <ImageButton
            android:id="@+id/btnRewind"
            android:layout_width="5dp"
            android:layout_weight="1"
            android:layout_height="60dp"
            android:background="@drawable/button_bg"
            android:src="@drawable/rewind"
            android:padding="10dp"
            android:layout_margin="10dp"/>

        <ImageButton
            android:id="@+id/btnPlay"
            android:layout_width="5dp"
            android:layout_weight="1"
            android:layout_height="60dp"
            android:background="@drawable/button_bg"
            android:src="@drawable/play"
            android:padding="10dp"
            android:layout_margin="10dp"/>

        <ImageButton
            android:id="@+id/btnForward"
            android:layout_width="5dp"
            android:layout_weight="1"
            android:layout_height="60dp"
            android:background="@drawable/button_bg"
            android:src="@drawable/forward"
            android:padding="10dp"
            android:layout_margin="10dp"/>
    </LinearLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
```

To implement the Facade pattern, we will use a PlayerInterface and an UpdateInterface.

* PlayerInterface.java:

```java
public interface PlayerInterface {
    void play();
    void pause();
    void forward();
    void rewind();
    void seek();
}
```

* UpdateInterface.java:

```java
public interface UpdateInterface {
    void update(String value);
}
```

We will have a constant class file to put constant values.

```java
public class Constants {
    public static final String PLAY_TAG = "Play";
    public static final String PAUSE_TAG = "Pause";
    public static final String PLAY = "Playing";
    public static final String PAUSE = "Paused";
    public static final String REWIND = "Rewind";
    public static final String FORWARD = "Forward";
    public static final String SEEK = "Seek";
}
```

Next we will create the Facade class which will implement the PlayerInterface.

* PlayerFacade.java:

```java
public class PlayerFacade implements PlayerInterface {
    private UpdateInterface update;

    public PlayerFacade(UpdateInterface update){
        this.update = update;
    }

    @Override
    public void play() {
        update.update(Constants.PLAY);
    }

    @Override
    public void pause() {
        update.update(Constants.PAUSE);
    }

    @Override
    public void forward() {
        update.update(Constants.FORWARD);
    }

    @Override
    public void rewind() {
        update.update(Constants.REWIND);
    }

    @Override
    public void seek() {
        update.update(Constants.SEEK);
    }
}
```

VideoPlayerActivity is the entry point of the app.

* VideoPlayerActivity.java:

```java
public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener, UpdateInterface, SeekBar.OnSeekBarChangeListener {
    private static final String VIDEO_SAMPLE = "tacoma_narrows";
    private VideoView mVideoView;
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";
    private ImageButton btnPlay;
    private PlayerInterface player;
    private int pauseDrawable, playDrawable;
    private SeekBar seekBar;
    private Handler updateHandler = new Handler();
    private int progressAfterSeek;
    private TextView progressTimeTv, remainingTimeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        initialization();

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
    }

    @Override
    public void update(String value) {
        if (null == mVideoView) {
            return;
        }
        switch(value) {
            case Constants.PLAY:
                updateHandler.postDelayed(updateVideoTime, 1000);
                mVideoView.start();
                break;
            case Constants.PAUSE:
                mVideoView.pause();
                break;
            case Constants.REWIND:
                if(mVideoView.getCurrentPosition() > 10000) {
                    mVideoView.seekTo(mVideoView.getCurrentPosition() - 10000);
                } else {
                    mVideoView.seekTo(1);
                }
                break;
            case Constants.FORWARD:
                mVideoView.seekTo(Math.min((mVideoView.getCurrentPosition() + 10000), mVideoView.getDuration()));
                break;
            case Constants.SEEK:
                mVideoView.seekTo(progressAfterSeek);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (null == player) {
            return;
        }
        switch (v.getId()) {
            case R.id.btnRewind:
                player.rewind();
                break;
            case R.id.btnForward:
                player.forward();
                break;
            case R.id.btnPlay:
                if (getTag().equals(Constants.PLAY_TAG)) {
                    setTag(Constants.PAUSE_TAG);
                    player.play();
                    changeImage(pauseDrawable);
                } else {
                    setTag(Constants.PLAY_TAG);
                    player.pause();
                    changeImage(playDrawable);
                }
                break;
        }
    }

    private void initialization() {
        mVideoView = findViewById(R.id.videoView);
        ImageButton btnRewind = findViewById(R.id.btnRewind);
        ImageButton btnForward = findViewById(R.id.btnForward);
        btnPlay = findViewById(R.id.btnPlay);
        seekBar = findViewById(R.id.seekbar);
        progressTimeTv = findViewById(R.id.progress_time_tv);
        remainingTimeTv = findViewById(R.id.remaining_time_tv);

        btnPlay.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnRewind.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

        setTag(Constants.PLAY_TAG);
        player = new PlayerFacade(this);

        playDrawable = R.drawable.play;
        pauseDrawable = R.drawable.pause;

        changeImage(playDrawable);
    }

    public void setTag(String tag) {
        btnPlay.setTag(tag);
    }

    private String getTag() {
        return (String) btnPlay.getTag();
    }

    private void changeImage(int imgResource) {
        btnPlay.setImageResource(imgResource);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    private Uri getMedia() {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + VIDEO_SAMPLE);
    }

    private void initializePlayer() {
        Uri videoUri = getMedia();
        mVideoView.setVideoURI(videoUri);

        if (mCurrentPosition > 0) {
            mVideoView.seekTo(mCurrentPosition);
        } else {
            // Skipping to 1 shows the first frame of the video.
            mVideoView.seekTo(1);
        }

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setProgress(0);
                seekBar.setMax(mVideoView.getDuration());
                remainingTimeTv.setText(milliSecondsToTimer(mVideoView.getDuration()));
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(VideoPlayerActivity.this, "Playback completed",
                        Toast.LENGTH_LONG).show();
                onPlayerTaskFinished();
            }
        });
    }

    private void onPlayerTaskFinished() {
        changeImage(playDrawable);
        mVideoView.seekTo(1);
        seekBar.setProgress(0);
        setProgressAndRemainingTimeTexts(true);
        setTag(Constants.PLAY_TAG);
        updateHandler.removeCallbacks(updateVideoTime);
    }

    private Runnable updateVideoTime = new Runnable() {
        @Override
        public void run() {
            long currentPosition = mVideoView.getCurrentPosition();
            setProgressAndRemainingTimeTexts(false);
            seekBar.setProgress((int) currentPosition);
            updateHandler.postDelayed(this, 1000);
        }
    };

    private void setProgressAndRemainingTimeTexts(boolean isVideoFinished) {
        if (!isVideoFinished) {
            long progressTime = mVideoView.getCurrentPosition();
            long remainingTime = mVideoView.getDuration() - progressTime;
            progressTimeTv.setText(milliSecondsToTimer(progressTime));
            remainingTimeTv.setText(milliSecondsToTimer(remainingTime));
        } else {
            progressTimeTv.setText(getResources().getString(R.string.initial_progress_time));
            remainingTimeTv.setText(milliSecondsToTimer(mVideoView.getDuration()));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
        onPlayerTaskFinished();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            player.pause();
            changeImage(playDrawable);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser) {
            // Means that the seekbar value is changed by user
            if (!mVideoView.isPlaying()) {
                updateHandler.postDelayed(updateVideoTime, 1000);
            }
            progressAfterSeek = progress;
            player.seek();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public static String milliSecondsToTimer(long milliseconds) {
        if (milliseconds % 1000 != 0) {
            milliseconds = milliseconds + (1000 - (milliseconds % 1000));
        }

        String finalTimerString = "";
        String secondsString;
        String minutesString;

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // When hours > 0, prepending 0 to minutes if it is one digit
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = "" + minutes;
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutesString + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }
}
```

We can have the following test class to write unit test code for video player:

* VideoPlayerActivityUnitTest.java:

public class VideoPlayerActivityUnitTest {

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithoutRemainder_returnsTrue() {
        assertEquals("00:09", VideoPlayerActivity.milliSecondsToTimer(9000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithoutRemainder_returnsFalse() {
        assertNotEquals("00:9", VideoPlayerActivity.milliSecondsToTimer(9000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithRemainder_returnsTrue() {
        assertEquals("00:10", VideoPlayerActivity.milliSecondsToTimer(9500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitSecondsWithRemainder_returnsFalse() {
        assertNotEquals("00:09", VideoPlayerActivity.milliSecondsToTimer(9500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithoutRemainder_returnsTrue() {
        assertEquals("00:15", VideoPlayerActivity.milliSecondsToTimer(15000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithoutRemainder_returnsFalse() {
        assertNotEquals("00:16", VideoPlayerActivity.milliSecondsToTimer(15000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithRemainder_returnsTrue() {
        assertEquals("00:16", VideoPlayerActivity.milliSecondsToTimer(15500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitSecondsWithRemainder_returnsFalse() {
        assertNotEquals("00:15", VideoPlayerActivity.milliSecondsToTimer(15500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithoutRemainder_returnsTrue() {
        assertEquals("01:55", VideoPlayerActivity.milliSecondsToTimer(115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithoutRemainder_returnsFalse() {
        assertNotEquals("01:16", VideoPlayerActivity.milliSecondsToTimer(115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithRemainder_returnsTrue() {
        assertEquals("01:56", VideoPlayerActivity.milliSecondsToTimer(115500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitMinutesWithRemainder_returnsFalse() {
        assertNotEquals("01:15", VideoPlayerActivity.milliSecondsToTimer(115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithoutRemainder_returnsTrue() {
        assertEquals("16:55", VideoPlayerActivity.milliSecondsToTimer(1015000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithoutRemainder_returnsFalse() {
        assertNotEquals("01:55", VideoPlayerActivity.milliSecondsToTimer(1015000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithRemainder_returnsTrue() {
        assertEquals("16:56", VideoPlayerActivity.milliSecondsToTimer(1015500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitMinutesWithRemainder_returnsFalse() {
        assertNotEquals("16:55", VideoPlayerActivity.milliSecondsToTimer(1015500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithoutRemainder_returnsTrue() {
        assertEquals("2:31:55", VideoPlayerActivity.milliSecondsToTimer(9115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithoutRemainder_returnsFalse() {
        assertNotEquals("02:31:55", VideoPlayerActivity.milliSecondsToTimer(9115000));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithRemainder_returnsTrue() {
        assertEquals("2:31:56", VideoPlayerActivity.milliSecondsToTimer(9115500));
    }

    @Test
    public void testMillisecondsToTimer_testSingleDigitHoursWithRemainder_returnsFalse() {
        assertNotEquals("2:31:55", VideoPlayerActivity.milliSecondsToTimer(9115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithoutRemainder_returnsTrue() {
        assertEquals("10:51:55", VideoPlayerActivity.milliSecondsToTimer(39115000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithoutRemainder_returnsFalse() {
        assertNotEquals("01:51:55", VideoPlayerActivity.milliSecondsToTimer(39115000));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithRemainder_returnsTrue() {
        assertEquals("10:51:56", VideoPlayerActivity.milliSecondsToTimer(39115500));
    }

    @Test
    public void testMillisecondsToTimer_testDoubleDigitHoursWithRemainder_returnsFalse() {
        assertNotEquals("10:51:55", VideoPlayerActivity.milliSecondsToTimer(39115500));
    }
}

