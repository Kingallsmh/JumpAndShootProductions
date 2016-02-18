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
import com.jump.game.controllers.MainController;
import com.jump.game.world.Environment;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public abstract class GameCharacter {
    MainController pc;
    
    int hp;
    float xVelocity = 0;
    float yVelocity = 0;
    float maxXVelocity;
    float maxYVelocity;
    float speed;
    float jump;
    float x, y;
    float width, height;
    float xAdjust, yAdjust, turnOffSet;
    
    boolean facingLeft = false;
    boolean isInvernable = false;
    float gravity = 2;
    
    int w = 64;
    int h = 64;
    float stateTime = 0;
    public Rectangle hitBox, NCollide, SCollide, ECollide, WCollide;
    public boolean NHit = false, SHit = false, EHit = false, WHit = false;
    
    State state;
    Texture spriteSheet;
    TextureRegion currentFrame;
    TextureRegion[] idleFrame, moveFrame, atkFrame, jumpFrame;
    Animation idleAnim, moveAnim, atkAnim, jumpAnim;
    
    public enum State{
        IDLE, MOVE, ATTACK, JUMP, BUSY, HIT;
    }
    
    public void CharacterLoop(float time){
        if(state != State.BUSY){
            pc.ListenForInput();
            Gravity();
            if(state != State.ATTACK){
                CharacterMoving();
                Attack();
            }
        }
        CharacterUpdate(time);
        AnimationLoop(time);
    }
    public void CharacterUpdate(float time){
        this.x += xVelocity;
        this.y += yVelocity;
        
        if(xVelocity > 0 && SHit){
            if(xVelocity < 0.1){
                xVelocity = 0;
            }
            else{
                xVelocity -= Gdx.graphics.getDeltaTime() * (speed/10);//Slow down the character
            }
        }
        else if(xVelocity < 0 && SHit){
            if(xVelocity > -0.1){
                xVelocity = 0;
            }
            else{
                xVelocity += Gdx.graphics.getDeltaTime() * (speed/10);
            }
        }
        
        hitBox.x = x;
        hitBox.y = y;
        NCollide.x = hitBox.x;
        NCollide.y = hitBox.y + hitBox.height;
        SCollide.x = hitBox.x;
        SCollide.y = hitBox.y - 1;
        ECollide.x = hitBox.x + hitBox.width;
        ECollide.y = hitBox.y;
        WCollide.x = hitBox.x - 1;
        WCollide.y = hitBox.y;
    }
    
    public void CharacterMoving(){
        if(pc.right){
            MoveRight();
        }
        else if(pc.left){
            MoveLeft();
        }
        else{
            if(xVelocity == 0){
                state = State.IDLE;
            }
        }
        Jump();
    }
    public void MoveRight() {
        //On the ground controls
        if(SHit){
            if(pc.fast){
            //******* Use this section to increase speed to a cap
            if(xVelocity < maxXVelocity){
                this.xVelocity = Gdx.graphics.getDeltaTime() * (speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                this.xVelocity = Gdx.graphics.getDeltaTime() * speed;
            }
            facingLeft = false;
            state = State.MOVE;
        }
        else{
            if(xVelocity < 0){
                facingLeft = false;
                xVelocity = -xVelocity;
            }
        }
    }
    public void MoveLeft() {
        //On the ground controls
        if(SHit){
            if(pc.fast){
            if(xVelocity > -maxXVelocity){
                this.xVelocity = Gdx.graphics.getDeltaTime() * (-speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                this.xVelocity = Gdx.graphics.getDeltaTime() * -speed;
            }
            facingLeft = true;
            state = State.MOVE;
        }
        if(!SHit){
            if(xVelocity > 0){
                facingLeft = true;
                xVelocity = -xVelocity;
            }
        }
    }
    public void Jump(){
        if(pc.jump && SHit){
            this.y += 1;
            this.yVelocity = (jump/2) * Gdx.graphics.getDeltaTime();
        }
    }
    public void Attack(){
        if(pc.action){
            state = State.ATTACK;
            stateTime = 0;
        }
    }
    public void Hit(){
        
    }
    public void Gravity(){
        if(!SHit){
            if(yVelocity > -maxYVelocity){
                this.yVelocity -= gravity * Gdx.graphics.getDeltaTime();
            }
        }
    }
    //Animation uses
    public void AnimationLoop(float time){
        if(state == State.MOVE){
            MoveAnim(time);
        }
        else if(state == State.IDLE){
            IdleAnim(time);
        }
        else if(state == State.ATTACK){
            AtkAnim(time);
        }
        else if(state == State.JUMP){
            JumpAnim(time);
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
        if(atkAnim.isAnimationFinished(stateTime)){
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
    public void JumpAnim(float time){
        stateTime += time;
        currentFrame = jumpAnim.getKeyFrame(stateTime, true);
        if(stateTime > 10000){
            stateTime = 0;
        }
    }
    
    public void CollisionDetect(ArrayList<Environment> objectList){
        SHit = false;
        for(Environment objectList1 : objectList){
            if(SCollide.overlaps(objectList1.hitbox)){
                SHit = true;
                yVelocity = 0;
                y = objectList1.y + objectList1.height;
                break;
            }
        }
        
        
    }
    public void Render(SpriteBatch batch, float delta){
        CharacterLoop(delta);
        if(facingLeft){
            batch.draw(currentFrame, x + xAdjust + w + turnOffSet, y + yAdjust, -w, h);
        }
        else{
            batch.draw(currentFrame, x + xAdjust, y + yAdjust);
        }
    }
}
