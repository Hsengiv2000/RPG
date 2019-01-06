package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Button extends Actor{
	float x ,y , width , height;
	Texture tex;
	public Button(float x, float y, String loc ){
		this.x = x;
		this.y = y;
		width = 32;
		 height =32;
	tex = new Texture(loc);
	
	}
	public Button(float x, float y, String loc , float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	tex = new Texture(loc);
	
	}
	@Override
	public void draw(Batch batch,  float parentAlpha) {
		super.draw( batch,   parentAlpha);
	
		batch.draw(tex, x , y , width , height);
		setBounds(x, y, width, height);
		
	}
	@Override
	public void act(float delta){
		super.act(delta);
		setBounds(x, y, 32, 32);
	}
	
}
