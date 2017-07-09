package ru.yarikbur.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import ru.yarikbur.variables.Constants;
import ru.yarikbur.world.MyWorld;

public class Rectangle {
	private static MyWorld world;
	private static Constants var;
	
	public Rectangle(){
		world = new MyWorld(0, 0);
		var = new Constants();
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	public Body createObj(BodyDef.BodyType type, Sprite sprite, float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set((x) / var.getPTM(), (y) / var.getPTM());

		Body body = world.getWorld().createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / var.getPTM(), sprite.getHeight() / 2 / var.getPTM());

		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.density = 1;
		fDef.friction = 0;
		Fixture fixture = body.createFixture(fDef);
		shape.dispose();
		return body;
	}
}
