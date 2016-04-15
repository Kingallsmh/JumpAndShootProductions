/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.entities.GameCharacter;

/**
 *
 * @author NSCCSTUDENT
 */
public class SackFlame extends Projectiles{
    
    public SackFlame(int object , int x, int y){//float xVel, float yVel, boolean facingLeft){
        //if(facingLeft){
        //    this.xVel = 0;//-xVel;
        //    angle = 0;
        //}
        //else{
            this.xVel = 0;
            angle = 0;
            this.x = x;//gChar.x + gChar.width + 4;
        //}
        this.bagSizeValue = 1;
        this.y = y;//gChar.y + 16;
        
        this.yVel = 0;
        speed = 2;
        maxSpeed = 0;
        xAdjust = 12;
        yAdjust = 10;
        hitbox = new Rectangle(xAdjust, yAdjust, 8, 9);
        this.object = object;
        s = 24;
        if(object == 1){
            spriteSheet = new Texture("sackFlame.png");
            projectilePic = new TextureRegion(spriteSheet, 0, 0, s, s);
        }
        else{
            spriteSheet = new Texture("Princess.png");
            projectilePic = new TextureRegion(spriteSheet, 0, 0, 17, 36);
        }
    }

    @Override
    public void Update() {
        x+= xVel;
        y+= yVel;
        hitbox.x = x + 8;
        hitbox.y = y;
    }
    
}
