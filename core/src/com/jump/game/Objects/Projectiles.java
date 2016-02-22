/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jump.game.entities.GameCharacter;

/**
 *
 * @author Kyle
 */
public class Projectiles {
    float x = 70;
    float y = 70;
    double xVel;
    double yVel;
    float speed = 5;
    public double angle = 0;
    float DestinationD;
    int s = 32;
    Texture spriteSheet;
    TextureRegion projectilePic;
    
    public Projectiles(){
        spriteSheet = new Texture("ProjectilesSheet.png");
        projectilePic = new TextureRegion(spriteSheet, 0, 0, s, s);
    }
    
    public void Update(GameCharacter chara){
//        DestinationD = ((chara.x - x) * (chara.x - x)) + ((chara.y - y) * (chara.y - y));
        float xDifference = (chara.x + 10) - (x);
        float yDifference = (chara.y + 10) - (y);
        float totalDifference = (float) Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
        
        float totalTime = totalDifference/speed;
        float xForce = xDifference/totalTime;
        float yForce = yDifference/totalTime;
        angle = (Math.toDegrees(Math.atan2(yForce, xForce))) + 90;
        
        
        if(xVel < 3 || xVel > -3){
            xVel += Gdx.graphics.getDeltaTime() * xForce;
        }
        if(yVel < 3 || yVel > -3){
            yVel += Gdx.graphics.getDeltaTime() * yForce;
        }
        
        
        x+= xVel;
        y+= yVel;
    }
    
    public void Render(SpriteBatch batch){
        batch.draw(projectilePic, x - 16, y - 16, 16, 16, 32, 32, 1, 1, (float) angle);
    }
}
