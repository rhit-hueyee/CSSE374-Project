package LevelCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import mainApp.Entity;

public class levelSave implements ActionListener {
	private int level;
	private LevelCreatorComponent levelInfo;
	private ArrayList<Entity> entities;
	
	public levelSave(LevelCreatorComponent lcc) {
		this.levelInfo = lcc;
		this.level = 1;
		entities = levelInfo.getEntities();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e1) {
		boolean fileAlreadyExists = true;
		while(fileAlreadyExists) {
			try {
				FileReader fReader = new FileReader("level" + this.level + ".txt");
				this.level ++;
		} 	catch (FileNotFoundException e) {
				fileAlreadyExists = false;
			}
		}
		PrintWriter fWriter = null;
				
		try {
			fWriter = new PrintWriter("level" + this.level + ".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Entity e : entities) {
			String printString = "";
			printString += e.getType();
			for(int i = 0; i < e.getFileValues().size(); i++) {
				printString += "," + e.getFileValues().get(i);
			}
			fWriter.println(printString);
		}
		
		fWriter.close();

	}

}
