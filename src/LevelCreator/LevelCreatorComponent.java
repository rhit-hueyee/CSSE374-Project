package LevelCreator;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import mainApp.BombComponent;
import mainApp.Entity;
import mainApp.HeroComponent;
import mainApp.HorizontalAlien;
import mainApp.Platform;
import mainApp.TrackerAlien;
import mainApp.VerticalAlien;

public class LevelCreatorComponent extends JComponent{
	private HeroComponent hero;
	private ArrayList<Entity> entities;
	private int[] draft;
	
	public final static int XSHIFT=5;
	public final static int YSHIFT=65;
	/**
	 * drawHero: drawState=0;
	 * drawPlatform: drawState=1;
	 * drawVerticalAlien: drawState=2;
	 * drawHorizontalAlien: drawState=3;
	 */
	private int drawState;
	
	public LevelCreatorComponent()
	{
		hero=null;
		entities=new ArrayList<Entity>();
		drawState=0;
		draft=new int[4];
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g=g.create();
		
		if(hero!=null)
			hero.paintComponent(g);
		
		for(Entity e:entities)
		{
			e.paintComponent(g);
		}
	}
	
	public void drawScreen()
	{
		this.repaint();
	}
	
	public void setDrawState(int drawState)
	{
		this.drawState=drawState;
	}
	
	public void handleMousePressedInput(int x,int y)
	{
		draft[0]=x-XSHIFT;
		draft[1]=y-YSHIFT;
	}
	
	public void handleMouseReleasedInput(int x,int y)
	{
		draft[2]=x-XSHIFT;
		draft[3]=y-YSHIFT;
		
		switch(drawState){
		case 0:
			hero=new HeroComponent(draft[0],draft[1],0,0);
			//entities.add(hero);
		break;
		
		case 1:
			int thisx=Math.min(draft[0], draft[2]);
			int thisy=Math.min(draft[1], draft[3]);
			int width=Math.abs(draft[0]-draft[2]);
			int height=Math.abs(draft[1]-draft[3]);
			entities.add(new Platform(thisx,thisy,width,height));
		break;
		
		case 2:
			entities.add(new VerticalAlien(draft[0],draft[1]));
		break;
			
		case 3:
			entities.add(new HorizontalAlien(draft[0],draft[1]));
		break;
		case 4:
			entities.add(new TrackerAlien(draft[0],draft[1], hero));
			break;	
		case 5:
			entities.add(new BombComponent(draft[0],draft[1]));
			break;
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
}
