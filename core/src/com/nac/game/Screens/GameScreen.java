package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.GameObjects.Board;
import com.nac.game.GameObjects.xY;
import com.nac.game.Utilities.ClickManager;
import com.nac.game.Utilities.GameOverCheck;

/**
 * Created by user on 10/29/2016.
 */
public class GameScreen implements Screen {
    Texture backInactive;
    Texture backActive;
    boolean back;
    boolean playerOneTurn;
    boolean playerOneWon;
    int x;
    int y;

    Driver game;
    Board board;

    ClickManager clickManager;

    public GameScreen(Driver game) {
        this.game = game;
        board = new Board(game, new xY(60,20), Driver.width - 120);
        backInactive = new Texture("buttons/backInactive.png");
        backActive = new Texture("buttons/backActive.png");
        clickManager = new ClickManager();
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        update(delta);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        board.render(game.batch);
        updateButtons();
        renderButtons(game.batch);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            game.DisposeScreen();
            game.AddScreen(new GameOverScreen(game, true));
        }
        game.batch.end();
    }

    private void update(float delta){
        updateCursor(delta);
        clickManager.update(delta);
    }

    private void updateCursor(float delta){
        int currentX = (x - board.getBoardStart().x)/board.getBlockSize();
        int currentY = (y - board.getBoardStart().y)/board.getBlockSize();

        if (currentX>2){
            currentX = 2;
        }else if(currentX<0){
            currentX = 0;
        }

        if (currentY>2){
            currentY = 2;
        }else if(currentY<0){
            currentY = 0;
        }
        board.light(currentX, currentY, playerOneTurn, game.batch);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickManager.canClick){
            clickManager.reset();
            if (playerOneTurn){
                board.draw(currentX, currentY, 1);
                game.sm.xPlace.play();
            }else{
                board.draw(currentX, currentY, 2);
                game.sm.oPlace.play();
            }
            playerOneTurn = !playerOneTurn;
            boolean gameOver = GameOverCheck.isGameOver(board, this);
            boolean boardFull = GameOverCheck.isBoardFull(board, this);
            if (gameOver){
                game.DisposeScreen();
                game.AddScreen(new GameOverScreen(game, playerOneWon));
            }
            else if (boardFull){
                game.DisposeScreen();
                game.AddScreen(new GameScreen(game));
            }
        }
    }

    public void setPlayerOneWon(boolean playerOneWon) {
        this.playerOneWon = playerOneWon;
    }

    //region button
    private void buttonListener(){
        if(back) {
            game.sm.buttonPress.play();
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
//    endregion

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
