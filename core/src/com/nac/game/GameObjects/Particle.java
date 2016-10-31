package com.nac.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.Utilities.utils;

/**
 * Created by user on 10/30/2016.
 */
public class Particle extends Sprite{

    float yPos;
    int speed;
    int rotationDirection;
    float i;


    public Particle() {
        super(new Texture("naughtWin.png"));
        i = utils.generate(1,8);

        speed = utils.generate(1,100);
        if(utils.generate(2) == 0){
            setTexture(new Texture("naughtWin.png"));
            rotationDirection = -1;
        } else{
            setTexture(new Texture("crossWin.png"));
            rotationDirection = 1;
        }
        setAlpha(i/10);
        setSize(i * 3, i * 3);
        yPos = utils.generate(0, 500)*-1;
        setPosition(utils.generate(0, Driver.width - (int)i), yPos);
        setOrigin(i/2, i/2);
    }

    public void render(SpriteBatch sb){
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        draw(sb);
    }

    private void update(float delta){
        yPos += delta * speed;
        setY(yPos);
        setRotation(yPos*rotationDirection);
        if(getY() > Driver.height){
            yPos = 0 - i;
        }
    }
}
