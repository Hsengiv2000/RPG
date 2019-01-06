package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Laser extends Actor{
	float x,  y;
	Rectangle bounds;
	int width, height;
	String size;
	Texture tex;
	public Laser(float x, float y ,String size){
	this.x = x;
	this.y = y;
	this.size = size;
	if(size == "broad"){
		width = 96; height = 48;
	}
	if(size == "tall"){
		width = 48; height = 96;
	}

	bounds = new Rectangle(x,y,width,height);
	tex = new Texture("laser"+size+".png");
	
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		
		batch.draw(tex, x, y , width, height);
	}

}
