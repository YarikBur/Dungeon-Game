package ru.yarikbur.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.main.Main;
import ru.yarikbur.map.Render;
import ru.yarikbur.mobs.Player;

public class Game implements Screen {
	private static Player player;
	private Render rend;
	
	private static SpriteBatch batch;

	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = Main.getBatch();
		player = new Player();
		player.createPlayer("atlas\\mage.png", "Player");
		rend = new Render();
		rend.renderMap();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		updatePositions();
	}
	
	private void updatePositions(){
		
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
		// TODO Auto-generated method stub
		
	}
}
