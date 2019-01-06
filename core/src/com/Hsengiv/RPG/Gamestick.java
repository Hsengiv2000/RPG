package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Gamestick extends Actor {
Texture tex;
Handler handler;
int x ,y;
public Gamestick(int x, int y , String loc , Handler handler){
	this.x = x;
	this.y = y;
	tex = new Texture(Gdx.files.internal(loc));
	this.handler  = handler;
	setBounds(x, y, 16, 16);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	batch.draw(tex, x , y , 16 ,  16 );
	super.draw(batch, parentAlpha);
}
@Override
public void act(float delta) {
	// TODO Auto-generated method stub
	super.act(delta);
}
}
