/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.controllers.PlayerController;
import com.jump.game.world.Stage;
import com.jump.game.world.TestStage2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jump.game.Configurations;
import com.jump.game.screens.GameScreen;

/**
 *
 * @author NSCCSTUDENT
 */
public class Main extends GameCharacter{
    
    TextureRegion grabFrame[];
    TextureRegion holdBagFrame;
    Animation grabAnim;
    public Bag bag;
    
    public Main(Stage stage, float x, float y, int difficulty){
        this.x = x;
        this.y = y;
        this.stage = stage;
        this.bag = new Bag(this);
        this.pc = new PlayerController();
        this.speed = 60;
        this.jump = 250;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
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
        
        //this.hp = 10;
        proCMax = 60;
        proC = proCMax;
        
        this.spriteSheet = new Texture("MainSheet.png");
        this.state = State.IDLE;
        this.idleFrame = new TextureRegion[2];
        this.moveFrame = new TextureRegion[4];
        this.atkFrame = new TextureRegion[2];
        this.jumpFrame = new TextureRegion[1];
        grabFrame = new TextureRegion[2];
        
        for(int i = 0; i < 2; i++){
            idleFrame[i] = new TextureRegion(spriteSheet, (i) * w, 0, w, h);
            grabFrame[i] = new TextureRegion(spriteSheet, i * w, h, w, h);
        }
        atkFrame[0] = grabFrame[1];
        atkFrame[1] = grabFrame[0];
        
        jumpFrame[0] = new TextureRegion(spriteSheet, 0, h*2, w, h);
        holdBagFrame = new TextureRegion(spriteSheet, w*2, h, w, h);
        moveFrame[0] = new TextureRegion(spriteSheet, (2) * w, 0, w, h);
        moveFrame[1] = new TextureRegion(spriteSheet, (1) * w, 0, w, h);
        moveFrame[2] = new TextureRegion(spriteSheet, (3) * w, 0, w, h);
        moveFrame[3] = new TextureRegion(spriteSheet, (1) * w, 0, w, h);
        
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.25f, moveFrame);
        atkAnim = new Animation(0.3f, atkFrame);
        grabAnim = new Animation(0.3f, grabFrame);
        switch(difficulty)
        {
            case 0:{SetDamageTaken(33); break;}
            case 1:{SetDamageTaken(45); break;}
            case 2:{SetDamageTaken(50); break;}
            case 3:{SetDamageTaken(101); break;}
            default:{ SetDamageTaken(33); break;}
        }
    }
    
    @Override
    public void CharacterLoop(float time) {
            pc.ListenForInput();
            if(this.pause == false){
                if(state != State.BUSY){

                this.EnemyDetect(stage.enemyList);
                    Gravity();
                    if(state != State.ATTACK && state != State.HIT && state != State.BAG){
                        CharacterMoving();
                        UseBagGrab();
                        Attack();
                    }
                    
                    StoreInSack();
                }
                Hit(damageTaken);
                CharacterUpdate(time);
                AnimationLoop(time);
        
            }else{
                if(pc.action)
                {
                    this.pause = false;
                    //stage = new TestStage(new OrthographicCamera(Configurations.cameraWidth, Configurations.cameraHeight));
                    stage.stageOver = true;
                }
            }
    }
    

    @Override
    public void AnimationLoop(float time) {
        if(state == State.JUMP){
            JumpAnim(time);
        }
        else if(state == State.MOVE){
            MoveAnim(time);
        }
        else if(state == State.IDLE){
            IdleAnim(time);
        }
        else if(state == State.ATTACK){
            AtkAnim(time);
        }
        else if(state == State.BAG){
            BagAnimation(time);
        }
        
    }

    @Override
    public void AtkAnim(float time) {
        if(stateTime == 0){
            bag.ReleaseFromBag();
        }
        stateTime += time;
        currentFrame = atkAnim.getKeyFrame(stateTime, false);
        if(atkAnim.isAnimationFinished(stateTime)){
            state = State.IDLE;
        }
    }
    
    public void BagAnimation(float time){
        stateTime += time;
        currentFrame = grabAnim.getKeyFrame(stateTime, false);
        if(grabAnim.isAnimationFinished(stateTime) && pc.action){
            currentFrame = holdBagFrame;
        }
        else if(grabAnim.isAnimationFinished(stateTime) && !pc.action){
            state = State.IDLE;
        }
    }
    
    public void UseBagGrab(){
        if(pc.action){
            this.state = State.BAG;
            stateTime = 0;
        }
    }
    
    public void StoreInSack(){
        if(state == State.BAG){
            for(int i = 0; i < stage.projectileList.size(); i++){
                bag.BagCollision(stage.projectileList.get(i));
            }
        } 
    }

    @Override
    public void Attack() {
        if(pc.throwItem){
            state = State.ATTACK;
            stateTime = 0;
        }
    }
    

    @Override
    public void Render(SpriteBatch batch, float delta) {
        bag.Render(batch);
        super.Render(batch, delta); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void Die() {
        //KillPlayer();
    }
    
    
}
