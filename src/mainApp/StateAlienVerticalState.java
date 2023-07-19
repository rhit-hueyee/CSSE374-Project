package mainApp;

public class StateAlienVerticalState implements StateAlienState {
	public void updateState(StateAlien context) {
		context.setVx(0);
		context.setVy(2);
	}
}
