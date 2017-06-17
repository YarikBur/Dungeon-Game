package com.yarikbur.menu;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.yarikbur.Server.Query;
import com.yarikbur.game.Authorization;
import com.yarikbur.game.Main;
import com.yarikbur.game.MyGame;

public class Menu implements InputProcessor, Screen {
	public  static Query server = new Query();
	public  boolean nom = false;
	private static String arg0 = "nu";
	private static String arg1 = "nu";
	private static String arg2 = "nu";
	public  static String login;
	public  static String pass;
	private static float yy, yY;
	public  static boolean logined = false;
	Authorization au;
	MyGame game;
	Map<String, TextureRegion> Buttons = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Banner = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Hero = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Icons = new HashMap<String, TextureRegion>();
	Map<String, TextureRegion> Arcs = new HashMap<String, TextureRegion>();
	final String FONT_CHARACTERS = "";
	SpriteBatch batch;
	BitmapFont font8bit;
	String menu = "menu";
	Graphics gl;
	Main main;
	
	public Menu(Main main){ this.main = main; }
	
	public void create(){
		batch = new SpriteBatch();
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\8bit.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 7;
		param.borderColor = Color.BLACK;
		param.borderWidth = 1;
		font8bit = gen.generateFont(param);
		gen.dispose();
		gl = Gdx.graphics;
		game = new MyGame();
		au = new Authorization();
		Gdx.input.setInputProcessor(this);
		load();
		try {
			renderMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void bg(SpriteBatch batch, float yy, float yY){
		Texture arcs1 = new Texture("not_atlas\\arcs1.png");
		Texture arcs2 = new Texture("not_atlas\\arcs2.png");
		for(float x=0;x<40*16;x+=arcs1.getWidth()) for(float y=-yy;y<22*16+yy;y+=arcs1.getHeight()) batch.draw(arcs1, x, y);
		for(float x=0;x<40*16;x+=arcs2.getWidth()) for(float y=-yY;y<22*16+yY;y+=arcs2.getHeight()) batch.draw(arcs2, x, y);
	}
	
	public void renderMenu() throws Exception{
		menu = "menu";
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float Height_l = Banner.get("Banner_0").getRegionHeight()*2, Width_l = Banner.get("Banner_0").getRegionWidth()*2;
		batch.begin();
		bg(batch, yy, yY);
		batch.draw(Banner.get("Banner_0"), -gl.getWidth()/2+Width_l*2,  gl.getHeight()-Height_l, Width_l, Height_l);
		
		status();
		if(logined) online();
		
		rendBut();
		batch.end();
	}
	
	public void renderSelectHero() throws Exception{
		menu = "SelectHero";
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg(batch, yy, yY);
		status();
		if(logined) online();
		rendHero();
		batch.end();
	}
	
	private void status(){
		String stat;
		int x;
		if(logined){
			stat = "Online";
			x = gl.getWidth()-90;
		}
		else{
			stat = "Offline";
			x = gl.getWidth()-100;
		}
		font8bit.draw(batch, "You: "+stat, x, gl.getHeight()-15);
		font8bit.draw(batch, "V   0 5", gl.getWidth()-40, 12);
	}
	
	@SuppressWarnings("static-access")
	private void online() throws Exception{
		font8bit.draw(batch, "Online:   "+server.query("online", "online", arg0, arg1, arg2), 8, 40);
		font8bit.draw(batch, "Total registered:   "+server.query("online", "registered", arg0, arg1, arg2), 8, 20);
	}
	
	private void rendHero(){
		float w = Hero.get("hero_0").getRegionWidth()*2;
		float h = Hero.get("hero_0").getRegionHeight()*2;
		float s = Hero.get("hero_0").getRegionHeight()/2;
		float ww = (w/2)/2;
		float hh = gl.getHeight()-112-h*1.5f;
		batch.draw(Icons.get("Icon0_8"), -gl.getWidth()+(gl.getWidth()+Icons.get("Icon0_8").getRegionWidth()*2+s/2), 
				gl.getHeight()-(Icons.get("Icon0_8").getRegionHeight()*2+s/2), 
				-Icons.get("Icon0_8").getRegionWidth()*2,Icons.get("Icon0_8").getRegionHeight()*2);
		batch.draw(Hero.get("hero_0"), gl.getWidth()/2-ww-(w/2)-(w*2)-s, hh, w, h);
		font8bit.draw(batch, "Warrior", gl.getWidth()/2-3-ww-(w/2)-(w*2)-s, hh-15);
		batch.draw(Hero.get("hero_1"), gl.getWidth()/2-ww-(w)-s/2, hh, w, h);
		font8bit.draw(batch, "Magican", gl.getWidth()/2-ww-(w)-s, hh-15);
		batch.draw(Hero.get("hero_2"), gl.getWidth()/2-ww-(w/2)+(w)+2, hh, w, h);
		font8bit.draw(batch, "The Robber", gl.getWidth()/2-ww-(w/2)+(w)-s/2-7, hh-15);
		batch.draw(Hero.get("hero_3"), gl.getWidth()/2-ww+(w*2)+s, hh, w, h);
		font8bit.draw(batch, "Hunteress", gl.getWidth()/2-2-(w/2)+(w*2)+s, hh-15);
	}
	
	private void rendBut() throws Exception{
		float w = Buttons.get("button_0").getRegionWidth()*2;
		float h = Buttons.get("button_0").getRegionHeight()*2;
		float s = Hero.get("hero_0").getRegionHeight()/2;
		float ww = (w/2)/2;
		float hh = gl.getHeight()-112-h*1.5f;
		batch.draw(Icons.get("Icon0_8"), -gl.getWidth()+(gl.getWidth()+Icons.get("Icon0_8").getRegionWidth()*2+s/2), 
				gl.getHeight()-(Icons.get("Icon0_8").getRegionHeight()*2+s/2), 
				-Icons.get("Icon0_8").getRegionWidth()*2,Icons.get("Icon0_8").getRegionHeight()*2);
		batch.draw(Buttons.get("button_0"), gl.getWidth()/2-ww-(w/2)-(w*2), hh, w, h);
		font8bit.draw(batch, "Play", gl.getWidth()/2+2-(w/2)-(w*2), hh-15);
		batch.draw(Buttons.get("button_2"), gl.getWidth()/2-ww-(w), hh, w, h);
		font8bit.draw(batch, "Highscores", gl.getWidth()/2-5-ww-(w), hh-15);
		batch.draw(Buttons.get("button_3"), gl.getWidth()/2-ww-(w/2)+(w), hh, w, h);
		font8bit.draw(batch, "Badges", gl.getWidth()/2-ww-(w/2)+(w)+7, hh-15);
		batch.draw(Buttons.get("button_1"), gl.getWidth()/2-ww+(w*2), hh, w, h);
		font8bit.draw(batch, "About the game", gl.getWidth()/2-2-(w/2)+(w*2), hh-15);
	}
	
	private void dis(String scr, String player){
		font8bit.dispose();
		batch.dispose();
		main.player = player;
		game.playerClass = player;
		if(scr.equals("au")) main.setScreen(main.authorization);
		else if(scr.equals("game")) main.setScreen(main.game);
	}
	
	private void load(){
		Texture but = new Texture("atlas\\dashboard.png");
		TextureRegion tmpTiles0[][] = TextureRegion.split(but, but.getWidth()/4, but.getHeight());
		for(int x=0;x<4;x++) Buttons.put("button_"+x, tmpTiles0[0][x]);
		
		Texture banner = new Texture("atlas\\banners.png");
		TextureRegion tmpBanner[][] = TextureRegion.split(banner, banner.getWidth(), banner.getHeight()/4);
		for(int y=0;y<4;y++) Banner.put("Banner_"+y, tmpBanner[y][0]);
		
		Texture hero = new Texture("atlas\\avatars.png");
		TextureRegion tmpHero[][] = TextureRegion.split(hero, hero.getWidth()/5, hero.getHeight());
		for(int x=0;x<5;x++) Hero.put("hero_"+x, tmpHero[0][x]);
		
		Texture icons = new Texture("atlas\\icons.png");
		TextureRegion tmpIcons[][] = TextureRegion.split(icons, icons.getWidth()/9, icons.getHeight()/4);
		for(int y=0;y<4;y++) for(int x=0;x<9;x++) Icons.put("Icon"+y+"_"+x, tmpIcons[y][x]);
		
	}

	@Override
	public boolean keyDown(int keycode) {return false;}

	@Override
	public boolean keyUp(int keycode) {return false;}

	@Override
	public boolean keyTyped(char character) {return false;}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}

	@SuppressWarnings("static-access")
	private void Close() throws Exception{
		if(logined) server.query("online", "setOnlineF", login, pass.toString(), " ");
		logined = false;
		Gdx.app.exit();
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		boolean t = false;
		if("menu"==menu){
			if(screenX >= 0 & screenY >= 0 & screenX <= 40 & screenY <= 40){
				try { Close(); }
				catch (Exception e1) { e1.printStackTrace(); }
			} else if(screenX >= 140 & screenY >= 140 & screenX <= 215 & screenY <= 240){menu = "SelectHero"; t = true;}
			else if(screenX >= 230 & screenY >= 140 & screenX <= 310 & screenY <= 240){System.out.println("Highscores"); t = true;}
			else if(screenX >= 335 & screenY >= 140 & screenX <= 405 & screenY <= 240){System.out.println("Badges"); t = true;}
			else if(screenX >= 415 & screenY >= 140 & screenX <= 525 & screenY <= 240){System.out.println("About the game"); t = true;}
			else if(screenX >= 535 & screenY >= 15 & screenX <= 620 & screenY <= 25) dis("au","login");
			else System.out.println("X - "+screenX+" Y - "+screenY);
		} else if ("SelectHero"==menu){
			if(screenX >= 0 & screenY >= 0 & screenX <= 40 & screenY <= 40){menu = "menu"; t = true;}
			else if(screenX >= 160 & screenY >= 145 & screenX <= 225 & screenY <= 235){dis("game", "Warrior"); t = true;}
			else if(screenX >= 235 & screenY >= 145 & screenX <= 300 & screenY <= 235){dis("game", "Magican"); t = true;}
			else if(screenX >= 315 & screenY >= 145 & screenX <= 395 & screenY <= 235){dis("game", "The Robber"); t = true;}
			else if(screenX >= 405 & screenY >= 145 & screenX <= 485 & screenY <= 235){dis("game", "Hunteress"); t = true;}
			else System.out.println("X - "+screenX+" Y - "+screenY);
		}
		
		
		if(t) return true;
		else return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {return false;}

	@Override
	public boolean scrolled(int amount) {return false;}

	@Override
	public void show() {
		this.create();
	}

	@Override
	public void render(float delta) {
		if(gl.getFramesPerSecond() >= 30) yy +=gl.getFramesPerSecond()/30-gl.getDeltaTime();
		if(gl.getFramesPerSecond() >= 30) yY +=gl.getFramesPerSecond()/38-gl.getDeltaTime();
		if(menu.equals("menu")){
			try {
				this.renderMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.renderSelectHero();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void hide() {
		
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
	public void dispose() {
		
	}
}