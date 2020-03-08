package com.bongotest.videoplayer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bongotest.videoplayer.R;
import com.bongotest.videoplayer.common.Constants;
import com.bongotest.videoplayer.facades.PlayerFacade;
import com.bongotest.videoplayer.interfaces.PlayerInterface;
import com.bongotest.videoplayer.interfaces.UpdateInterface;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener, UpdateInterface, SeekBar.OnSeekBarChangeListener {
    private static final String VIDEO_SAMPLE = "tacoma_narrows";
    private VideoView mVideoView;

    private Uri getMedia() {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + VIDEO_SAMPLE);
    }

    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";
    private final String TAG = this.getClass().getSimpleName();
    private ImageButton btnPlay, btnRewind, btnForward;
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
                if ((mVideoView.getCurrentPosition() + 10000) < mVideoView.getDuration()) {
                    mVideoView.seekTo(mVideoView.getCurrentPosition() + 10000);
                }
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
        btnRewind = findViewById(R.id.btnRewind);
        btnForward = findViewById(R.id.btnForward);
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

    public String getTag() {
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
            mVideoView.pause();
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

    public String milliSecondsToTimer(long milliseconds) {
        if (milliseconds % 1000 != 0) {
            milliseconds = milliseconds + (1000 - (milliseconds % 1000));
        }

        String finalTimerString = "";
        String secondsString = "";
        String minutesString = "";

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
