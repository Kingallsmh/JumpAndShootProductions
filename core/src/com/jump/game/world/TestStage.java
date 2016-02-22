/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

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
        for(int i = 0; i < 10; i++){
            objectList.add(new Floor(i*64, 0));
        }
        for(int i = 0; i<6; i++){
            objectList.add(new Floor(6*64,i*32));
        }
        for(int i = 0; i<6; i++){
            objectList.add(new Floor(0,i*32));
        }
        for(int i = 0; i<7; i++){
            objectList.add(new Floor(i*64,192));
        }
        
        
        this.main = new Buggith();
        pTest = new HomingProjectile(200, 150);
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
        pTest.Update(main);
        pTest.Render(batch);
    }
    
}
