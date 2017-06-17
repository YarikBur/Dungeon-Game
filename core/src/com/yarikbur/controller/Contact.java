package com.yarikbur.controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class Contact implements ContactListener {
	public boolean pickUp=false;
	public Body body = null;
	@Override
	public void beginContact(com.badlogic.gdx.physics.box2d.Contact contact) {
		
	}

	@Override
	public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {
		
	}

	@Override
	public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {
		WorldManifold manifold = contact.getWorldManifold();
		for(int i=0;i<manifold.getNumberOfContactPoints();i++){
			//---Floor---
			if(contact.getFixtureA().getUserData()!=null&&contact.getFixtureA().getUserData().equals("floor"))
				contact.setEnabled(false);
			if(contact.getFixtureB().getUserData()!=null&&contact.getFixtureB().getUserData().equals("floor"))
				contact.setEnabled(false);
			//---Key---
			if(contact.getFixtureA().getUserData()!=null&&contact.getFixtureA().getUserData().equals("key")){
				contact.setEnabled(false);
				pickUp = true;
				body = contact.getFixtureA().getBody();
			}
			else if(contact.getFixtureB().getUserData()!=null&&contact.getFixtureB().getUserData().equals("key")){
				contact.setEnabled(false);
				pickUp = true;
				body = contact.getFixtureA().getBody();
			} else pickUp = false;
			
			//System.out.println(contact.getFixtureA().getUserData()+"   "+contact.getFixtureB().getUserData());
		}
	}

	@Override
	public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {
		
	}
}