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
import com.jump.game.world.Stage;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public abstract class GameCharacter {
    MainController pc;
    Stage stage;
    
    int hp;
    float xVelocity = 0;
    float yVelocity = 0;
    float maxXVelocity;
    float maxYVelocity;
    float speed;
    float jump;
    public float x, y;
    public float width, height;
    float xAdjust, yAdjust, turnOffSet;
    float colBoxSizeAdjust, colBoxAdjust;
    
    boolean facingLeft = false;
    public boolean isInvincible = false;
    float hitColor = 0.5f;
    float hitCounter = 0;
    float gravity = 2;
    int proC = 0;
    int proCMax;
    
    int w = 64;
    int h = 64;
    float stateTime = 0;
    public Rectangle hitBox, NCollide, SCollide, ECollide, WCollide;
    public boolean NHit = false, SHit = false, EHit = false, WHit = false;
    
    public State state;
    Texture spriteSheet;
    TextureRegion currentFrame;
    TextureRegion[] idleFrame, moveFrame, atkFrame, jumpFrame;
    Animation idleAnim, moveAnim, atkAnim, jumpAnim;
    
    public enum State{
        IDLE, MOVE, ATTACK, JUMP, MELEE, BAG, BUSY, HIT;
    }
    
    public void CharacterLoop(float time){
        if(state != State.BUSY){
            pc.ListenForInput();
            Gravity();
            if(state != State.ATTACK && state != State.HIT){
                CharacterMoving();
            }
            Attack();
        }
        Hit();
        CharacterUpdate(time);
        AnimationLoop(time);
    }
    
    public void CharacterUpdate(float time){
        this.x += xVelocity;
        this.y += yVelocity;
        
        if(SHit){
            if(xVelocity > 0){
                if(xVelocity < 0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity -= Gdx.graphics.getDeltaTime() * (speed/10);//Slow down the character
                }
            }
            else if(xVelocity < 0){
                if(xVelocity > -0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity += Gdx.graphics.getDeltaTime() * (speed/10);
                }
            }
        }
        else if(!SHit){
            if(xVelocity > 0){
                if(xVelocity < 0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity -= Gdx.graphics.getDeltaTime() * (speed/25);//Slow down the character
                }
            }
            else if(xVelocity < 0){
                if(xVelocity > -0.1){
                    xVelocity = 0;
                }
                else{
                    xVelocity += Gdx.graphics.getDeltaTime() * (speed/25);
                }
            }
        }
        
        hitBox.x = x;
        hitBox.y = y;
        NCollide.x = hitBox.x + colBoxAdjust;
        NCollide.y = hitBox.y + hitBox.height;
        SCollide.x = hitBox.x + colBoxAdjust;
        SCollide.y = hitBox.y - 1;
        ECollide.x = hitBox.x + hitBox.width;
        ECollide.y = hitBox.y + colBoxAdjust;
        WCollide.x = hitBox.x - 1;
        WCollide.y = hitBox.y + colBoxAdjust;
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
            if(pc.fast && SHit){
            //******* Use this section to increase speed to a cap
            if(xVelocity < maxXVelocity){
                this.xVelocity = Gdx.graphics.getDeltaTime() * (speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(WHit){
                    x += 1;
                }
                this.xVelocity = Gdx.graphics.getDeltaTime() * speed;
            }
            facingLeft = false;
            state = State.MOVE;
    }
    public void MoveLeft() {
        //On the ground controls
            if(pc.fast && SHit){
            if(xVelocity > -maxXVelocity){
                this.xVelocity = Gdx.graphics.getDeltaTime() * (-speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(EHit){
                    x -= 1;
                }
                this.xVelocity = Gdx.graphics.getDeltaTime() * -speed;
            }
            facingLeft = true;
            state = State.MOVE;
    }
    public void Jump(){
        if(pc.jump && SHit){
            this.y += 1;
            this.yVelocity = (jump/2) * Gdx.graphics.getDeltaTime();
        }
    }
    public void Attack(){
        if(pc.action && proC > proCMax){
            state = State.ATTACK;
            stateTime = 0;
            proC = 0;
        }
    }
    public void Hit(){
        if(state == State.HIT || isInvincible){
            if(hitCounter % 5 == 1){
                if(hitColor == 1f){
                    hitColor = 0.5f;
                }
                else{
                    hitColor = 1f;
                }
            }
            if(hitCounter == 0){
                isInvincible = true;
                state = State.IDLE;
            }
            if(hitCounter >= 200){
                hitCounter = 0;
                isInvincible = false;
            }
            else{
                hitCounter++;
            }
        }
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
        EHit = false;
        for(Environment objectList1: objectList){
            if(ECollide.overlaps(objectList1.hitbox)){
                EHit = true;
                xVelocity = 0;
                x = objectList1.x - this.hitBox.width - 1;
                break;
            }
        }
        WHit = false;
        for(Environment objectList1: objectList){
            if(WCollide.overlaps(objectList1.hitbox)){
                WHit = true;
                xVelocity = 0;
                x = objectList1.x + objectList1.width + 1;
                break;
            }
        }
        NHit = false;
        for(Environment objectList1: objectList){
            if(NCollide.overlaps(objectList1.hitbox)){
                NHit = true;
                yVelocity = 0;
                y = objectList1.y - this.hitBox.height - 1;
                break;
            }
        }
    }
    
    public void EnemyDetect(ArrayList<GameCharacter> enemyList){
        if(!isInvincible){
            for(GameCharacter enemy : enemyList){
                if(hitBox.overlaps(enemy.hitBox)){
                    if(WCollide.overlaps(enemy.hitBox)){
                        xVelocity = Gdx.graphics.getDeltaTime() * 30;
                        y += 1;
                        yVelocity = Gdx.graphics.getDeltaTime() * 40;
                    }
                    else if(ECollide.overlaps(enemy.hitBox)){
                        xVelocity = Gdx.graphics.getDeltaTime() * -30;
                        y += 1;
                        yVelocity = Gdx.graphics.getDeltaTime() * 40;
                    }
                    state = State.HIT;
                    System.out.println("Hit");
                }
            }
        }
        
    }
    
    public void Render(SpriteBatch batch, float delta){
        CharacterLoop(delta);
        if(isInvincible){
            batch.setColor(1, 1, 1, hitColor);
        }
        if(facingLeft){
           
            batch.draw(currentFrame, x + xAdjust + w + turnOffSet, y + yAdjust, -w, h);
            
        }
        else{
            batch.draw(currentFrame, x + xAdjust, y + yAdjust);
        }
        batch.setColor(1, 1, 1, 1f);
    }
}
