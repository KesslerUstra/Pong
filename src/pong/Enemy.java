package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public double x, y;
	public int width,height;
	
	public Enemy(int x, int y,int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		y+= (Game.ball.getY() - y- height/2) *0.5;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillRect((int)x,(int) y,width,height);
	}
}
