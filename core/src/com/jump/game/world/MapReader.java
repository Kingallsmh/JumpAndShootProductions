/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class MapReader {
    
    public ArrayList<String> map;
    public int rows;
    public int cols;
    
    public MapReader(){
        FileHandle file = Gdx.files.internal("TestStage.txt");
        String text = file.readString(null);
        System.out.println(text);
        
        rows = 4;
        cols = 10;
        map = new ArrayList<String>();
        
        for(int i = 0; i < rows; i++){
            map.add(text.substring(0, cols));
            text = text.substring(cols);
        }
        System.out.println(map.get(0));
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        
    }
    
}
