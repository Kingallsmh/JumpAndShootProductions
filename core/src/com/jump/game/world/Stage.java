/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public abstract class Stage {
    
    String name;
    ArrayList<Objects> objectList;
    //ArrayList<entities> entityList;
    
    public abstract void CalculateCollisions();
    
}
