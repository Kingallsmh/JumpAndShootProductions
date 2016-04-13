/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jump.game.Configurations;
import com.jump.game.entities.GameCharacter;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class Camera {
    OrthographicCamera cam;
    
    
    public Camera(OrthographicCamera cam){
        this.cam = cam;
    }
    
    public void FollowPlayer(GameCharacter gChar, float stageWidth){
        if(gChar.x - Configurations.cameraWidth/2 < 0){
        }
        else{
            cam.position.x = gChar.x; 
        }
        
        if(gChar.x - Configurations.cameraWidth/2 > stageWidth){
        }
        else{
            cam.position.x = gChar.x; 
        }
    }
    
    public boolean IsOnScreen(float x){
        if(x < cam.position.x - Configurations.cameraWidth/2){
            return false;
        }
        else if(x > cam.position.x + Configurations.cameraWidth/2){
            return false;
        }
        
        return true;
    }
}
