package ru.yarikbur.world.map;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Render {
	private static Texture tiles = null;
	
	private Generate generateMap = null;
	
	private static Map<String, TextureRegion> textureRegionsAtlas = null;
	
	public Render(){
		if(tiles==null) tiles=new Texture("atlas/tilesTest.png");
		if(textureRegionsAtlas==null){
			textureRegionsAtlas = new HashMap<String, TextureRegion>();
			createAtlas();
		}
		if(generateMap==null) generateMap = new Generate();
	}
	
	private void createAtlas(){
		TextureRegion tmpPlayer[][] = TextureRegion.split(tiles, tiles.getWidth()/4, tiles.getHeight());
		for(int x=0;x<4;x++) textureRegionsAtlas.put("tiles"+x, tmpPlayer[0][x]);
		/*
		 * Tiles0 - Стена
		 * Tiles1 - Пол
		 * Tiles2 - Закрытая дверь
		 * Tiles3 - Открытая дверь
		 */
	}
	
	private float i = 16;
	public void renderMap(SpriteBatch batch){
		for(int y=0;y<22;y++){
			for(int x=0;x<40;x++){
				if(generateMap.getMap()[y][x]==0) batch.draw(textureRegionsAtlas.get("tiles0"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==1) batch.draw(textureRegionsAtlas.get("tiles1"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==2) batch.draw(textureRegionsAtlas.get("tiles2"), x*i, Gdx.graphics.getHeight()-y*i-i);
				else if(generateMap.getMap()[y][x]==3) batch.draw(textureRegionsAtlas.get("tiles3"), x*i, Gdx.graphics.getHeight()-y*i-i);
			}
		}
	}
}
