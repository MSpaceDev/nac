package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;

/**
 * Created by user on 10/29/2016.
 */
public class GameOverScreen extends GUIScreen {

    public GameOverScreen(Driver game) {
        super(game);
    }

    public void render(float delta){
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        updateButtons();
        renderButtons(game.batch);
        game.batch.end();
    }

    private void buttonListener(){
        if(back){
            game.DisposeScreen();
        }
    }

    private void renderButtons(SpriteBatch sb){
        if (!back) {
            sb.draw(backInactive, Driver.width / 2 - backInactive.getWidth() / 2, Driver.height / 8 * 3);
        }else{
            sb.draw(backActive, Driver.width / 2 - backActive.getWidth() / 2, Driver.height / 8 * 3);
        }
    }

    private void updateButtons(){
        x = Gdx.input.getX();
        y = Driver.height - Gdx.input.getY();

        if(x > Driver.width / 2 - backInactive.getWidth() / 2 && x <  Driver.width / 2 + backInactive.getWidth() / 2
                && y > Driver.height / 8 * 3 && y < Driver.height / 8 * 3 + backInactive.getHeight()){
            back = true;
        } else{
            back = false;
        }
    }
}
