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
    int size; //how many grids must be nested in each cell
    int blockSize;
    int headingHeight;

    public Board(Driver game, int gridSize, xY boardStart) {
        this.game = game;
        this.boardStart = boardStart;
        back = new Texture("buttons/backInactive.png");
        headingHeight = back.getWidth();
        size = (int)Math.pow(3, gridSize);
        blockSize = Math.min(Driver.width - 2*boardStart.x, Driver.height - 2*boardStart.y) / size;
        createGrid();
    }

    public void render(SpriteBatch sb){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                grid[i][j].render(game.batch);
            }
        }
    }

    private void createGrid(){
        grid = new Block[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Block(j, i, this);
            }
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

    public int getSize() {
        return size;
    }

    public void light(int x, int y){
        grid[y][x].light(Gdx.graphics.getDeltaTime());
    }
}
