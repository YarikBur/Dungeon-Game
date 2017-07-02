package ru.yarikbur.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.menu.Menu;

public class Main extends Game {
	private static SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		setScreen(new Menu());
	}
	
	public static SpriteBatch getBatch(){ return batch; }
}
