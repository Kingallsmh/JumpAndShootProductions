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
public abstract class Character {
    int hp;
    float xVelocity;
    float yVelocity;
    float maxXVelocity;
    float maxYVelocity;
    
    int w = 64;
    int h = 64;
    
    State state;
    Texture spriteSheet;
    TextureRegion currentFrame;
    TextureRegion[] idleFrame, moveFrame, atkFrame;
    Animation idleAnim, moveAnim, atkAnim;
    
    public enum State{
        IDLE, MOVE, ATTACK;
    }
    
    public abstract void CharacterLoop();
    public abstract void AnimationLoop();
    public abstract void MoveRight();
    public abstract void MoveLeft();
    public abstract void Render();
}
