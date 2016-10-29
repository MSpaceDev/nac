package com.nac.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nac.game.Driver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "naughts and crosses";
		config.height = Driver.height;
		config.width = Driver.width;
		config.resizable = false;
		new LwjglApplication(new Driver(), config);
	}
}
