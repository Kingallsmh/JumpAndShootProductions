/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.Objects.HomingProjectile;
import com.jump.game.Objects.Projectiles;
import com.jump.game.entities.Bag;
import com.jump.game.entities.Buggith;
import com.jump.game.entities.CrimsonKnight;
import com.jump.game.entities.DepthKnight;
import com.jump.game.entities.ForestKnight;
import com.jump.game.entities.GameCharacter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jump.game.Objects.SackFlame;
import com.jump.game.entities.Kibble;
import com.jump.game.entities.Main;
import com.jump.game.world.Floor.Tiles;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestStage extends Stage{

Camera camFuncs;
BitmapFont loserFont;
Texture cloud, sun;

float width = 5000;

    public TestStage(OrthographicCamera cam, int savePoint, int difficulty){
        
        this.sS = 1;
        cloud = new Texture("cloud1.png");
        sun = new Texture("sun.png");
        this.savePoint = savePoint;
        this.difficulty = difficulty;
        //Save points
        switch (this.savePoint){
            case 0: 
                this.xStart = 30;
                this.yStart = 24;
                break;
            case 1:
                this.xStart = 1020;
                this.yStart = 194;
                break;
        }
        
        this.objectList = new ArrayList<Environment>();
        camFuncs = new Camera(cam);
        this.name = "Test";
        loserFont = new BitmapFont();
        
        
        //The first tiles of the level
        for(int i = 0; i < 10; i++){
            if(i == 5){
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 2));
            }
            else{
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 0));
            }
            
        }
        //Corners for the step up
        objectList.add(new Floor(sS, 10*16, 16, Tiles.TOPCORNER, true, 0));
        objectList.add(new Floor(sS, 20*16, 16, Tiles.TOPCORNER, false, 0));
        for(int i = 11; i < 20; i++){
            objectList.add(new Floor(sS, i*16, 16, Tiles.GRASS2, false, 0));
        }
        for(int i = 10; i < 21; i++){       
            if(i == 10){
                objectList.add(new Floor(sS, i*16, 0, Tiles.UNDERGROUND, false, 1));
            }else{
                objectList.add(new Floor(sS, i*16, 0, Tiles.UNDERGROUND, false, 0));
            }
            
        }
        //Wall on left side
        for(int i = 1; i < 15; i++){
            objectList.add(new Floor(sS, 0, i*16, Tiles.DIRTWALL, false, 0));
        }
        
        for(int i = 21; i < 30; i++){
            if(i == 29){
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 2));
            }else{
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 0));
            }
        }
        
        for(int i = 38; i < 42; i++){
            objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 0));
        }
        int climb = 48;
        for(int i = 45; i < 50; i++){
            climb += 16;
            objectList.add(new Floor(sS, i*16, climb, Tiles.GRASS1, false, 0));
        }
        
        //Highest platform
        for(int i = 55; i < 70; i++){
            if(i == 55){
                objectList.add(new Floor(sS, i*16, 176, Tiles.GRASS1, false, 1));
            }
            else{
                objectList.add(new Floor(sS, i*16, 176, Tiles.GRASS1, false, 0));
            }
            
        }
        
        for(int i = 78; i < 84; i++){
            objectList.add(new Floor(sS, i*16, 117, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 90; i < 96; i++){
            objectList.add(new Floor(sS, i*16, 59, Tiles.GRASS1, false, 0));
        }
        
        //Back to ground level with a floating platform
        for(int i = 105; i < 135; i++){
            if(i == 109){
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 2));
            }
            else if(i == 120){
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 3));
            }
            else{
                objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 0));
            }
            
        }
        
        for(int i = 115; i < 125; i++){
            objectList.add(new Floor(sS, i*16, 64, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 140; i <170 ; i++){
            objectList.add(new Floor(sS, i*16, 0, Tiles.GRASS1, false, 0));
        }
        
        
        
        
        
        
        
        
        this.main = new Main(this, xStart, yStart, difficulty);
        this.projectileList = new ArrayList<Projectiles>();
        this.enemyList = new ArrayList<GameCharacter>();
        this.enemyList.add(new ForestKnight(this, 250, 40, difficulty));
        this.enemyList.add(new Kibble(this, 250, 40, difficulty));
        this.enemyList.add(new Buggith(this, 751, 116, difficulty));
        this.enemyList.add(new ForestKnight(this, 1062, 196, difficulty));
        this.enemyList.add(new ForestKnight(this, 1841, 80, difficulty));
        this.enemyList.add(new ForestKnight(this, 2072, 20, difficulty));
        this.enemyList.add(new Kibble(this, 1750, 20, difficulty));
        this.enemyList.add(new Buggith(this, 1841, 20, difficulty));
        
        this.projectileList.add(new SackFlame(1, 100,60));
        this.projectileList.add(new SackFlame(2, 2487, 30));
    }
    
    
    
    @Override
    public void CalculateCollisions(GameCharacter gChar) {
            gChar.CollisionDetect(objectList);
    }

    @Override
    public void RenderStage(SpriteBatch batch, float time) {
        
        //Draw clouds
        batch.draw(sun, camFuncs.cam.position.x - 40, camFuncs.cam.position.y);
        batch.draw(cloud, camFuncs.cam.position.x, camFuncs.cam.position.y);
        batch.draw(cloud, camFuncs.cam.position.x - 80, camFuncs.cam.position.y - 70);
        batch.draw(cloud, camFuncs.cam.position.x - 190, camFuncs.cam.position.y - 10);
        camFuncs.FollowPlayer(main, width);
        
        CheckpointReached();
        WinStage(batch);
        for (Environment objectList1 : objectList) {
            objectList1.RenderBackground(batch);
        }
        
        for (Environment objectList1 : objectList) {
            objectList1.RenderObject(batch);
        }
        for (int i = 0; i < enemyList.size(); i++){
            CalculateCollisions(enemyList.get(i));
            enemyList.get(i).Render(batch, time);
        }
        CalculateCollisions(main);
        main.Render(batch, time);
        
        for(int i = 0; i < projectileList.size(); i++){
            projectileList.get(i).Update();
            projectileList.get(i).Render(batch, objectList, projectileList, enemyList, main);
        }
        
        CheckDeath(batch);
        CalcOnScreen();
        System.out.println("X: " + main.x + " Y: " + main.y);
    }
    
    public void CheckDeath(SpriteBatch batch){
              
        if(main.hp <=0)
        {
            
            KillPlayer(batch);
        }
        
        if(main.y < 0-20-main.height){
           KillPlayer(batch);
        }
        
        
    }
    
    public void KillPlayer(SpriteBatch batch)
    {
       loserFont.setColor(0, 0, 0, 1);
       loserFont.draw(batch, "YOU LOSE", camFuncs.cam.position.x, camFuncs.cam.position.y);
       main.pause = true;
    }
    
    public void WinStage(SpriteBatch batch){
        if(win){
            loserFont.setColor(0, 0, 0, 1);
            loserFont.draw(batch, "YOU WIN!!!!", camFuncs.cam.position.x, camFuncs.cam.position.y);
            main.pause = true;
        }
    }

    @Override
    public void CalcOnScreen() {
        for(int i = 0; i < enemyList.size(); i++){
            if(!camFuncs.IsOnScreen(enemyList.get(i).x)){
                enemyList.get(i).pause = true;
            }
            else if(camFuncs.IsOnScreen(enemyList.get(i).x)){
                enemyList.get(i).pause = false;
            }
        }
        
        for(int i = 0; i < projectileList.size(); i++){
            if(!camFuncs.IsOnScreen(projectileList.get(i).hitbox.x)){
                if(projectileList.get(i).object == 0){
                    projectileList.remove(projectileList.get(i));
                }
                else{
                    //DO NOTHING
                }
            }
        }
    }

    @Override
    public void CheckpointReached() {
        if(savePoint < 1 && main.x > 1020){
            savePoint = 1;
        }
    }
    
}
