package ru.yarikbur.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import ru.yarikbur.game.player.Stats;
import ru.yarikbur.input.All;
import ru.yarikbur.main.Main;
import ru.yarikbur.world.MyWorld;
import ru.yarikbur.world.map.Generate;
import ru.yarikbur.world.map.Render;

public class Game implements Screen {
	private final Main main;
	private All input = new All();
	private static Stats stats;
	private Render renderMap;
	private Generate generateMap;
	private MyWorld world;
	
	private SpriteBatch batch;
	
	private BitmapFont font;
	
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private OrthographicCamera camera;
	
	public Game(final Main main) { this.main = main; }

	@SuppressWarnings("static-access")
	@Override
	public void show() {
		batch = main.getBatch();
		font = main.getFont();
		stats = new Stats();
		renderMap = new Render();
		generateMap = new Generate();
		world = new MyWorld();
		
		Gdx.input.setInputProcessor(input.getInputMultiplexer());
		
		generateMap.createBody();
		
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*3,Gdx.graphics.getHeight()*3);
		camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}

	@Override
	public void render(float delta) {
		camera.update();
		
		Gdx.gl.glClearColor(0.5f, 0.2f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(100, 100, 0);
		
		batch.begin();
		
		renderMap.renderMap(batch);
		
		font.draw(batch, "Name: "+stats.getName(), 5, Gdx.graphics.getHeight()-5);
		font.draw(batch, "Level: "+stats.getLevel(), 5, 18);
		
		batch.end();
		
		debugRenderer.render(world.getWorld(), debugMatrix);
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
