package com.yarikbur.utils;

public class Random {
	static java.util.Random Rnd = new java.util.Random();
	
	public static int showRandomInteger(int aStart, int aEnd){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Form > before");
	    }
	    long range = (long)aEnd - (long)aStart + 1;
	    long fraction = (long)(range * Rnd.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	}
	
	public float Integer(float min, float max){
		return (float) (min+Math.floor(Math.random()*(max+1-min)));
	}
}
