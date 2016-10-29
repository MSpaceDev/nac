package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.Screens.GameScreen;

/**
 * Created by HappySaila on 10/29/16.
 * individual square that is interactable
 */
public class Block{
    int val; //0=" " 1="X" 2="O"
    Sprite cross;
    Sprite naught;
    Board board;
    xY position;
    xY boardStart;

    public Block(int x, int y, Board board) {
        val = 0;
        this.position = new xY(x, y);
        this.board = board;
        boardStart = board.boardStart;

        //instantiate textures
        cross = new Sprite(new Texture("cross.png"));
        naught = new Sprite(new Texture("naught.png"));

        //Set sizes
        cross.setSize(board.blockSize, board.blockSize);
        naught.setSize(board.blockSize, board.blockSize);
    }

    public void render(SpriteBatch sb){
        renderIcons(sb);
}

    private void renderIcons(SpriteBatch sb){
        if (val == 1){
            cross.draw(sb);
        } else if(val == 2){
            naught.draw(sb);
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
}
