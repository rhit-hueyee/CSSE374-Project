package mainApp;

public class VerticalAlien extends AlienComponent{
	private Platform justHitted;
	
	public VerticalAlien(int x, int y) {
		super(x,y);
		this.vy = 1;
	}

	public VerticalAlien(int[] data) {
		super(data[0],data[1]);
		this.vy = 1;
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWith(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHero(Entity other) {
		// TODO Auto-generated method stub
		other.collideVerticalAlien(this);
		
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideVerticalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideBomb(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidePlatform(Entity other) {
		if(other!=justHitted)
		{
			super.collidePlatform(other);
			this.vy*=-1;
			justHitted=(Platform)other;
		}
	}

	@Override
	public String getType() {
		return "V";
	}
}
