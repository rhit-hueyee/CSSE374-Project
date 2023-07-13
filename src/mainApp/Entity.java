package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Entity extends JComponent{
	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	protected int ax;
	protected int ay;
	protected int friction;
	protected double elasticity;
	protected int speedlimit;
	protected static final double GAMETICK=50;
	protected boolean shouldRemove;
	protected int hitBoxWidth;
	protected int hitBoxHeight;
	
	public abstract void update();
	
	/**
	 * Constructor
	 * 
	 * @param x X coordinate of upper left corner of the rectangle boxing the entity
	 * @param y Y coordinate of upper left corner of the rectangle boxing the entity
	 * @param vx Velocity on X direction
	 * @param vy Velocity on Y direction
	 * @param hitBoxWidth width of the hitbox
	 * @param hitBoxHeight height of the hitbox
	 */
	public Entity(int x,int y,int vx,int vy,int hitBoxWidth, int hitBoxHeight)
	{
		this.x=x;
		this.y=y;
		this.vx=vx;
		this.vy=vy;
		this.hitBoxWidth=hitBoxWidth;
		this.hitBoxHeight=hitBoxHeight;
		shouldRemove=false;
	}
	
	/**
	 * @return the path of the entity in next gametick
	 */
	public int[] getHitPath()
	{
		int[] arr={x,y,vx,vy};
		
		if(vx >=0)
			arr[2]+=getHitBox()[0];
		else
			arr[0]+=getHitBox()[0];
		if(vy>=0)
			arr[3]+=getHitBox()[1];
		else
			arr[1]+=getHitBox()[1];
		
		return arr;
	}
	
	/**
	 * Check if this entity overlaps with another entity
	 * 
	 * @param other the other entity we are checking
	 * @return true if overlaps, false otherwise
	 */
	public boolean overlaps(Entity other)
	{
		/*
		int[] path1=this.getHitPath();
		int[] path2=this.getHitPath();
		return(Math.abs(path2[0]-path1[0])!=Math.abs(path2[2]-path1[0])||
				Math.abs(path2[0]-path1[2])!=Math.abs(path2[2]-path1[2])&&
				Math.abs(path2[1]-path1[1])!=Math.abs(path2[3]-path1[1])||
				Math.abs(path2[1]-path1[3])!=Math.abs(path2[3]-path1[3]));
				*/
		return Math.abs(this.x-other.getX()+(hitBoxWidth-other.getHitBox()[0])/2)<=(hitBoxWidth+other.getHitBox()[0])/2 &&
				Math.abs(this.y-other.getY()+(hitBoxHeight-other.getHitBox()[1])/2)<=(hitBoxHeight+other.getHitBox()[1])/2;
	}
	
	/**
	 * @return the {width of the hitbox, height of the hitbox}
	 */
	public int[] getHitBox()
	{
		int[] arr={hitBoxWidth,hitBoxHeight};
		return arr;
	}
	
	/**
	 *@return X coordinate of upper left corner of the rectangle boxing the entity
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 *@return Y coordinate of upper left corner of the rectangle boxing the entity
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * Do everything when the entity dies
	 */
	public abstract void onRemove();
	
	public ArrayList<Integer> getFileValues() {
		ArrayList<Integer>  result = new ArrayList<Integer>();
		result.add(getX());
		result.add(getY());
		return result;
	}
	
	public abstract String getType();
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	public void drawScreen() {
		this.repaint();
	}
	
	
	/**
	 * Called whenever two entities collide
	 * 
	 * @param e1 a entity in the collision
	 * @param e2 the other entity in the collision
	 */
	public static void collide(Entity e1,Entity e2)
	{
		e1.collideWith(e2);
		e2.collideWith(e1);
	}
	
	/**
	 * Must be override for every subclass. The method should only contain this one line:
	 * other.collide[The name of the class](this);
	 * 
	 * @param other the entity it's colliding with
	 */
	public abstract void collideWith(Entity other);
	
	
	/**
	 * implements all of the methods below based on need
	 * @param other
	 */
	public abstract void collideHero(Entity other);
	public abstract void collideHorizontalAlien(Entity other);
	public abstract void collideVerticalAlien(Entity other);
	public abstract void collideBomb(Entity other);
	public abstract void collidePlatform(Entity other);
}
