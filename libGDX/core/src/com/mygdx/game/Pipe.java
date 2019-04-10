package com.mygdx.game;
import java.util.*;

public class Pipe {
	protected int x, y, width, height, gap;
	
	public Pipe(int height, int width) {
		Random rand = new Random();
		this.gap = 120;
		this.y = rand.nextInt(height - this.gap) + this.gap;
		//if (player.getScore() % 2 == 0) {
		//	this.y = height;
		//}
		//else {
		//	this.y = this.gap;
		//}
		this.x = width;
		this.width = 100;
		this.height = height - this.y;
	}
	
	
	
	public int getGap() {
		return gap;
	}
	
	public void setX(int value) {
		x = value;
	}
	
	public int getX() { 
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
