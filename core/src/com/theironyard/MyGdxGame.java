package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.transform.sax.SAXTransformerFactory;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	TextureRegion stand, jump;

	float x, y, xv, yv;
	static final float MAX_VELOCITY = 500;

	static final int WIDTH = 18;
	static final int HEIGHT = 26;

	static final int DRAW_WIDTH = WIDTH*3;
	static final int DRAW_HEIGHT = HEIGHT*3;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		Texture sheet = new Texture("koalio.png");
		TextureRegion[][] tiles = TextureRegion.split(sheet, WIDTH, HEIGHT);
		stand = tiles [0][0];
		jump = tiles [0][1];
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(stand, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
	}

	public void move(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP))	{
			yv = MAX_VELOCITY;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
		}

		y = y + (yv * Gdx.graphics.getDeltaTime());
		x = x + (xv * Gdx.graphics.getDeltaTime());

		yv = decelerate(yv);
		xv = decelerate(xv);
	}
	public float decelerate(float velocity) {
		float deceleration = 0.95f;
		velocity *= deceleration;
		if(Math.abs(velocity) < 1){
			velocity = 0;
		}
		return velocity;
	}

	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
