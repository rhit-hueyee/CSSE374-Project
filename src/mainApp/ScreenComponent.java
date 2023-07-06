package mainApp;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ScreenComponent extends JComponent{
	private HeroComponent hero;
	private ArrayList<Entity> entities;
	private LevelLoader levelLoader;
	private int width;
	private int height;
	private int lives; 
	private int bombs; 
	private int bombCount; 
	private int lvl;
	
	public ScreenComponent(int width, int height) throws FileNotFoundException {
		this.lvl = 1;
		this.levelLoader = new LevelLoader(lvl);
		entities = new ArrayList<Entity>();
		this.width = width;
		this.height = height;
		spawnEntities(); //new
		this.lives = hero.getLives(); 
		this.bombCount = levelLoader.getBombs().size();
	}
	
	/*
	 * NEW: getting lives
	 */
	public String getLives() {
		String message = "";
		if(this.lives == 0) {
			this.lives = 1;
			message = "GAME OVER";
			//do something else...
		}
		else if(this.bombs >= this.bombCount){ 	//based on number of bombs in the level
			message = "you won!";
		}
		else {
			message = "<html>Lives: " + this.lives + "<br />Bombs: " + this.bombs+ "</HTML>";
			}
		return message;
	}
	
	public void changeLevel(int lvl) throws FileNotFoundException {
		this.levelLoader = new LevelLoader(lvl);
	}
	
	public void createBorderPlatforms() {
		entities.add(new Platform(0,0,10,height));
		entities.add(new Platform(0,height-80,width,10));
		entities.add(new Platform(0,0,width,10));
		entities.add(new Platform(width-20,0,10,height));
	}
	
	public void spawnEntities() {
		hero = new HeroComponent(levelLoader.getHero());
		entities.add(hero);
		createBorderPlatforms();
		AlienComponentFactory factory = null;
		for(int i = 0; i < levelLoader.getHorizontalAliens().size(); i++) {
			factory = new HorizontalAlienFactory();
			AlienComponent horizontalAlien = factory.createAlienComponent(levelLoader.getHorizontalAliens().get(i));
			entities.add(horizontalAlien);
			//entities.add(new HorizontalAlien(levelLoader.getHorizontalAliens().get(i)));
		}
		for(int i = 0; i < levelLoader.getVerticalAliens().size(); i++) {
			factory = new VerticalAlienFactory();
			AlienComponent verticalAlien = factory.createAlienComponent(levelLoader.getVerticalAliens().get(i));
			entities.add(verticalAlien);
			//entities.add(new VerticalAlien(levelLoader.getVerticalAliens().get(i)));
		}
		for(int i = 0; i < levelLoader.getTrackerAliens().size(); i++) {
			factory = new TrackerAlienFactory();
			AlienComponent trackerAlien = factory.createAlienComponent(levelLoader.getTrackerAliens().get(i));
			entities.add(trackerAlien);
			//entities.add(new TrackerAlien(levelLoader.getTrackerAliens().get(i), hero));
		}
		for(int i = 0; i < levelLoader.getBombs().size(); i++) {
			//entities.add(new BombComponent(levelLoader.getBombs().get(i)));
		}
		for(int i = 0; i < levelLoader.getPlatforms().size(); i++) {
			//entities.add(new Platform(levelLoader.getPlatforms().get(i)));
		}
		
		for(Entity e:entities)
		{
			if(e instanceof TrackerAlien)
				((TrackerAlien)e).setMap(entities);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Entity entity : entities) {
		entity.paintComponent(g);
		}
	}
	
	
	public void handleArrowPressedInput(int id) {
		hero.handleArrowPressedInput(id);
		if(id == 85) {
		try {
				this.entities = new ArrayList<Entity>();
				this.lvl = this.lvl +1;
				this.levelLoader = new LevelLoader(lvl);
				spawnEntities();
				
			} catch (FileNotFoundException e) {
				System.out.println("End of Game");
			}

		}
		else if(id ==  68) {
			try {
				this.entities = new ArrayList<Entity>();
				this.lvl = this.lvl-1;
				this.levelLoader = new LevelLoader(lvl);
				spawnEntities();
				
			} catch (FileNotFoundException e) {
				System.out.println("Beginning of Game");
			}

		}
	}
	
	public void handleArrowReleasedInput(int id) {
		hero.handleArrowReleasedInput(id);
	}
	
	public void updateState() {
		
		for(int i=0;i<entities.size();i++)
		{
			for(int j=0;j<entities.size();j++)
			{
				if(entities.get(i).overlaps(entities.get(j)))
					Entity.collide(entities.get(i), entities.get(j));
			}
		}
		this.lives = hero.getLives(); //keep this for the label to update correctly
		this.bombs = hero.getBombs(); //keep this for the label to update correctly
		hero.update();
		
		for(int i=0;i<entities.size();i++)
		{
			if(entities.get(i).shouldRemove)
			{
				entities.remove(i);
				i--;
			}
			entities.get(i).update();
		}
	}
	
	public void drawScreen() {
		this.repaint();
	}
	
}