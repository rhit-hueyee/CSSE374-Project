package mainApp;

public class StateAlienHorizontalState implements StateAlienState {
	public void updateState(StateAlien context) {
		context.setVx(2);
		context.setVy(0);
	}
}
