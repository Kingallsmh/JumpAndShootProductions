/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.entities.GameCharacter;

/**
 *
 * @author NSCCSTUDENT
 */
public class SackFlame extends Projectiles{
    
    public SackFlame(int x, int y){//float xVel, float yVel, boolean facingLeft){
        //if(facingLeft){
        //    this.xVel = 0;//-xVel;
        //    angle = 0;
        //}
        //else{
            this.xVel = 0;
            angle = 90;
            this.x = x;//gChar.x + gChar.width + 4;
        //}
        this.bagSizeValue = 1;
        this.y = y;//gChar.y + 16;
        
        this.yVel = 0;
        speed = 0;
        maxSpeed = 0;
        xAdjust = 12;
        yAdjust = 10;
        hitbox = new Rectangle(xAdjust, yAdjust, 8, 9);
        
        s = 64;
        spriteSheet = new Texture("sackFlame.png");
        projectilePic = new TextureRegion(spriteSheet, 0, 0, s, s);
    }

    @Override
    public void Update() {
        //x+= xVel;
        //y+= yVel;
        //hitbox.x = x - 4;
        //hitbox.y = y - 4;
    }
    
}
