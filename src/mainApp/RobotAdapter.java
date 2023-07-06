package mainApp;

public class RobotAdapter extends AlienComponent{
	private Robot robot;	
	
	public RobotAdapter(int x, int y, Robot robot) {
		super(x, y);
		this.robot = robot;
	}

	@Override
	public void collideWith(Entity other) {
		robot.collideWith(other);
	
	}

	@Override
	public String getType() {
		return robot.getLetter();
	}

}