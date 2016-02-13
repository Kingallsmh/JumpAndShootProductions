/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author NSCCSTUDENT
 */
public class Floor extends Objects{

    
    
    public Floor(float x, float y){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 32;
        this.testSheet = new Texture("TestTile.png");
        this.pic = new TextureRegion(testSheet, 0, 0, width, height);
        
        this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
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
