package mainApp;

public abstract class AlienComponentFactory {
	public abstract AlienComponent createAlienComponent(int x, int y);
    public abstract AlienComponent createAlienComponent(int[] data);
}
