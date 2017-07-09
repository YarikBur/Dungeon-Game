package ru.yarikbur.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.game.Game;
import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;

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
		if(input.getDebug() && input.getKeycode() != 0) font.draw(batch, "Key: "+input.getKeycode(), 0, Gdx.graphics.getHeight()-15);
	}
	
	private void rendMenu(){
//		if(input.getKeycode()==21) 
	}
	
	float r=0,g=0,b=0;
	boolean upR=true, upG=true, upB=true;
	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(location.equals("menu")) rendMenu();
		if(input.getKeycode()==35) Main.setScr(new Game());
		
		mainText();
		batch.end();
		
		if(upR){
			r+=0.0025;
			if(r>=1) upR=!upR;
		}else{
			r-=0.0005;
			if(r<=0) upR=!upR;
		}
		
		if(upG){
			g+=0.0015;
			if(g>=1) upG=!upG;
		}else{
			g-=0.0015;
			if(g<=0) upG=!upG;
		}
		
		if(upB){
			b+=0.0005;
			if(b>=1) upB=!upB;
		}else{
			b-=0.0025;
			if(b<=0) upB=!upB;
		}
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
