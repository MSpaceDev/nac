package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by HappySaila on 10/29/16.
 * individual square that is interactable
 */
public class Block {
    int val; //0=" " 1="X" 2="O"
    float timer;
    boolean flashUp;
    float alpha;

    Texture img;
    Sprite block;
    Board board;
    xY position;
    xY boardStart;
    xY renderPosition;

    public Block(int x, int y, Board board) {
        this.position = new xY(x, y);
        this.board = board;
        boardStart = board.boardStart;
        renderPosition = new xY(boardStart.x + (position.x * board.blockSize), boardStart.y + (position.y * board.blockSize));

        //instantiate textures
        img = new Texture("cross.png");
        block = new Sprite(img);
        block.setSize(board.blockSize, board.blockSize);
        block.setPosition(renderPosition.x, renderPosition.y);
    }

    public void render(SpriteBatch sb){
    }

    public void light(float delta){
        if (flashUp){
            alpha += delta;
            block.setAlpha(alpha);
            if (alpha>0.9f){
                flashUp = false;
            }
        }else{
            alpha -= delta;
            block.setAlpha(alpha);
            if (alpha<0.1f){
                flashUp = true;
            }
        }
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

    public void setVal(int val) {
        this.val = val;
    }
}
