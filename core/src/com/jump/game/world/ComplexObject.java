/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class ComplexObject {
    public ArrayList<Rectangle> hitboxList;
    public int x, y;
    Level lvl;
    int object;
    public TextureRegion picList[];
    
    public enum Level{
        FOREST, MOUNTAIN, MAGMA
    }
    
    public ComplexObject(){
        
    }
    
    public void LoadFromLibrary(Level lvl, int object){
        
    }
}
