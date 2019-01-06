package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Trigger extends Actor{
public float x, y;
public String levelname;
Rectangle bounds;
public Trigger(float x , float y , String levelname){
	this.x = x;
	this.y = y;
	this.levelname = levelname;
	bounds = new Rectangle(x, y, 48, 20);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	super.draw(batch, parentAlpha);
	
}
@Override
public void act(float delta) {


	super.act(delta);
}
}