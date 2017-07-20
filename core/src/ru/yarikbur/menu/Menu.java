package ru.yarikbur.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;

public class Menu implements Screen {
	private SpriteBatch batch;
	
	private BitmapFont font;
	
	private All input = new All();
	
	private String location = "menu";
	private ru.yarikbur.game.player.Stats stats;
	
	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = Main.getBatch();
		font = new BitmapFont();
		stats = new ru.yarikbur.game.player.Stats();
		
		Gdx.input.setInputProcessor(input.getInputMultiplexer());
		stats.setStartTime(System.currentTimeMillis());
	}
	
	@SuppressWarnings("static-access")
	private void mainText(){
		if(input.getDebug()) font.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight()-1);
		if(input.getDebug() && input.getKeycode() != 0) font.draw(batch, "Key: "+input.getKeycode(), 0, Gdx.graphics.getHeight()-15);
	}
	
	private void statsPlayerDraw(){
		font.draw(batch, "Name: " + stats.getName(), 0, Gdx.graphics.getHeight()-30);
		if(stats.getHealth()==stats.getHealthMax()) font.draw(batch, "Health: " + stats.getHealth(), 0, Gdx.graphics.getHeight()-50);
		else font.draw(batch, "Health: " + stats.getHealth() + "/" + stats.getHealthMax() + "   Regeneration "+ (stats.getRegenerationHealth()*60) +" per minute", 0, Gdx.graphics.getHeight()-50);
		if(stats.getMana()==stats.getManaMax()) font.draw(batch, "Mana: " + stats.getMana(), 0, Gdx.graphics.getHeight()-65);
		else font.draw(batch, "Mana: " + stats.getMana() + "/" + stats.getManaMax() + "   Regeneration "+ (stats.getRegenerationMana()*60) +" per minute", 0, Gdx.graphics.getHeight()-65);
		font.draw(batch, "Force: " + stats.getForce(), 0, Gdx.graphics.getHeight()-80);
		font.draw(batch, "Stamina: " + stats.getStamina(), 0, Gdx.graphics.getHeight()-95);
		font.draw(batch, "Adroitness: " + stats.getAdroitness(), 0, Gdx.graphics.getHeight()-110);
		font.draw(batch, "Intelligence: " + stats.getIntelligence(), 0, Gdx.graphics.getHeight()-125);
		font.draw(batch, "Luck: " + stats.getLuck(), 0, Gdx.graphics.getHeight()-140);
		font.draw(batch, "Wisdom: " + stats.getWisdom(), 0, Gdx.graphics.getHeight()-155);
		font.draw(batch, "Experience: " + stats.getExperience(), 0, Gdx.graphics.getHeight()-175);
	}
	
	@SuppressWarnings("static-access")
	private void rendMenu(){
//		if(input.getKeycode()==21) 
		if(input.getKeycode()==35) Main.setScr(Main.game);
	}
	
	float r=0,g=0,b=0;
	boolean upR=true, upG=true, upB=true;
	
	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		stats.updateStats();
		stats.regenerationStats();
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(location.equals("menu")) rendMenu();
		if(input.getKeycode()==37) stats.setIntelligence(stats.getIntelligence()+1); // I
		if(input.getKeycode()==47) stats.setStamina(stats.getStamina()+1); // S
		if(input.getKeycode()==51) stats.setWisdom(stats.getWisdom()+1); // W
		
		mainText();
		statsPlayerDraw();
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
