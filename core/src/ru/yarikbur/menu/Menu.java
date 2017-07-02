package ru.yarikbur.menu;

import com.badlogic.gdx.Screen;

public class Menu implements Screen {

	@Override
	public void show() {
		System.out.println("show");
	}

	@Override
	public void render(float delta) {
		System.out.println("render");
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("resize");
	}

	@Override
	public void pause() {
		System.out.println("pause");
	}

	@Override
	public void resume() {
		System.out.println("resume");
	}

	@Override
	public void hide() {
		System.out.println("hide");
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
	}
}
