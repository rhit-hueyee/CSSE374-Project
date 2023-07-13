package mainApp;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;

public abstract class ScreenComponentSpawner {
	protected ArrayList<Entity> entities;

	public ScreenComponentSpawner(ArrayList<Entity> entities) {
			this.entities = entities;
	}

	protected abstract Entity addHero(LevelLoader lvlLoader);
	
	protected abstract void addBorders(int height, int width);
	
	protected abstract void addHorizontalAliens(LevelLoader lvlLoader);
	
	protected abstract void addVerticalAliens(LevelLoader lvlLoader);
	
	protected abstract void addTrackerAliens(LevelLoader lvlLoader);
	
	protected abstract void addBombs(LevelLoader lvlLoader);
	
	protected abstract void addPlatforms(LevelLoader lvlLoader);
	
	protected abstract void setTracker();
	
	public ArrayList<Entity> getEntities(){
		return this.entities;
	}
}