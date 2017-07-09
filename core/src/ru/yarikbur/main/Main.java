package ru.yarikbur.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.menu.Menu;

public class Main extends Game {
	private static SpriteBatch batch;
	private static Main main;
	
	@Override
	public void create() {
		main = this;
		batch = new SpriteBatch();
		
		setScreen(new Menu());
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	public static SpriteBatch getBatch(){ return batch; }
	public static void setScr(Screen scr){ main.setScreen(scr); }
}
