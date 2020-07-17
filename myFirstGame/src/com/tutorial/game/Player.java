package com.tutorial.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random() ;

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		
		
		
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	
		
	}

	@Override
	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		
		
		g.fillRect(x, y , 32, 32);
	
		
	}

}
