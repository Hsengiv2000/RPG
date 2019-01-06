package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedTile extends Actor{

Texture tex;
int x , y;
int rand = 0 , change = 0 , rand2  =0;;;
public AnimatedTile(String loc , int x , int y){
	this.tex = new Texture(loc + ".png");
	this.x = x;
	this.y = y;
}
@Override
public void draw(Batch batch, float parentAlpha) {
	if(change > rand ){
	batch.draw(tex ,  x ,  y ,  48  ,  48);
	
	}
	if(change > rand2){
		change = 0;
	}
	super.draw(batch, parentAlpha);
}
}
