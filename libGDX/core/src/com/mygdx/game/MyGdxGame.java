package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer sr;
	SpriteBatch batch;
	BitmapFont font;
	String output;
	Player player = new Player();
	boolean ispressed = false, playing = false, firsttime = true, startup = true, apressed, aimode;
	int width, height, count = 0;
	Pipe[] pipes = new Pipe[0];
	
	
	public Pipe[] newPipe(Pipe[] pipes, int height, int width) {
		Pipe[] newPipes;
		if (pipes.length <= 3) {
			newPipes = new Pipe[pipes.length + 1];
			for (int i = 0; i < pipes.length; i++) {
				newPipes[i] = pipes[i];
			}
		}
		else {
			newPipes = new Pipe[4];
			for (int i = 0; i < 3; i++) {
				newPipes[i] = pipes[i + 1];
			}
		}
		newPipes[newPipes.length - 1] = new Pipe(height, width);
		
		return newPipes;
	}
	
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		sr = new ShapeRenderer();
	}

	@Override
	public void render () {
		if (firsttime == true) {
			firsttime = false;
			height = Gdx.graphics.getHeight();
			width = Gdx.graphics.getWidth();
		}
		playing = player.check(playing, height, player, pipes);
		
		ispressed = player.input(playing, ispressed, player, pipes);
		if (player.getButtonstate() == true && playing == false) {
			startup = false;
			playing = true;
			count = 0;
			apressed = player.getIsapressed();
			aimode = player.getAimode();
			player = new Player();
			player.setAimode(aimode);
			player.setIsapressed(apressed);
			pipes = new Pipe[0];
		}
		if (playing == true) {
			if (count % 100 == 0 && count >= 200) {
				pipes = newPipe(pipes, height, width);
			}
		}
			
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GREEN);
		for (int i = 0; i < pipes.length; i++) {
			sr.rect(pipes[i].getX(), pipes[i].getY(), pipes[i].getWidth(), pipes[i].getHeight());
			sr.rect(pipes[i].getX(), 0, pipes[i].getWidth(), height - pipes[i].getHeight() - pipes[i].getGap());
			if (playing == true) {
				pipes[i].setX(pipes[i].getX() - 3);
			}
		}
			
		if (playing == true || startup == true) {
			sr.setColor(Color.YELLOW);
		}
		else {
			sr.setColor(Color.RED);
		}
		sr.circle(player.getX(), player.getY(), player.getRad());
	
		sr.end();
		
		if (startup == false) {
			player.gravity(player);
			batch.begin();
			font.setColor(Color.WHITE);
			font.draw(batch, "Score: " + player.getScore(), width - 100, height - 10);
			
			if (player.getAimode() == false) {
				output = "OFF";
				font.setColor(Color.RED);
			}
			else {
				output = "ON";
				font.setColor(Color.FOREST);
			}
			font.draw(batch, "AI MODE: " + output, 0, height - 10);
			batch.end();
		}
			
		count += 1;
		
		if (player.getAcc() == 0) { //A flag used to check whether the player has decided to quit the game
			dispose();
		}
	}
	
	@Override
	public void dispose () {
		Gdx.app.exit();
	}
}
