package mainApp;

public class VerticalAlienFactory extends AlienComponentFactory {
    @Override
    public AlienComponent createAlienComponent(int x, int y) {
        return new VerticalAlien(x, y);
    }

    @Override
    public AlienComponent createAlienComponent(int[] data) {
        return new VerticalAlien(data);
    }
}