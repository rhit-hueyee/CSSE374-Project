package LevelCreator;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.*;
//
//public class MainCreator {
//
//	public static void main(String[] args) {
//		runApp();
//
//	}
//	
//	private static void runApp()
//	{
//		JFrame frame = new JFrame("Bomb Jack");
//		frame.setSize(1000, 550);
//		
//		LevelCreatorComponent creator=new LevelCreatorComponent();
//		frame.add(creator,BorderLayout.CENTER);
//		
//		JPanel panel=new JPanel();
//		frame.add(panel,BorderLayout.NORTH);
//		JButton drawHero=new JButton("Draw Hero");
//		drawHero.addActionListener(new DrawStateListener(0,creator));
//		panel.add(drawHero);
//		
//		JButton drawPlatform=new JButton("Draw Platform");
//		drawPlatform.addActionListener(new DrawStateListener(1,creator));
//		panel.add(drawPlatform);
//		
//		JButton drawVerticalAlien=new JButton("Draw Vertical Alien");
//		drawVerticalAlien.addActionListener(new DrawStateListener(2,creator));
//		panel.add(drawVerticalAlien);
//		
//		JButton drawHorizontalAlien=new JButton("Draw Horizontal Alien");
//		drawHorizontalAlien.addActionListener(new DrawStateListener(3,creator));
//		panel.add(drawHorizontalAlien);
//		
//		JButton drawTrackerAlien = new JButton("Draw Tracker Alien");
//		drawTrackerAlien.addActionListener(new DrawStateListener(4, creator));
//		panel.add(drawTrackerAlien);
//		
//		JButton drawBomb = new JButton("Draw Bomb");
//		drawBomb.addActionListener(new DrawStateListener(5, creator));
//		panel.add(drawBomb);
//		
//		JButton save = new JButton("Save Level");
//		save.addActionListener(new levelSave(creator));
//		panel.add(save);
//		
//		
//		frame.addMouseListener(new CreatorMouseListener(creator));
//		
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		Timer timer=new Timer(10, new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				creator.drawScreen();
//			}
//			
//		});
//		timer.start();
//	}
//}


public class MainCreator {
    public static void main(String[] args) {
        LevelCreatorFacade facade = new LevelCreatorFacade();
        facade.run();
    }
}
