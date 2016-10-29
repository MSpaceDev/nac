package com.nac.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nac.game.Driver;

/**
 * Created by HappySaila on 10/29/16.
 * will contain a grid of squares
 */
public class Board {
    Driver game;
    xY boardStart;
    Block[][] grid;
    int size; //how many grids must be nested in each cell
    int blockSize;

    public Board(Driver game, int gridSize, xY boardStart) {
        this.game = game;
        this.boardStart = boardStart;
        size = (int)Math.pow(3, gridSize);
        blockSize = Math.max(Driver.width - 2*boardStart.x, Driver.height - 2*boardStart.y) / size;
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

    public Block[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }
}
