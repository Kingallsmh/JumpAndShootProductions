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
import com.jump.game.controllers.MainController;
import com.jump.game.world.Objects;
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
    float xAdjust, yAdjust;
    
    boolean facingLeft = true;
    float gravity = 2;
    
    int w = 64;
    int h = 64;
    float stateTime = 0;
    public Rectangle hitBox, NCollide, SCollide, ECollide, WCollide;
    public boolean NHit = false, SHit = false, EHit = false, WHit = false;
    
    State state;
    Texture spriteSheet;
    TextureRegion currentFrame;
    TextureRegion[] idleFrame, moveFrame, atkFrame;
    Animation idleAnim, moveAnim, atkAnim;
    
    public enum State{
        IDLE, MOVE, ATTACK;
    }
    
    public abstract void CharacterLoop(float time);
    public abstract void CharacterUpdate(float time);
    public abstract void AnimationLoop(float time);
    public abstract void CharacterMoving();
    public abstract void MoveRight();
    public abstract void MoveLeft();
    public abstract void Jump();
    public abstract void Gravity();
    public abstract void CollisionDetect(ArrayList<Objects> objectList);
    public abstract void Render(SpriteBatch batch, float delta);
}
