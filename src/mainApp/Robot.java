package mainApp;

public class Robot {
	int x;
	int y;
	public Robot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void collideWith(Entity other) {
		other.collideHorizontalAlien(new RobotAdapter(this.x, this.y, this));
	}
	
	public String getLetter() {
		return "R";
	}
}
