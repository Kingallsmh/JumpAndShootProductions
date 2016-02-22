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
public class AIPassiveController extends MainController{
int counter = 0;
    
    @Override
    public void ListenForInput() {
        PressRight();
        PressLeft();
//        PressJump();
        if(counter > 140){
            counter = 0;
        }
        counter ++;
    }

    @Override
    public void PressFast() {
    }

    @Override
    public void PressUp() {
    }

    @Override
    public void PressDown() {
    }

    @Override
    public void PressLeft() {
        if(counter > 5 && counter < 40){
            left = true;
        }
        else{
            left = false;
        }
    }

    @Override
    public void PressRight() {
        if(counter > 45 && counter < 120){
            right = true;
        }
        else{
            right = false;
        }
    }

    @Override
    public void PressAction() {
    }

    @Override
    public void PressJump() {
        if(counter == 40 || counter == 80){
            jump = true;
        }
        else{
            jump = false;
        }
    }
    
}
