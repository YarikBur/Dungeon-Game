package ru.yarikbur.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.game.player.Stats;
import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;

public class Game implements Screen {
	private All input = new All();
	private static Stats stats;
	
	private SpriteBatch batch;
	
	private BitmapFont font;

	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = Main.getBatch();
		font = new BitmapFont();
		stats = new Stats();
		
		Gdx.input.setInputProcessor(input.getInputMultiplexer());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.2f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
//		System.out.println("Name: " + stats.getName());
		font.draw(batch, "Name: " + stats.getName(), 0, 10);
		
		batch.end();
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
		this.dispose();
	}

	@Override
	public void dispose() {
		try{ batch.dispose(); }
		catch(java.lang.IllegalArgumentException e){ }
		font.dispose();
	}
}
