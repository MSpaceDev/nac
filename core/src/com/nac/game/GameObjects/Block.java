package com.nac.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by HappySaila on 10/29/16.
 * individual square that is interactable
 */
public class Block{
    int val; //0=" " 1="X" 2="O"
    Sprite cross;
    Sprite naught;
    Sprite currentSprite;
    boolean flashUp;
    float alpha;

    Board board;
    xY position;
    xY boardStart;
    xY renderPosition;

    public Block(int x, int y, Board board) {
        val = 0;
        this.position = new xY(x, y);
        this.board = board;
        boardStart = board.boardStart;
        renderPosition = new xY(boardStart.x + (position.x * board.blockSize), boardStart.y + (position.y * board.blockSize));

        //instantiate textures
        cross = new Sprite(new Texture("cross.png"));
        naught = new Sprite(new Texture("naught.png"));

        //Set sizes
        cross.setSize(board.blockSize, board.blockSize);
        cross.setPosition(renderPosition.x, renderPosition.y);
        naught.setSize(board.blockSize, board.blockSize);
        naught.setPosition(renderPosition.x, renderPosition.y);
    }

    public void render(SpriteBatch sb){
        renderIcons(sb);
}

    private void renderIcons(SpriteBatch sb) {
        if (val == 1) {
            cross.draw(sb);
        } else if (val == 2) {
            naught.draw(sb);
        }
    }

    public void light(float delta){
        currentSprite = getCurrentSprite();
        if (flashUp){
            alpha += delta;
            currentSprite.setAlpha(alpha);
            if (alpha>0.9f){
                flashUp = false;
            }
        }else{
            alpha -= delta;
            currentSprite.setAlpha(alpha);
            if (alpha<0.1f){
                flashUp = true;
            }
        }
    }

    private Sprite getCurrentSprite(){
        if (val ==1){
            return cross;
        }else{
            return naught;
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
