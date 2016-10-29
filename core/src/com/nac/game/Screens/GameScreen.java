package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nac.game.Driver;
import com.nac.game.GameObjects.Board;
import com.nac.game.GameObjects.xY;

/**
 * Created by user on 10/29/2016.
 */
public class GameScreen implements Screen {
    Driver game;
    Board board;

    public GameScreen(Driver game) {
        this.game = game;
        board = new Board(game, 1, new xY(0,0));
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        board.render(game.batch);
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
