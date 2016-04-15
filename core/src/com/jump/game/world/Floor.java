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
public class Floor extends Environment{

    @Override
    public void RenderBackground(SpriteBatch batch) {
        if(drawThing == 1){
            batch.draw(bush, x, y + 16);
        }else if(drawThing == 2){
            batch.draw(noselessTom, x - noselessTom.getWidth()/2, y + 16);
        }
        else if(drawThing == 3){
            batch.draw(tree, x, y + 16);
        }
    }

    enum Tiles{
        GRASS1, GRASS2, UNDERGROUND, TOPCORNER, DIRTWALL
    }
    
    int drawThing;
    
    public Floor(int spriteSheet , float x, float y, Tiles imageChoice, boolean flipX, int drawThing){
        this.drawThing = drawThing;
        this.x = x;
        this.y = y;
        this.flipX = flipX;
        this.isMoving = false;
        this.width = 16;
        this.height = 16;
        if(spriteSheet == 1){
            this.testSheet = new Texture("ForestParts.png");
        }
        else if(spriteSheet == 2){
            this.testSheet = new Texture("ForestParts2.png");
        }
        else if(spriteSheet == 3){
            this.testSheet = new Texture("ForestParts3.png");
        }
        this.bush = new Texture("bush.png");
        this.noselessTom = new Texture("tree2.png");
        this.tree = new Texture("tree.png");
        
        if(null != imageChoice)switch (imageChoice) {
            case GRASS1:
                this.pic = new TextureRegion(testSheet, 0, 0, width, height);
                break;
            case GRASS2:
                this.pic = new TextureRegion(testSheet, width, 0, width, height);
                break;
            case UNDERGROUND:
                this.pic = new TextureRegion(testSheet, width*2, 0, width, height);
                break;
            case TOPCORNER:
                this.pic = new TextureRegion(testSheet, width*3, 0, width, height);
                break;
            case DIRTWALL:
                this.pic = new TextureRegion(testSheet, width*3, height, width, height);
                break;
            default:
                break;
        }
        
        
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
        if(!flipX){
            batch.draw(pic, x, y);
        }
        else{
            batch.draw(pic, x + width, y, -width, height);
        }
    }
    
    
    
}
