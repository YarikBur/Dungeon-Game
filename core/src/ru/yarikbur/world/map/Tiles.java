package ru.yarikbur.world.map;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tiles {
	private static Texture tiles = null;
	private static int lines = 0;
	private static int сolumns = 0;
	
	private static Map<String, TextureRegion> textureRegionsTiles = null;
	
	public Tiles(String tiles, int lines, int columns){
		if(Tiles.tiles==null) Tiles.tiles = new Texture(tiles);
		if(textureRegionsTiles==null){
			textureRegionsTiles = new HashMap<String, TextureRegion>();
			createAtlas();
		}
		if(Tiles.lines==0 || Tiles.сolumns==0){
			Tiles.lines = lines;
			Tiles.сolumns = columns;
		}
	}
	public Tiles(){
		if(Tiles.tiles==null) Tiles.tiles = new Texture("atlas/tilesTest.png");
		if(textureRegionsTiles==null){
			textureRegionsTiles = new HashMap<String, TextureRegion>();
			createAtlas();
		}
	}
	
	private void createAtlas(){
		TextureRegion tmpPlayer[][] = TextureRegion.split(tiles, tiles.getWidth()/4, tiles.getHeight());
		for(int x=0;x<4;x++) textureRegionsTiles.put("tiles"+x, tmpPlayer[0][x]);
		/*
		 * Tiles0 - Стена
		 * Tiles1 - Пол
		 * Tiles2 - Закрытая дверь
		 * Tiles3 - Открытая дверь
		 */
	}
	
	public TextureRegion getTextureRegionTiles(String region){ return textureRegionsTiles.get(region); }
}
