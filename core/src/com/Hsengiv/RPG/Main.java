package com.Hsengiv.RPG;


import android.content.SharedPreferences;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class Main extends Game {

	AssetManager assets;
	int level =1;
	//SharedPreferences prefs;
	//SharedPreferences.Editor editor;
public Main(SharedPreferences prefs){
//this.prefs = prefs;
	//editor = prefs.edit();
	//editor.commit();
}
public Main(){
	
}
//Preferences pref;

Player player;
	@Override
	public void create () {
		
		assets = new AssetManager();
		/*if(prefs != null){
		level = prefs.getInteger("level");
		}*/
	
	/*file = Gdx.files.internal("text.txt");
	level = file.readString().length();*/
	//	  if(prefs == null){
	//	pref = Gdx.app.getPreferences("com.Hsengiv.RPG");
	//	pref.flush();
	//	level = pref.getInteger("Level" , 1);
	//	if(level <1){
	//		level =1;
	//	}
	//Gdx.app.log("log: " , prefs.getString("init", "false"));
		//player = new Player( 0 , 0 , this );
	 
		//player.armour = pref.getInteger("armour", 100);
	////	player.attack = pref.getInteger("attack", 4);
	///	player.attackspeed = pref.getInteger("attackspeed", 50);
	//	player.health = pref.getInteger("health", 320);
		//  }
		//  if(prefs != null){
				
				
			//	level = prefs.getInt("Level" , 1);
			//	if(level <1){
					level =1;
			//	}
			//Gdx.app.log("log: " , prefs.getString("init", "false"));
				player = new Player( 0 , 0 , this );
			 
				//player.armour = prefs.getInt("armour", 100);
				//player.attack = prefs.getInt("attack", 42);
				//player.attackspeed = prefs.getInt("attackspeed", 50);
				//player.health = prefs.getInt("health", 320);
				 // }
	this.setScreen(new LoadingScreen(this));
	

		
		
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1,1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
         
		 /* editor.putString("init", "true");
		  editor.putInt("Level", level - 1);
		  editor.putInt("armour" ,player.armour );
		  editor.putInt("health" ,player.health );
		  editor.putInt("attack" ,13 );
		  editor.putInt("attackspeed" ,player.attackspeed );
		  editor.commit();
		  
		  pref.putString("init", "true");
		  pref.putInteger("Level", level - 1);
		  pref.putInteger("armour" ,player.armour );
		  pref.putInteger("health" ,player.health );
		  pref.putInteger("attack" ,2 );
		  pref.putInteger("attackspeed" ,player.attackspeed );
		  pref.flush();
*/		 
		super.dispose();
		/*int gdine = file.readString().length();
		int dine = level - gdine;
		for(int i = 0; i < dine ; i ++){
			file.writeString("1",true);
		}*/
		
		/* pref.putInteger("level", level );
		  pref.flush();*/
		assets.dispose();
	}
	public void getPlayer(Player player){
		this.player  = player;
	}
	public void createlevel(int levelno){
		this.setScreen(new Handler(this, "level"+levelno+".tmx"));
		level++;
	}
}
