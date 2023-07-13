package mainApp;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ScreenComponent extends JComponent implements Subject{
	private Entity hero;
	private ArrayList<Entity> entities;
	private LevelLoader levelLoader;
	private int width;
	private int height;
	private int lives; 
	private int bombs; 
	private int bombCount; 
	private int lvl;
    private List<Observer> observers;
	
	public ScreenComponent(int width, int height) throws FileNotFoundException {
		this.lvl = 1;
		this.levelLoader = new LevelLoader(lvl);
		entities = new ArrayList<Entity>();
		this.width = width;
		this.height = height;
		spawnEntities(); //new
		this.lives = ((HeroComponent) hero).getLives(); 
		this.bombCount = levelLoader.getBombs().size();
		this.observers = new ArrayList<>();
	}
	
	/*
	 * NEW: getting lives
	 */
	public String getLives() {
		String message = "";
		if(this.lives == 0) {
			this.lives = 1;
			message = "GAME OVER";
			try {
				changeLevel(lvl +1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	
	public void spawnEntities() {
		ScreenComponentSpawner spawner = new ConcreteSpawner(this.entities);
		
		hero = spawner.addHero(levelLoader);
		spawner.addBorders(this.height, this.width);
		spawner.addHorizontalAliens(levelLoader);
		spawner.addVerticalAliens(levelLoader);
		spawner.addTrackerAliens(levelLoader);
		spawner.addBombs(levelLoader);
		spawner.addPlatforms(levelLoader);
		spawner.setTracker();
		
		this.entities = spawner.getEntities();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Entity entity : entities) {
		entity.paintComponent(g);
		}
	}
	
	
	public void handleArrowPressedInput(int id) {
		((HeroComponent) hero).handleArrowPressedInput(id);
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
		((HeroComponent) hero).handleArrowReleasedInput(id);
	}
	
	public void updateState() {
        notifyObservers();
		
		for(int i=0;i<entities.size();i++)
		{
			for(int j=0;j<entities.size();j++)
			{
				if(entities.get(i).overlaps(entities.get(j)))
					Entity.collide(entities.get(i), entities.get(j));
			}
		}
		this.lives = ((HeroComponent) hero).getLives(); //keep this for the label to update correctly
		this.bombs = ((HeroComponent) hero).getBombs(); //keep this for the label to update correctly
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
	



    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
	
}