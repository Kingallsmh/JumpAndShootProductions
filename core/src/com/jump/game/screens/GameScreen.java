/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jump.game.Configurations;
import com.jump.game.world.Environment;
import com.jump.game.world.Stage;
import com.jump.game.world.TestStage;

/**
 *
 * @author NSCCSTUDENT
 */
public class GameScreen implements Screen{
    //USING FOR TESTING, NOT TO BE IMPLEMENTED IN GAME
    ShapeRenderer testShapes;
    BitmapFont testFont;
    //************************************************
    
    SpriteBatch batch;
    OrthographicCamera cam;
    Environment floor;
    Stage test;
    Texture cloud, sun;

    public GameScreen(SpriteBatch batch){
        //Using for testing and debugging
        testShapes = new ShapeRenderer();
        testFont = new BitmapFont();
        
        this.batch = batch;
        this.cam = new OrthographicCamera();
        cam.setToOrtho(false, Configurations.cameraWidth, Configurations.cameraHeight);
        test = new TestStage(cam, 0);
        cloud = new Texture("cloud1.png");
        sun = new Texture("sun.png");
    }
    
    public void newStage()
    {
        int save = test.savePoint;
        test = null;
        test = new TestStage(cam, save);
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        
        if(test.stageOver)
        {
            newStage();
        }
        
        Gdx.gl.glClearColor(0.5f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
	batch.setProjectionMatrix(cam.combined);
	batch.begin();
        //Draw clouds
        batch.draw(sun, cam.position.x - 40, cam.position.y);
        batch.draw(cloud, cam.position.x, cam.position.y);
        batch.draw(cloud, cam.position.x - 80, cam.position.y - 70);
        batch.draw(cloud, cam.position.x - 190, cam.position.y - 10);
        
        // Start drawing here
        test.RenderStage(batch, delta);
        testFont.setColor(0, 0, 0, 1);
        //testFont.draw(batch, String.valueOf(test.projectileList.get(0).angle), 20, 20);
        //Stop drawing
	batch.end();
        
        //Testing for hitboxs is drawn here
        testShapes.begin(ShapeRenderer.ShapeType.Line);
        testShapes.setProjectionMatrix(cam.combined);
        testShapes.setColor(Color.RED);
        testShapes.rect(test.main.hitBox.x, test.main.hitBox.y, test.main.hitBox.width, test.main.hitBox.height);
        //testShapes.rect(test.projectileList.get(0).hitbox.x, test.projectileList.get(0).hitbox.y, 
                //test.projectileList.get(0).hitbox.width, test.projectileList.get(0).hitbox.height);
        testShapes.rect(test.main.NCollide.x, test.main.NCollide.y, test.main.NCollide.width, test.main.NCollide.height);
        testShapes.rect(test.main.SCollide.x, test.main.SCollide.y, test.main.SCollide.width, test.main.SCollide.height);
        testShapes.rect(test.main.ECollide.x, test.main.ECollide.y, test.main.ECollide.width, test.main.ECollide.height);
        testShapes.rect(test.main.WCollide.x, test.main.WCollide.y, test.main.WCollide.width, test.main.WCollide.height);
        testShapes.setColor(Color.BLUE);
//        testShapes.rect(test.main.bag.hitbox.x, test.b.hitbox.y, test.b.hitbox.width, test.b.hitbox.height);
        
//        for(Rectangle colArray : mCamp.collisionList){
//            testing.rect(colArray.x, colArray.y, colArray.width, colArray.height);
//        }
        testShapes.end();
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
