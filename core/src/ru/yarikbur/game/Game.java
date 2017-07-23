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
	private final Main main;
	private All input = new All();
	private static Stats stats;
	
	private SpriteBatch batch;
	
	private BitmapFont font;

	public Game(final Main main) { this.main = main; }

	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = main.getBatch();
		font = main.getFont();
		stats = new Stats();
		
		Gdx.input.setInputProcessor(input.getInputMultiplexer());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.2f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		
		
		batch.end();
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
		this.dispose();
	}

	@Override
	public void dispose() {
		
	}
}
