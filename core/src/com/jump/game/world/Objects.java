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
public abstract class Objects {
    public float x, y;
    Rectangle hitbox;
    Texture testSheet;
    TextureRegion pic;
    
    public CollisionProperties collide;
    
    enum CollisionProperties{
        PASSABLE, IMPASSABLE, HARMFUL, HINDERING;
    }
    
    public abstract void UpdateObject();
    public abstract void CollisionHandler();
    public abstract void RenderObject(SpriteBatch batch);
}
