package com.Hsengiv.RPG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Handler implements Screen {
	final Main main;
	OrthographicCamera cam;

	ShapeRenderer sr = new ShapeRenderer();
	OrthogonalTiledMapRenderer tmr;
	TextButtonStyle style;
	ParticleEffect p;
	Animes sparks;
	ParticleEffectActor PEA;
   Array<Rectangle>logbox ;
   Gamestick left, top , right, down;
   Array<Dialogue>logs;
	int visible1 = 0,visible2 = 0,visible3 = 0, visiblel1 = 0;;
	TiledMap map;
	Array<Stains >stains = new Array<Stains>();
	public static  Touchpad touchpad;
	 private TouchpadStyle touchpadStyle;
	 int s1 = 6436, s2 =6466 , s3 = 363, a1 = 352 , a2 = 45 , a3 = 803 , sl1 = 45 , al1 = 245;
	    private Skin touchpadSkin;
	    Array<Rain>drops;
	    float can;
	    private Drawable touchBackground;
	    int weapono = 0;
	    private Drawable touchKnob;
	    Texture bg;
	   public static boolean resume= false;
	    BitmapFont font;
	    Texture knob;
	    HUD hud;
	    Trigger trigger;
	    boolean touchdown = false;
	    //Dialog log;
	    Array<Life>health;
	    Array<Spike>spikes1,spikes2,spikes3;
	    Array<Laser>laser;
	    int attackdelta = 0;
	    Texture box;
	    Skin dialogskin;
	    int keysno = 0;
	    Button pan, see, equip , data;
	    int tx, ty;
	    Array<box>boxes;
	    Player player;
	   
	    box []boxss;
	    Array<Key>keys;
	    Dialogue log, levellog;
	    Sound clash , collect , move, levelup , zoom,open;
	    String level;
	    Array<Switch>switches1,switches2,switches3, laserswitch;
	    Array<Entity>entities;
	    Array<Weapons>weapons;
	    Array<AnimatedTile>ctiles;
	
	Cell cell;
	Stage stage;
	boolean stepping1 = false, stepping2 = false,stepping3 = false;
	Music BGM,rain;
	StretchViewport view;
	
	ArrayList<Rectangle>bounds;
	SpriteBatch sb;
	float delta = 0;
public Handler (final Main main, String level){
	this.main = main;
	this.level = level;
	player = main.player;
	
}
@Override
public void show() {
	cam = new OrthographicCamera();
 spikes1 = new Array<Spike>();
 spikes2 = new Array<Spike>();
 spikes3 = new Array<Spike>();
 laser = new Array<Laser>();
	drops = new Array<Rain>();
	ctiles = new Array<AnimatedTile>();
	font = main.assets.get("font.fnt", BitmapFont.class);
	cam.setToOrtho(false, 800,480);
	hud = new HUD(this);
	bg = main.assets.get("outerjoystick.png", Texture.class);;
	knob = main.assets.get("insidejoystick.png", Texture.class);
	cam.position.set(278,364, 0);
	map = main.assets.get(level, TiledMap.class);
	tmr = new OrthogonalTiledMapRenderer(map);
	tmr.setView(cam);
	box = main.assets.get("coolkiddialogue.png");

	view = new StretchViewport(800, 480 , cam);
	stage = new Stage(view);
	 touchpadSkin = new Skin();
     //Set background image
     touchpadSkin.add("touchBackground", bg);
     //Set knob image
     touchpadSkin.add("touchKnob", knob);
     //Create TouchPad Style
     touchpadStyle = new TouchpadStyle();
     //Create Drawable's from TouchPad skin
     touchBackground = touchpadSkin.getDrawable("touchBackground");
     touchKnob = touchpadSkin.getDrawable("touchKnob");
     //Apply the Drawables to the TouchPad Style
     touchpadStyle.background = touchBackground;
     touchpadStyle.knob = touchKnob;
     //Create new TouchPad with the created style
     touchpad = new Touchpad(6, touchpadStyle);
     //setBounds(x,y,width,height)
     touchpad.setBounds(6,6,100, 100);
     
     touchpad.setPosition(0 - 370, 0 - 210);
   /* top = new Gamestick(48 , 48 , "topstick.png" , this);
    down = new Gamestick(48 , 16 , "downstick.png" , this);
    left= new Gamestick(16, 32 , "leftstick.png" , this);
    right = new Gamestick(80 , 32 , "rightstick.png" , this);*/
  health = new Array<Life>();
  logbox = new Array<Rectangle>();
  logs = new Array<Dialogue>();
	
  /*if(Gdx.input.isKeyPressed(Keys.A)){
		cam.position.x -= 10;
	}
	
	if(Gdx.input.isKeyPressed(Keys.S)){
		cam.position.y -= 10;
	}
	if(Gdx.input.isKeyPressed(Keys.D)){
		cam.position.x += 10;
	}
	if(Gdx.input.isKeyPressed(Keys.W)){
		cam.position.y += 10;
	}*/
    
     
   //  style.font = font;
	sb = new SpriteBatch();
	MapLayer playerlayer = map.getLayers().get("player");
	{
		MapObjects ob = playerlayer.getObjects();
		Iterator<MapObject> objectIt = ob.iterator();
			while(objectIt.hasNext()){
				MapObject obj = objectIt.next();
				if(obj instanceof RectangleMapObject){
					float posx = ((RectangleMapObject) obj).getRectangle().x;
					float posy = ((RectangleMapObject) obj).getRectangle().y;
					
					player.x = posx;
					player.y = posy;
					player.handler = this;
				}
			}
		
	}
	
	switches1 = new Array<Switch>();
	switches2 = new Array<Switch>();
	switches3 = new Array<Switch>();
	laserswitch = new Array<Switch>();
	entities = new Array<Entity>();
	weapons = new Array<Weapons>();
	log = new Dialogue("you got a key!", player);
	levellog = new Dialogue("Please collect all the keys to progress", player);
	bounds = new ArrayList<Rectangle>();
	if(map.getLayers().get("collijon")!=null){
	for (int i = 0; i < 50; i++) {
		for (int j = 0; j < 50; j++) {
			TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("collijon");
			 cell = new Cell();
			if(cur.getCell(i ,  j)!= null){
				cell = cur.getCell(i , j);
			 bounds.add(new Rectangle((i * 48)  , (j *48 ) , 48 ,48 ));
				
			}}
			keys = new Array<Key>();
			if(map.getLayers().get("Key")!=null){
			MapLayer layer = map.getLayers().get("Key");
			{
				MapObjects ob = layer.getObjects();
				Iterator<MapObject> objectIt = ob.iterator();
					while(objectIt.hasNext()){
						MapObject obj = objectIt.next();
						if(obj instanceof RectangleMapObject){
							
							keys.add(new Key(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y, main)); 
							
						}
					}
				
			}}
		}
		
	}
	if(map.getLayers().get("water")!=null){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("water");
				 cell = new Cell();
				if(cur.getCell(i ,  j)!= null){
					cell = cur.getCell(i , j);
				 bounds.add(new Rectangle((i * 48)  , (j *48 ) , 48 ,48 ));
				 if(map.getLayers().get("rain")!=null){
					 AnimatedTile ctile = new AnimatedTile("ripple" , (i * 48) , (j *48));
					 ctile.rand = 30;
					 ctile.rand += new Random().nextInt(50);
					 ctile.rand2  = ctile.rand * 2;
					ctiles.add(ctile);
					stage.addActor(ctile);
				}
				 if(map.getLayers().get("rain")==null){
					 AnimatedTile ctile = new AnimatedTile("water2" , (i * 48) , (j *48));
					 ctile.rand = 50;
					 ctile.rand2  = ctile.rand * 2;
						ctiles.add(ctile);
						stage.addActor(ctile);
						
					}
				 }}
		}}
	if(map.getLayers().get("Entity")!=null){
		MapLayer layer = map.getLayers().get("Entity");
		{
			MapObjects ob = layer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Entity entity = new Entity(obj.getProperties().get("loc").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y,obj.getProperties().get("width").toString(),obj.getProperties().get("height").toString() ,"efw" , this, obj.getProperties().get("attack").toString(),obj.getProperties().get("speed").toString(),obj.getProperties().get("armour").toString() , obj.getProperties().get("col").toString(), obj.getProperties().get("row").toString() , obj.getProperties().get("side").toString(),obj.getProperties().get("attackspeed").toString()) ;
						entities.add(new Entity(obj.getProperties().get("loc").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y,obj.getProperties().get("width").toString(),obj.getProperties().get("height").toString() ,"rg" , this, obj.getProperties().get("attack").toString(),obj.getProperties().get("speed").toString(),obj.getProperties().get("armour").toString(), obj.getProperties().get("col").toString(), obj.getProperties().get("row").toString() , obj.getProperties().get("side").toString() , obj.getProperties().get("attackspeed").toString())); 
						health.add(new Life(entity.x , entity.y + 20));
						
					}
				}
			
		}}
	
	if(map.getLayers().get("Weapons")!=null){
		MapLayer layer = map.getLayers().get("Weapons");
		{
			MapObjects ob = layer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
					//	Weapons weapon = new Weapons(obj.getProperties().get("loc").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y,obj.getProperties().get("width").toString(),obj.getProperties().get("height").toString()  , this, obj.getProperties().get("attack").toString(),obj.getProperties().get("armour").toString() , obj.getProperties().get("attackspeed").toString(), obj.getProperties().get("health").toString()) ;
						weapons.add(new Weapons(obj.getProperties().get("loc").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y,obj.getProperties().get("width").toString(),obj.getProperties().get("height").toString()  , this, obj.getProperties().get("attack").toString(),obj.getProperties().get("armour").toString() , obj.getProperties().get("attackspeed").toString(), obj.getProperties().get("health").toString()));
						//health.add(new Life(weapon.x , weapon.y + 20));
						
					}
				}
			
		}}
	
	if(map.getLayers().get("Dialogue")!=null){
		MapLayer layer = map.getLayers().get("Dialogue");
		{
			MapObjects ob = layer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Dialogue templog = new Dialogue(obj.getProperties().get("dialog").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y);
						logs.add(templog);
						stage.addActor(templog);
						logbox.add(new Rectangle(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , 48 , 48));
						//health.add(new Life(weapon.x , weapon.y + 20));
						
					}
				}
			
		}}
	
	
if(map.getLayers().get("spikes1")!=null){
	for (int i = 0; i < 50; i++) {
		for (int j = 0; j < 50; j++) {
			TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("spikes1");
			 cell = new Cell();
			if(cur.getCell(i ,  j)!= null){
				cell = cur.getCell(i , j);
			 spikes1.add(new Spike((i * 48)  , (j *48 ) ));
				
			}
		}
	}
	}
	
	if(map.getLayers().get("laserbroad")!=null){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("laserbroad");
				 cell = new Cell();
				if(cur.getCell(i ,  j)!= null){
					cell = cur.getCell(i , j);
				 laser.add(new Laser((i * 48)  , (j *48 ) , "broad" ));
					
				}
			}
		}
		}
	if(map.getLayers().get("lasertall")!=null){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("lasertall");
				 cell = new Cell();
				if(cur.getCell(i ,  j)!= null){
					cell = cur.getCell(i , j);
				 laser.add(new Laser((i * 48)  , (j *48 ) , "tall" ));
					
				}
			}
		}
		}
	if(map.getLayers().get("spikes2")!=null){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("spikes2");
				 cell = new Cell();
				if(cur.getCell(i ,  j)!= null){
					cell = cur.getCell(i , j);
				 spikes2.add(new Spike((i * 48)  , (j *48 ) ));
					
				}
			}
		}
		}
	if(map.getLayers().get("spikes3")!=null){
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get("spikes3");
				 cell = new Cell();
				if(cur.getCell(i ,  j)!= null){
					cell = cur.getCell(i , j);
				 spikes3.add(new Spike((i * 48)  , (j *48 ) ));
					
				}
			}
		}
		}
	if(map.getLayers().get("switch1")!=null){
	MapLayer switchlayer = map.getLayers().get("switch1");
	{
		MapObjects ob = switchlayer.getObjects();
		Iterator<MapObject> objectIt = ob.iterator();
			while(objectIt.hasNext()){
				MapObject obj = objectIt.next();
				if(obj instanceof RectangleMapObject){
					Switch switchy = new Switch(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , "switch.png");
					switches1.add(switchy); 
					stage.addActor(switchy);
				}
			}
	}
	}
	if(map.getLayers().get("laserswitch")!=null){
		MapLayer switchlayer = map.getLayers().get("laserswitch");
		{
			MapObjects ob = switchlayer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Switch switchy = new Switch(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , "laserswitch.png");
						laserswitch.add(switchy); 
						stage.addActor(switchy);
					}
				}
		}}
	if(map.getLayers().get("switch2")!=null){
		MapLayer switchlayer = map.getLayers().get("switch2");
		{
			MapObjects ob = switchlayer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Switch switchy = new Switch(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , "switch.png");
						switches2.add(switchy); 
						stage.addActor(switchy);
					}
				}
		}
		}
	if(map.getLayers().get("switch3")!=null){
		MapLayer switchlayer = map.getLayers().get("switch3");
		{
			MapObjects ob = switchlayer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Switch switchy = new Switch(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , "switch.png");
						switches3.add(switchy); 
						stage.addActor(switchy);
					}
				}
		}
		}


	boxes = new Array<box>();
	if(map.getLayers().get("boxes")!=null){
	MapLayer layer = map.getLayers().get("boxes");
	{
		MapObjects ob = layer.getObjects();
		Iterator<MapObject> objectIt = ob.iterator();
			while(objectIt.hasNext()){
				MapObject obj = objectIt.next();
				if(obj instanceof RectangleMapObject){
					boxes.add(new box(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y, main)); 
				}
			}}
		
	}
	Random rand = new Random();
	if(map.getLayers().get("rain") != null){
		rain = main.assets.get("rain-01.mp3" , Music.class);
		rain.play();
		rain.setLooping( true);
for(int i = 0; i < 30; i++){
	drops.add(new Rain(rand.nextInt(800) , 0, rand.nextInt(4) + 2));
}}


	
	/*dialogskin = new Skin(Gdx.files.internal("uiskin.json"));
	dialogskin.add("BOX",box);
	log = new Dialog("confirm exit", dialogskin);

	
			log.text("rly exit");
			log.button("yes", "goodbye");
			log.button("no", "glad you stay");
		

		@Override
		protected void result(final Object object) {
			new Dialog("", dialogskin) {

				{
					text(object.toString());
					button("OK");
				}

			}.show(stage);
		}

	log.show(stage);*/
  
	
	if(map.getLayers().get("entrance")!=null){
	MapLayer extrancelayer = map.getLayers().get("entrance");
	{
		MapObjects ob = extrancelayer.getObjects();
		Iterator<MapObject> objectIt = ob.iterator();
			while(objectIt.hasNext()){
				MapObject obj = objectIt.next();
				if(obj instanceof RectangleMapObject){
					float posx = ((RectangleMapObject) obj).getRectangle().x;
					float posy = ((RectangleMapObject) obj).getRectangle().y;
					trigger = new Trigger(posx , posy,  "mainlevel"+main.level+".tmx"); 
				}
			}
		
	}}
	sparks = new Animes(8 , 4);
	for(Laser lase : laser){
		stage.addActor(lase);
	}
	
	for(Spike spike : spikes1){
		stage.addActor(spike);
	}
	for(Spike spike : spikes2){
		stage.addActor(spike);
	}
	for(Spike spike : spikes3){
		stage.addActor(spike);
	}
	
	for(Key key : keys){
	stage.addActor(key);
	}
	for(Weapons weapon : weapons){
		stage.addActor(weapon);
		}
	for(Life bar : health){
		stage.addActor(bar);
		
		}
	for(Entity entity : entities){
		stage.addActor(entity);
	
		}
	for(box boxy : boxes){
		stage.addActor(boxy);
	}
	
	// p = new ParticleEffect();
	//p.load(Gdx.files.internal("Particle"), Gdx.files.internal("")); //files.internal loads from the "assets" folder

//PEA = new ParticleEffectActor(p, this);
//PEA.setVisible(false);
	stage.addActor(log);
	stage.addActor(levellog);
	stage.addActor(player);
	stage.addActor(sparks);
	sparks.setVisible(false);
	//stage.addActor(PEA);
	stage.addActor(player.log);
	
	see = new Button(cam.position.x +300 , cam.position.y  +120, "see.png");
	
	//see.setVisible(true);
	see.setVisible(false);
	see.setTouchable(Touchable.disabled);
	see.addListener(new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	zoom.play();
           
           weapons.get(weapono).info.setVisible(true);
           weapons.get(weapono).info.display = true;
           
            //touchpad.setPosition(0 - 900, 0 - 360);
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	weapons.get(weapono).info.display = false;
        	zoom.play();
             
            ;
           
            
        }
    });
    stage.addActor(see);
    
data = new Button(cam.position.x -200, cam.position.y  +120, "idacrd.png" , 48 , 32);
	
	//see.setVisible(true);
	data.setVisible(true);
	data.setTouchable(Touchable.enabled);
	data.addListener(new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	zoom.play();
           
       player.log.setVisible(true);
        player.log.display = true;
           
            //touchpad.setPosition(0 - 900, 0 - 360);
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	player.log.display = false;
        	zoom.play();
             
            ;
           
            
        }
    });
    stage.addActor(data);
    
equip = new Button(cam.position.x +300 , cam.position.y  +120, "equip.png");
	
	//see.setVisible(true);
	equip.setVisible(false);
	equip.setTouchable(Touchable.disabled);
	equip.addListener(new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	zoom.play();
        	player.attack += weapons.get(weapono).attack;
        	player.attackspeed -= weapons.get(weapono).attackspeed;
        	player.armour += weapons.get(weapono).armour;
           stage.getActors().removeValue((weapons.get(weapono)), true);
           weapons.removeIndex(weapono);
          
           weapono = 0;
          equip.setVisible(false);
          equip.setTouchable(Touchable.disabled);
          see.setVisible(false);
          see.setTouchable(Touchable.disabled);
            //touchpad.setPosition(0 - 900, 0 - 360);
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	
        	zoom.play();
        	
             
            ;
           
            
        }
    });
    stage.addActor(equip);
	 pan = new Button(cam.position.x + 300, cam.position.y + 500,"zoom.png");
   pan.setTouchable(Touchable.enabled) ;
    pan.addListener(new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	zoom.play();
            cam.viewportWidth = 1280; //This method is not getting triggered becaused the call never comes in this function.
            cam.viewportHeight  = 720;
            pan.setVisible(false);
            //touchpad.setPosition(0 - 900, 0 - 360);
            return true;
        }
        @Override
        public void touchUp(InputEvent event, float x, float y,
                                 int pointer, int button)
        {
        	zoom.play();
            cam.viewportWidth = 800; //This method is not getting triggered becaused the call never comes in this function.
            cam.viewportHeight = 480;
            pan.setVisible(true);
           
            
        }
    });
    stage.addActor(pan);
    if(map.getLayers().get("Dialogue")!=null){
		MapLayer layer = map.getLayers().get("Dialogue");
		{
			MapObjects ob = layer.getObjects();
			Iterator<MapObject> objectIt = ob.iterator();
				while(objectIt.hasNext()){
					MapObject obj = objectIt.next();
					if(obj instanceof RectangleMapObject){
						Dialogue templog = new Dialogue(obj.getProperties().get("dialog").toString(),((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y);
						logs.add(templog);
						stage.addActor(templog);
						logbox.add(new Rectangle(((RectangleMapObject) obj).getRectangle().x, ((RectangleMapObject) obj).getRectangle().y , 48 , 48));
						//health.add(new Life(weapon.x , weapon.y + 20));
						
					}
				}
			
		}}
    clash = main.assets.get("clash.wav" , Sound.class);
	
    zoom =  main.assets.get("zoom.wav" , Sound.class);
    open =  main.assets.get("open.wav" , Sound.class);
    move =main.assets.get("movingblock.wav" , Sound.class);
    levelup =main.assets.get("levelup.wav" , Sound.class);
    collect = main.assets.get("keycollecting.wav" , Sound.class);
	Gdx.input.setInputProcessor(stage);
BGM = main.assets.get("bgm.MP3" , Music.class);
stage.addActor(touchpad);
/*top.setTouchable(Touchable.enabled);
left.setTouchable(Touchable.enabled);
right.setTouchable(Touchable.enabled);
down.setTouchable(Touchable.enabled);
right.addListener(new InputListener()
{
    @Override
    public boolean touchDown(InputEvent event, float x, float y,
                             int pointer, int button)
    {
    	player.x += player.dx;
        //touchpad.setPosition(0 - 900, 0 - 360);
        return true;
    }
    @Override
    public void touchUp(InputEvent event, float x, float y,
                             int pointer, int button)
    {
    	
       
        
    }
});
stage.addActor(top);
stage.addActor(down);
stage.addActor(left);
stage.addActor(right);*/
//BGM.play();
//BGM.setLooping(true);
}
public void reseyes(){
	cam.viewportHeight =1000;
	cam.viewportWidth = 1000;
}
@Override
public void render(float delta) {
	
	updatesteps();
	keysno = keys.size;
	Gdx.gl.glClearColor(0,0,0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	/*for(int i = 0; i < 9; i ++){
	if(Gdx.input.isTouched(i)){
		if(Gdx.input.getX(i) == cam.position.x - 400 && Gdx.input.getY(i) == cam.position.y + 200){
		cam.viewportHeight = 1000;
		cam.viewportWidth  = 1000;
		System.out.println(cam.position.x + " "+cam.position.y);
		}
		
	}}
*/	
if(player.health <0){
	main.setScreen(new GameOver(main));
}
	cam.position.x = Math.min(Math.max(player.x, 400) , 2400 - 400);
	cam.position.y = Math.min(Math.max(player.y, 240) , 30*48 - 240);
	/*top.setPosition(cam.position.x +80 , 48 );
    down.setPosition(80 , 16);
    left.setPosition(60, 32 );
    right.setPosition(100 , 32 );*/
	for(int i = 0; i < bounds.size(); i++){
    	if(player.leftbounds.overlaps(bounds.get(i))){
    		clash.play();
    		
    	 player.x +=2;
    	 
    	}
    	if(player.rightbounds.overlaps(bounds.get(i))){
    		clash.play();
       	 player.x -=2;
       	 
       	}
    	if(player.upbounds.overlaps(bounds.get(i))){
    		clash.play();
       	 player.y -=2;
       	 
       	}
    	if(player.downbounds.overlaps(bounds.get(i))){
    		clash.play();
       	 player.y +=2;
       	 
       	}}
	createspikes1();
	for(int i = 0; i < bounds.size(); i++){
		for(int j = 0; j < boxes.size; j++){
    	if(boxes.get(j).leftbounds.overlaps(bounds.get(i))){
    		clash.play();
    		boxes.get(j).x +=2;
    	 
    	}
    	if(boxes.get(j).rightbounds.overlaps(bounds.get(i))){
    		clash.play();
    		boxes.get(j).x -=2;
       	 
       	}
    	if(boxes.get(j).upbounds.overlaps(bounds.get(i))){
    		clash.play();
    		boxes.get(j).y -=2;
       	 
       	}
    	if(boxes.get(j).downbounds.overlaps(bounds.get(i))){
    		clash.play();
    		boxes.get(j).y +=2;
       	 
       	}}}
	//////////////////////////
/////////////////////////////
	createspikes2();
	createLaser1();
createspikes3();
	for(int i = 0; i < keys.size; i++){
		Key key = keys.get(i);
		if(player.bounds.overlaps(keys.get(i).bounds)){
			collect.play();
			stage.getActors().removeValue(key, true);
			keys.removeIndex(i);
			keysno--;
		log.display = true;
		
		
		
		
			
			
			
		}
	}
	///NOTE: ENTRANCE LAYER IS NECCESARY FOR EACH MAP
	if(log.display == true){
		this.delta+= delta;
				if(this.delta > 3){
						this.delta =0;
						log.display = false;

		}
		
	}
if(player.bounds.overlaps(trigger.bounds) && keysno == 0){
		levelup.play();
		main.createlevel(main.level);if(rain != null){
		rain.dispose();}
	}
	if(player.bounds.overlaps(trigger.bounds) && keysno != 0){
		levellog.display = true;
	}
	if(levellog.display == true){
		this.delta+= delta;
		//System.out.println(this.delta);
		if(this.delta > 3){
						this.delta =0;
						levellog.display = false;

		}
		
	}
	for(int i = 0; i < boxes.size; i++){
		box boxy = boxes.get(i);
		if(player.bounds.overlaps(boxes.get(i).bounds)){
			
			move.play();
		if(player.x > boxes.get(i).x+24){boxes.get(i).x -=2;}
			else if(player.x  < boxes.get(i).x- 24){boxes.get(i).x +=2;}
			if(player.y > boxes.get(i).y +24){boxes.get(i).y -=2;}
			else if(player.y -36< boxes.get(i).y- 24){boxes.get(i).y +=2;}
			
		
		
	/*	if(player.leftbounds.overlaps(boxes.get(i).bounds)){
			boxes.get(i).x -=2;
		}
		if(player.rightbounds.overlaps(boxes.get(i).bounds)){
			boxes.get(i).x +=2;
		}
		if(player.upbounds.overlaps(boxes.get(i).bounds)){
			boxes.get(i).y +=2;
		}
		if(player.downbounds.overlaps(boxes.get(i).bounds)){
			boxes.get(i).y -=2;
		}*/
		
		
			
			
			
		}
	}
	for(int i =0; i < entities.size; i ++){
		health.get(i).x =entities.get(i).x;
		health.get(i).y =entities.get(i).y +20;
	}
	for(int i =0; i < entities.size; i ++){

		int counter = 0;
		if(player.bounds.overlaps(entities.get(i).bounds)){
		//	PEA.setVisible(true);
	//		entities.get(i).width *=1/2;//	(entities.get(i).damagetaken / entities.get(i).xhealth) ;
			sparks.setVisible(true);
			sparks.update(entities.get(i).x, entities.get(i).y);
		attackdelta++;
		entities.get(i).attackdelta ++;
	
		
		if(attackdelta > player.attackspeed){
		   
			health.get(i).life -= player.attack / entities.get(i).armour ;
			entities.get(i).damagetaken -= player.attack / entities.get(i).armour ;
		counter  +=1;
			attackdelta = 0;
		}
		if(counter > 3){
			stage.addActor(new Stains(player.x , player.y , "bloodstain.png"));
			
		}
		
		if(entities.get(i).attackdelta > entities.get(i).attackspeed){
			player.health -= entities.get(i).attack  / player.armour;
			entities.get(i).speed -= 0.02;
			entities.get(i).attackdelta  = 0;
			entities.get(i).width = entities.get(i).xwidth;
			entities.get(i).height = entities.get(i).xheight;
		}
	}
		if(!player.bounds.overlaps(entities.get(i).bounds)){
			sparks.setVisible(false);
			//PEA.setVisible(false);
		}
		
		
	}
	
	for(int i =0; i < weapons.size; i ++){
		
		if(player.bounds.overlaps(weapons.get(i).bounds)){
		see.setVisible(true);
		see.setTouchable(Touchable.enabled);
		
		equip.setVisible(true);
		equip.setTouchable(Touchable.enabled);
		weapono = i;
		}
		else if(!player.bounds.overlaps(weapons.get(i).bounds)){
			see.setVisible(false);
			see.setTouchable(Touchable.disabled);
			equip.setVisible(false);
			equip.setTouchable(Touchable.disabled);
			weapono = 0;
		}
			
	}
for(int i =0; i < logbox.size; i ++){
		
		if(player.bounds.overlaps(logbox.get(i))){
		logs.get(i).display = true;;
		}
		/*if(logs.get(i).display == true){
			this.delta+= delta;
		
			if(this.delta > 3){
							this.delta =0;
							logs.get(i).display = false;

			}
			
		}*/
		if(!player.bounds.overlaps(logbox.get(i))){
			logs.get(i).display = false;;
			}
}

	for(int i =0; i < entities.size; i ++){
		if(health.get(i).life < 0){
			stage.getActors().removeValue(entities.get(i),true);
		entities.removeIndex(i);
		stage.getActors().removeValue(health.get(i),true);
		health.removeIndex(i);
		//	PEA.setVisible(false);
			
		sparks.setVisible(false);
		};
		
	}
	System.out.println("Attack : " + player.attack);
	System.out.println("AttackSpeed : " + player.attackspeed);
	System.out.println("armour : " + player.armour);
	System.out.println("health : " + player.health);
	
	/*	for(int i = 0; i < boxes.size; i++){
		
		//if( player.rightbounds.overlaps(boxes.get(i).leftbounds)  ){
	//	boxes.get(i).x+=2;
	//	}
		if(player.rightbounds.x + player.rightbounds.width == boxes.get(i).leftbounds.x && player.rightbounds.y <= boxes.get(i).leftbounds.y + boxes.get(i).leftbounds.height+3 && player.rightbounds.y >= boxes.get(i).leftbounds.y - 3){
			boxes.get(i).x+=2;
		}
			if(player.leftbounds.x  == boxes.get(i).rightbounds.x +boxes.get(i).rightbounds.width && player.leftbounds.y <= boxes.get(i).rightbounds.y + boxes.get(i).rightbounds.height+3 && player.leftbounds.y >= boxes.get(i).rightbounds.y - 3){
			boxes.get(i).x-=2;
		}
		if(player.upbounds.y + player.upbounds.height == boxes.get(i).downbounds.y && player.upbounds.x <= boxes.get(i).downbounds.x + boxes.get(i).downbounds.width +3 && player.upbounds.x >= boxes.get(i).downbounds.x - 3){
			boxes.get(i).y+=2;
		}
				if(player.downbounds.y  == boxes.get(i).upbounds.y +boxes.get(i).upbounds.height && player.downbounds.x <= boxes.get(i).upbounds.x + boxes.get(i).upbounds.width +3 && player.downbounds.x >= boxes.get(i).upbounds.x - 3){
			boxes.get(i).y-=2;
		}
		
		if(player.leftbounds.x  == boxes.get(i).rightbounds.x +boxes.get(i).rightbounds.width && player.leftbounds.y <= boxes.get(i).rightbounds.y + boxes.get(i).rightbounds.height+3 && player.leftbounds.y >= boxes.get(i).rightbounds.y - 3 && player.downbounds.y  == boxes.get(i).upbounds.y +boxes.get(i).upbounds.height && player.downbounds.x <= boxes.get(i).upbounds.x + boxes.get(i).upbounds.width +3 && player.downbounds.x >= boxes.get(i).upbounds.x - 3){
			boxes.get(i).y-=2;
			boxes.get(i).x-=2;
		}
		if(player.leftbounds.x  == boxes.get(i).rightbounds.x +boxes.get(i).rightbounds.width && player.leftbounds.y <= boxes.get(i).rightbounds.y + boxes.get(i).rightbounds.height+3 && player.leftbounds.y >= boxes.get(i).rightbounds.y - 3 && player.upbounds.y + player.upbounds.height == boxes.get(i).downbounds.y && player.upbounds.x <= boxes.get(i).downbounds.x + boxes.get(i).downbounds.width +3 && player.upbounds.x >= boxes.get(i).downbounds.x - 3){
			boxes.get(i).x-=2;
			boxes.get(i).y+=2;
		}
		if(player.rightbounds.x + player.rightbounds.width == boxes.get(i).leftbounds.x && player.rightbounds.y <= boxes.get(i).leftbounds.y + boxes.get(i).leftbounds.height+3 && player.rightbounds.y >= boxes.get(i).leftbounds.y - 3 && player.upbounds.y + player.upbounds.height == boxes.get(i).downbounds.y && player.upbounds.x <= boxes.get(i).downbounds.x + boxes.get(i).downbounds.width +3 && player.upbounds.x >= boxes.get(i).downbounds.x - 3){
			boxes.get(i).x+=2;
			boxes.get(i).y+=2;
		}
		if(player.rightbounds.x + player.rightbounds.width == boxes.get(i).leftbounds.x && player.rightbounds.y <= boxes.get(i).leftbounds.y + boxes.get(i).leftbounds.height+3 && player.rightbounds.y >= boxes.get(i).leftbounds.y - 3 &&player.downbounds.y  == boxes.get(i).upbounds.y +boxes.get(i).upbounds.height && player.downbounds.x <= boxes.get(i).upbounds.x + boxes.get(i).upbounds.width +3 && player.downbounds.x >= boxes.get(i).upbounds.x - 3 ){
			boxes.get(i).x+=2;
			boxes.get(i).y-=2;
		}
		}
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		else	if(player.leftbounds.overlaps(boxes.get(i).rightbounds)   ){
			boxes.get(i).x-=2;
		}
		
		
		
		
		
		
		
		
	
		
		if(player.upbounds.overlaps(boxes.get(i).downbounds) ){
		boxes.get(i).y+=2;
		}
		else	if(player.downbounds.overlaps(boxes.get(i).upbounds) ){
			boxes.get(i).y-=2;
		}*/
	
	
	//}
	for(int i = 0; i < ctiles.size; i ++){
		ctiles.get(i).change ++;
		
	}
	pan.x = cam.position.x + 300;
	pan.y = cam.position.y +200;
	see.x = cam.position.x + 300;
	see.y = cam.position.y +100;
	data.x = cam.position.x +200;
	data.y = cam.position.y +200;
	equip.x = cam.position.x + 300;
	equip.y = cam.position.y +150;
	cam.update();
//	System.out.println(player.x+" "+ player.y);
	tmr.setView(cam);
	tmr.render();
	touchpad.setPosition(cam.position.x  - 365, cam.position.y - 210);
/*	top.setPosition(cam.position.x +80 , 48 );
    down.setPosition(80 , 16);
    left.setPosition(60, 32 );
    right.setPosition(cam.position.x  - 365, cam.position.y - 210 );*/
	stage.act(delta);
	stage.draw();
	//System.out.println(visible1 + " " + visible2 + " " + visible3);
	hud.draw(keysno);
	sr.begin(ShapeType.Line);
	sr.setProjectionMatrix(cam.combined);
	for(int i = 0; i < boxes.size; i++){
	//	sr.rect(boxes.get(i).leftbounds.x, boxes.get(i).leftbounds.y, boxes.get(i).leftbounds.width, boxes.get(i).leftbounds.height);
	//sr.rect(boxes.get(i).upbounds.x, boxes.get(i).upbounds.y, boxes.get(i).upbounds.width, boxes.get(i).upbounds.height);
	//	sr.rect( boxes.get(i).downbounds.x, boxes.get(i).downbounds.y, boxes.get(i).downbounds.width, boxes.get(i).downbounds.height);
	//	sr.rect(boxes.get(i).rightbounds.x, boxes.get(i).rightbounds.y, boxes.get(i).rightbounds.width, boxes.get(i).rightbounds.height);
	//	sr.rect(boxes.get(i).ibounds.x, boxes.get(i).ibounds.y, boxes.get(i).ibounds.width, boxes.get(i).ibounds.height);
	}
	//sr.rect(player.leftbounds.x, player.leftbounds.y, player.leftbounds.width, player.leftbounds.height);
	//sr.rect(player.upbounds.x, player.upbounds.y, player.upbounds.width, player.upbounds.height);
	//sr.rect( player.downbounds.x, player.downbounds.y, player.downbounds.width, player.downbounds.height);
	//sr.rect(player.rightbounds.x, player.rightbounds.y, player.rightbounds.width, player.rightbounds.height);
	//sr.rect(player.ibounds.x , player.ibounds.y , player.ibounds.width, player.ibounds.height);
	sr.end();
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
	main.player = this.player;
 bg.dispose();
 touchpadSkin.dispose();
 map.dispose();
  tmr.dispose();
  sb.dispose();stage.dispose();
  knob.dispose();
  BGM.dispose();
  boxes.clear();
  keys.clear();
  //rain.pause();
  spikes1.clear();
  spikes2.clear();
  spikes3.clear();
  sr.dispose();
  player.dispose();
 
}
public void Attack(Entity entity , float delta){
	entity.xdelta += delta;
	if(entity.xdelta > 0.6){
		player.health -= entity.attack / player.armour;
		entity.xdelta = 0;
	}
}
public void createspikes3(){
	for(int i = 0; i <  spikes3.size; i++){
		if(visible3 ==0){
			spikes3.get(i).setVisible(true);
		}
		else if(visible3 >0){
			spikes3.get(i).setVisible(false);
		}
	}if(visible3==0){
	for(int i = 0; i <  spikes3.size; i++){
    	if(player.leftbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
    		
    	 player.x +=2;
    	 
    	}
    	if(player.rightbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
       	 player.x -=2;
       	 
       	}
    	if(player.upbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
       	 player.y -=2;
       	 
       	}
    	if(player.downbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
       	 player.y +=2;
       	 
       	}}}
	for(int i = 0; i <  spikes3.size; i++){
		for(int a = 0; a < boxes.size; a ++){
    	if(boxes.get(a).leftbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
    		
    		boxes.get(a).x +=2;
    	 
    	}
    	if(boxes.get(a).rightbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
    		boxes.get(a).x -=2;
       	 
       	}
    	if(boxes.get(a).upbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y -=2;
       	 
       	}
    	if(boxes.get(a).downbounds.overlaps(spikes3.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y +=2;
       	 
       	}}}
	

/*	for(int a =0; a < switches3.size; a++){
		for(int f = 0; f< boxes.size; f++){
			if(stepping3 ){
			
				visible3  = 2;
				break;
			}
			if(!stepping3){
				visible3 =0;
				break;
			}
		}
	}*/
}
public void createLaser1(){
	for(int i = 0; i <  laser.size; i++){
		if(visiblel1 ==0){
			laser.get(i).setVisible(true);
		}
		else if(visiblel1 >0){
			laser.get(i).setVisible(false);
		}
	}if(visiblel1==0){
	for(int i = 0; i <  laser.size; i++){
    	if(player.leftbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
    		
    	 player.x +=2;
    	 
    	}
    	if(player.rightbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
       	 player.x -=2;
       	 
       	}
    	if(player.upbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
       	 player.y -=2;
       	 
       	}
    	if(player.downbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
       	 player.y +=2;
       	 
       	}}}
	for(int i = 0; i <  laser.size; i++){
		for(int a = 0; a < boxes.size; a ++){
    	if(boxes.get(a).leftbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
    		
    		boxes.get(a).x +=2;
    	 
    	}
    	if(boxes.get(a).rightbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
    		boxes.get(a).x -=2;
       	 
       	}
    	if(boxes.get(a).upbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y -=2;
       	 
       	}
    	if(boxes.get(a).downbounds.overlaps(laser.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y +=2;
       	 
       	}}}
	

/*	for(int a =0; a < switches3.size; a++){
		for(int f = 0; f< boxes.size; f++){
			if(stepping3 ){
			
				visible3  = 2;
				break;
			}
			if(!stepping3){
				visible3 =0;
				break;
			}
		}
	}*/
}
public void updatesteps(){
	for(int a = 0; a < switches1.size ; a++){
		for(int i = 0 ; i <boxes.size; i ++){
	if(boxes.get(i).bounds.overlaps(switches1.get(a).bounds)){
		visible1 = 2;
		s1 = i;
		a1 = a;
		
		}
	if(boxes.size >=s1){
		if(!boxes.get(s1).bounds.overlaps(switches1.get(a1).bounds)){
			visible1 = 0;			
			s1 = 999;
			a1 = 34;
					}}
	}	
}
	for(int a = 0; a < laserswitch.size ; a++){
		for(int i = 0 ; i <boxes.size; i ++){
	if(boxes.get(i).bounds.overlaps(laserswitch.get(a).bounds)){
		visiblel1 = 2;
		sl1 = i;
		al1 = a;
		
	
		}
	
	 if(boxes.size >=sl1){
		if(!boxes.get(sl1).bounds.overlaps(laserswitch.get(al1).bounds)){
			visiblel1 = 0;			
			sl1 = 999;
			;
			al1 = 34;
		}}
	}	
}

	for(int a = 0; a < switches2.size ; a++){
		for(int i = 0 ; i <boxes.size; i ++){
	if(boxes.get(i).ibounds.overlaps(switches2.get(a).bounds)){
		visible2 = 2;
		s2 = i;
		a2 = a;
		
		
	}
	if(boxes.size >= s2){
	if(!boxes.get(s2).ibounds.overlaps(switches2.get(a2).bounds)){
		visible2 = 0;
		s2 = 999;
		a2 =3452;
		
	}}}}

	for(int a = 0; a < switches3.size ; a++){
		for(int i = 0 ; i <boxes.size; i ++){
	if(boxes.get(i).ibounds.overlaps(switches3.get(a).bounds)){
		visible3 = 2;
		s3 = i;
		a3 = a;
		
	
	}
	if(boxes.size >= s3){
	if(!boxes.get(s3).ibounds.overlaps(switches3.get(a3).bounds)){
		visible3 = 0;
		a3 = 485;
		s3 = 999;
	
	}}}}
	
}
public void createspikes2(){
	for(int i = 0; i <  spikes2.size; i++){
		if(visible2 ==0){
			spikes2.get(i).setVisible(true);
		}
		else if(visible2>0){
			spikes2.get(i).setVisible(false);
		}
	}if(visible2==0){
	for(int i = 0; i <  spikes2.size; i++){
    	if(player.leftbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
    		
    	 player.x +=2;
    	 
    	}
    	if(player.rightbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
       	 player.x -=2;
       	 
       	}
    	if(player.upbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
       	 player.y -=2;
       	 
       	}
    	if(player.downbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
       	 player.y +=2;
       	 
       	}}}
///////////////
	for(int i = 0; i <  spikes2.size; i++){
		for(int a = 0; a < boxes.size; a ++){
    	if(boxes.get(a).leftbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
    		
    		boxes.get(a).x +=2;
    	 
    	}
    	if(boxes.get(a).rightbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
    		boxes.get(a).x -=2;
       	 
       	}
    	if(boxes.get(a).upbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y -=2;
       	 
       	}
    	if(boxes.get(a).downbounds.overlaps(spikes2.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y +=2;
       	 
       	}}}
}
	/*for(int a =0; a < switches2.size; a++){
		for(int f = 0; f< boxes.size; f++){
			if(stepping2 ){
			
				visible2 = 2;
				break;
			}
			
			if(!stepping2){
				visible2 = 0;
				break;
			}
		}}*/
	

public void createspikes1(){
	for(int i = 0; i <  spikes1.size; i++){
		if(visible1==0){
			spikes1.get(i).setVisible(true);
		}
		else if(visible1>0){
			spikes1.get(i).setVisible(false);
		}
	}if(visible1==0){
	for(int i = 0; i <  spikes1.size; i++){
    	if(player.leftbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
    		
    	 player.x +=2;
    	 
    	}
    	if(player.rightbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
       	 player.x -=2;
       	 
       	}
    	if(player.upbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
       	 player.y -=2;
       	 
       	}
    	if(player.downbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
       	 player.y +=2;
       	 
       	}}}
	for(int i = 0; i <  spikes1.size; i++){
		for(int a = 0; a < boxes.size; a ++){
    	if(boxes.get(a).leftbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
    		
    		boxes.get(a).x +=2;
    	 
    	}
    	if(boxes.get(a).rightbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
    		boxes.get(a).x -=2;
       	 
       	}
    	if(boxes.get(a).upbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y -=2;
       	 
       	}
    	if(boxes.get(a).downbounds.overlaps(spikes1.get(i).bounds)){
    		clash.play();
    		boxes.get(a).y +=2;
       	 
       	}}}
/*	for(int a =0; a < switches1.size; a++){
		for(int f = 0; f< boxes.size; f++){
			box boxy = boxes.get(f);
			if(stepping1 ){
			
				visible1 = 2;
				break;
				
			}
			
			if(!stepping1){
				visible1  = 0;
				break;
				
			}
			}
		}*/
	}

}
