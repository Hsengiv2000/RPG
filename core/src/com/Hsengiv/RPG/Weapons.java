package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Weapons extends Actor{
	float armour,  speed , safespeed;
	public Rectangle bounds;
	Handler handler;
	String name;
	Animation animation;
	float statetime;
	int side = 0 , attackspeed;
	Life life;
	float x , y;
	int col ,row;
	int attackdelta = 0;
	int width, height;
	Dialogue info;
	int health = 0;
	int face;
	float attack;
	Texture EntityTexture;
public Weapons(String loc, float x , float y,  String width, String height, Handler handler, String attack, String armour,  String attackspeed , String health){
	EntityTexture = new Texture(loc + ".png");
	 this.width = Integer.parseInt(width);
	    this.height = Integer.parseInt(height);
	bounds = new Rectangle(x , y , this.width, this.height );
	this.health = Integer.parseInt(health);

this.attack = Float.parseFloat(attack);

this.armour = Float.parseFloat(armour);
this.attackspeed = Integer.parseInt(attack);


this.x  = x;
this.y  = y;

	this.handler = handler;
	info = new Dialogue("Additional stats: "+"\n attack = "+(this.attack)+"\n attackspeed = " + (this.attackspeed) + "\n Armour = " +this.armour + "\n health = "+this.health, handler.player);
	info.setVisible(false);
    handler.stage.addActor(info);
}


@Override
public void draw(Batch batch, float parentAlpha) {
	batch.draw(EntityTexture ,  x , y , width, height);
	super.draw(batch, parentAlpha);
}
}
