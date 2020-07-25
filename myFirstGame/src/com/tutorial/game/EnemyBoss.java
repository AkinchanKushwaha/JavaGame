package com.tutorial.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBoss extends GameObject {
	
	private Handler handler ;
	
	private int timer =80;
	private int timer2 = 50;

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer<=0) velY = 0 ;
		else timer--;
		
		if(timer<= 0) timer2 --;
		if(timer2<=0) {
			if(velX == 0 ) velX = 2;
		}
		
		//if(y<= 0 || y>= Game.HEIGHT-32) velY*= -1;
		if(x<= 0 || x>= Game.WIDTH-100) velX*= -1;
		
//		 handler.addObject(new Trail((int)x,(int)y,ID.Trail, Color.red, 96, 96, 0.008f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 96, 96);
		
		
	}
	 public Rectangle getBounds() {
		
			return new Rectangle((int)x,(int)y, 96,96);
	}

}
