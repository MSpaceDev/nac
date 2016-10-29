package com.nac.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nac.game.Screens.GUIScreen;
import com.nac.game.Screens.GameScreen;
import com.nac.game.Utilities.SoundManager;

import java.util.Stack;

public class Driver extends Game {
	public SpriteBatch batch;
	public static final int height = 500;
	public static final int width = 500;
	Stack<Screen> screens;
	public SoundManager sm;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screens = new Stack<Screen>();
		sm = new SoundManager(this);
		AddScreen(new GUIScreen(this));
	}

	public void AddScreen(Screen screen){
		screens.push(screen);
		setScreen(screens.peek());
	}

	public void DisposeScreen(){
		if (screens.size()>1){
			screens.pop();
			setScreen(screens.peek());
		}
	}

	@Override
	public void render () {
		super.render();
	}
}
