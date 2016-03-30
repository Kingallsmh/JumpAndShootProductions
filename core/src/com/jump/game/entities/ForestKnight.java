/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.Objects.HomingProjectile;
import com.jump.game.Objects.StraightShot;
import com.jump.game.controllers.AIPassiveController;
import com.jump.game.controllers.AIShootingController;
import com.jump.game.controllers.PlayerController;
import com.jump.game.world.Stage;

/**
 *
 * @author NSCCSTUDENT
 */
public class ForestKnight extends GameCharacter{
    
    boolean throwDown = false;
    
    public ForestKnight(Stage stage, float x, float y){
        this.stage = stage;
        this.pc = new AIShootingController();
        this.speed = 60;
        this.jump = 250;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
        this.x = 200;
        this.y = 130;
        // Use image file to figure this out
        this.width = 16;
        this.height = 28;
        this.xAdjust = -24; // Image needs to be pushed to the left
        this.yAdjust = -2;
        this.turnOffSet = 0;
        this.colBoxSizeAdjust = 4;
        this.colBoxAdjust = 2;
        
        this.hitBox = new Rectangle(x, y, width, height);
        this.NCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.SCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.ECollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        this.WCollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        
        this.hp = 10;
        proCMax = 60;
        proC = proCMax;
        
        this.spriteSheet = new Texture("KnightSheet.png");
        this.state = State.IDLE;
        this.idleFrame = new TextureRegion[2];
        this.moveFrame = new TextureRegion[4];
        this.atkFrame = new TextureRegion[2];
        
        for(int i = 0; i < 2; i++){
            idleFrame[i] = new TextureRegion(spriteSheet, (i) * w, 0, w, h);
            moveFrame[i*2] = new TextureRegion(spriteSheet, (i) * w, h, w, h);
            moveFrame[1] = idleFrame[0];
            moveFrame[3] = idleFrame[0];
            atkFrame[i] = new TextureRegion(spriteSheet, (i) * w, h*2, w, h);
        }
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.25f, moveFrame);
        atkAnim = new Animation(0.3f, atkFrame);
    }
    
    @Override
    public void Attack() {
        if(pc.action && pc.down && proC > proCMax){
            state = State.ATTACK;
            stateTime = 0;
            proC = 0;
            throwDown = true;
        }
        else if(pc.action && proC > proCMax){
            state = State.ATTACK;
            stateTime = 0;
            proC = 0;
        }
        
        if(proC == 15){
            if(throwDown){
                stage.projectileList.add(new StraightShot(this, 0, -2, facingLeft));
            }
            else{
                stage.projectileList.add(new StraightShot(this, 2, 0, facingLeft));
            }
            throwDown = false;
        }
        if( proC <= proCMax){
            proC ++;
        }
    }
    

    @Override
    public void AtkAnim(float time) {
        stateTime += time;
        currentFrame = atkAnim.getKeyFrame(stateTime, false);
        if(atkAnim.isAnimationFinished(stateTime)){
            state = State.IDLE;
        }
    }
}
