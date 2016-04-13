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
public class StraightShot extends Projectiles{
    
    public StraightShot(GameCharacter gChar, float xVel, float yVel, boolean facingLeft){
        if(facingLeft){
            this.xVel = -xVel;
            angle = 270;
            this.x = gChar.x - 4;
        }
        else{
            this.xVel = xVel;
            angle = 90;
            this.x = gChar.x + gChar.width + 4;
        }
        this.bagSizeValue = 1;
        this.y = gChar.y + 16;
        
        this.yVel = yVel;
        speed = xVel;
        maxSpeed = 0;
        xAdjust = 12;
        yAdjust = 10;
        hitbox = new Rectangle(xAdjust, yAdjust, 8, 9);
        s = 32;
        spriteSheet = new Texture("ProjectilesSheet.png");
        projectilePic = new TextureRegion(spriteSheet, s, 0, s, s);
    }

    @Override
    public void Update() {
        x+= xVel;
        y+= yVel;
        hitbox.x = x - 4;
        hitbox.y = y - 4;
    }
    
}
