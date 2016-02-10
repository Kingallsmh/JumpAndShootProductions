/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.Configurations;
import com.jump.game.world.Floor;
import com.jump.game.world.Objects;

/**
 *
 * @author NSCCSTUDENT
 */
public class GameScreen implements Screen{
    SpriteBatch batch;
    OrthographicCamera cam;
    Objects floor;

    public GameScreen(SpriteBatch batch){
        this.batch = batch;
        this.cam = new OrthographicCamera();
        cam.setToOrtho(false, Configurations.cameraWidth, Configurations.cameraHeight);
        floor = new Floor(30, 40);
        
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.setProjectionMatrix(cam.combined);
	batch.begin();
        // Start drawing here
        
        
        
        //Stop drawing
	batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
    
    
}
