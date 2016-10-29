package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nac.game.Driver;

/**
 * Created by user on 10/29/2016.
 */
public class GameScreen implements Screen {
    Driver game;
    Texture img = new Texture("cross.png");
    public GameScreen(Driver game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        game.batch.draw(img, 0, 0);
        game.batch.end();
    }

    //region rubbish
    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    //endregion
}
