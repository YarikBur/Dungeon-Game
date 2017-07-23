package ru.yarikbur.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	private static SpriteBatch batch;
	private static BitmapFont font;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new ru.yarikbur.menu.Menu(this));
	}
	
	public static SpriteBatch getBatch(){ return batch; }
	public static BitmapFont getFont(){ return font; }
}
