package com.yarikbur.game;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yarikbur.Server.Query;
import com.yarikbur.menu.Menu;

public class Authorization implements Screen {
	static float yy, yY;
	static Query server;
	static Menu menu;
	static String answer;
	static String login;
	static StringBuffer pass;
	static String ip;
	String nom;
	static boolean err;
	SpriteBatch batch;
	Graphics gl;
	Main main;
	
	private static void input() throws IOException{
		try {
			Console cnsl = null;
			cnsl = System.console();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Ip: "); ip = br.readLine();
			try{
				if(ip.equals("127.0.0.1") | ip.equals("l") | ip.equals("localhost")){
					ip = "192.168.10.103";
					login = "YarikBur";
					pass.append("test");
				} else {
					System.out.print("Login: "); login = br.readLine();
					pass.setLength(0);
					char[] pwd = cnsl.readPassword("Password: ");
					for(int i=0; i<pwd.length; i++) pass.append(pwd[i]);
				}
			} catch(java.net.NoRouteToHostException n){
				err = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Authorization(Main main){ this.main = main; }
	public Authorization() { }

	private void create(){
		answer = " ";
		pass = new StringBuffer();
		pass.setLength(0);
		login = " ";
		ip = " ";
		err = false;
		
		server = new Query();
		menu = new Menu(main);
	}
	
	private void bg(SpriteBatch batch, float yy, float yY){
		Texture arcs1 = new Texture("not_atlas\\arcs1.png");
		Texture arcs2 = new Texture("not_atlas\\arcs2.png");
		for(float x=0;x<40*16;x+=arcs1.getWidth()) for(float y=-yY;y<22*16+yy;y+=arcs1.getHeight()) batch.draw(arcs1, x, y);
		for(float x=0;x<40*16;x+=arcs2.getWidth()) for(float y=-yy;y<22*16+yY;y+=arcs2.getHeight()) batch.draw(arcs2, x, y);
	}
	
	@Override
	public void show() {
		gl = Gdx.graphics;
		batch = new SpriteBatch();
		create();
	}

	@SuppressWarnings("static-access")
	@Override
	public void render(float delta){
		yy +=gl.getFramesPerSecond()/30-gl.getDeltaTime();
		yY +=gl.getFramesPerSecond()/32-gl.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg(batch, yy, yY);
		batch.end();
		while(!answer.equals("nu")){
			create();
			try {
				input();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(err) break;
			server.ip = ip;
			try {
				answer = server.query(" ", " ", "authorization", login, pass.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(answer.equals("nu")) System.out.println("Start game");
			else System.out.println(answer);
		}
		if(!err){
			try {
				server.query("online", "setOnlineT", login, pass.toString(), " ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			menu.login = login;
			menu.pass = pass.toString();
			menu.logined = true;
		} else {
			System.out.println("Connection error");
			menu.logined = false;
		}
		
		main.setScreen(main.menu);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
