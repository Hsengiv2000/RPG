package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor{
float x,  y;

float armour,  speed , safespeed;
public Rectangle bounds;
Handler handler;
String name;
Animation animation;
float statetime;
int side = 0 , attackspeed;
Life life;
int col ,row;
float damagetaken = 96;
int attackdelta = 0;
int health = 96 , xhealth = 96;;
int face;
float attack;
Texture EntityTexture;
TextureRegion[]frames;
float width, height , xwidth, xheight;
TextureRegion currentframe;
int xdelta = 0;
boolean resume = false;
public Entity(String loc, float x , float y,String width, String height, String Entitiy, Handler handler, String attack, String speed , String armour, String col, String row, String side, String attackspeed){
	EntityTexture = new Texture(loc + ".png");
	this.col = Integer.parseInt(col);
	this.row =Integer.parseInt(row);
	this.x  = x;
	this.y = y;TextureRegion [][] tmp =  TextureRegion.split(EntityTexture, EntityTexture.getWidth() / this.col, EntityTexture.getHeight()/ this.row);
	
    frames = new TextureRegion[this.col * this.row];
    int index =0;
    for(int i = 0 ; i < this.row;i++){
   	 for(int j = 0; j < this.col; j++){
   		 frames[index++] = tmp[i][j];
   	 }
    }
    this.face = Integer.parseInt(side);
    animation = new Animation(1f , frames);
    statetime = 0f;
    currentframe = animation.getKeyFrame(6);
    this.width = Integer.parseInt(width);
    this.height = Integer.parseInt(height);
    xwidth = this.width;
    xheight = this.height;
	bounds = new Rectangle(x , y , this.width , this.height);
	name = Entitiy;
	System.out.println(name);
this.attack = Float.parseFloat(attack);
this.speed = Float.parseFloat(speed);
this.armour = Float.parseFloat(armour);
this.attackspeed = Integer.parseInt(attack);
safespeed = this.speed;



	this.handler = handler;
	
}

@Override
public void draw(Batch batch, float parentAlpha) {
	if(face == 1){
	if(side == 0){
		batch.draw(getCur(), x , y , width , height);
	}
	if(side == 1){
		batch.draw(getCur(), x +width , y , -width , height);
	}}
	else	if(face == 0){
		if(side ==1){
			batch.draw(getCur(), x , y , width , height);
		}
		if(side == 0){
			batch.draw(getCur(), x +width , y , -width , height);
		}}
	
	//batch.draw(getCur(), x , y , width , height);
	
	bounds.set(x ,  y , xwidth, xheight);
	super.draw(batch, parentAlpha);
	if(handler.resume){
	update();
	}
}
public void update(){
	
	if (statetime < 4) {

		statetime += Gdx.graphics.getDeltaTime() * 8;
		if(statetime >4){
		statetime=0;
		}

		} else {
		statetime = 0;
		}
	currentframe = animation.getKeyFrame(0 + statetime);
	if((Math.sqrt(((handler.player.x - this.x)*(handler.player.x - this.x))+((handler.player.y - this.y)*(handler.player.y - this.y))) < 300)){
		if(handler.player.x > this.x){
			this.x +=speed; 
			side = 0;
		}
		else if(handler.player.x < this.x){
			this.x -=speed;
			side = 1;
		}
		if(handler.player.y > this.y){
			this.y +=speed;
		}
		else if(handler.player.y < this.y){
			this.y -=speed;
		}
	};
	if((Math.sqrt(((handler.player.x - this.x)*(handler.player.x - this.x))+((handler.player.y - this.y)*(handler.player.y - this.y))) > 300)){
	this.speed 	= safespeed;
	}
	}
	/*if(handler.player.x < this.x + 300 && handler.player.y < this.y +300 && handler.player.x > this.x && handler.player.y > this.y){
		this.x += speed;
		this.y += speed;
	}
	
	if(handler.player.x > this.x - 300 && handler.player.y < this.y +300 && handler.player.x < this.x && handler.player.y > this.y){
		this.x -= speed;
		this.y += speed;
	}
	if(handler.player.x > this.x - 300 && handler.player.y > this.y -300 && handler.player.x < this.x && handler.player.y < this.y){
		this.x -= speed;
		this.y -= speed;
	}
	if(handler.player.x < this.x + 300 && handler.player.y <this.y -300 && handler.player.x > this.x && handler.player.y < this.y){
		this.x += speed;
		this.y -= speed;
	}*/
public TextureRegion getCur(){
	return currentframe;
}
public TextureRegion getCurrentframe() {
	return currentframe;
}
}


