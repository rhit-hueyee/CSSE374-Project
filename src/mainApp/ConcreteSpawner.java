package mainApp;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcreteSpawner extends ScreenComponentSpawner {
	private Entity mainHero;

	public ConcreteSpawner(ArrayList<Entity> entities) {
		super(entities);
	}

	@Override
	protected Entity addHero(LevelLoader levelLoader) {
		Entity hero = new HeroComponent(levelLoader.getHero());
		if(levelLoader.getLevel().contains("2")) {
			hero = new SpeedDecorator(hero);
		}
		entities.add(hero);	
		this.mainHero = hero;
		return hero;
	}
	
	protected void addBorders(int height, int width) {
		entities.add(new Platform(0,0,10,height));
		entities.add(new Platform(0,height-80,width,10));
		entities.add(new Platform(0,0,width,10));
		entities.add(new Platform(width-20,0,10,height));
	}

	@Override
	protected void addHorizontalAliens(LevelLoader levelLoader) {
		for(int i = 0; i < levelLoader.getHorizontalAliens().size(); i++) {
			AlienComponentFactory factory = new HorizontalAlienFactory();
			AlienComponent horizontalAlien = factory.createAlienComponent(levelLoader.getHorizontalAliens().get(i));
			entities.add(horizontalAlien);
			//entities.add(new HorizontalAlien(levelLoader.getHorizontalAliens().get(i)));
		}
		
	}

	@Override
	protected void addVerticalAliens(LevelLoader levelLoader) {
		for(int i = 0; i < levelLoader.getVerticalAliens().size(); i++) {
			AlienComponentFactory factory = new VerticalAlienFactory();
			AlienComponent verticalAlien = factory.createAlienComponent(levelLoader.getVerticalAliens().get(i));
			entities.add(verticalAlien);
			//entities.add(new VerticalAlien(levelLoader.getVerticalAliens().get(i)));
		}
		
	}

	@Override
	protected void addTrackerAliens(LevelLoader levelLoader) {
		for(int i = 0; i < levelLoader.getTrackerAliens().size(); i++) {
			AlienComponentFactory factory = new TrackerAlienFactory(mainHero);
			AlienComponent trackerAlien = factory.createAlienComponent(levelLoader.getTrackerAliens().get(i));
			entities.add(trackerAlien);
			//entities.add(new TrackerAlien(levelLoader.getTrackerAliens().get(i), hero));
		}
		
	}

	@Override
	protected void addBombs(LevelLoader levelLoader) {
		for(int i = 0; i < levelLoader.getBombs().size(); i++) {
			entities.add(new BombComponent(levelLoader.getBombs().get(i)));
		}
		
	}

	@Override
	protected void addPlatforms(LevelLoader levelLoader) {
		for(int i = 0; i < levelLoader.getPlatforms().size(); i++) {
			entities.add(new Platform(levelLoader.getPlatforms().get(i)));
		}
		
	}

	@Override
	protected void setTracker() {
		// Iterate over entities using the iterator
		Iterator<Entity> iterator = new ScreenComponentIterator(entities);
		while (iterator.hasNext()) {
		    Entity e = iterator.next();
		    if(e instanceof TrackerAlien)
				((TrackerAlien)e).setMap(entities);
		}
	}


}
