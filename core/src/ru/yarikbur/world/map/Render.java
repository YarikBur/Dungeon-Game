package ru.yarikbur.world.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.yarikbur.world.MyWorld;

public class Render {
	private Generate generateMap = null;
	private MyWorld world = null;
	private Tiles tiles = null;
	
	public Render(){
		if(generateMap==null) generateMap = new Generate();
		if(world==null) world = new MyWorld();
		if(tiles==null) tiles = new Tiles();
	}
	
	public void createBody(){ generateMap.createBody(); }
	
	private float i = 16;
	public void renderMap(SpriteBatch batch){
		for(int y=0;y<22;y++){
			for(int x=0;x<40;x++){
				if(generateMap.getMap()[y][x]==0) batch.draw(tiles.getTextureRegionTiles("tiles0"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==1) batch.draw(tiles.getTextureRegionTiles("tiles1"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==2) batch.draw(tiles.getTextureRegionTiles("tiles2"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==3) batch.draw(tiles.getTextureRegionTiles("tiles3"), x*i, Gdx.graphics.getHeight()-y*i-i);
			}
		}
	}
}
