package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;

/**
 * Created by user on 10/29/2016.
 */
public class GUIScreen extends GameScreen {
    Texture bg;
    Texture title;
    Texture playActive;
    Texture playInactive;
    Texture exitActive;
    Texture exitInactive;
    boolean play;
    boolean exit;
    int x;
    int y;

    public GUIScreen(Driver game) {
        super(game, true);
        bg = new Texture("background.png");
        title = new Texture("buttons/title.png");
        playInactive = new Texture("buttons/playInactive.png");
        playActive = new Texture("buttons/playActive.png");
        exitInactive = new Texture("buttons/exitInactive.png");
        exitActive = new Texture("buttons/exitActive.png");
    }

    public void render(float delta){
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        updateButtons();
        renderButtons(game.batch);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        game.batch.end();
    }

    private void buttonListener(){
        if (play){
            game.AddScreen(new SizeSelectScreen(game));
        } else if (exit){
            System.exit(0);
        }
    }

    private void renderButtons(SpriteBatch sb){
        sb.draw(title, Driver.width / 2 - title.getWidth() / 2, Driver.height / 8 * 6);

        if(!play) {
        sb.draw(playInactive, Driver.width / 2 - playInactive.getWidth() / 2, Driver.height / 8 * 4);
    } else{
        sb.draw(playActive, Driver.width / 2 - playActive.getWidth() / 2, Driver.height / 8 * 4);
    }
        if(!exit) {
        sb.draw(exitInactive, Driver.width / 2 - exitInactive.getWidth() / 2, Driver.height / 8 * 2);
    } else{
        sb.draw(exitActive, Driver.width / 2 - exitActive.getWidth() / 2, Driver.height / 8 * 2);
    }
}

    private void updateButtons(){
        x = Gdx.input.getX();
        y = Driver.height - Gdx.input.getY();

        if (x > Driver.width / 2 - playInactive.getWidth() / 2 && x <  Driver.width / 2 - playInactive.getWidth() / 2 + playInactive.getWidth() &&
                y > Driver.height / 8 * 4 && y < Driver.height / 8 * 4 + playInactive.getHeight()){
            play = true;
        }else{
            play = false;
        }

        if (x > Driver.width / 2 - exitInactive.getWidth() / 2 && x <  Driver.width / 2 - exitInactive.getWidth() / 2 + exitInactive.getWidth() &&
                y > Driver.height / 8 * 2 && y < Driver.height / 8 * 2 + exitInactive.getHeight()){
            exit = true;
        }else{
            exit = false;
        }


    }
}
