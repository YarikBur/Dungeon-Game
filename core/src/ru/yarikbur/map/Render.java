package ru.yarikbur.map;

public class Render {
	private static int map[][];
	
	private static Generator gen;
	
	@SuppressWarnings("static-access")
	public Render(){
		gen = new Generator();
		map = gen.getMap();
	}
	
	public static void renderMap(){
		for(int y=0; y<22; y++) for(int x=0; x<40; x++){
				if(map[y][x]==0) System.out.print("Wall");
		}
	}
}
