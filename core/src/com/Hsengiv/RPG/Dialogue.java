package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Dialogue extends Actor
{
    Texture tex;
    String text;
    Player player;float x, y;
    boolean display = false;
    
    BitmapFont fnt = new BitmapFont();
	public Dialogue(String text, Player player){
		this.text = text;
		tex = new Texture("coolkiddialogue.png");
	    this.player = player;
	    fnt.setColor(Color.BLACK);
	    this.x = player.x;
	    this.y = player.y;
	}
	public Dialogue(String text , float x , float y){
		this.text = text;
		tex = new Texture("coolkiddialogue.png");
	   this.x = x;
	   this.y = y;
	    fnt.setColor(Color.BLACK);
	   
	}
	public void setText(String text){
		this.text = text;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		super.draw(batch, parentAlpha);
		if(player != null){
			this.x = player.x ; this.y = player.y;
		}
		if(display){
		batch.draw(tex, x, y, 250 , 200);
		fnt.drawWrapped(batch, text,  x + 15 ,y + 170, 200);
		}
		
	}
	@Override
	public void act(float delta) {
	
		super.act(delta);
	}
	

}
