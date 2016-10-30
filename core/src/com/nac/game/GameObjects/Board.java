package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nac.game.Driver;
import com.nac.game.Screens.GUIScreen;
import com.nac.game.Screens.GameScreen;

/**
 * Created by HappySaila on 10/29/16.
 * will contain a grid of squares
 */
public class Board{
    Driver game;
    Texture back;
    Texture boardWhite;
    Texture boardRed;
    xY boardStart;
    Block[][] grid;
    int blockSize;
    int headingHeight;
    int size;
    int winner; //0=no winner 1="X" winner 2="O" winner
    boolean activeBoard;
    boolean flashUp;
    float alpha;
    Sprite board;

    public Board(Driver game, xY boardStart, int size) {
        this.game = game;
        this.boardStart = boardStart;
        boardRed = new Texture("boardRed.png");
        boardWhite = new Texture("boardWhite.png");
        back = new Texture("buttons/backInactive.png");
        headingHeight = back.getWidth();
        this.size = size;
        blockSize = size / 3;
        createGrid();
        board = new Sprite(boardWhite);
        board.setSize(size, size);
        board.setPosition(boardStart.x, boardStart.y);
        winner = 0;
    }

    public void render(SpriteBatch sb){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                grid[i][j].render(game.batch);
            }
        }
        if(!activeBoard){
            sb.draw(boardRed, boardStart.x, boardStart.y, size, size);
        } else{
            sb.draw(boardWhite, boardStart.x, boardStart.y, size, size);
        }
    }

    private void createGrid(){
        grid = new Block[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Block(j, i, this);
            }
        }
    }

    public boolean draw(int x, int y, int val){
//        will return true if something was drawn
        if (grid[y][x].getVal() == 0){
            grid[y][x].setVal(val);
        }else{
            return false;
        }
        return true;
    }

    public void flash(SpriteBatch sb, float delta){
        if (flashUp){
            alpha += delta;
            board.setAlpha(alpha);
            if (alpha>0.9f){
                flashUp = false;
            }
        }else{
            alpha -= delta;
            board.setAlpha(alpha);
            if (alpha<0.1f){
                flashUp = true;
            }
        }
        board.draw(sb);
    }

    public int getBlockSize() {
        return blockSize;
    }

    public xY getBoardStart() {
        return boardStart;
    }

    public Block[][] getGrid() {
        return grid;
    }


    public void light(int x, int y, boolean playerOneTurn, SpriteBatch sb){
        if (grid[y][x].getVal()==0){
            grid[y][x].light(Gdx.graphics.getDeltaTime(), playerOneTurn, sb);
        }
    }

    public int getWinner() {
        return winner;
    }

    public void setActiveBoard(boolean activeBoard) {
        this.activeBoard = activeBoard;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
