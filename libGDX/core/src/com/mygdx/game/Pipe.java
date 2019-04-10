package com.mygdx.game;
import java.util.*;

public class Pipe {
	protected int x, y, width, height;
	
	public Pipe(int height, int width) {
		Random rand = new Random();
		this.y = rand.nextInt(height - 120) + 120;
		this.x = width;
		this.width = 100;
		this.height = height - this.y;
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
