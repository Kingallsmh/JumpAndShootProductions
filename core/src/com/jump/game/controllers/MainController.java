/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.controllers;

/**
 *
 * @author NSCCSTUDENT
 */
public abstract class MainController {
    public boolean up, fast, right, left, down, action, jump, longJump;
    
    public abstract void ListenForInput();
    public abstract void PressFast();
    public abstract void PressUp();
    public abstract void PressDown();
    public abstract void PressLeft();
    public abstract void PressRight();
    public abstract void PressAction();
    public abstract void PressJump();
}
