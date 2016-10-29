package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.GameObjects.Board;
import com.nac.game.GameObjects.xY;
import com.nac.game.Utilities.GameOverCheck;

/**
 * Created by user on 10/29/2016.
 */
public class GameScreen implements Screen {
    Texture backInactive;
    Texture backActive;
    boolean back;
    int x;
    int y;

    Driver game;
    Board board;

    public GameScreen(Driver game, boolean simple) {
        this.game = game;
        if (simple){
            board = new Board(game, 1, new xY(60,20));
        } else{
            board = new Board(game, 2, new xY(60,20));
        }
        backInactive = new Texture("buttons/backInactive.png");
        backActive = new Texture("buttons/backActive.png");
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        board.render(game.batch);
        updateButtons();
        renderButtons(game.batch);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            game.DisposeScreen();
            game.AddScreen(new GameOverScreen(game));
        }
        game.batch.end();
    }

    private void buttonListener(){
        if(back) {
            game.DisposeScreen();
        }
    }

    private void renderButtons(SpriteBatch sb){
        if(!back){
            sb.draw(backInactive, Driver.width / 7 * 4, Driver.height / 10 * 8);
        } else{
            sb.draw(backActive, Driver.width / 7 * 4, Driver.height / 10 * 8);
        }
    }

    private void updateButtons(){
        x = Gdx.input.getX();
        y = Driver.height - Gdx.input.getY();

        if(x > Driver.width / 7 * 4 && x <  Driver.width / 7 * 4 + backInactive.getWidth() && y > Driver.height / 10 * 8 && y < Driver.height / 10 * 8 + backInactive.getHeight()){
            back = true;
        } else{
            back = false;
        }
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
