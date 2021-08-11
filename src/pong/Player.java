package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	private boolean up, down;
	public int x, y,width,height;
	
	public Player(int x,int y,int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void up() {
		this.up = true;
	}
	
	public void down() {
		this.down = true;
	}
	
	public void upFalse() {
		this.up = false;
	}
	
	public void downFalse() {
		this.down = false;
	}
	
	public void tick() {
		if(up) {
			y--;
		}
		if(down) {
			y++;
		}
		
		if(y < 0) {
			y = 0;
		}
		
		if(y > Game.HEIGHT-height) {
			y = Game.HEIGHT-height;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0,255,0));
		g.fillRect(x, y,width,height);
	}
}
