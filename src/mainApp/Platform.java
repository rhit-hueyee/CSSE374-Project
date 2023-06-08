package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Platform extends Entity{
	
	public Platform(int x, int y, int w, int h) {
		super(x,y,0,0,w,h);
	}
	
	public Platform(int[] data) {
		super(data[0], data[1], 0, 0, data[2], data[3]);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g = g.create();
		g.translate(x, y);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, hitBoxWidth, hitBoxHeight);
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWith(Entity other) {
		other.collidePlatform(this);
		
	}

	@Override
	public void collideHero(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideVerticalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideBomb(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidePlatform(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "P";
	}
	
	@Override
	public ArrayList<Integer> getFileValues(){
		ArrayList<Integer> result = super.getFileValues();
		result.add(super.hitBoxWidth);
		result.add(super.hitBoxHeight);
		return result;
	}
	
}
