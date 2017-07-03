package ru.yarikbur.input;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class All {
	static InputProcessor inputProcessorOne = new InputOne();
	static InputMultiplexer inputMultiplexer = new InputMultiplexer();
	
	public All(){
		inputMultiplexer.addProcessor(inputProcessorOne);
	}
	
	public static InputMultiplexer getInputMultiplexer(){ return inputMultiplexer; }
	
	public static boolean getDebug(){
		if(InputOne.getDebug()) return true;
		else return false;
	}
	
	public static int getKeycode(){
		return InputOne.getKeycode();
	}
}
