package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Switch extends Actor{
	float x,  y;
	Texture tex;
	Rectangle bounds;
public Switch(  float x , float y , String string){
	this.x = x;
	this.y = y;
   tex = new Texture(string);
   bounds = new Rectangle(x , y , 48 , 48);
		   }
@Override
public void draw(Batch batch, float parentAlpha) {
	// TODO Auto-generated method stub
	super.draw(batch, parentAlpha);
	
	batch.draw(tex, x , y ,  48 , 48);
}




}
