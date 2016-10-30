package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    xY boardStart;
    Block[][] grid;
    int blockSize;
    int headingHeight;
    int winner; //0=no winner 1="X" winner 2="O" winner

    public Board(Driver game, xY boardStart, int size) {
        this.game = game;
        this.boardStart = boardStart;
        back = new Texture("buttons/backInactive.png");
        headingHeight = back.getWidth();
        blockSize = size / 3;
        createGrid();
    }

    public void render(SpriteBatch sb){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                grid[i][j].render(game.batch);
            }
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

    public void draw(int x, int y, int val){
        if (grid[y][x].getVal() == 0){
            grid[y][x].setVal(val);
        }
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

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
