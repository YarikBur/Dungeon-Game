package ru.yarikbur.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;
import ru.yarikbur.map.Generator;

public class Menu implements Screen {
	private SpriteBatch batch;
	
	private BitmapFont font;
	
	private All input = new All();
	
	private String location = "menu";
	
	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = Main.getBatch();
		font = new BitmapFont();
		
		Gdx.input.setInputProcessor(input.getInputMultiplexer());
	}
	
	@SuppressWarnings("static-access")
	private void mainText(){
		if(input.getDebug()) font.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight()-1);
	}
	
	private void rendMenu(){
//		if(input.getKeycode()==21) 
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(location.equals("menu")) rendMenu();
		
		mainText();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("resize");
	}

	@Override
	public void pause() {
		System.out.println("pause");
	}

	@Override
	public void resume() {
		System.out.println("resume");
	}

	@Override
	public void hide() {
		System.out.println("hide");
		this.dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
