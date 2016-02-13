/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jump.game.entities.GameCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestStage extends Stage{

    public TestStage(){
        this.objectList = new ArrayList<Objects>();
        this.name = "Test";
        for(int i = 0; i < 10; i++){
            objectList.add(new Floor(i*64, 0));
        }
        
    }
    
    
    
    @Override
    public void CalculateCollisions(GameCharacter gChar) {
            gChar.CollisionDetect(objectList);
    }

    @Override
    public void RenderStage(SpriteBatch batch) {
        for (Objects objectList1 : objectList) {
            objectList1.RenderObject(batch);
        }
    }
    
}
