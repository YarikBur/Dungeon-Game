package ru.yarikbur.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;

public class StatsShow implements Screen {
	private final Main main;
	
	private BitmapFont font;
	private SpriteBatch batch;
	
	private static Stats stats;
	private All input;
	
	public StatsShow(final Main main) { this.main = main; }

	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = main.getBatch();
		font = main.getFont();
		stats = new Stats();
		input = new All();
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
		font.draw(batch, "Level: " + stats.getLevel(), 0, Gdx.graphics.getHeight()-170);
		font.draw(batch, "Unassigned points: " + stats.getPoints(), 0, Gdx.graphics.getHeight()-185);
		font.draw(batch, "Experience: " + stats.getExperience() + "/" + stats.getExperienceMax(), 0, Gdx.graphics.getHeight()-205);
	}

	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		stats.updateLevel();
		stats.updateStats();
		stats.regenerationStats();
		Gdx.gl.glClearColor(0.5f, 0.2f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		statsPlayerDraw();
		
		stats.Level50(batch, font);
		batch.end();
		
		if(input.getKeycode()==37) stats.setIntelligence(stats.getIntelligence()+1); // I
		if(input.getKeycode()==47) stats.setStamina(stats.getStamina()+1); // S
		if(input.getKeycode()==51) stats.setWisdom(stats.getWisdom()+1); // W
		if(input.getKeycode()==44) stats.setExperience(stats.getExperience()+30000); // P
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
