package mainApp;

public class HorizontalAlienFactory extends AlienComponentFactory {
    @Override
    public AlienComponent createAlienComponent(int x, int y) {
        return new HorizontalAlien(x, y);
    }

    @Override
    public AlienComponent createAlienComponent(int[] data) {
        return new HorizontalAlien(data);
    }
}
