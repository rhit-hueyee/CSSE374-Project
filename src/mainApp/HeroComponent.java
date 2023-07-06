package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class HeroComponent extends Entity {
	private boolean atAir;
	private boolean touchedGround;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean downPressed;
	
	private int lives; //new
	private int bombs; //new
	private int oldbomb;
	private boolean stat;
	private boolean oldstat;
	
	private BufferedImage Image;
	private static final int DEFAULT_SIZE = 40;
	
	private Color color=Color.RED;
	
	private static final int GRAVITY = 2;
	private static final int JUMPIMPULSE = 20;
	private static final int hitBoxWidth=10;
	private static final int hitBoxHeight=10;
	private static final int ACCELERATION=1;
	private static final int FRICTION=2;
	
	SpeedSettings speedSettings = SpeedSettings.getInstance();
	private int speedlimit;

	public HeroComponent(int x,int y,int vx,int vy) {
		super(x,y,vx,vy,hitBoxWidth,hitBoxHeight);
		atAir = true; 
		ax=ACCELERATION;
		speedlimit=speedSettings.getGlobalSpeedSettings();
		friction=FRICTION;
		lives=4;
	}
	
	public HeroComponent(int[] data) {
		super(data[0],data[1],0,0,hitBoxWidth,hitBoxHeight);
		atAir = true; 
		ax=ACCELERATION;
		speedlimit=speedSettings.getGlobalSpeedSettings();
		friction=FRICTION;
		lives=4;
		try {
			this.Image = ImageIO.read(getClass().getResource("/hero.png")); //it checks here
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HeroComponent(String[][] map) {
		super(0,0,0,0,hitBoxHeight,hitBoxHeight);
		int xStart = 0;
		int yStart = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j =0 ; j < map[0].length; j++) {
				if(map[j][i].equals("J")) {
					xStart = i;
					yStart = j;
				}
			}
		}
		this.x = xStart * 10;
		this.y = yStart * 10;
		atAir = true; 
		ax=ACCELERATION;
		speedlimit=speedSettings.getGlobalSpeedSettings();;
		friction=FRICTION;
		
		this.lives = 4; //new
		this.bombs = 0; //new
		this.oldbomb = this.bombs;
		this.stat = false;
		this.oldstat = this.stat;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g = g.create();
		g.translate(x, y);
		g.setColor(color);
		g.fillRect(0, 0, hitBoxHeight, hitBoxHeight);
		g.drawImage(this.Image, -(hitBoxHeight+5), -(hitBoxHeight*3), DEFAULT_SIZE, DEFAULT_SIZE, null);
	}

	public void update() {
		if(rightPressed)
		{
			vx+=ax;
			if(vx>speedlimit)
				vx=speedlimit;
				
		}
		else if(leftPressed)
		{
			vx-=ax;
			if(vx<-speedlimit)
				vx=-speedlimit;
		}
		else
		{
			if(vx>friction)
				vx-=friction;
			else if(vx<-friction)
				vx+=friction;
			else
				vx=0;
				
		}
		
		if (!atAir&&upPressed)
			vy =-GRAVITY;
		else if(atAir)
			vy= GRAVITY;
		else
			vy=0;
		x += vx;
		y += vy; //takes us down
		if(!touchedGround&&!upPressed)
			atAir=true;
		touchedGround=false;
	}


	public void handleArrowPressedInput(int id) {
		if (id == 38) {
			upPressed=true;
		} else if (id == 39) {
			if (vx < 0)
				vx = 0;
			rightPressed=true;
		} else if (id == 37) {
			if (vx > 0)
				vx = 0;
			leftPressed=true;
		}
	}

	public void handleArrowReleasedInput(int id) {
		if (id == 39) {
			rightPressed=false;
		} else if (id == 37) {
			leftPressed=false;
		}
		else if (id == 38) {
			upPressed=false;
			atAir = true; //start the fall. Wont stop falling until it reaches the ground
		}
	}
	
	/*
	 * NEW: Keeps the lives at zero since remove function isn't currently working
	 */
	public void checkLives(){
		if(this.lives <= 0) {
			this.lives = 0;
		}
	}
	/*
	 * NEW: Getting the lives
	 */
	public int getLives() {
		return this.lives;
	}
	/*
	 * NEW: Getting the number of bombs
	 * trying to limit the number of times the bomb count is added.
	 */
	public int getBombs() {
		if(this.bombs - this.oldbomb >1) {
			this.bombs = (this.oldbomb + 1);
		}
		this.oldbomb = this.bombs;
		return this.bombs;
	}

	@Override
	public void onRemove() {
		
	}

	@Override
	public void collideWith(Entity other) {
		other.collideHero(this);
	}

	@Override
	public void collideHero(Entity other) {
		
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
		this.lives--; //new
		checkLives(); //new
		x=50;
		y=50;
	}

	@Override
	public void collideVerticalAlien(Entity other) {
		// TODO Auto-generated method stub
		this.lives--; //new
		checkLives(); //new
		x=50;
		y=50;
		
	}

	/*
	 * Will need a method somewhere to handle when the desired number of bombs is reached
	 * Cannot get the bomb value to only increase by 1
	 */
	@Override
	public void collideBomb(Entity other) {
		// TODO Auto-generated method stub
		this.bombs++;
		/*if(this.oldbomb >2) {
			other.x = other.x-10;
		this.bombs++; //new
		}*/
		
	
		
	}

	@Override
	public void collidePlatform(Entity other) {
		if(this.y+hitBoxHeight<=other.getY()+10)
		{
			this.y=other.getY()-hitBoxHeight;
			atAir=false;
			touchedGround=true;	
		}
		else if(this.y>=other.getY()+other.getHitBox()[1]-10)
		{
			this.y=other.getY()+other.getHitBox()[1];
			if(upPressed)
				y+=GRAVITY*2;
		}
		else if(this.x+hitBoxWidth/2>=other.getX()+other.getHitBox()[0]/2)
		{
			this.x=other.x+other.getHitBox()[0];
			if(leftPressed)
				x+=speedSettings.getGlobalSpeedSettings()*2;
		}
		else if(this.x+hitBoxWidth/2<=other.getX()+other.getHitBox()[0]/2)
		{
			this.x=other.x-hitBoxWidth;
			if(rightPressed)
				x-=speedSettings.getGlobalSpeedSettings()*2;
		}
	}

	@Override
	public String getType() {
		return "H";
	}
}
