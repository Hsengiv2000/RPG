package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MenuScreen implements Screen{
	   
	final Main game;
	Texture bg;
	OrthographicCamera cam;
	SpriteBatch sb;	
	 TextButton button , buttut;
	    TextureAtlas atlas , atlas2;
	    BitmapFont font;
	    TextButtonStyle style , style2;
	    Sound select;
	    Skin skin , skin2;
	 //   Music bgm;
	    FileHandle file;
	    
	Stage stage;
	
	public MenuScreen(final Main game){
		this.game =  game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,800 , 480 );
		cam.viewportHeight  = 480;
		cam.viewportWidth =800;
		cam.position.set( 0 ,   0 , 0);
		sb = new SpriteBatch();
		StretchViewport view = new StretchViewport(800, 480 ,cam);
		stage = new Stage(view);
		bg = new Texture(Gdx.files.internal("baground.png"));
		select = Gdx.audio.newSound(Gdx.files.internal("select.wav"));
		Gdx.input.setInputProcessor(stage);
		
	}
	@Override
	public void show() {
		 atlas = new TextureAtlas("play.pack");
	        skin = new Skin();
	        skin.addRegions(atlas);
	        style = new TextButtonStyle();
	        style.up = skin.getDrawable("play");
	        style.over = skin.getDrawable("playout");
	        style.down = skin.getDrawable("playout");
	        font = new BitmapFont(Gdx.files.internal("font.fnt"));
	       
	        style.font = font;
	        button = new TextButton("" , style);
	        button.setSize(96, 48);
	        button.setPosition(400, 192);
	        button.addListener(new InputListener(){
	        	@Override
	        	public boolean touchDown(InputEvent event, float x, float y,
	        			int pointer, int button) {
	        		select.play();
	        		game.createlevel(game.level);
	        		return super.touchDown(event, x, y, pointer, button);
	        	}
	        });
	        atlas = new TextureAtlas("outer/tutbut.pack");
	        skin = new Skin();
	        skin.addRegions(atlas);
	        style = new TextButtonStyle();
	        style.up = skin.getDrawable("tutbut");
	        style.over = skin.getDrawable("tutbut");
	        style.down = skin.getDrawable("tutbut");
	        font = new BitmapFont(Gdx.files.internal("font.fnt"));
	       font.setColor(Color.BLACK);
	        style.font = font;
	        /*buttut = new TextButton("" , style);
	        buttut.setSize(96, 48);
	        buttut.setPosition(400, 240);
	        buttut.addListener(new InputListener(){
	        	@Override
	        	public boolean touchDown(InputEvent event, float x, float y,
	        			int pointer, int button) {
	        		game.setScreen(new TutorialScreen(game) );
	        		select.play();
	        		return super.touchDown(event, x, y, pointer, button);
	        	}
	        });
	       
	        stage.addActor(buttut);*/
	        
	        stage.addActor(button);
	}

	@Override
	public void render(float delta) {

		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 
		 
		 stage.getBatch().setProjectionMatrix(cam.combined);
		 stage.getBatch().begin();
		 stage.getBatch().draw(bg, 0 , 0 , 800 , 480 );
		 /*if(Contacter.LEVEL > Contacter.nooflevels){
		 font.draw(stage.getBatch(), "Sorry, more levels coming soon! play again? ",  0,  192);
		// Contacter.LEVEL = 1;
		 }
		 else if(Contacter.LEVEL <= Contacter.nooflevels){
			 font.draw(stage.getBatch(), "Proceed to level: " + Contacter.LEVEL,  0,  192);
			 }*/
		 stage.getBatch().end();
		 
		 stage.act(delta);
		 stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();
		atlas2.dispose();
		font.dispose();
		select.dispose();
		skin2.dispose();
		
		
	}

}
