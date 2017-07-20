package ru.yarikbur.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	private static SpriteBatch batch;
	private static Main main;
	
	//Screens
	public static Screen menu;
	public static Screen game;
	
	@Override
	public void create() {
		menu = new ru.yarikbur.menu.Menu();
		game = new ru.yarikbur.game.Game();
		main = this;
		batch = new SpriteBatch();
		
		this.setScreen(menu);
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		super.resize(width, height);
	}
	
	@Override 
	public void pause(){
		super.pause();
	}
	
	@Override
	public void resume(){
		super.resume();
	}
	
	public static SpriteBatch getBatch(){ return batch; }
	public static void setScr(Screen scr){ main.setScreen(scr); }
}
