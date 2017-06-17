package com.yarikbur.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.yarikbur.controller.Contact;
import com.yarikbur.utils.Random;

public class MyGame extends ApplicationAdapter implements InputProcessor, Screen {
	Main main;
	public String playerClass;
	private int playerLevel=1;
	private int mapLevel=1;
	private int keys=0;
	private boolean debag = false;
	Map<String, TextureRegion> textureRegionsTiles0 = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> textureRegionsPlayer = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> textureStatusPane = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Hero = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Items = new HashMap<String, TextureRegion>();
	TextureRegion[] walkFrames;
	TextureRegion playerFrame;
	Texture walkSheet;
	public SpriteBatch batch;
	Sprite sprite;
	public World world;
	Body body;
	Body walls;
	Body stop;
	Graphics gl;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	public BitmapFont font;
	public BitmapFont font8bit;
	public BitmapFont font8bit2;
	Sound Walk;
	Random rnd = new Random();
	@SuppressWarnings("rawtypes")
	Animation walkAnimation;
	int walking = 0;
    
	public ArrayList<Integer> x_room = new ArrayList<Integer>();
	public ArrayList<Integer> y_room = new ArrayList<Integer>();
	public ArrayList<Integer> w_room = new ArrayList<Integer>();
	public ArrayList<Integer> h_room = new ArrayList<Integer>();
	public ArrayList<Integer> w_n_room = new ArrayList<Integer>();
	public ArrayList<Integer> h_n_room = new ArrayList<Integer>();
	public ArrayList<Integer> x_pipe = new ArrayList<Integer>();
	public ArrayList<Integer> y_pipe = new ArrayList<Integer>();
	Contact MyContactListener = new Contact();
	public boolean pickup;
	final float met = 100f;
	float stateTime;
	public int m[][] = new int[22][40];
	public int mItems[][] = new int[22][40];
	
	public MyGame(Main main, String player) { this.main = main; this.playerClass = player; }
	public MyGame() { }
	@SuppressWarnings("unused")
	private float elapsed = 0;

	@Override
	public void show() {
		System.out.println(playerClass);
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\8bit.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 7;
		param.borderColor = Color.BLACK;
		param.borderWidth = 1;
		font8bit = gen.generateFont(param);
		param.size = 8;
		font8bit2 = gen.generateFont(param);
		gen.dispose();
		gl = Gdx.graphics;
		batch = new SpriteBatch();
		Walk = Gdx.audio.newSound(Gdx.files.internal("sounds\\stone_walk.ogg"));
		load();
		world = new World(new Vector2(0,0), true);
		world.setContactListener(MyContactListener);
		font = new BitmapFont();
        font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(gl.getWidth(),gl.getHeight());
		map();
		pos();
		rendWall();
		rendItems();
		player();
		animPlayer();
		sprite.setCenter(sprite.getWidth()/2, sprite.getHeight()/2);
	}
	
	private void print(String s){
		System.out.println(s);
	}
	int xKey=0;
	int yKey=0;
	private void pos(){
		//---Player---
		sprite = new Sprite(textureRegionsPlayer.get("player1_0"));
		int x; int y;
		while(true){
			x = (int) rnd.Integer(0, 39);
			y = (int) rnd.Integer(0, 21);
			if(m[y][x]==1) break;
		}
		m[y][x] = 2;
		sprite.setPosition(-gl.getWidth()/2+(x*16), gl.getHeight()/2-((y+1)*16));
		//---A drain pipe---
		for(int Y=1;Y<21;Y++) for(int X=1;X<39;X++) if(m[Y][X]==1 & m[Y-1][X]==0){
			x_pipe.add(X); y_pipe.add(Y);
		}
		int r;
		for(int i=0;i<y_pipe.size();i++){
			r = (int) rnd.Integer(0, 10);
			try{if(r==3) m[y_pipe.get(i)-1][x_pipe.get(i)]=7;}
			catch(java.lang.ArrayIndexOutOfBoundsException e){ }
		}
		//---Test Key---
		if(m[y][x+1] != 0){
			mItems[y][x+1] = 201;
			xKey = x+1;
			yKey=y;
		}
		else if(m[y][x-1] != 0){
			mItems[y][x-1] = 201;
			xKey=x-1;
			yKey=y;
		}
		else if(m[y+1][x] != 0){
			mItems[y+1][x] = 201;
			xKey=x;
			yKey=y+1;
		}
		else if(m[y-1][x] != 0){
			mItems[y-1][x] = 201;
			xKey=x;
			yKey=y-1;
		}
	}
	
	private void load(){
		Texture player = null;
		if(playerClass=="Magican") player = new Texture(Gdx.files.internal("atlas\\mage.png"));
		else if(playerClass=="Warrior") player = new Texture(Gdx.files.internal("atlas\\warrior.png"));
		else if(playerClass=="The Robber") player = new Texture(Gdx.files.internal("atlas\\rogue.png"));
		else if(playerClass=="Hunteress") player = new Texture(Gdx.files.internal("atlas\\ranger.png"));
		
		TextureRegion tmpPlayer[][] = TextureRegion.split(player, player.getWidth()/21, player.getHeight()/7);
		for(int y=0;y<7;y++) for(int x=0;x<21;x++) textureRegionsPlayer.put("player"+y+"_"+x, tmpPlayer[y][x]);
		
		Texture tiles0 = new Texture(Gdx.files.internal("atlas\\tiles0.png"));
		TextureRegion tmpTiles0[][] = TextureRegion.split(tiles0, tiles0.getWidth()/16, tiles0.getHeight()/4);
		for(int y=0;y<4;y++) for(int x=0;x<16;x++) textureRegionsTiles0.put("tiles0_"+y+"_"+x, tmpTiles0[y][x]);
		
		Texture statusPane = new Texture(Gdx.files.internal("not_atlas\\status_pane.png"));
		TextureRegion tmpStatusPane[][] = TextureRegion.split(statusPane, statusPane.getWidth() / 8, statusPane.getHeight());
		for(int x=0;x<8;x++) textureStatusPane.put("statusPane_"+x, tmpStatusPane[0][x]);
		
		Texture hero = new Texture("atlas\\avatars.png");
		TextureRegion tmpHero[][] = TextureRegion.split(hero, hero.getWidth()/5, hero.getHeight());
		for(int x=0;x<5;x++) Hero.put("hero_"+x, tmpHero[0][x]);
		
		Texture items = new Texture("atlas\\items.png");
		TextureRegion tmpItems[][] = TextureRegion.split(items, items.getWidth()/8, items.getHeight()/16);
		for(int y=0;y<16;y++) for(int x=0;x<8;x++) Items.put("item"+y+"_"+x, tmpItems[y][x]);
	}
	
	@SuppressWarnings("rawtypes")
	private void animPlayer(){
		if(playerClass=="Magican") walkSheet = new Texture(Gdx.files.internal("atlas\\mage.png"));
		else if(playerClass=="Warrior") walkSheet = new Texture(Gdx.files.internal("atlas\\warrior.png"));
		else if(playerClass=="The Robber") walkSheet = new Texture(Gdx.files.internal("atlas\\rogue.png"));
		else if(playerClass=="Hunteress") walkSheet = new Texture(Gdx.files.internal("atlas\\ranger.png"));
		
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,walkSheet.getWidth()/20,walkSheet.getHeight()/8-1);
		
		walkFrames = new TextureRegion[6];
		int indexR = 0;
            for (int j = 2; j <8; j++) {
            	walkFrames[indexR++] = tmp[1][j];
            }
		walkAnimation = new Animation(0.08f,walkFrames);
		stateTime = 0f;
	}

	@Override
	public void render(float delta) {
		pickup = MyContactListener.pickUp;
		camera.update();
		
		world.step(gl.getDeltaTime(), 4, 2);
		stateTime += gl.getDeltaTime();
		playerFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
		sprite.setPosition((body.getPosition().x * met) - sprite.getWidth()/2, (body.getPosition().y * met) -sprite.getHeight()/2);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		debugMatrix = batch.getProjectionMatrix().cpy().scale(met, met, 0);
		
		batch.begin();
		
		rendMap();
		go();
		if(pickup) pickUp("Key");
		statusBar();
		if(debag) text();
		batch.end();
		if(debag) debugRenderer.render(world, debugMatrix);
	}
	
	public void pickUp(String Item){
		font8bit.draw(batch, "Pick up \""+Item+"\" - E.", -24, 50);
	}
	
	private void statusBar(){
		float w = textureStatusPane.get("statusPane_0").getRegionWidth();
		float h = textureStatusPane.get("statusPane_0").getRegionHeight();
		float ww = Hero.get("hero_0").getRegionWidth();
		float hh = Hero.get("hero_0").getRegionHeight();
		batch.draw(textureStatusPane.get("statusPane_0"), -gl.getWidth()/2, 	gl.getHeight()/2-(h*2),w*2,h*2);
		batch.draw(textureStatusPane.get("statusPane_1"), -gl.getWidth()/2+w*2, gl.getHeight()/2-(h*2),w*2,h*2);
		batch.draw(textureStatusPane.get("statusPane_2"), -gl.getWidth()/2+w*4, gl.getHeight()/2-(h*2),w*2,h*2);
		batch.draw(textureStatusPane.get("statusPane_3"), -gl.getWidth()/2+w*6, gl.getHeight()/2-(h*2),w*2,h*2);
		batch.draw(textureStatusPane.get("statusPane_4"), -gl.getWidth()/2+w*8, gl.getHeight()/2-(h*2),w*2,h*2);
		for(float i=8;i<=36;i+=2) batch.draw(textureStatusPane.get("statusPane_6"), -gl.getWidth()/2+(w*i), gl.getHeight()/2-(h*2),w*2,h*2);
		batch.draw(textureStatusPane.get("statusPane_7"), -gl.getWidth()/2+w*38, gl.getHeight()/2-(h*2),w*2,h*2);
		
		if(playerClass=="Magican") batch.draw(Hero.get("hero_1"), -gl.getWidth()/2+w*2-ww/2, gl.getHeight()/2-h+hh/2);
		else if(playerClass=="Warrior") batch.draw(Hero.get("hero_0"), -gl.getWidth()/2+w*2-ww/2, gl.getHeight()/2-h+hh/2);
		else if(playerClass=="The Robber") batch.draw(Hero.get("hero_2"), -gl.getWidth()/2+w*2-ww/2, gl.getHeight()/2-h+hh/2);
		else if(playerClass=="Hunteress") batch.draw(Hero.get("hero_3"), -gl.getWidth()/2+w*2-ww/2, gl.getHeight()/2-h+hh/2);
		font8bit2.draw(batch, ""+mapLevel, -gl.getWidth()/2+w*2+ww+w/2+2, gl.getHeight()/2-h+hh+h/6-6);
		font8bit2.draw(batch, ""+keys, -gl.getWidth()/2+w*5+ww-4, gl.getHeight()/2-h+hh+h/6-6);
		font8bit.draw(batch, ""+playerLevel, -gl.getWidth()/2+w*2+22, gl.getHeight()/2-h+10);
	}
	
	public void rendMap(){
		for(int y=0;y<22;y++) for(int x=0;x<40;x++){
			if(m[y][x] == 0) batch.draw(textureRegionsTiles0.get("tiles0_1_0"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 1) batch.draw(textureRegionsTiles0.get("tiles0_0_1"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 2) batch.draw(textureRegionsTiles0.get("tiles0_0_7"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 3) batch.draw(textureRegionsTiles0.get("tiles0_0_8"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 4) batch.draw(textureRegionsTiles0.get("tiles0_0_5"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 5) batch.draw(textureRegionsTiles0.get("tiles0_0_6"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 6) batch.draw(textureRegionsTiles0.get("tiles0_0_10"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			if(m[y][x] == 7) batch.draw(textureRegionsTiles0.get("tiles0_0_12"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
			
			if(mItems[y][x]==201) batch.draw(Items.get("item1_1"), -gl.getWidth()/2+(x*16), gl.getHeight()/2-16-(y*16));
		}
	}
	
	public void rendWall(){
		for(int y=0;y<22;y++) for(int x=0;x<40;x++){
			if(m[y][x]==0) createShape(x+0.5f, y+0.5f, 6, 6, "block");
			else if(m[y][x]==1 & mItems[y][x]==0) createShape(x+0.5f, y+0.5f, 4, 4, "floor");
		}
	}
	
	public void rendItems(){
		for(int y=0;y<22;y++) for(int x=0;x<40;x++){
			if(mItems[y][x]==201) createShape(x+0.5f, y+0.5f, 7, 7, "key");
		}
	}
	
	public void text(){
		font.draw(batch, "Class: "+playerClass, -gl.getWidth()/2+8, gl.getHeight()/2-5);
		font.draw(batch, "Mause - X(16):"+(-gl.getWidth()/2+Gdx.input.getX())/16+
				" Y(16):"+(gl.getHeight()/2-Gdx.input.getY())/16, -gl.getWidth()/2+8, gl.getHeight()/2-20);
		font.draw(batch,"Restitution: " + body.getFixtureList().first().getRestitution(), 
				-gl.getWidth()/2+8,gl.getHeight()/2-35);
		font.draw(batch, "FPS: "+gl.getFramesPerSecond(), -gl.getWidth()/2+8, gl.getHeight()/2-50);
		font.draw(batch, "X: "+(int)((sprite.getX()+sprite.getWidth()/2)/16)+
				" Y: "+(int)((sprite.getY()+sprite.getHeight()/2)/16), 
				-gl.getWidth()/2+8, gl.getHeight()/2-65);
		font.draw(batch, "Mause(Massive) - X(16):"+Gdx.input.getX()/16+" Y(16):"+Gdx.input.getY()/16, -gl.getWidth()/2+8, gl.getHeight()/2-80);
		font.draw(batch, ""+pickup, -gl.getWidth()/2+8, gl.getHeight()/2-95);
	}

	@Override
	public void resize(int width, int height) {  }

	@Override
	public void pause() {  }

	@Override
	public void resume() {  }

	@Override
	public void hide() {  }

	@Override
	public void dispose() {
		world.dispose();
	}
	
	private void player(){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX()+sprite.getWidth()/2)/met, (sprite.getY()+sprite.getHeight()/2)/met);
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2/met, sprite.getHeight()/2/met);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 25f;
		fixtureDef.restitution = 0.4f;
		@SuppressWarnings("unused")
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
	}
	
	private Body createBox(BodyType type, int width, int height, float d){
		BodyDef def = new BodyDef();
		def.type = type;
		Body box = world.createBody(def);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/met, height/met);
		box.createFixture(shape,d);
		shape.dispose();
		return box;
	}
	
	private void createShape(float x, float y, int w, int h, String userData){
		Body box = createBox(BodyType.StaticBody,w,h,100);
		box.setTransform((-gl.getWidth()/2+(x*16))/met,(gl.getHeight()/2-(y*16))/met,0);
		box.getFixtureList().get(0).setUserData(userData);
	}
	
	boolean LRP = true;
	boolean Left=false, Right = false, Up = false, Down=false;
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.RIGHT || keycode == 32){
			body.setLinearVelocity(1f, 0);
			Right = true;
		}
		if(keycode == Input.Keys.LEFT || keycode == 29){
			body.setLinearVelocity(-1f,0);
			Left = true;
		}
		if(keycode == Input.Keys.UP || keycode == 51){
			body.setLinearVelocity(0, 1f);
			Up = true;
		}
		if(keycode == Input.Keys.DOWN || keycode == 47){
			body.setLinearVelocity(0,-1f);
			Down = true;
		}
		if(keycode==255) debag = !debag;
		return true;
	}
	
	public void go(){
		float x = body.getPosition().x*met-sprite.getWidth()/2;
		float y = body.getPosition().y*met-sprite.getHeight()/2;
		if(walking==8){
			walking=0;
			Walk.play();
		}
		if(Left){
			batch.draw(playerFrame, x+sprite.getWidth(),y,-playerFrame.getRegionWidth(),playerFrame.getRegionHeight());
			LRP = false;
		}
		if(Right){
			batch.draw(playerFrame, x, y);
			LRP = true;
		}
		if(Up){
			if(LRP) batch.draw(playerFrame, x, y);
			else batch.draw(playerFrame, x+sprite.getWidth(),y,-playerFrame.getRegionWidth(),playerFrame.getRegionHeight());
		}
		if(Down){
			if(LRP) batch.draw(playerFrame, x, y);
			else batch.draw(playerFrame, x+sprite.getWidth(),y,-playerFrame.getRegionWidth(),playerFrame.getRegionHeight());
		}
		if(Left|Right|Up|Down) walking++;
		if(!Left & !Right & !Up & !Down){
			walking=0;
			if(LRP) batch.draw(textureRegionsPlayer.get("player1_0"), x, y);
			else batch.draw(textureRegionsPlayer.get("player1_0"), x+textureRegionsPlayer.get("player1_0").getRegionWidth(), y, -textureRegionsPlayer.get("player1_0").getRegionWidth(), textureRegionsPlayer.get("player1_0").getRegionHeight());
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		body.setLinearVelocity(0, 0);
		Left=false; Right = false; Up = false; Down=false;
		if(keycode == 33 & pickup){
			int x = (int) (gl.getWidth()-(MyContactListener.body.getPosition().x*met-Items.get("item1_1").getRegionWidth()/2));
			int y = (int) (gl.getHeight()-(MyContactListener.body.getPosition().y*met-Items.get("item1_1").getRegionHeight()/2)+16);
			if(x<0)x=-x;
			if(y<0)y=-y;
			print(x+"   "+y);
			print((xKey*16)+"   "+(yKey*16));
			MyContactListener.body.setActive(false);
			world.destroyBody(MyContactListener.body);
			pickup = false;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {return false;}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}

	@Override
	public boolean scrolled(int amount) {return false;}
	
	private void map(){
		int x,y,w,h;
		boolean t;
		for(int Y=0;Y<22;Y++) for(int X=0;X<40;X++) m[Y][X]=0;
		x_room.clear(); y_room.clear(); w_room.clear(); h_room.clear();
		for(int i=1;i<=8;i++){
			while(true){
				t=true;
				x = Random.showRandomInteger(1, 36);
				y = Random.showRandomInteger(1, 19);
				w = Random.showRandomInteger(3, 6);
				h = Random.showRandomInteger(2, 7);
				if(testRoom(x,y,w,h,i,39,21)) for(int Y=0;Y<h;Y++) for(int X=0;X<w;X++) {
					if(x+X >=39 | x+X < 1) t=false;
					if(y+Y >=21 | y+Y < 1) t=false;
				}
				if(t) break;
			}
			try{for(int Y=0;Y<h;Y++) for(int X=0;X<w;X++) m[y+Y][x+X] = 1;}
			catch (java.lang.ArrayIndexOutOfBoundsException e){this.map();}
			x_room.add(x); y_room.add(y); w_room.add(w); h_room.add(h);
		}
		System.out.println("X:      "+x_room+"\nY:      "+y_room+"\nWidth:  "+w_room+"\nHeight: "+h_room);
		corridors();
	}
	
	private boolean testRoom(int x, int y, int width, int height, int num, int masX, int masY){
		boolean t = true;
		int XR=1, YR=1;
		if(num == 1) return true;
		if(masX - x + width >= 39 | masX - x + width < 1|
				masY - y + height > 21 | masY - y + height < 1) t=false;
		for(int Y=y;Y<masY - y + height+1;Y++){
			if(t) for(int X=x;X<masX - x + width+1;X++){
				if(XR<width+1 && YR<width+1) try{		
					if(m[Y][X] != 0){
						t=false;
						break;
					} else if(XR!=width) XR++;
				} catch (java.lang.ArrayIndexOutOfBoundsException e){
					t=false;
					break;
				}
			} else break;
			XR=1;
			if(YR!=height) YR++;
		}
		if(t) return true;
		else return false;
	}
	
	private void corridors(){
		int w,h;
		w_n_room.clear(); h_n_room.clear();
		for(int i=0;i<8;i++){
			w = w_room.get(i);
			h = h_room.get(i);
			if((w+1)%2 == 0) w_n_room.add(i);
			else if(h%2 == 0)h_n_room.add(i);
		}
		System.out.println("W: "+w_n_room+" H: "+h_n_room);
	}
}