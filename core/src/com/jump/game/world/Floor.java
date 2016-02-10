/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author NSCCSTUDENT
 */
public class Floor extends Objects{

    
    
    public Floor(float x, float y){
        this.x = x;
        this.y = y;
        this.testSheet = new Texture("TestTile.png");
        this.pic = new TextureRegion(testSheet, 0, 0, 64, 32);
    }
    
    @Override
    public void CollisionHandler() {
        
    }

    @Override
    public void UpdateObject() {
        
    }

    @Override
    public void RenderObject(SpriteBatch batch) {
        batch.draw(pic, x, y);
    }
    
    
    
}
