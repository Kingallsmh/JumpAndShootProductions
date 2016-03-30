/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jump.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jump.game.Objects.Projectiles;
import java.util.ArrayList;

/**
 *
 * @author NSCCSTUDENT
 */
public class Bag {
    int s = 32;
    ArrayList<Projectiles> inTheBag;
    int sizeLimit = 20;
    int currentSize = 0;
    Texture bagSheet;
    TextureRegion bagOpening[], bagSize[], currentBag, currentOpening;
    float xOpenAdjust;
    float yOpenAdjust;
    
    GameCharacter chara;
    public Rectangle hitbox;
    float width = 5;
    float height = 30;
    boolean isBeingUsed = false;
    
    public Bag(GameCharacter chara){
        this.chara = chara;
        hitbox = new Rectangle(chara.x, chara.y, width, height);
        
        inTheBag = new ArrayList<Projectiles>();
        bagSheet = new Texture("BagSheet.png");
        bagOpening = new TextureRegion[2];
        bagSize = new TextureRegion[6];
        
        for(int i = 0; i < 2; i++){
            bagOpening[i] = new TextureRegion(bagSheet, i * s, 0, s, s);
        }
        
        bagSize[0] = new TextureRegion(bagSheet, 2 * s, 0, s, s);
        for(int i = 1; i < 5; i++){
            bagSize[i] = new TextureRegion(bagSheet, (i-1) * s, s, s, s);
        }
        bagSize[5] = new TextureRegion(bagSheet, 0, s*2, s, s);
    
    }    
    public void BagUpdate(){
        if(chara.facingLeft){
            hitbox.x = chara.x - hitbox.width;
        }
        else{
            hitbox.x = chara.x + chara.hitBox.width;
        }
        hitbox.y = chara.y;
    }
    
    public void PutInBag(Projectiles pickup){
        if(pickup.bagSizeValue + currentSize >= sizeLimit){
        }
        else{
            inTheBag.add(pickup);
            currentSize += pickup.bagSizeValue;
            System.out.println(currentSize);
        }
    }
    
    public void BagCollision(Projectiles projectile){
        if(this.hitbox.overlaps(projectile.hitbox)){
            PutInBag(projectile);
        }
    }
    
    public void BagAnimation(){
        if(isBeingUsed){
            currentOpening = bagOpening[1];
        }
        else{
            currentOpening = bagOpening[0];
        }
        
        if(currentSize == 0){
            currentBag = bagSize[0];
        }
        else if(currentSize < 5){
            currentBag = bagSize[1];
        }
        else if(currentSize < 10){
            currentBag = bagSize[2];
        }
        else if(currentSize < 15){
            currentBag = bagSize[3];
        }
        else if(currentSize < 20){
            currentBag = bagSize[4];
        }
        else if(currentSize == 20){
            currentBag = bagSize[5];
        }
        
    }
    
    public void Render(SpriteBatch batch){
        BagUpdate();
        BagAnimation();
        
        if(chara.facingLeft){
            batch.draw(currentOpening, hitbox.x + s, hitbox.y + 4, -currentOpening.getRegionWidth(), currentOpening.getRegionHeight());
            batch.draw(currentBag, hitbox.x + s + (s/2), hitbox.y + 4, -currentOpening.getRegionWidth(), currentOpening.getRegionHeight());
        }
        else{
            batch.draw(currentOpening, hitbox.x - s + 5, hitbox.y + 4);
            batch.draw(currentBag, hitbox.x - s - (s/2) + 5, hitbox.y + 4);
        }
        
    }
}
