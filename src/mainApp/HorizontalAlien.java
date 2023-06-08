package mainApp;

public class HorizontalAlien extends AlienComponent {
	private Platform justHitted;
	
	public HorizontalAlien(int x, int y) {
		super(x,y);
		this.vx = 1;
	}
	
	public HorizontalAlien(int[] data) {
		super(data[0],data[1]);
		this.vx = 1;
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
		other.collideHorizontalAlien(this);
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
			this.vx*=-1;
			justHitted=(Platform)other;
		}
	}

	@Override
	public String getType() {
		return "J";
	}
}
