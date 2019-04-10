package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Buttons;

public class Player {
	protected boolean buttonstate, passed, aimode, isapressed;
	protected int score, x, y, radius;
	protected double yvel, yacc;
	
	public Player() {
		this.isapressed = false;
		this.aimode = false;
		this.buttonstate = false;
		this.passed = true;
		this.score = 0;
		this.x = 100;
		this.y = 250;
		this.radius = 10;
		this.yacc = 0.6;
	}
	
	
	public void gravity(Player player) {
		if (player.getVely() >= -10) {
			player.setVely(player.getVely() - player.getAcc()); 
		}
		else {
			player.setVely(-10);
		}
		player.setY(player.getY() + (int)player.getVely());
	}
	
	
	public boolean input(boolean playing, boolean ispressed, Player player, Pipe[] pipes) {
		boolean aijump = false;
		if (player.getAimode() == true && ispressed == false && playing == true) {
			boolean found = false; //finds out which pipe is next
			if (player.getY() - player.getRad() + (int)(player.getVely() - player.getAcc()) <= 0) {
				aijump = true;
			}
			
			for (int i = 0; i < pipes.length; i++) {
				if (pipes[i].getX() + pipes[i].getWidth() > player.getX() - player.getRad() && found == false) {
					found = true;
					if (player.getY() - player.getRad() + (int)(player.getVely() - player.getAcc()) <= pipes[i].getY() - pipes[i].getGap()) {
						aijump = true;
					}
				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			if (player.getIsapressed() == false) {
				player.setIsapressed(true);
				if (player.getAimode() == false) {
					player.setAimode(true);
				}
				else {
					player.setAimode(false);
				}
			}
		}
		else {
			player.setIsapressed(false);
		}
		
		if(((Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isButtonPressed(Buttons.LEFT)) && (player.getAimode() == false || playing == false)) || aijump == true) { //The part after the OR is for AI
			if(ispressed == false) {
				player.setButtonstate(true);
				ispressed = true;
				if (playing == true) {
					player.setVely(9);
				}
			}
			else {
				player.setButtonstate(false);
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.Q)) {
			player.setAcc(0);
		}
		else {
			ispressed = false;
		}
		return ispressed;
	}
	
	
	public boolean check(boolean playing, int height, Player player, Pipe[] pipes) {
		if (player.getY() + player.getRad() >= height) {
			player.setVely(-1);
		}
		else if (player.getY() - player.getRad() <= 0) {
			player.setVely(0);
			player.setButtonstate(false);
			playing = false;
		}
		
		for (int i = 0; i < pipes.length; i++) {
			if (pipes[i].getX() < player.getX() + player.getRad() && player.getX() - player.getRad() < pipes[i].getX() + pipes[i].getWidth()) {
				
				if (player.getX() > pipes[i].getX() + (pipes[i].getWidth() / 2)) {
					if (player.getPassed() == true) {
						player.setPassed(false);
						setScore(getScore() + 1);
					}
				}
				else {
					player.setPassed(true);
				}
				
				if (player.getY() + player.getRad() > pipes[i].getY() || player.getY() - player.getRad() < pipes[i].getY() - pipes[i].getGap()) {
					player.setButtonstate(false);
					playing = false;
				}
			}
		}
		
		return playing;
	}
	
	public boolean getIsapressed() {
		return isapressed;
	}
	
	public void setIsapressed(boolean value) {
		isapressed = value;
	}
	
	public boolean getAimode() {
		return aimode;
	}
	
	public void setAimode(boolean value) {
		aimode = value;
	}
	
	public boolean getButtonstate() {
		return buttonstate;
	}
	
	public void setButtonstate(boolean value) {
		buttonstate = value;
	}
	
	public boolean getPassed() {
		return passed;
	}
	
	public void setPassed(boolean value) {
		passed = value;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int value) {
		score = value;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int value) {
		y = value;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRad() {
		return radius;
	}
	
	public void setAcc(double value) {
		yacc = value;
	}
	
	public double getAcc() {
		return yacc;
	}
	
	public void setVely(double value) {
		yvel = value;
	}
	
	public double getVely() {
		return yvel;
	}
}
