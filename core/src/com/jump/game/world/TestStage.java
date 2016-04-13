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

float width = 5000;


    public TestStage(OrthographicCamera cam){
        this.objectList = new ArrayList<Environment>();
        camFuncs = new Camera(cam);
        this.name = "Test";
        loserFont = new BitmapFont();
        
        for(int i = 0; i < 10; i++){
            if(i == 0){
                objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 2));
            }
            else{
                objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 0));
            }
            
        }
        objectList.add(new Floor(10*16, 16, Tiles.TOPCORNER, true, 1));
        objectList.add(new Floor(20*16, 16, Tiles.TOPCORNER, false, 0));
        for(int i = 11; i < 20; i++){
            objectList.add(new Floor(i*16, 16, Tiles.GRASS2, false, 0));
        }
        for(int i = 10; i < 21; i++){
            objectList.add(new Floor(i*16, 0, Tiles.UNDERGROUND, false, 0));
        }
        for(int i = 1; i < 15; i++){
            objectList.add(new Floor(0, i*16, Tiles.DIRTWALL, false, 0));
        }
        for(int i = 21; i < 30; i++){
            objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 38; i < 42; i++){
            objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 0));
        }
        int climb = 48;
        for(int i = 45; i < 50; i++){
            climb += 16;
            objectList.add(new Floor(i*16, climb, Tiles.GRASS1, false, 0));
        }
        
        
        for(int i = 55; i < 70; i++){
            objectList.add(new Floor(i*16, 176, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 78; i < 84; i++){
            objectList.add(new Floor(i*16, 117, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 90; i < 96; i++){
            objectList.add(new Floor(i*16, 59, Tiles.GRASS1, false, 0));
        }
        
        
        for(int i = 105; i < 135; i++){
            objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 115; i < 125; i++){
            objectList.add(new Floor(i*16, 64, Tiles.GRASS1, false, 0));
        }
        
        for(int i = 140; i <170 ; i++){
            objectList.add(new Floor(i*16, 0, Tiles.GRASS1, false, 0));
        }
        
        
        
        
        
        
        this.main = new Main(this);
        this.projectileList = new ArrayList<Projectiles>();
        this.enemyList = new ArrayList<GameCharacter>();
        this.enemyList.add(new Kibble(100, 40));
        this.enemyList.add(new ForestKnight(this, 250, 40));
    }
    
    
    
    @Override
    public void CalculateCollisions(GameCharacter gChar) {
            gChar.CollisionDetect(objectList);
    }

    @Override
    public void RenderStage(SpriteBatch batch, float time) {
        camFuncs.FollowPlayer(main, width);
        
        for (Environment objectList1 : objectList) {
            objectList1.RenderObject(batch);
        }
        for (GameCharacter enemyL : enemyList){
            CalculateCollisions(enemyL);
            enemyL.Render(batch, time);
        }
        CalculateCollisions(main);
        main.Render(batch, time);
        
        for(int i = 0; i < projectileList.size(); i++){
            projectileList.get(i).Update();
            projectileList.get(i).Render(batch);
            projectileList.get(i).DetectCollisionWithMain(objectList, projectileList, main);
        }
        
        CheckDeath(batch);
    }
    
    public void CheckDeath(SpriteBatch batch){
              
        //System.out.println(main.hp);
        if(main.hp <=0)
        {
            
            KillPlayer(batch);
            //System.out.println(main.hp);
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
    
}
