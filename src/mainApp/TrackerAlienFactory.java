package mainApp;

import mainApp.TrackerAlien;

public class TrackerAlienFactory extends AlienComponentFactory {
       private Entity target;

	public TrackerAlienFactory(Entity target) {
        this.target = target;
    }

    @Override
    public AlienComponent createAlienComponent(int x, int y) {
        return new TrackerAlien(x, y, target);
    }

    @Override
    public AlienComponent createAlienComponent(int[] data) {
        return new TrackerAlien(data, target);
    }
}
