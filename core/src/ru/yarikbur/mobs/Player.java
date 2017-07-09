package ru.yarikbur.mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import ru.yarikbur.models.Rectangle;
import ru.yarikbur.variables.Constants;

public class Player {
	private static Animator anim;
	private static Rectangle obj;
	private static Constants var;
	
	private static Body Player;
	private static Sprite player;
	
	public Player(){
		obj = new Rectangle();
		var = new Constants();
	}
	
	@SuppressWarnings("static-access")
	public static void createPlayer(String sprite, String userData){
		anim = new Animator();
		anim.createAnim(21, 8, sprite);
		
		player = new Sprite(anim.getRegionPlayer("player1_0"));
		player.setPosition(Gdx.graphics.getWidth()/2-player.getWidth()/2, Gdx.graphics.getHeight()/2-player.getHeight()/2);
		Player = obj.createObj(BodyDef.BodyType.DynamicBody, player, 150*var.getPTM(), 150*var.getPTM());
		Player.getFixtureList().get(0).setUserData(userData);
	}
	
	public static Body getBody(){ return Player; }
	public static Sprite getSprite(){ return player; }
	public static Vector2 getBodyPos(){ return Player.getPosition(); }
	public static Vector2 getSpritePos(){ 
		Vector2 vec = new Vector2();
		vec.x = player.getX();
		vec.y = player.getY();
		return vec;
	}
}
