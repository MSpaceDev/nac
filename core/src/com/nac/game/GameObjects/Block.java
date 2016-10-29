package com.nac.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by HappySaila on 10/29/16.
 * individual square that is interactable
 */
public class Block {
    int val; //0=" " 1="X" 2="O"
    Texture img;
    Board board;
    xY position;
    xY boardStart;

    public Block(int x, int y, Board board) {
        this.position = new xY(x, y);
        this.board = board;
        boardStart = board.boardStart;

        //instantiate textures
        img = new Texture("cross.png");
    }

    public void render(SpriteBatch sb){
        sb.draw(img, boardStart.x + (position.x * board.blockSize), boardStart.y + (position.y * board.blockSize),
                board.blockSize, board.blockSize);
    }

    public int getVal() {
        return val;
    }

    public int getX(){
        return position.x;
    }

    public int getY(){
        return position.y;
    }
}
