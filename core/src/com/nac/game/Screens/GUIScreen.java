package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.Utilities.ClickManager;
import com.nac.game.Utilities.SoundManager;

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
    Texture playMusicActive;
    Texture playMusicInactive;
    Texture playMusicHoverActive;
    Texture playMusicHoverInactive;
    boolean play;
    boolean exit;

    boolean hoverMusicButton;
    boolean toggleMusicButton;

    int x;
    int y;

    public GUIScreen(Driver game) {
        super(game, true);
        toggleMusicButton = true;
        game.sm.music.loop(0.5f);

        //instantiate textures
        bg = new Texture("background.png");
        title = new Texture("buttons/title.png");
        playActive = new Texture("buttons/playActive.png");
        playInactive = new Texture("buttons/playInactive.png");
        playMusicHoverActive = new Texture("buttons/playMusicHoverActive.png");
        playMusicHoverInactive = new Texture("buttons/playMusicHoverInactive.png");
        playMusicActive = new Texture("buttons/playMusicActive.png");
        playMusicInactive = new Texture("buttons/playMusicInactive.png");
        exitInactive = new Texture("buttons/exitInactive.png");
        exitActive = new Texture("buttons/exitActive.png");

        clickManager = new ClickManager(0.2f);
    }

    public void render(float delta){
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        clickManager.update(delta);
        updateButtons();
        renderButtons(game.batch);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickManager.canClick){
            buttonListener();
            clickManager.reset();
        }
        System.out.println(toggleMusicButton);
        game.batch.end();
    }

    private void buttonListener(){
        if (play){
            game.sm.buttonPress.play();
            game.AddScreen(new SizeSelectScreen(game));
        } else if (exit){
            System.exit(0);
        } else if(hoverMusicButton){
            toggleMusicButton = !toggleMusicButton;
        }

        if(!toggleMusicButton && hoverMusicButton){
            game.sm.music.setVolume(0, 0.0f);
        } else if(toggleMusicButton && hoverMusicButton){
            game.sm.music.setVolume(0, 0.5f);
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
        if(!hoverMusicButton && !toggleMusicButton){
            sb.draw(playMusicHoverInactive, Driver.width / 2 - playMusicHoverInactive.getWidth() / 2, Driver.height / 16 * 6.5f);
        } else if(hoverMusicButton && !toggleMusicButton){
            sb.draw(playMusicHoverActive, Driver.width / 2 - playMusicHoverInactive.getWidth() / 2, Driver.height / 16 * 6.5f);
        } else if(!hoverMusicButton && toggleMusicButton){
            sb.draw(playMusicInactive, Driver.width / 2 - playMusicHoverInactive.getWidth() / 2, Driver.height / 16 * 6.5f);
        } else if(hoverMusicButton && toggleMusicButton){
            sb.draw(playMusicActive, Driver.width / 2 - playMusicHoverInactive.getWidth() / 2, Driver.height / 16 * 6.5f);
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

        if(x > Driver.width / 2 - playMusicHoverInactive.getWidth() / 2 && x < Driver.width / 2 - playMusicHoverInactive.getWidth() / 2 + playMusicHoverInactive.getWidth()
                && y > Driver.height / 16 * 6.5f && y < Driver.height / 16 * 6.5f + playMusicHoverInactive.getHeight()){
            hoverMusicButton = true;
        } else{
            hoverMusicButton = false;
        }
    }
}
