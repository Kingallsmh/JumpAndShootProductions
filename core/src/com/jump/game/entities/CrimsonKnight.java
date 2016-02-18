/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.controllers.PlayerController;
import com.jump.game.world.Environment;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class CrimsonKnight extends GameCharacter{
    
    public CrimsonKnight(){
        this.pc = new PlayerController();
        this.speed = 60;
        this.jump = 150;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
        this.x = 200;
        this.y = 130;
        // Use image file to figure this out
        this.width = 20;
        this.height = 28;
        this.xAdjust = -24; // Image needs to be pushed to the left
        this.yAdjust = -2;
        this.turnOffSet = 4;
        
        this.hitBox = new Rectangle(x, y, width, height);
        this.NCollide = new Rectangle(x, y, width, 1);
        this.SCollide = new Rectangle(x, y, width, 1);
        this.ECollide = new Rectangle(x, y, 1, height);
        this.WCollide = new Rectangle(x, y, 1, height);
        
        this.hp = 10;
        
        this.spriteSheet = new Texture("RedKnightSheet.png");
        this.state = State.IDLE;
        this.idleFrame = new TextureRegion[2];
        this.moveFrame = new TextureRegion[4];
        this.atkFrame = new TextureRegion[2];
        
        for(int i = 0; i < 2; i++){
            idleFrame[i] = new TextureRegion(spriteSheet, (i+2) * w, 0, w, h);
            moveFrame[i*2] = new TextureRegion(spriteSheet, (i+2) * w, h, w, h);
            moveFrame[1] = idleFrame[0];
            moveFrame[3] = idleFrame[0];
            atkFrame[i] = new TextureRegion(spriteSheet, (i+2) * w, h*2, w, h);
        }
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.25f, moveFrame);
        atkAnim = new Animation(0.4f, atkFrame);
    }
    
}
