package com.jump.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.screens.GameScreen;

//No, evan you were not
public class MainGame extends Game{
	SpriteBatch batch;
                
	@Override
	public void create () {
            batch = new SpriteBatch();
            this.setScreen(new GameScreen(batch));
	}

	@Override
	public void render () {
            super.render();
	}
}
