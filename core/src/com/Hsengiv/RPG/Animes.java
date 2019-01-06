package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animes extends Actor{
Animation animation;
Texture playertexture;
TextureRegion[]frames;
float statetime;
float x = 0, y = 0;

TextureRegion currentframe;

public Animes(int col, int row){
	super();
	playertexture = new Texture(Gdx.files.internal("sparks.png"));
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
    currentframe = animation.getKeyFrame(1);
	
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
public TextureRegion getCur(){
	return currentframe;
}
public void update(float x, float y){
	this.x = x;
	this.y = y;
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	super.draw(batch, parentAlpha);
	batch.draw(getCur(), x , y - 60 , 256, 256);
}
@Override
public void act(float delta){
	if (statetime < 32) {

		statetime += Gdx.graphics.getDeltaTime() * 8;
		if(statetime >32){
		statetime=0;
		}

		} else {
		statetime = 0;
		}
	currentframe = animation.getKeyFrame(0 + statetime);
	super.act(delta);
}
}
