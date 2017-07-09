package ru.yarikbur.mobs;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
private static int FRAME_COLS, FRAME_ROWS;
	
	static Animation<TextureRegion> walkAnimation;
	public static Map<String, TextureRegion> textureRegionsPlayer = new HashMap<String, TextureRegion>();
	
	static Texture walkSheet;
	static String texture;
	
	@SuppressWarnings("static-access")
	public void createAnim(int FRAME_COLS, int FRAME_ROWS, String texture){
		this.FRAME_COLS = FRAME_COLS;
		this.FRAME_ROWS = FRAME_ROWS;
		this.texture = texture;
		
		load();
	}

	public Animator() { }
	public Animation<TextureRegion> getAnim(){ return walkAnimation; }
	public TextureRegion getRegionPlayer(String key){ return textureRegionsPlayer.get(key); }
	
	
	private static void load(){
		walkSheet = new Texture(Gdx.files.internal(texture));
		
		TextureRegion tmpPlayer[][] = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS-1);
		for(int y=0;y<FRAME_ROWS-1;y++) for(int x=0;x<FRAME_COLS;x++) textureRegionsPlayer.put("player"+y+"_"+x, tmpPlayer[y][x]);
		
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS-1);
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		walkFrames = new TextureRegion[6];
		int indexR = 0;
        for (int j = 2; j <8; j++) 
        	walkFrames[indexR++] = tmp[1][j];
        
		walkAnimation = new Animation<TextureRegion>(0.07f,walkFrames);
	}
}
