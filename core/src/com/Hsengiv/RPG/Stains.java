package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Stains extends Actor{
float x, y, width, height;

Texture tex;

public Stains(float x, float y, String loc){
	this.x = x;
	this.y= y;
	width  = 64;
	height = 64;
	tex = new Texture(loc);
		
	
}
@Override
public void draw(Batch batch, float parentAlpha){
	super.draw(batch, parentAlpha);
	batch.draw(tex, x, y , width, height);
	setBounds(x , y , width, height);
}

@Override
public void act(float delta){
	
	super.act(delta);
	setBounds(x, y , 32, 32);
	
}
}
