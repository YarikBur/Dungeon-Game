package ru.yarikbur.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputOne implements InputProcessor {
	private static boolean debug=false, pressed=false;
	private static int key;
	
	public static boolean getDebug(){ return debug; }
	public static boolean getPressed(){ return pressed; }
	public static int getKeycode(){ return key; }
	
	@Override
	public boolean keyDown(int keycode) {
		key=keycode;
		pressed=true;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		key=0;
		pressed=false;
		if(keycode==245) debug = !debug;
		else if(keycode==131) Gdx.app.exit();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
