package com.yarikbur.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.yarikbur.main.Main;

public class DungeonLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("not_atlas\\icon-128.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-64.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-32.png", FileType.Internal);
		config.addIcon("not_atlas\\icon-16.png", FileType.Internal);
		config.title = "Dungeon";
		config.width = 40*16;
		config.height = 22*16;
		new LwjglApplication(new Main(), config);
	}
}
