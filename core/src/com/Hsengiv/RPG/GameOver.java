package com.Hsengiv.RPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

public class GameOver implements Screen{
	  final Main main;
	  OrthographicCamera cam;
		SpriteBatch sb;	
		 TextButton button;
		    TextureAtlas atlas;
		    BitmapFont font;
		    TextButtonStyle style;
		    Skin skin;
		    Texture bg;
		    Stage stage;
		    StretchViewport vp;
		    Sound select = Gdx.audio.newSound(Gdx.files.internal("select.wav"));
	  public GameOver(final Main main){
		  this.main = main;
	  }
		@Override
		public void show() {
			bg = new Texture("gameover.png");
			cam = new OrthographicCamera(800 , 480);
			vp = new StretchViewport(800, 480 , cam);
			stage = new Stage(vp);
			sb = new SpriteBatch();
	        Gdx.input.setInputProcessor(stage);

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
		        		main.setScreen(new MenuScreen(main) );
		        		return super.touchDown(event, x, y, pointer, button);
		        	}
		        });
		        Gdx.input.setInputProcessor(stage);
			stage.addActor(button);
		}

		@Override
		public void render(float delta) {
	 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); 
			 
			 stage.getBatch().setProjectionMatrix(cam.combined);
			 stage.getBatch().begin();
			 stage.getBatch().draw(bg, 0 , 0 , 800 , 480 );
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
			sb.dispose();
			stage.dispose();
			font.dispose();
			skin.dispose();
			select.dispose();
			bg.dispose();
			atlas.dispose();
			
		}

	}
