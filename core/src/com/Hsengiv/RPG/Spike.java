package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Spike extends Actor{
float x,  y;
Rectangle bounds;
Texture tex = new Texture("spikes.png");
public Spike(float x, float y){
this.x = x;
this.y = y;
bounds = new Rectangle(x,y,48,48);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	// TODO Auto-generated method stub
	super.draw(batch, parentAlpha);
	
	batch.draw(tex, x, y , 48, 48);
}

}