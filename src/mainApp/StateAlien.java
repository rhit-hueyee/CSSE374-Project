package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class StateAlien extends AlienComponent {
	private StateAlienState currentState;
	
	public StateAlien(int x, int y) {
		super (x ,y);
		currentState = new StateAlienVerticalState();
	}
	
	public void changeState(StateAlienState newState) {
		currentState = newState;
	}
	
	@Override
	public void update() {
		currentState.updateState(this);
		super.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

        g = g.create();
        g.translate(x, y);
        g.setColor(Color.RED);
        g.fillOval(0, 0, hitBoxWidth, hitBoxHeight);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collideWith(Entity other) {
		if(this.currentState instanceof StateAlienVerticalState) {
			this.changeState(new StateAlienHorizontalState());
		} else {
			this.changeState(new StateAlienVerticalState());
		}
		
		
	}

	public void setVx(int newVx) {
		this.vx = newVx;
	}
	
	public void setVy(int newVy) {
		this.vy = newVy;
	}
	
	
	
	
}
