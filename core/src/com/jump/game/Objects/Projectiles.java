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
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.entities.GameCharacter;
import com.jump.game.entities.GameCharacter.State;
import com.jump.game.world.Environment;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kyle
 */
public abstract class Projectiles{
    GameCharacter gChar;
    
    float x;
    float y;
    double xVel;
    double yVel;
    float speed;
    float maxSpeed;
    public double angle = 90;
    public Rectangle hitbox;
    float xAdjust;
    float yAdjust;
    int s = 32;
    Texture spriteSheet;
    TextureRegion projectilePic;
    
    public Projectiles(){
        spriteSheet = new Texture("ProjectilesSheet.png");
        projectilePic = new TextureRegion(spriteSheet, 0, 0, s, s);
    }
    
    public abstract void Update(GameCharacter chara);
    
    public void DetectCollision(ArrayList<Environment> environList, ArrayList<Projectiles> pList, GameCharacter gChar){
        for(Environment objectList1 : environList){
            if(hitbox.overlaps(objectList1.hitbox)){
                pList.remove(this);
                break;
            }
        }
        if(hitbox.overlaps(gChar.hitBox) && !gChar.isInvincible){
            pList.remove(this);
            gChar.state = State.HIT;
        }
        
    }
    
    public void Render(SpriteBatch batch){
        batch.draw(projectilePic, x - 16, y - 16, 16, 16, 32, 32, 1, 1, (float) angle);
    }
}
