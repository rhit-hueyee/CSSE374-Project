package mainApp;

public class SpeedDecorator extends EntityDecorator {

    public SpeedDecorator(Entity decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public void update() {
        // Modify the speed of the decorated entity
        int increasedSpeed = 2;
        this.speedlimit += increasedSpeed;

        // Call the original update method of the decorated entity
        super.update();
    }

	@Override
	public void onRemove() {
		decoratedEntity.onRemove();
		
	}

	@Override
	public String getType() {
		return decoratedEntity.getType();
	}

	@Override
	public void collideWith(Entity other) {
		decoratedEntity.collideWith(other);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHero(Entity other) {
		decoratedEntity.collideHero(other);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
		decoratedEntity.collideHorizontalAlien(other);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideVerticalAlien(Entity other) {
		decoratedEntity.collideVerticalAlien(other);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideBomb(Entity other) {
		decoratedEntity.collideBomb(other);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidePlatform(Entity other) {
		decoratedEntity.collidePlatform(other);
		// TODO Auto-generated method stub
		
	}
}

