package com.yarikbur.game.desktop;

import java.util.Scanner;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.yarikbur.game.player.Stats;
import ru.yarikbur.main.Main;

public class DungeonLauncher {
	private static Stats stats;
	
	public static void main (String[] arg) {
		stats = new Stats();
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("not_atlas\\icon-128.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-64.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-32.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-16.png", FileType.Internal);
		config.title = "Dungeon";
		config.width = 40*16;
		config.height = 22*16;
		config.resizable = false;
		input();
		new LwjglApplication(new Main(), config);
	}
	
	private static void input(){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Name: ");
		stats.setName(input.nextLine());
	}
}
