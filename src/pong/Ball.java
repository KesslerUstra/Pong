package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	private double x, y,dx,dy,speed;
	private int width, height,angle;
	
	public Ball(int x, int y,int width, int height) {
		this.speed = 1.7;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		angle = new Random().nextInt(75-45) + 46;
		dx =  Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}

	public double getY() {
		return y;
	}

	public void tick() {
		
		if((y+dy*speed + height)>= Game.HEIGHT) {
			dy*=-1;
		}
		else if(y +(dy*speed) < 0) {
			dy*=-1;
		}
		
		if(x > Game.WIDTH) {
			//Ponto do Player
			new Game();
			return;
		}
		else if(x < 0) {
			//Ponto do Bot
			new Game();
			return;
		}
		
		Rectangle boundsBall = new Rectangle((int)(x+dx*speed),(int)(y+dy*speed),width,height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
		
		if(boundsBall.intersects(boundsPlayer)) {
			angle = new Random().nextInt(75-45) + 46;
			dx =  Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx < 0) {
				dx*=-1;
			}
			
		}
		else if(boundsBall.intersects(boundsEnemy)) {
			angle = new Random().nextInt(75-45) + 46;
			dx =  Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx > 0) {
				dx*=-1;
			}
		}
		
		y+=dy*speed;
		x+=dx*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255,255,255));
		g.fillRect((int)x,(int) y,width,height);
	}
}
