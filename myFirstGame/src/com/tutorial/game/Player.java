package com.tutorial.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random() ;
	Handler handler ;


	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x,y, 32,32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
		x = Game.clamp(x,0, Game.WIDTH-45);
		y = Game.clamp(y,0, Game.HEIGHT-70);
		
		handler.addObject(new Trail(x,y,ID.Trail, Color.WHITE, 32, 32, 0.1f, handler));
	
		collision();
	
	}
	
	private void collision() {
		for(int i = 0 ; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) { //tempObject is now basic enemy.
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
				}
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(x, y , 32, 32);
	
	}

	
}
