package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;

/**
 * Created by user on 10/29/2016.
 */
public class SizeSelectScreen extends GUIScreen {
    Texture simpleActive;
    Texture simpleInactive;
    Texture megaActive;
    Texture megaInactive;
    boolean simple;
    boolean mega;

    public SizeSelectScreen(Driver game) {
        super(game);
        simpleActive = new Texture("buttons/simpleActive.png");
        simpleInactive = new Texture("buttons/simpleInactive.png");
        megaActive = new Texture("buttons/megaActive.png");
        megaInactive = new Texture("buttons/megaInactive.png");
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
        if(simple) {
            game.DisposeScreen();
            game.AddScreen(new GameScreen(game, true));
        } else if(mega){
            game.DisposeScreen();
            game.AddScreen(new GameScreen(game, false));
        }
    }

    private void renderButtons(SpriteBatch sb){
        if(!simple){
            sb.draw(simpleInactive, Driver.width / 2 - simpleInactive.getWidth() / 2, Driver.height / 8 * 5);
        } else{
            sb.draw(simpleActive, Driver.width / 2 - simpleActive.getWidth() / 2, Driver.height / 8 * 5);
        }

        if(!mega){
            sb.draw(megaInactive, Driver.width / 2 - simpleInactive.getWidth() / 2, Driver.height / 8 * 3);
        } else{
            sb.draw(megaActive, Driver.width / 2 - simpleActive.getWidth() / 2, Driver.height / 8 * 3);
        }
    }

    private void updateButtons(){
        x = Gdx.input.getX();
        y = Driver.height - Gdx.input.getY();

        if(x > Driver.width / 2 - simpleInactive.getWidth() / 2 && x <  Driver.width / 2 - simpleInactive.getWidth() / 2 + simpleInactive.getWidth() &&
                y > Driver.height / 8 * 5 && y < Driver.height / 8 * 5 + simpleInactive.getHeight()){
            simple = true;
        } else{
            simple = false;
        }
        if(x > Driver.width / 2 - megaInactive.getWidth() / 2 && x <  Driver.width / 2 - megaInactive.getWidth() / 2 + megaInactive.getWidth() &&
                y > Driver.height / 8 * 3 && y < Driver.height / 8 * 3 + megaInactive.getHeight()){
            mega = true;
        } else{
            mega = false;
        }
    }
}
