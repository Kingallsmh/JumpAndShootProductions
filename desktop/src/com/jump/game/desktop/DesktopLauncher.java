package com.jump.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jump.game.Configurations;
import com.jump.game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Princess Kidnapped!";
                config.width = Configurations.screenWidth;
                config.height = Configurations.screenHeight;
		new LwjglApplication(new MainGame(), config);
	}
}
