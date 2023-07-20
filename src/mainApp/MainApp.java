package mainApp;
//:)
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
// test
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Class: MainApp
 * @author A402 Put your team name here
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	//test
	private JLabel label = new JLabel(); //new
	
	private void runApp() throws FileNotFoundException {
		JFrame frame = new JFrame("Bomb Jack");
		int width = 1000;
		int height = 550;
		frame.setSize(width, height); //changed the y 
		
		SpeedSettings speedSettings = SpeedSettings.getInstance();
		speedSettings.setGlobalSpeedSettings(3);
		
		ScreenComponent drawer = new ScreenComponent(width, height);
		frame.add(drawer,BorderLayout.CENTER);
		

		frame.add(label, BorderLayout.SOUTH);
		
		
		KeyPressedCounterProxy keyPressProxy = new KeyPressedCounterProxy(drawer);
        frame.addKeyListener(keyPressProxy);
		
		//frame.addKeyListener(kh);

		
		GameTickListener gt=new GameTickListener(drawer);
		//Adding aliens to the gametick listener
		/*
		gt.AddAlien(ac);
		gt.AddAlien(ac2);
		*/
		gt.Addlabel(label); //NEW: Adds the label so it can be updated
		
		Timer timer=new Timer(10, gt);
		timer.start();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	} // runApp


	/**
	 * ensures: runs the application
	 * @param args unused
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MainApp mainApp = new MainApp();
		mainApp.runApp();		
	} // main

}