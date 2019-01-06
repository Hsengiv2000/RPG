package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Life extends Actor{
	float x, y;
	ShapeRenderer sr;
	Texture tex = new Texture("life.png");
	int life = 96;
	public Life(float x , float y){
		this.x = x;
		this.y = y;
		sr = new ShapeRenderer();
		
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha ) {
		batch.draw(tex, x , y + 10 , life , 25);
		super.draw(batch, parentAlpha);
	}
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}

}
