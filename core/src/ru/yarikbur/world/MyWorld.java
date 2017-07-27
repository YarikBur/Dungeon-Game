package ru.yarikbur.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class MyWorld {
	private static World world = null;
	
	public MyWorld(float x, float y){
		if(world==null) world = new World(new Vector2(x, y), false);
	}
	public MyWorld() {
		if(world==null) world = new World(new Vector2(0, 0), false);
	}
	public World getWorld(){ return world; }
	public void dispose(){ world.dispose(); }
}
