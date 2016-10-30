package com.nac.game.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.nac.game.Driver;

/**
 * Created by user on 10/29/2016.
 */
public class SoundManager {
    public Sound music;
    public Sound buttonPress;
    public Sound xPlace;
    public Sound oPlace;
    public Sound winner;

    public SoundManager(Driver game){
        buttonPress = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonPress.mp3"));
        music = Gdx.audio.newSound(Gdx.files.internal("sounds/music.mp3"));
        xPlace = Gdx.audio.newSound(Gdx.files.internal("sounds/xPlace.mp3"));
        oPlace = Gdx.audio.newSound(Gdx.files.internal("sounds/oPlace.mp3"));
        winner = Gdx.audio.newSound(Gdx.files.internal("sounds/winner.ogg"));
    }
}
