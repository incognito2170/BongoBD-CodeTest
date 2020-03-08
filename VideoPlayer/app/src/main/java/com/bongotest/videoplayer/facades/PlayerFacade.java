package com.bongotest.videoplayer.facades;

import com.bongotest.videoplayer.common.Constants;
import com.bongotest.videoplayer.interfaces.PlayerInterface;
import com.bongotest.videoplayer.interfaces.UpdateInterface;

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
