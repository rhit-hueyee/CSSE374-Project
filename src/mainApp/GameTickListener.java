package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class GameTickListener implements ActionListener {

	private ScreenComponent screen;
	private JLabel label; //new
	
	//private ArrayList<AlienComponent> AlienComponent = new ArrayList<>(); // an array list for the aliens

	public GameTickListener(ScreenComponent component) { // aliens
		this.screen = component;
	}

	//Adding the alien to the array list
	/*
	public void AddAlien(AlienComponent alien) {
		this.AlienComponent.add(alien);
	}
	*/
	
	/*
	 * New: adds the label from the main class so it can be updated
	 */
	public void Addlabel(JLabel label) {
		this.label = label;
		screen.addObserver(new GameLabelObserver(screen, label));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(screen.getLives().equals("GAME OVER")) { //new. Doesn't do anything right now
			//label.setText(screen.getLives());
			//remove the components somehow
		}

		screen.updateState();
		screen.drawScreen();
		label.setText(screen.getLives()); //new
	}

}
