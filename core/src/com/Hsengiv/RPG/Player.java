package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor	{
	float x,y;
	Texture tex;
	Rectangle bounds;
	public Rectangle leftbounds, rightbounds, upbounds, downbounds, ibounds;
	TextureRegion tr;
	String counter ="";
	float dx = 2, dy = 2;
	int attack   = 46, attackspeed = 50;
	public static final int row = 4 , col = 4;
	Animation animation;
	Texture playertexture;
	TextureRegion[]frames;
	TextureRegion currentframe;
	//leftbounds = (player.bounds.x + 20, player.bounds.y- 5, player.bounds.getWidth() - 40, player.bounds.getHeight() - 40);
	//sr.rect(player.bounds.x + 20, player.bounds.y + 8, player.bounds.getWidth() - 40, player.bounds.getHeight() - 40);
	//sr.rect(player.bounds.x + 30 , player.bounds.y  , player.bounds.getWidth() - 40, player.bounds.getHeight() -40 );
	//sr.rect(player.bounds.x + 10, player.bounds.y, player.bounds.getWidth()-40, player.bounds.getHeight() - 40);
	String movement;
	final Main game;
	float statetime;
	int armour = 100;
	int health =  320;
	Dialogue log;
	Handler handler;
	Life life ;
	public Player(float x, float y,  final Main game ){
		super();
		this.game = game;
		 this.x = x;
		 this.y = y;
		 log = new Dialogue("My Stats: "+"\n attack = "+(this.attack)+"\n attackspeed = " + (this.attackspeed) + "\n Armour = " +this.armour + "\n health = "+this.health,this);
			log.setVisible(false);
		  
	     playertexture = new Texture(Gdx.files.internal("ownsprite.png"));
	     TextureRegion [][] tmp =  TextureRegion.split(playertexture, playertexture.getWidth() / col, playertexture.getHeight()/ row);
	     frames = new TextureRegion[col * row];
	     int index =0;
	     for(int i = 0 ; i < row;i++){
	    	 for(int j = 0; j < col; j++){
	    		 frames[index++] = tmp[i][j];
	    	 }
	     }
	     
	     animation = new Animation(1f , frames);
	     statetime = 0f;
	     currentframe = animation.getKeyFrame(6);
	     bounds = new Rectangle(x  , y ,currentframe.getRegionWidth() ,currentframe.getRegionHeight());
	     leftbounds = new Rectangle( x+9 , y - 4 , currentframe.getRegionWidth() - 40 , currentframe.getRegionHeight() - 28);
	    rightbounds = new Rectangle( x+31 , y -4 , currentframe.getRegionWidth() - 40 , currentframe.getRegionHeight() - 28);
	     upbounds = new Rectangle( x + 5 , y +8 , currentframe.getRegionWidth() - 7  , currentframe.getRegionHeight() - 40);
	     downbounds = new Rectangle( x+5 , y - 5 , currentframe.getRegionWidth() - 7 , currentframe.getRegionHeight() - 40);
	     ibounds = new Rectangle(leftbounds.x -6, downbounds.y , rightbounds.x - leftbounds.x ,( upbounds. y - downbounds.y) + 10);
	    // bounds = new Rectangle(position.x + 15 , position.y + 34 , 30 , 10);
	   
	}
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public TextureRegion[] getFrames() {
		return frames;
	}
	public void setFrames(TextureRegion[] frames) {
		this.frames = frames;
	}
	public TextureRegion getCurrentframe() {
		return currentframe;
	}
	public void setCurrentframe(TextureRegion currentframe) {
		this.currentframe = currentframe;
	}
	public float getStatetime() {
		return statetime;
	}
	public void setStatetime(float statetime) {
		this.statetime = statetime;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		log.setText("My Stats: "+"\n attack = "+(this.attack)+"\n attackspeed = " + (this.attackspeed) + "\n Armour = " +this.armour + "\n health = "+this.health);
		super.draw(batch, parentAlpha);
		batch.draw(getCur(), x , y , getCurrentframe().getRegionWidth() , getCurrentframe().getRegionHeight());
	}
	@Override
	public void act(float delta){
		update();
		super.act(delta);
	}
	public void update(){
		//System.out.println(currentframe.getRegionHeight());
		if(x < 0){
			x = 0;
		}
		if( x > 2400){
			x = 2400;
		}
		if( y < 0){
			y = 0;
		}
		if(y > 30*48 - 32.5){
			y = 30*48 - 32.5f;
		}
		bounds.set(x  , y ,currentframe.getRegionWidth() ,currentframe.getRegionHeight());
		leftbounds.set(x+9 , y -4, currentframe.getRegionWidth() - 40 , currentframe.getRegionHeight() - 28);
		rightbounds.set(x+31 , y-4 , currentframe.getRegionWidth() - 40 , currentframe.getRegionHeight() - 28);
		upbounds.set(x + 5 , y +8 , currentframe.getRegionWidth() - 7  , currentframe.getRegionHeight() - 40);
		downbounds.set( x+5 , y - 5 , currentframe.getRegionWidth() - 7 , currentframe.getRegionHeight() - 40);
		ibounds.set(leftbounds.x -6, downbounds.y , rightbounds.x - leftbounds.x ,( upbounds. y - downbounds.y) + 10);
		if (statetime < 4) {

			statetime += Gdx.graphics.getDeltaTime() * 8;
			if(statetime >4){
			statetime=0;
			}

			} else {
			statetime = 0;
			}
		if(Handler.touchpad.getKnobPercentX() == 0 && Handler.touchpad.getKnobPercentY() == 0 ){
			
			Handler.resume = true;
			
			
		}
		else if(!(Handler.touchpad.getKnobPercentX() == 0 && Handler.touchpad.getKnobPercentY() == 0 )){
			Handler.resume = false;
			
		}
		if( Handler.touchpad.getKnobPercentY() > 0){
			y += dy;
			//movement = "up";
			//currentframe = animation.getKeyFrame(12 + statetime);
			}
		 if( Handler.touchpad.getKnobPercentY() < 0){
			y -= dy;
			//movement = "down";
			//currentframe = animation.getKeyFrame(0 + statetime);
			}
			 if( Handler.touchpad.getKnobPercentX() < 0){
			x -=dx;
			//movement = "left";
			//currentframe = animation.getKeyFrame(4 + statetime);
			}
		  if( Handler.touchpad.getKnobPercentX() > 0){
			x +=dx;
			////movement = "right";
			//currentframe = animation.getKeyFrame(8 + statetime);
			}
		if(Handler.touchpad.getKnobPercentX() > 0 && Handler.touchpad.getKnobPercentY() > 0){
			//counter = "ru";
			if(Handler.touchpad.getKnobPercentX() > Handler.touchpad.getKnobPercentY()){
				movement = "right";
				counter = "r";
				currentframe = animation.getKeyFrame(8 + statetime);
			}
			else{
				movement = "up";
				counter ="u";
				currentframe = animation.getKeyFrame(12 + statetime);
			}
		}
		else if(Handler.touchpad.getKnobPercentX() > 0 && Handler.touchpad.getKnobPercentY() < 0){
			//counter = "rd";
			if(Handler.touchpad.getKnobPercentX() > -1 * Handler.touchpad.getKnobPercentY()){
				movement = "right";
				counter = "r";
				currentframe = animation.getKeyFrame(8 + statetime);
			}
			else{
				movement = "down";
				counter = "d";
				currentframe = animation.getKeyFrame(0 + statetime);
			}
		}
		else if(Handler.touchpad.getKnobPercentX() < 0 && Handler.touchpad.getKnobPercentY() > 0){
			//counter = "lu";
			if(Handler.touchpad.getKnobPercentX() * -1 < Handler.touchpad.getKnobPercentY()){
				movement = "up";
				counter = "u";
				currentframe = animation.getKeyFrame(12 + statetime);
			}
			else{
				movement = "left";
				counter = "l";
				currentframe = animation.getKeyFrame(4 + statetime);
			}
		}
		else if(Handler.touchpad.getKnobPercentX() < 0 && Handler.touchpad.getKnobPercentY() < 0){
			//counter = "ld";
			if(Handler.touchpad.getKnobPercentX() * -1 > -1* Handler.touchpad.getKnobPercentY()){
				movement = "left";
				counter = "l";
				currentframe = animation.getKeyFrame(4 + statetime);
			}
			else{
				movement = "down";
				counter = "d";
				currentframe = animation.getKeyFrame(0 + statetime);
			}
		}
	}
	
		public void readjust(int x, int y, int xz, int yz){
			/*if(movement == "up" ){
				y -=dy;
			}
			if(movement == "down" ){
				y +=dy;
			}
			if(movement == "right" ){
				x-=dx;
			}
			if(movement == "left" ){
				x+=dx;
			}*/
			if(x > xz && y >yz){
				x = xz; y =yz;
			}
			
		}
		public void readjust(){
		/*	if(movement == "up" ){
				y -=2f;
			}
			if(movement == "down" ){
				y +=2f;
			}
			if(movement == "right" ){
				x-=2f;
			}
			if(movement == "left" ){
				x+=2f;
			}*/
			//
			if(counter == "ru"){
				y -=2f;
				x -=2f;
			}
			if(counter == "rd"){
				y +=2f;
				x -=2f;
			}
			if(counter == "lu"){
				y -=2f;
				x +=2f;
			}
			if(counter == "ld"){
				y +=2f;
				x +=2f;
			}
			if(counter == "r"){
				
				x +=2f;
			}
			if(counter == "l"){
				
				x -=2f;
			}
			if(counter == "d"){
				y -=2f;
				
			}
			if(counter == "u"){
				y +=2f;
				
			}
			
			
		}
		public void dispose(){
			tex.dispose();
			
		}
		
public TextureRegion getCur(){
	return currentframe;
}

	

}
