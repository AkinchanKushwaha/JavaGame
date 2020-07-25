package com.tutorial.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -7728264178317937023L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	
	private boolean running = false;
	
	private Random r;
	
	private Handler handler;
	
	private HUD hud;
	
	private Spawn spawner;
	
	
	public Game() {
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		new window(WIDTH ,HEIGHT, "let's build a game!",this);
		
		hud = new HUD();
		
		spawner = new Spawn(handler , hud);
		
		r = new Random();
		
		
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
		
		
		
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));

		
		
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * "lastTime", "now," and "ns" are used to calculate "delta." amountOfTicks is the amount of ticks/second, and ns is the amount of nanoseconds/tick.
		When delta is calculated, you have (now-lastTime)/(ns/tick), but now and lastTime  are in nanoseconds, 
		so it has units "tick". We then add this to delta, and keep going.
		Whenever delta+=1, one tick has passed, and we therefore call the command tick(), 
		and reset delta to 0 in the while(delta>=1) loop.
		the if(running) loop updates the window (by rendering again), and increases the frames with 1.
		the if(System.currentTimeMillis()-timer>1000) loop writes out the FPS once per second by 
		checking if the current time is more than 1000 milliseconds (1 second) larger than "timer" was.
		IF so, we update "timer" to be 1 second later (timer+=1000;), and print the amount of frames that have passed, 
		and set frames to 0. Since this event happens once every second, the value "frames" is the frames per second.
		stop() stops the game.
	 */
	
	public void run() {
		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0 ;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta +=(now - lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000) { 
				timer +=1000;
				System.out.println("FPS "+ frames);
				frames = 0 ;
			}
		}
		stop();
		
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();	
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) 
			return var = max;
		else if(var <= min) 
			return var = min;
		else 
			return var;
	}
	
	public static void main(String[] args) {
		new Game();

	}

}
