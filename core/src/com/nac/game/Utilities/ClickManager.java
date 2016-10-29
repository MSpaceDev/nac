package com.nac.game.Utilities;

/**
 * Created by HappySaila on 10/29/16.
 * will manage if the cursor can be clicked
 */
public class ClickManager {
    public boolean canClick;
    float timer;
    float clickDelay = 0.5f;

    public void update(float delta){
        timer+=delta;
        if (timer>clickDelay){
            canClick = true;
            timer = 0;
        }
    }

    public void reset(){
        timer = 0;
        canClick = false;
    }
}
