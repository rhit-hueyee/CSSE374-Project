package mainApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComponent;

public class LevelLoader{
	private String level;
	private int[] hero;
	private ArrayList<int[]> horizontalAliens;
	private ArrayList<int[]> verticalAliens;
	private ArrayList<int[]> trackerAliens;
	private ArrayList<int[]> bombs;
	private ArrayList<int[]> platforms;
	
	public LevelLoader(int lvl) throws FileNotFoundException {
		this.level = "level" + lvl + ".txt";
		this.hero = new int[2];
		this.horizontalAliens = new ArrayList<int[]>();
		this.verticalAliens = new ArrayList<int[]>();
		this.trackerAliens = new ArrayList<int[]>();		
		this.bombs = new ArrayList<int[]>();
		this.platforms = new ArrayList<int[]>();
		refresh();
		
	}
	
	public void refresh() throws FileNotFoundException {
		FileReader fReader = new FileReader(this.level);
		Scanner s = new Scanner(fReader);
		String line;
		int[] tempArray = null;
		while(s.hasNextLine()) {
			//for each line
			line = s.nextLine();
			String[] data = line.split(",");
			//determine which fields to add to
			switch (data[0]) {
			case "H": 
				for(int i = 1; i < data.length; i++) { 
					hero[i-1] = Integer.parseInt(data[i]);
				}
				break;
			case "J":
				tempArray = new int[2];
				for(int i = 1; i < data.length; i++) { 
					tempArray[i-1] = Integer.parseInt(data[i]);
				}
				horizontalAliens.add(tempArray);
				break;
			case "V":
				tempArray = new int[2];
				for(int i = 1; i < data.length; i++) { 
					tempArray[i-1] = Integer.parseInt(data[i]);
				}
				verticalAliens.add(tempArray);
				break;
			case "T":
				tempArray = new int[2];
				for(int i = 1; i < data.length; i++) { 
					tempArray[i-1] = Integer.parseInt(data[i]);
				}
				trackerAliens.add(tempArray);
				break;
				
			case "B":
				tempArray = new int[2];
				for(int i = 1; i < data.length; i++) { 
					tempArray[i-1] = Integer.parseInt(data[i]);
				}
				bombs.add(tempArray);
				break;
			case "P":
				tempArray = new int[4];
				for(int i = 1; i < data.length; i++) { 
					tempArray[i-1] = Integer.parseInt(data[i]);
				}
				platforms.add(tempArray);
				break;
			}
			
		}
		s.close();
	}
	
	public int[] getHero(){
		return hero;
	}
	
	public ArrayList<int[]> getHorizontalAliens(){
		return horizontalAliens;
	}
	
	public ArrayList<int[]> getVerticalAliens(){
		return verticalAliens;
	}
	
	public ArrayList<int[]> getTrackerAliens(){
		return trackerAliens;
	}
	
	public ArrayList<int[]> getBombs(){
		return bombs;
	}
	
	public ArrayList<int[]> getPlatforms(){
		return platforms;
	}
	
	
	
}
