package com.yarikbur.game;

import com.badlogic.gdx.Game;
import com.yarikbur.menu.Menu;

public class Main extends Game {
	public MyGame game;
	public Menu menu;
	public Authorization authorization;
	
	@Override
	  public void create() {
	    game = new MyGame(this);
	    menu = new Menu(this);
	    authorization = new Authorization(this);
	    setScreen(menu);
	}
}