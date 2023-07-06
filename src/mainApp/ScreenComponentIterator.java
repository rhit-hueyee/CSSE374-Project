package mainApp;

import java.util.Iterator;
import java.util.List;

public class ScreenComponentIterator implements Iterator<Entity> {
    private List<Entity> entities;
    private int currentIndex;

    public ScreenComponentIterator(List<Entity> entities) {
        this.entities = entities;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < entities.size();
    }

    @Override
    public Entity next() {
        return entities.get(currentIndex++);
    }

    // Optional: If you want to support removal of entities during iteration
    @Override
    public void remove() {
        entities.remove(currentIndex - 1);
        currentIndex--;
    }
}
