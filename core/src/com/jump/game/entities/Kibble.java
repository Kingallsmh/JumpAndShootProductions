/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.controllers.AIPassiveController;
import com.jump.game.world.Stage;

/**
 *
 * @author NSCCSTUDENT
 */
public class Kibble extends GameCharacter{
    
    public Kibble(Stage stage, float x, float y, int difficulty){
        this.stage = stage;
        this.pc = new AIPassiveController();
        this.speed = 30;
        this.jump = 150;
        this.maxXVelocity = 3;
        this.maxYVelocity = 3;
        this.x = 120;
        this.y = 60;
        // Use image file to figure this out
        this.width = 11;
        this.height = 15;
        this.xAdjust = -27;
        this.yAdjust = -2;
        this.turnOffSet = 1;
        this.colBoxAdjust = 2;
        this.colBoxSizeAdjust = 4;
        
        this.hitBox = new Rectangle(x, y, width, height);
        this.NCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.SCollide = new Rectangle(x, y, width - colBoxSizeAdjust, 1);
        this.ECollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        this.WCollide = new Rectangle(x, y, 1, height - colBoxSizeAdjust);
        
        this.hp = 10;
        
        this.spriteSheet = new Texture("KibbleSheet.png");
        this.state = State.JUMP;
        this.idleFrame = new TextureRegion[4];
        this.moveFrame = new TextureRegion[3];
        this.jumpFrame = new TextureRegion[1];
        
        for(int i = 0; i < 4; i++){
            idleFrame[i] = new TextureRegion(spriteSheet, (i) * w, 0, w, h);
            if(i < 3){
                moveFrame[i] = new TextureRegion(spriteSheet, (i) * w, h, w, h);
            }
            if(i < 1){
                jumpFrame[i] = new TextureRegion(spriteSheet, (i) * w, h*2, w, h);
            }
        }
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.15f, moveFrame);
        jumpAnim = new Animation(0.1f, jumpFrame);
        
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
    public void Attack() {
        
    }

    @Override
    public void Jump() {
        if(pc.jump && SHit){
            this.yVelocity = (jump/2) * Gdx.graphics.getDeltaTime();
        }
        if(!SHit){
            state = State.JUMP;
        }
        else if(SHit && state == State.JUMP){
            state = State.IDLE;
        }
        
    }

    
    
}
