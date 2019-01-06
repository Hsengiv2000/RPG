package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class box extends Actor{
float x,y;
Rectangle bounds, ibounds;
Texture tex;
boolean stepping1 = false , stepping2 = false, stepping3 = false;;
public Rectangle leftbounds, rightbounds, upbounds, downbounds;
	public box(float x, float y, final Main game){
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, 48, 48);
		tex = game.assets.get("box.png", Texture.class);
		leftbounds = new Rectangle( x-5 , y-1 , 7 , 54);
	    rightbounds = new Rectangle( x+50 , y-3 , 7 , 54);
	     upbounds = new Rectangle( x -6 , y +48, 59 , 7);
	     ibounds = new Rectangle(x +10 , y +10 , 30 , 30);
	     downbounds = new Rectangle( x-3, y -2 , 57 , 7);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tex, x, y, 48, 48);
		
		super.draw(batch, parentAlpha);
	}
	@Override
	public void act(float delta) {
		bounds.set(x, y, 48, 48);
		ibounds.set(x + 10, y + 10, 30, 30);
		leftbounds.set( x-5 , y -1 , 7 , 54);
		rightbounds.set( x+50 , y -3, 7 ,54);
		upbounds.set( x - 6 , y +48, 59 ,7);
		downbounds.set( x - 3, y -2  , 57 , 7);
		super.act(delta);
	}
}
