package ru.yarikbur.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class HorizontalSlidingPane extends Group {
	private static int transmission=0;
	private static int currentSection=1;
	
	@SuppressWarnings("unused")
	private static float sectionWidth, sectionHeight;
	private static float amountX=0;
	private static float stopOffset=0;
	private static float speed=1500;
	private static float flingSpeed=1000;
	private static float overscrollDistance=500;
	
	private Group sections;
	
	@SuppressWarnings("unused")
	private Actor touchFocusedChild;
	
	@SuppressWarnings("unused")
	private GestureDetector gestureDetector;
	
	public void setTransmission(int transmission){ HorizontalSlidingPane.transmission = transmission; }
	public void setCurrentSection(int currentSection){ HorizontalSlidingPane.currentSection = currentSection; }
	public void setAmountX(float amountX){ HorizontalSlidingPane.amountX = amountX; }
	public void setStopOffset(float stopOffset){ HorizontalSlidingPane.stopOffset = stopOffset; }
	public void setSpeed(float speed){ HorizontalSlidingPane.speed = speed; }
	public void setFlingSpeed(float flingSpeed){ HorizontalSlidingPane.flingSpeed = flingSpeed; }
	public void setOverscrollDistance(float overscrollDistance){ HorizontalSlidingPane.overscrollDistance = overscrollDistance; }
	
	public int getTransmission(){ return transmission; }
	public int getCurrentSection(){ return currentSection; }
	public float getAmountX(){ return amountX; }
	public float getStopOffset(){ return stopOffset; }
	public float getSpeed(){ return speed; }
	public float getFlingSpeed(){ return flingSpeed; }
	public float getOverscrollDistance(){ return overscrollDistance; }
	
	public HorizontalSlidingPane(){
		sections = new Group();
		this.addActor(sections);
		sectionWidth = Gdx.graphics.getWidth();
		sectionHeight = Gdx.graphics.getHeight();
		gestureDetector = new GestureDetector(new GestureDetector.GestureListener() {
			
			@Override
			public boolean zoom(float initialDistance, float distance) { return false; }
			
			@Override
			public boolean touchDown(float x, float y, int pointer, int button) { return false; }
			
			@Override
			public boolean tap(float x, float y, int count, int button) { return false; }
			
			@Override
			public void pinchStop() { }
			
			@Override
			public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return false; }
			
			@Override
			public boolean panStop(float x, float y, int pointer, int button) { return false; }
			
			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				if(amountX <- overscrollDistance) return false;
				if(amountX > (sections.getActions().size - 1)) return true;
				amountX-=deltaX;
				cancelTouchFocusedChild();
				return true;
			}
			
			@Override
			public boolean longPress(float x, float y) { return false; }
			
			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				if(Math.abs(velocityX) > flingSpeed){
					if(velocityX > 0) setStopSection(currentSection - 1);
					else setStopSection(currentSection + 1);
				}
				cancelTouchFocusedChild();
				return true;
			}
		});
	}
	
	public void setStopSection(int stoplineSection){
		
	}
	
	public void cancelTouchFocusedChild(){
		
	}
	
	public void addWidget(Actor widget){
		
	}
}
