package com.Hsengiv.RPG;

import java.util.stream.IntStream;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rain {
	int x , y , d;
	Texture drop;
	
public Rain(int x, int y , int d){
	this.x = x;
	this.y = y;
   drop = new Texture("rain.png");
   this.d = d;
 
   }
public void draw(OrthographicCamera cam, SpriteBatch sb){
sb.draw(drop, x, y, 22, 22);
update();
}
public void update(){
	y-= d;
	if(y < 0){
		y = 480;
	}
}
}