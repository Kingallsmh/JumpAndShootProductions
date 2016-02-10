/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NSCCSTUDENT
 */
public class TestStage extends Stage{

    public TestStage(){
        this.objectList = new ArrayList<Objects>();
        this.name = "Test";
    }
    
    @Override
    public void CalculateCollisions() {
        
    }
    
}
