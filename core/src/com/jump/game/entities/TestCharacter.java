/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestCharacter extends Character{
    
    public TestCharacter(){
        this.hp = 10;
        this.spriteSheet = new Texture("RedKnightSheet.png");
        this.state = State.IDLE;
        this.idleFrame = new TextureRegion[2];
        this.moveFrame = new TextureRegion[2];
        this.atkFrame = new TextureRegion[2];
        
        for(int i = 0; i < 2; i++){
            idleFrame[i] = new TextureRegion(spriteSheet, i+2 * w, 0, w, h);
            moveFrame[i] = new TextureRegion(spriteSheet, i+2 * w, h, w, h);
            atkFrame[i] = new TextureRegion(spriteSheet, i+2 * w, h*2, w, h);
        }
        
        idleAnim = new Animation(0.4f, idleFrame);
        moveAnim = new Animation(0.4f, moveFrame);
        atkAnim = new Animation(0.4f, atkFrame);
    }

    @Override
    public void CharacterLoop() {
        
    }

    @Override
    public void AnimationLoop() {
    }

    @Override
    public void MoveRight() {
    }

    @Override
    public void MoveLeft() {
    }

    @Override
    public void Render() {
    }
}
