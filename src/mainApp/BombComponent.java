package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * New: Bomb class. Very basic just trying to add some bombs. 
 */
public class BombComponent extends Entity{
	private static final int hitBoxWidth=20;
	private static final int hitBoxHeight=20;
	private BufferedImage bombImage;
	private static final int DEFAULT_SIZE = 40;
	
	public BombComponent(int x, int y) {
		super(x,y,0,0,hitBoxWidth,hitBoxHeight);
	}
	
	public BombComponent(int[] data) {
		super(data[0], data[1], 0, 0,hitBoxWidth,hitBoxHeight );
		// TODO Auto-generated constructor stub
		try {
			this.bombImage = ImageIO.read(getClass().getResource("/bomb2.jpg")); //it checks here
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g = g.create();
		g.translate(x, y);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, hitBoxWidth, hitBoxHeight);
		g.drawImage(this.bombImage, -(hitBoxHeight), -(hitBoxHeight), DEFAULT_SIZE, DEFAULT_SIZE, null);
	}
	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWith(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHero(Entity other) {
		this.shouldRemove = true; //not working properly yet
		other.collideBomb(this);
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
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "B";
	}
}


