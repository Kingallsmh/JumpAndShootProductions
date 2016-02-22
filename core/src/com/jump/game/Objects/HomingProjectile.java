/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.entities.GameCharacter;

/**
 *
 * @author NSCCSTUDENT
 */
public class HomingProjectile extends Projectiles{
    
    public HomingProjectile(float x, float y){
        this.x = x;
        this.y = y;
        speed = 3;
        maxSpeed = 2;
        angle = 0;
        xAdjust = 12;
        yAdjust = 10;
        hitbox = new Rectangle(xAdjust, yAdjust, 8, 9);
        s = 32;
        spriteSheet = new Texture("ProjectilesSheet.png");
        projectilePic = new TextureRegion(spriteSheet, 0, 0, s, s);
    }

    @Override
    public void Update(GameCharacter chara) {
        float xDifference = (chara.x + 10) - (x);
        float yDifference = (chara.y + 10) - (y);
        float totalDifference = (float) Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
        
        float totalTime = totalDifference/speed;
        float xForce = xDifference/totalTime;
        float yForce = yDifference/totalTime;
        angle = (Math.toDegrees(Math.atan2(yForce, xForce))) + 90;
        
        float calcMaxSpeed = maxSpeed * Gdx.graphics.getDeltaTime();
        
        if(xVel < calcMaxSpeed || xVel > -calcMaxSpeed){
            xVel += Gdx.graphics.getDeltaTime() * xForce;
        }
        if(yVel < calcMaxSpeed || yVel > -calcMaxSpeed){
            yVel += Gdx.graphics.getDeltaTime() * yForce;
        }
        
        x+= xVel;
        y+= yVel;
        hitbox.x = x - 4;
        hitbox.y = y  - 4;
    }

}
