package com.nac.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Driver;
import com.nac.game.GameObjects.Particle;

import java.util.ArrayList;

/**
 * Created by user on 10/29/2016.
 */
public class GameOverScreen extends GameScreen {
    Texture xWin;
    Texture oWin;
    Texture draw;
    Texture bg;
    int winner;

    ArrayList<Particle> particles;

    public GameOverScreen(Driver game, int winner) {
        super(game);
        bg = new Texture("background.png");
        xWin = new Texture("buttons/xWin.png");
        oWin = new Texture("buttons/oWin.png");
        draw = new Texture("buttons/draw.png");
        game.sm.winner.play();
        this.winner = winner;

        particles = new ArrayList<Particle>();
        for (int i = 0; i < 60; i++) {
            particles.add(new Particle());
        }
    }

    public void render(float delta){
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        renderParticles(game.batch);
        if(winner == 0){
            game.batch.draw(draw, Driver.width / 2 - oWin.getWidth() / 2, Driver.height / 8 * 5);
        } else if(winner == 1) {
            game.batch.draw(xWin, Driver.width / 2 - xWin.getWidth() / 2, Driver.height / 8 * 5);
        } else {
            game.batch.draw(oWin, Driver.width / 2 - oWin.getWidth() / 2, Driver.height / 8 * 5);
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            buttonListener();
        }
        updateButtons();
        renderButtons(game.batch);
        game.batch.end();
    }

    private void buttonListener(){
        if(back){
            game.sm.buttonPress.play();
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

    private void renderParticles(SpriteBatch sb){
        for (Particle p: particles){
            p.render(sb);
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
