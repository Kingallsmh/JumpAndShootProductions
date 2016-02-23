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
import com.jump.game.controllers.AIPassiveController;
import com.jump.game.controllers.PlayerController;

/**
 *
 * @author Kyle
 */
public class Buggith extends GameCharacter{
    
    int onWall = 0;
    TextureRegion[] IdlePosFrame = new TextureRegion[2];
    TextureRegion[] MoveVerticalFrame = new TextureRegion[2];
    Animation moveVerticalAnim;
    
    public Buggith(){
        this.pc = new PlayerController();
        this.speed = 30;
        this.jump = 150;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
        this.x = 90;
        this.y = 60;
        // Use image file to figure this out
        this.width = 15;
        this.height = 15;
        this.xAdjust = -22;
        this.yAdjust = -2;
        this.turnOffSet = -5;
        this.colBoxAdjust = 4;
        this.colBoxSizeAdjust = 8;
        
        this.hitBox = new Rectangle(x, y, width, height);
        this.NCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.SCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.ECollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        this.WCollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        
        this.hp = 10;
        
        this.spriteSheet = new Texture("BuggithSheet.png");
        this.state = State.IDLE;
        this.idleFrame = new TextureRegion[1];
        this.moveFrame = new TextureRegion[2];
        onWall = 0;
        IdlePosFrame = new TextureRegion[2];
        MoveVerticalFrame = new TextureRegion[2];
        this.jumpFrame = new TextureRegion[1];
        
        for(int i = 0; i < 2; i++){
            IdlePosFrame[i] = new TextureRegion(spriteSheet, (i) * w, 0, w, h);
            MoveVerticalFrame[i] = new TextureRegion(spriteSheet, (i) * w, h*2, w, h);
        }
        
        for(int i = 0; i < 2; i++){
            idleFrame[0] = new TextureRegion(spriteSheet, (0) * w, 0, w, h);
            moveFrame[i] = new TextureRegion(spriteSheet, (i) * w, h, w, h);
        }
        
        jumpFrame[0] = new TextureRegion(spriteSheet, 2 * w, 0, w, h);
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.15f, moveFrame);
        moveVerticalAnim = new Animation(0.15f, MoveVerticalFrame);
        jumpAnim = new Animation(0.1f, jumpFrame);
    }
    
    @Override
    public void Attack() {
        if(pc.action){
            
        }
    }
    
    @Override
    public void Jump() {
        super.Jump();
        if(pc.jump && NHit){
            stateTime = 0;
            y -= 1;
        }
        if(pc.jump && EHit){
            stateTime = 0;
            x -= 1;
        }
        if(pc.jump && WHit){
            stateTime = 0;
            x += 1;
        }
    }

    @Override
    public void JumpAnim(float time) {
        super.JumpAnim(time); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveRight() {
        if(SHit){
            if(pc.fast){
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
        else{
            if(xVelocity < 0){
                facingLeft = false;
                xVelocity = -xVelocity;
            }
        }
        //When on the left wall
        if(WHit){
            if(pc.fast){
            if(yVelocity > -maxXVelocity){
                this.yVelocity = Gdx.graphics.getDeltaTime() * (-speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(NHit){
                    y -= 1;
                }
                this.yVelocity = Gdx.graphics.getDeltaTime() * -speed;
            }
            facingLeft = false;
            state = State.MOVE;
        }
        
        //When on the ceiling
        if(NHit){
            if(pc.fast){
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
            facingLeft = false;
            state = State.MOVE;
        }
        
        //When on a right wall
        if(EHit){
            if(pc.fast){
            if(yVelocity < maxXVelocity){
                this.yVelocity = Gdx.graphics.getDeltaTime() * (speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(SHit){
                    y += 1;
                }
                this.yVelocity = Gdx.graphics.getDeltaTime() * speed;
            }
            facingLeft = false;
            state = State.MOVE;
        }
    }

    @Override
    public void MoveLeft() {
        //When on the floor
        if(SHit){
            if(pc.fast){
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
        //When On the left wall
        if(WHit){
            if(pc.fast){
            if(yVelocity < maxXVelocity){
                this.yVelocity = Gdx.graphics.getDeltaTime() * (speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(SHit){
                    y += 1;
                }
                this.yVelocity = Gdx.graphics.getDeltaTime() * speed;
            }
            facingLeft = true;
            state = State.MOVE;
        }
        //When on the ceiling
        if(NHit){
            if(pc.fast){
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
            facingLeft = true;
            state = State.MOVE;
        }
        //When on a right wall
        if(EHit){
            if(pc.fast){
            if(yVelocity > -maxXVelocity){
                this.yVelocity = Gdx.graphics.getDeltaTime() * (-speed*2);
            }
                MoveAnim(Gdx.graphics.getDeltaTime());
            }
            else{
                if(NHit){
                    y -= 1;
                }
                this.yVelocity = Gdx.graphics.getDeltaTime() * -speed;
            }
            facingLeft = true;
            state = State.MOVE;
        }
    }
    
    @Override
    public void CharacterUpdate(float time) {
        super.CharacterUpdate(time); //To change body of generated methods, choose Tools | Templates.
        if(yVelocity > 0 && WHit){
            if(yVelocity < 0.1){
                yVelocity = 0;
            }
            else{
                yVelocity -= Gdx.graphics.getDeltaTime() * (speed/10);//Slow down the character
            }
        }
        else if(yVelocity < 0 && WHit){
            if(yVelocity > -0.1){
                yVelocity = 0;
            }
            else{
                yVelocity += Gdx.graphics.getDeltaTime() * (speed/10);
            }
        }
        
        if(yVelocity > 0 && EHit){
            if(yVelocity < 0.1){
                yVelocity = 0;
            }
            else{
                yVelocity -= Gdx.graphics.getDeltaTime() * (speed/10);//Slow down the character
            }
        }
        else if(yVelocity < 0 && EHit){
            if(yVelocity > -0.1){
                yVelocity = 0;
            }
            else{
                yVelocity += Gdx.graphics.getDeltaTime() * (speed/10);
            }
        }
        
        if(xVelocity > 0 && NHit){
            if(xVelocity < 0.1){
                xVelocity = 0;
            }
            else{
                xVelocity -= Gdx.graphics.getDeltaTime() * (speed/10);//Slow down the character
            }
        }
        else if(xVelocity < 0 && NHit){
            if(xVelocity > -0.1){
                xVelocity = 0;
            }
            else{
                xVelocity += Gdx.graphics.getDeltaTime() * (speed/10);
            }
        }
        
        if(SHit){
            onWall = 1;
        }
        else if(EHit){
            onWall = 3;
        }
        else if(WHit){
            onWall = 2;
        }
        else if(NHit){
            onWall = 4;
        }
        else{
            onWall = 0;
            state = State.JUMP;
        }
    }
    
    @Override
    public void Gravity() {
        if(!SHit && !EHit && !WHit && !NHit){
            if(yVelocity > -maxYVelocity){
                this.yVelocity -= gravity * Gdx.graphics.getDeltaTime();
            }
        }
    }
    
    @Override
    public void IdleAnim(float time){
        if(onWall == 1 || onWall == 4){
            currentFrame = IdlePosFrame[0];    
        }
        else if(onWall == 2 || onWall == 3){
            currentFrame = IdlePosFrame[1];
        }
    }

    @Override
    public void MoveAnim(float time) {
        stateTime += time;
        if(onWall == 1 || onWall == 4){
            currentFrame = moveAnim.getKeyFrame(stateTime, true);  
        }
        else if(onWall == 2 || onWall == 3){
            currentFrame = moveVerticalAnim.getKeyFrame(stateTime, true);
        }
        else if(onWall == 0){
            currentFrame = atkAnim.getKeyFrame(stateTime, true);
        }
        
        if(stateTime > 10000){
            stateTime = 0;
        }
    }

    @Override
    public void Render(SpriteBatch batch, float delta) {
        CharacterLoop(delta);
        if(isInvincible){
            batch.setColor(1, 1, 1, hitColor);
        }
        if(onWall == 1){
            if(facingLeft){
                batch.draw(currentFrame, x + xAdjust + w + turnOffSet, y + yAdjust, -w, h);
            }
            else{
                batch.draw(currentFrame, x + xAdjust, y + yAdjust);
            }
        }
        if(onWall == 2){
            if(facingLeft){
                batch.draw(currentFrame, x + xAdjust + w - 2, y - 8, -w, h);
            }
            else{
                batch.draw(currentFrame, x + xAdjust + w - 2, y - xAdjust + 3, -w, -h);
            }
        }
        if(onWall == 3){
            if(facingLeft){
                batch.draw(currentFrame, x + xAdjust - 3, y - xAdjust + 3, w, -h);
            }
            else{
                batch.draw(currentFrame, x + xAdjust - 3, y - 8, w, h);
            }
        }
        
        if(onWall == 4){
            if(facingLeft){
                batch.draw(currentFrame, x + xAdjust , y + 17, w, -h);
            }
            else{
                batch.draw(currentFrame, x + xAdjust + w + turnOffSet, y + 17, -w, -h);
            }
        }
        if(onWall == 0){
            batch.draw(currentFrame, x + xAdjust - 2, y + yAdjust - 2, w, h);
        }
        
        batch.setColor(1, 1, 1, 1f);
    }
}
