package com.Hsengiv.RPG;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
	SpriteBatch hudbatch;
	OrthographicCamera hudcam;
	//Button zoom;
	Handler handler;
	Texture tex;
	
	BitmapFont fnt ;
	public HUD(Handler handler){
		hudbatch = new SpriteBatch();
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, 800, 480);
		fnt = new BitmapFont();
		fnt.scale(1.5f);
		fnt.setColor(Color.NAVY);
		tex = new Texture("pbar.png");
		this.handler = handler;
      /// hudcam.position.set(0,0,0);		
      
	}
	public void draw(int keys){
		hudcam.update();
		hudbatch.setProjectionMatrix(hudcam.combined);
		hudbatch.begin();
		hudbatch.draw(tex ,  240 , 440 , handler.player.health , 35);
		fnt.draw(hudbatch, "Keys: " + keys, 0, +480);
		fnt.draw(hudbatch, ""+ handler.player.health , + 240, +475);
	Iterator iter = handler.drops.iterator();
		while(iter.hasNext()){
			Rain drop = (Rain) iter.next();
			drop.draw(hudcam , hudbatch);
			
		}
		hudbatch.end();
	}

}
