/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.Objects.HomingProjectile;
import com.jump.game.Objects.Projectiles;
import com.jump.game.entities.Buggith;
import com.jump.game.entities.CrimsonKnight;
import com.jump.game.entities.DepthKnight;
import com.jump.game.entities.ForestKnight;
import com.jump.game.entities.GameCharacter;
import com.jump.game.entities.Kibble;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestStage extends Stage{

    
    public TestStage(){
        this.objectList = new ArrayList<Environment>();
        this.name = "Test";
        
        MapReader mRead = new MapReader();
        for(int i = mRead.map.size() - 1; i >= 0; i--){
            if(mRead.map.get(i).contains("2")){
                int spotX = mRead.map.get(i).indexOf("2");
                System.out.println(spotX);
                System.out.println(i);
                objectList.add(new Floor(spotX*64, i * 32));
            }
        }
        
//        for(int i = 0; i < 10; i++){
//            objectList.add(new Floor(i*64, 0));
//        }
//        for(int i = 0; i<6; i++){
//            objectList.add(new Floor(6*64,i*32));
//        }
//        for(int i = 0; i<6; i++){
//            objectList.add(new Floor(0,i*32));
//        }
//        for(int i = 0; i<7; i++){
//            objectList.add(new Floor(i*64,192));
//        }
        
        
        this.main = new ForestKnight(this);
        this.projectileList = new ArrayList<Projectiles>();
        this.enemyList = new ArrayList<GameCharacter>();
        this.enemyList.add(new Kibble());
        
        
        
    }
    
    
    
    @Override
    public void CalculateCollisions(GameCharacter gChar) {
            gChar.CollisionDetect(objectList);
            main.EnemyDetect(enemyList);
    }

    @Override
    public void RenderStage(SpriteBatch batch, float time) {
        
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
            projectileList.get(i).Update(enemyList.get(0));
            projectileList.get(i).Render(batch);
            projectileList.get(i).DetectCollision(objectList, projectileList, enemyList.get(0));
        }
        
    }
    
}
