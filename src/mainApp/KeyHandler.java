package mainApp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	private ScreenComponent screen;
	
	public KeyHandler(ScreenComponent s) {
		this.screen = s;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		screen.handleArrowPressedInput(e.getKeyCode());	

	}

	@Override
	public void keyReleased(KeyEvent e) {
		screen.handleArrowReleasedInput(e.getKeyCode());

	}

}
