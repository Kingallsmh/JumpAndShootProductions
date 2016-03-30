/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world.worldLibrary;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jump.game.world.ComplexObject;
import com.jump.game.world.ComplexObject.Level;

/**
 *
 * @author NSCCSTUDENT
 */
public class ObjectLibrary {
    
    Texture spriteSheet= new Texture("ForestParts.png");
    
    public ObjectLibrary(){
        
    }
    
    public ComplexObject PickObject(Level lvl, int object){
        ComplexObject co;
        if(lvl == Level.FOREST){
            int s = 16;
            co = new ComplexObject();
            co.picList[0] = new TextureRegion(spriteSheet, 16, 48, 32, 5);
            co.picList[1] = new TextureRegion(spriteSheet, 48, 32, s, s*3);
            co.picList[2] = new TextureRegion(spriteSheet, 64, 48, 16, 6);
//            co.hitboxList.add(new Rectangle(0))
        }
        
        return null;
    }
    
}
