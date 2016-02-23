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
    
    float initXVel;
    float initYVel;
    boolean burned = false;
    float burnStop = 0.5f;
    
    public HomingProjectile(GameCharacter gChar, float xVel, float yVel){
        this.x = gChar.x + 8;
        this.y = gChar.y + 8;
        this.initXVel = xVel;
        this.initYVel = yVel;
        speed = 10;
        maxSpeed = 1;
        angle = 0;
        xAdjust = 12;
        yAdjust = 10;
        hitbox = new Rectangle(xAdjust, yAdjust, 8, 9);
        s = 32;
        spriteSheet = new Texture("ProjectilesSheet.png");
        projectilePic = new TextureRegion(spriteSheet, 32*3, 0, s, s);
    }

    @Override
    public void Update(GameCharacter chara) {
        if(burned){
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
            hitbox.y = y - 4;
        }
        else{
            BurnInitVelocity();
        }
    }
    
    public void BurnInitVelocity(){
        if(initXVel > -burnStop && initXVel < burnStop && initYVel > -burnStop && initYVel < burnStop){
            xVel = initXVel;
            yVel = initYVel;
            initXVel = 0;
            initYVel = 0;
            burned = true;
        }
        
        if(initXVel > -burnStop && initXVel < burnStop){
                initXVel = 0;
            }
        else if(initXVel > 0){
            initXVel -= Gdx.graphics.getDeltaTime() * 1;
        }
        else if(initXVel < 0){
            initXVel += Gdx.graphics.getDeltaTime() * 1;
        }
        
        if(initYVel > -burnStop && initYVel < burnStop){
                initYVel = 0;
            }
        else if(initYVel > 0){
            initYVel -= Gdx.graphics.getDeltaTime() * 1;
        }
        else if(initYVel < 0){
            initYVel += Gdx.graphics.getDeltaTime() * 1;
        }
        
        x+= initXVel;
        y+= initYVel;
        
        hitbox.x = x - 4;
        hitbox.y = y  - 4;
    }

}
