package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.GameObjects.Board;
import com.nac.game.GameObjects.xY;
import com.nac.game.Utilities.ClickManager;
import com.nac.game.Utilities.GameOverCheck;

/**
 * Created by HappySaila on 10/30/16.
 * mega naughts and crosses
 */
public class MegaGame extends GameScreen {
    Driver game;
    Board[][] board;
    xY boardStart;
    Board currentBoard;
    Texture backInactive;
    Texture backActive;
    ClickManager clickManager;

    public MegaGame(Driver game) {
        super(game);
        this.game = game;
        boardStart = new xY(60, 20);
        board = new Board[3][3];
        backInactive = new Texture("buttons/backInactive.png");
        backActive = new Texture("buttons/backActive.png");
        createBoard();
    }

    public void render(float delta) {
        game.batch.begin();
        update(delta);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        renderBoard();
        updateButtons();
        renderButtons(game.batch);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        game.batch.end();
    }

    private void renderBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].render(game.batch);
            }
        }
    }

    private void update(float delta){
        updateCursor(delta);
    }

    private void updateCursor(float delta){
        int currentX = (x - boardStart.x)/currentBoard.getBlockSize();
        int currentY = (y - boardStart.y)/currentBoard.getBlockSize();
        int boardX = currentX/3;
        int boardY = currentY/3;
        int cellX = currentX%3;
        int cellY = currentY%3;

        //region clamping values to avoid arrayoutofbounds
        if (currentX>8){
            currentX = 8;
        }else if(currentX<0){
            currentX = 0;
        }

        if (currentY>8){
            currentY = 8;
        }else if(currentY<0){
            currentY = 0;
        }

        if (cellX>2){
            cellX = 2;
        }else if(cellX<0){
            cellX = 0;
        }

        if (cellY>2){
            cellY = 2;
        }else if(cellY<0){
            cellY = 0;
        }

        if (boardX>2){
            boardX = 2;
        }else if(boardX<0){
            boardX = 0;
        }

        if (boardY>2){
            boardY = 2;
        }else if(boardY<0){
            boardY = 0;
        }
        //endregion


        board[boardX][boardY].light(cellX, cellY, playerOneTurn, game.batch);
//
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickManager.canClick){
//            clickManager.reset();
//            if (playerOneTurn){
//                board.draw(currentX, currentY, 1);
//                game.sm.xPlace.play();
//            }else{
//                board.draw(currentX, currentY, 2);
//                game.sm.oPlace.play();
//            }
//            playerOneTurn = !playerOneTurn;
//            boolean gameOver = GameOverCheck.isGameOver(board, this);
//            boolean boardFull = GameOverCheck.isBoardFull(board, this);
//            if (gameOver){
//                game.DisposeScreen();
//                game.AddScreen(new GameOverScreen(game, playerOneWon));
//            }
//            else if (boardFull){
//                game.DisposeScreen();
//                game.AddScreen(new GameScreen(game));
//            }
//        }
    }

    private void createBoard(){
        int boardSize = (Driver.width - 120)/3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Board(game, new xY(boardStart.x + (i*boardSize), boardStart.y + (j*boardSize)), boardSize);
            }
        }
        currentBoard = board[1][1];
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


}

