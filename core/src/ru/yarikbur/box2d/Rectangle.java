package ru.yarikbur.box2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import ru.yarikbur.world.MyWorld;

public class Rectangle {
	private MyWorld world = null;
	
	public Rectangle(){
		if(world==null) world = new MyWorld();
	}
	
	public Body Box(BodyDef.BodyType type, TextureRegion textureRegion, float x, float y, float density, float friction){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set((x)/100, (y/100));
		
		Body body = world.getWorld().createBody(bodyDef);
		PolygonShape pShape = new PolygonShape();
		pShape.setAsBox(textureRegion.getRegionWidth()/2/100, textureRegion.getRegionHeight()/2/100);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = pShape;
		fDef.density = density;
		fDef.friction = friction;
		
		@SuppressWarnings("unused")
		Fixture fixture = body.createFixture(fDef);
		
		pShape.dispose();
		
		return body;
	}
}
