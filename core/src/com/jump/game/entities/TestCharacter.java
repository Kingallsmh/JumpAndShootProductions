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
import com.jump.game.world.Objects;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestCharacter extends GameCharacter{
    
    public TestCharacter(){
        this.pc = new PlayerController();
        this.speed = 60;
        this.jump = 150;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
        this.x = 0;
        this.y = 130;
        // Use image file to figure this out
        this.width = 20;
        this.height = 28;
        this.xAdjust = 24;
        this.yAdjust = 1;
        
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

    @Override
    public void CharacterLoop(float time) {
        pc.ListenForInput();
        Gravity();
        CharacterMoving();
        CharacterUpdate(time);
        AnimationLoop(time);
    }
    
    @Override
    public void CharacterUpdate(float time) {
        if(xVelocity == 0){
            state = State.IDLE;
        }
        this.x += xVelocity;
        this.y += yVelocity;
        
        hitBox.x = x + xAdjust;
        hitBox.y = y + yAdjust;
        NCollide.x = hitBox.x;
        NCollide.y = hitBox.y + hitBox.height;
        SCollide.x = hitBox.x;
        SCollide.y = hitBox.y - 1;
        ECollide.x = hitBox.x + hitBox.width;
        ECollide.y = hitBox.y;
        WCollide.x = hitBox.x - 1;
        WCollide.y = hitBox.y;
    }

    @Override
    public void AnimationLoop(float time) {
        if(state == State.IDLE){
            IdleAnim(time);
        }
        else if(state == State.MOVE){
            MoveAnim(time);
        }
        else if(state == State.ATTACK){
            AtkAnim(time);
        }
    }
    
    @Override
    public void CharacterMoving(){
        if(pc.right){
            MoveRight();
        }
        else if(pc.left){
            MoveLeft();
        }
        else{
            if(xVelocity > 0){
                if(xVelocity < 0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity -= Gdx.graphics.getDeltaTime() * (speed/4);//Slow down the character
                }
            }
            else if(xVelocity < 0){
                if(xVelocity > -0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity += Gdx.graphics.getDeltaTime() * (speed/4);
                }
            }
        }
        Jump();
    }

    @Override
    public void MoveRight() {
        if(pc.fast){
            //******* Use this section to increase speed to a cap
            if(xVelocity < maxXVelocity){
                this.xVelocity += Gdx.graphics.getDeltaTime() * 2;
            }
            MoveAnim(Gdx.graphics.getDeltaTime());
        }
        else{
            this.xVelocity = Gdx.graphics.getDeltaTime() * speed;
        }
        facingLeft = false;
        state = State.MOVE;
    }

    @Override
    public void MoveLeft() {
        if(pc.fast){
            if(xVelocity > -maxXVelocity){
                this.xVelocity += Gdx.graphics.getDeltaTime() * -2;
            }
            MoveAnim(Gdx.graphics.getDeltaTime());
        }
        else{
            this.xVelocity = Gdx.graphics.getDeltaTime() * -speed;
        }
        facingLeft = true;
        state = State.MOVE;
    }

    @Override
    public void Render(SpriteBatch batch, float delta) {
        CharacterLoop(delta);
        if(facingLeft){
            batch.draw(currentFrame, x + w + 4, y, -w, h);
        }
        else{
            batch.draw(currentFrame, x, y);
        }
        
    }
    
    public void IdleAnim(float time){
        stateTime += time;
        currentFrame = idleAnim.getKeyFrame(stateTime, true);        
        if(stateTime > 10000){
            stateTime = 0;
        }
    }
    
    public void AtkAnim(float time){
        stateTime += time;
        currentFrame = atkAnim.getKeyFrame(stateTime, false);
        if(atkAnim.isAnimationFinished(stateTime  - 1)){
            state = State.IDLE;
        }
    }
    
    public void MoveAnim(float time){
        stateTime += time;
        currentFrame = moveAnim.getKeyFrame(stateTime, true);
        if(stateTime > 10000){
            stateTime = 0;
        }
    }

    @Override
    public void Jump() {
        if(pc.jump && SHit){
            this.yVelocity = (jump/2) * Gdx.graphics.getDeltaTime();
        }
//        else if(pc.longJump && !SHit){
//            this.yVelocity = jump * Gdx.graphics.getDeltaTime();
//        }
    }

    @Override
    public void CollisionDetect(ArrayList<Objects> objectList) {
        SHit = false;
        for(Objects objectList1 : objectList){
            if(SCollide.overlaps(objectList1.hitbox)){
                SHit = true;
                yVelocity = 0;
                y = objectList1.y + objectList1.height - 1;
                break;
            }
        }
    }

    @Override
    public void Gravity() {
        if(!SHit){
            if(yVelocity > -maxYVelocity){
                this.yVelocity -= gravity * Gdx.graphics.getDeltaTime();
            }
            
        }
    }

    
}
