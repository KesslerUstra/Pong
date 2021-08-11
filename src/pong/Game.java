package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable,KeyListener{
	
	public static int WIDTH = 160,HEIGHT = 120,SCALE = 3;
	private boolean isRunning;
	public static Player player;
	public  static Enemy enemy;
	public static Ball ball;
	private BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(0,40,5,40);
		enemy = new Enemy(WIDTH-5,40,5,40);
		ball = new Ball(WIDTH/2,HEIGHT/2,2,2);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		new Thread(game).start();
		
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs =  this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("Arial",Font.BOLD,9));
		g.drawString("V.1.0",138,120);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
		
	}

	@Override
	public void run() {
		requestFocus();
		isRunning = true;
		long lastTime = System.nanoTime();
		double frames_quantidade = 60.0;
		double ns = 1000000000/frames_quantidade;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now- lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >=1000) {
				System.out.println("FPS : "+frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP :
			player.up();
		break;
		case KeyEvent.VK_DOWN :
			player.down();
		break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP :
			player.upFalse();
		break;
		case KeyEvent.VK_DOWN :
			player.downFalse();
		break;
		}
		
	}
}
