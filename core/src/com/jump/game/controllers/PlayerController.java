/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author NSCCSTUDENT
 */
public class PlayerController extends MainController{
    
    public PlayerController(){
        
    }
    
    @Override
    public void PressUp(){
       this.up = Gdx.input.isKeyPressed(Input.Keys.UP);        
    }
    
    @Override
    public void PressDown(){
        this.down = Gdx.input.isKeyPressed(Input.Keys.DOWN); 
    }

    @Override
    public void PressLeft() {
        this.left = Gdx.input.isKeyPressed(Input.Keys.LEFT); 
    }

    @Override
    public void PressRight() {
        this.right = Gdx.input.isKeyPressed(Input.Keys.RIGHT); 
    }

    @Override
    public void PressAction() {
        this.action = Gdx.input.isKeyPressed(Input.Keys.X); 
    }

    @Override
    public void PressJump() {
        this.jump = Gdx.input.isKeyPressed(Input.Keys.Z); 
    }
}
