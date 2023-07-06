package mainApp;

public abstract class EntityDecorator extends Entity {
    protected Entity decoratedEntity;

    public EntityDecorator(Entity decoratedEntity) {
        super(decoratedEntity.x, decoratedEntity.y, decoratedEntity.vx, decoratedEntity.vy, decoratedEntity.hitBoxWidth, decoratedEntity.hitBoxHeight);
        this.decoratedEntity = decoratedEntity;
    }

    @Override
    public void update() {
        decoratedEntity.update();
    }

    // Override other methods as needed
}
