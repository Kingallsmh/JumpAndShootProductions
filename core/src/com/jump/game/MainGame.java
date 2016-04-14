package com.jump.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.screens.GameScreen;

//No, evan you were not
public class MainGame extends Game{
	SpriteBatch batch;
	
        int difficulty = 3;
        
        public void setDifficulty(int difficulty)
        {
            this.difficulty = difficulty;
        }
        
	@Override
	public void create () {
            batch = new SpriteBatch();
            this.setScreen(new GameScreen(batch, difficulty));
	}

	@Override
	public void render () {
            super.render();
	}
}
