package com.Hsengiv.RPG;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {
	   ParticleEffect effect;
Handler handler;
	   public ParticleEffectActor(ParticleEffect effect, Handler handler) {
	      this.effect = effect;
	      this.handler = handler;
	      
	   }


	   @Override
	public void draw(Batch batch, float parentAlpha) {
		   effect.draw(batch); //define behavior when stage calls Actor.draw()
		super.draw(batch, parentAlpha);
	}


	public void act(float delta) {
	      super.act(delta);
	  //    effect.setPosition(handler.player.x, handler.player.y); //set to whatever x/y you prefer
	      effect.getEmitters().first().setPosition(handler.player.x, handler.player.y);
	      effect.update(delta); //update it
	                effect.start(); //need to start the particle spawning
	   }

	   public ParticleEffect getEffect() {
	      return effect;
	   }
	}