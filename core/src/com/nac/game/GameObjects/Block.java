package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Utilities.utils;

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
    int speed;
    float rotation;

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
        cross.setOrigin(cross.getWidth()/2, cross.getHeight()/2);
        naught.setSize(board.blockSize, board.blockSize);
        naught.setPosition(renderPosition.x, renderPosition.y);
        naught.setOrigin(naught.getWidth()/2, naught.getHeight()/2);
        speed = utils.generate(-15, 15);

    }

    public void render(SpriteBatch sb){
        renderIcons(sb);
        update(Gdx.graphics.getDeltaTime());
}

    private void renderIcons(SpriteBatch sb) {
        if (val == 0) {
            cross.draw(sb);
            cross.setAlpha(1);
        } else if (val == 2) {
            naught.draw(sb);
            naught.setAlpha(1);
        }
    }

    public void light(float delta, boolean playerOneTurn, SpriteBatch sb){
        if (playerOneTurn){
            currentSprite = cross;
        }else{
            currentSprite = naught;
        }
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
        currentSprite.draw(sb);
    }

    private void update(float delta){
        rotation+=delta*speed;
        naught.setRotation(rotation);
        cross.setRotation(rotation);
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
