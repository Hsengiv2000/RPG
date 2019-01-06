package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Key extends Actor {
	float x;float y;
	Texture tex;
	final Main game;
	Rectangle bounds;
 public Key(float x2, float y2 , final Main game){
	 super();
	 this.game = game;
	 this.x = x2;
	 tex = game.assets.get("key.png", Texture.class);
	 this.y = y2;
	 bounds = new Rectangle(x , y , 48 , 48);
 }
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tex, x, y, 48, 48);
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		bounds.set(x, y, 48, 48);
		super.act(delta);
	}

}
