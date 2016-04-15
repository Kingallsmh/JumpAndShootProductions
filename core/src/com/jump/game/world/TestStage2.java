/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
public class TestStage2 extends Stage {
    
    Camera camFuncs;
    BitmapFont loserFont;

float width = 5000;

    public TestStage2(OrthographicCamera cam, int savePoint, int difficulty){
        
        this.sS = 2;
        this.savePoint = savePoint;
        this.difficulty = difficulty;
        //Save points
        switch (savePoint){
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
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 2));
            }
            else{
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 0));
            }
            
        }
        //Corners for the step up
        objectList.add(new Floor(sS, 10*16, 16, Floor.Tiles.TOPCORNER, true, 0));
        objectList.add(new Floor(sS, 20*16, 16, Floor.Tiles.TOPCORNER, false, 0));
        for(int i = 11; i < 20; i++){
            objectList.add(new Floor(sS, i*16, 16, Floor.Tiles.GRASS2, false, 0));
        }
        for(int i = 10; i < 21; i++){       
            if(i == 10){
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.UNDERGROUND, false, 1));
            }else{
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.UNDERGROUND, false, 0));
            }
            
        }
        //Wall on left side
        for(int i = 1; i < 15; i++){
            objectList.add(new Floor(sS, 0, i*16, Floor.Tiles.DIRTWALL, false, 0));
        }
        
        for(int i = 21; i < 30; i++){
            if(i == 29){
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 2));
            }else{
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 0));
            }
        }
        //big jump
        for(int i = 38; i < 42; i++){
            objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 0));
        }
        //stair
        objectList.add(new Floor(sS, 45*16, 64, Floor.Tiles.GRASS1, false, 0));
        objectList.add(new Floor(sS, 45*16, 170, Floor.Tiles.GRASS1, false, 0));
        objectList.add(new Floor(sS, 45*16, 173, Floor.Tiles.GRASS1, false, 0));
        objectList.add(new Floor(sS, 38*16, 120, Floor.Tiles.GRASS1, false, 0));
     
        
        //Highest platform
        for(int i = 54; i < 70; i++){
            if(i == 54){
                objectList.add(new Floor(sS, i*16, 176, Floor.Tiles.GRASS1, false, 1));
            }
            else{
                if(i == 57 || i == 58 || i == 59 || i ==64 || i ==65){
                    objectList.add(new Floor(sS, i*16, 176, Floor.Tiles.DIRTWALL, false, 0));
                    objectList.add(new Floor(sS, i*16, 192, Floor.Tiles.DIRTWALL, false, 0));
                    objectList.add(new Floor(sS, i*16, 208, Floor.Tiles.GRASS1, false, 0));
                }else{
                    objectList.add(new Floor(sS, i*16, 176, Floor.Tiles.GRASS1, false, 0));
                }
            }
            
        }
        //step down1
        for(int i = 60; i < 71; i++){
            objectList.add(new Floor(sS, i*16, 117, Floor.Tiles.GRASS1, false, 0));
        }
        //step down 2
        for(int i = 59; i < 70; i++){
            objectList.add(new Floor(sS, i*16, 16, Floor.Tiles.GRASS1, false, 0));
        }
        //wall blocking cheaters
        for(int i =  117; i < 500; i += 16){
            objectList.add(new Floor(sS, 1168, i, Floor.Tiles.GRASS1, false, 0));
        }
        
        objectList.add(new Floor(sS, 1216, 0, Floor.Tiles.GRASS1, false, 0));
        objectList.add(new Floor(sS, 1286, 0, Floor.Tiles.GRASS1, false, 0));
    
        
        //Back to ground level with a floating platform
        for(int i = 86; i < 100; i++){
            if(i == 86){
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 2));
            }
            else if(i == 99){
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 0));
            }
            else{
                objectList.add(new Floor(sS, i*16, 0, Floor.Tiles.GRASS1, false, 0));
            }
            
        }
 
        
        this.main = new Main(this, xStart, yStart, difficulty);
        this.projectileList = new ArrayList<Projectiles>();
        this.enemyList = new ArrayList<GameCharacter>();
        this.enemyList.add(new ForestKnight(this, 350, 40, difficulty));
        this.enemyList.add(new Kibble(this, 100, 40, difficulty));
        
        for(int i = 0; i <10; i++){
            this.enemyList.add(new Kibble(this, (float) (60*16) + (i*10), 208, difficulty));
        }

        
        
        this.projectileList.add(new SackFlame(1, 30,60));
        this.projectileList.add(new SackFlame(2, 96 * 16 ,18));
    }
    
    public void WinStage(SpriteBatch batch){
        if(win){
            loserFont.setColor(0, 0, 0, 1);
            loserFont.draw(batch, "YOU WIN!!!!", camFuncs.cam.position.x, camFuncs.cam.position.y);
            main.pause = true;
        }
    }
    
    @Override
    public void CalculateCollisions(GameCharacter gChar) {
            gChar.CollisionDetect(objectList);
    }

    @Override
    public void RenderStage(SpriteBatch batch, float time) {
        camFuncs.FollowPlayer(main, width);
        WinStage(batch);
        CheckpointReached();
        
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
                projectileList.remove(projectileList.get(i));
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
