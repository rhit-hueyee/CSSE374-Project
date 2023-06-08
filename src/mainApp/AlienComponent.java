package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class AlienComponent extends Entity{
	private static final int DEFULThitBoxWidth=10;
	private static final int DEFULThitBoxHeight=10;

	
	/*
	 * To make an alien go in one direction, set one of the 'v' variables to 0.
	 */
	
	public AlienComponent(int[] data) {
		super(data[0], data[1], data[2], data[3], DEFULThitBoxHeight,DEFULThitBoxHeight);
	}
	
	public AlienComponent(int x, int y, int vx, int vy) {
		super(x,y,vx,vy,DEFULThitBoxHeight,DEFULThitBoxHeight);
	}
	
	public AlienComponent(int x, int y) {
		super(x,y,0,0,DEFULThitBoxHeight,DEFULThitBoxHeight);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g = g.create();
		g.translate(x, y);
		g.setColor(Color.GREEN);
		g.fillOval(0, 0, hitBoxWidth, hitBoxHeight);
	}
	
	public void update() {
		this.x+=vx;
		this.y+=vy;
	}
	
	@Override
	public void onRemove() {
		
	}

	@Override
	public void collideHero(Entity other) {
		
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
	}

	@Override
	public void collideVerticalAlien(Entity other) {
	}

	/*
	 * Will need a method somewhere to handle when the desired number of bombs is reached
	 * Cannot get the bomb value to only increase by 1
	 */
	@Override
	public void collideBomb(Entity other) {
	}

	@Override
	public void collidePlatform(Entity other) {
		if(this.y+hitBoxHeight<=other.getY()+10)
		{
			this.y=other.getY()-hitBoxHeight;
		}
		else if(this.y>=other.getY()+other.getHitBox()[1]-10)
		{
			this.y=other.getY()+other.getHitBox()[1];
		}
		else if(this.x+hitBoxWidth/2>=other.getX()+other.getHitBox()[0]/2)
		{
			this.x=other.x+other.getHitBox()[0];
		}
		else if(this.x+hitBoxWidth/2<=other.getX()+other.getHitBox()[0]/2)
		{
			this.x=other.x-hitBoxWidth;
		}
	}
}
