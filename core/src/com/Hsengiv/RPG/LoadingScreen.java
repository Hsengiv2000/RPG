package com.Hsengiv.RPG;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;

public class LoadingScreen implements Screen {

    private final Main game;
    SpriteBatch sb;
    BitmapFont fnt;

    private ShapeRenderer shapeRenderer;
    private float progress;
    OrthographicCamera cam;

    public LoadingScreen(final Main game) {
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
        sb = new SpriteBatch();
        fnt = new BitmapFont();
        fnt.setColor(Color.RED);
    }

    private void queueAssets() {
        game.assets.load("ownsprite.png", Texture.class);
        game.assets.load("coolkiddialogue.png", Texture.class);
        game.assets.load("outerjoystick.png", Texture.class);
        game.assets.load("insidejoystick.png", Texture.class);
        game.assets.load("key.png", Texture.class);
        game.assets.load("coin.png", Texture.class);
        game.assets.load("box.png", Texture.class);
        game.assets.load("bgm.MP3", Music.class);
        game.assets.load("rain-01.mp3", Music.class);
        game.assets.load("clash.wav" , Sound.class);
        game.assets.load("open.wav" , Sound.class);
        game.assets.load("zoom.wav" , Sound.class);
        game.assets.load("movingblock.wav" , Sound.class);
        game.assets.load("levelup.wav" , Sound.class);
     
        game.assets.load("keycollecting.wav" , Sound.class);
      //  game.assets.load(".png", Texture.class);
      //  game.assets.load(".png", Texture.class);
      //  game.assets.load(".png", Texture.class);'
        game.assets.load("font.fnt", BitmapFont.class);
    	game.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    	game.assets.load("level0.tmx", TiledMap.class);
    	game.assets.load("testlevel1.tmx", TiledMap.class);
    	game.assets.load("testlevel2.tmx", TiledMap.class);
    	game.assets.load("testlevel3.tmx", TiledMap.class);
        game.assets.load("level1.tmx", TiledMap.class);
        game.assets.load("level2.tmx", TiledMap.class);
        game.assets.load("level3.tmx", TiledMap.class);
        game.assets.load("mainlevel1.tmx", TiledMap.class);
        game.assets.load("mainlevel2.tmx", TiledMap.class);
       // game.assets.load("mainlevel3.tmx", TiledMap.class);
    }

    @Override
    public void show() {
    	cam.update();
        System.out.println("LOADING");
        shapeRenderer.setProjectionMatrix(cam.combined);
        this.progress = 0f;
        queueAssets();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assets.getProgress(), .1f);
        if (game.assets.update() && progress >= game.assets.getProgress() - .001f) {
         game.createlevel(game.level);
            }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         System.out.println(progress);
        update(delta);
            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            fnt.draw(sb, "LOADING... " + progress * 100, 240, 16);
            sb.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.BLACK);
       // shapeRenderer.rect(32, 132,336, 16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, 480 / 2 - 8, progress * (800 - 64), 16);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
