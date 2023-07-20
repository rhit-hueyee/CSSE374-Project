package mainApp;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrackerAlien extends AlienComponent{
	protected ArrayList<Entity> map;
	protected boolean[][] blocks;
	private Point wayPoint;
	private Entity target;
	private int tickcount;
	public TrackerAlien(int x, int y, Entity target) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.target=target;
		map=new ArrayList<Entity>();
		tickcount=0;
		blocks=new boolean[101][56];
	}
	
	public TrackerAlien(int[] is, Entity target) {
		super(is[0], is[1]);
		this.target=target;
		map=new ArrayList<Entity>();
		wayPoint=new Point(0,0);
		tickcount=0;
		blocks=new boolean[101][56];
	}
	
	public void setMap(ArrayList<Entity> input)
	{
		for(Entity e:input)
		{
			if(e instanceof  Platform)
			{
				this.map.add(e);
			}
		}
		
		for(Entity e:map)
		{
			for(int i=(e.x-9)/10;i<=(e.x+e.hitBoxWidth)/10;i++)
			{
				for(int j=(e.y-9)/10;j<=(e.y+e.hitBoxHeight)/10;j++)
				{
					if(i>=0&&i<101&&j>=0&&j<56)
						blocks[i][j]=true;
				}
			}
		}
	}
	
	
	public void getTargetLocation() {
		int a = wayPoint.x - this.getX();
		int b = wayPoint.y - this.getY();
		if(a<0) {
			this.vx = -2;
		}
		else if(a == 0) {
			this.vx = 0;
		}
		else {
			this.vx = 2;
		}

		if(b<0) {
			this.vy = -2;
		}
		else if(b == 0) {
			this.vy = 0;
		}
		else {
			this.vy = 2;
		}
		
	}
	
	public int track()
	{
		double a,b;
		a=((double)this.y-target.y)/((double)this.x-target.x);
		b=this.y-a*this.x;
		Entity nearest=null;
		double shortest=Double.MAX_VALUE;
		for(Entity e:map)
		{
			if(ifBlocks(e,a,b))
			{
				if(onTheWay(e))
				{
					if(nearest==null)
						nearest=e;
					else if(Math.pow(e.x-this.x, 2)+Math.pow(e.y-this.y, 2)<shortest)
					{
						nearest=e;
						shortest=Math.pow(e.x-this.x, 2)+Math.pow(e.y-this.y, 2);
					}
					else if(Math.pow(e.x+e.getHitBox()[0]-this.x,2)+Math.pow(e.y+e.getHitBox()[1]-this.y,2)<shortest)
					{
						nearest=e;
						shortest=Math.pow(e.x+e.getHitBox()[0]-this.x,2)+Math.pow(e.y+e.getHitBox()[1]-this.y,2);
					}
				}
			}
		}
		
		if(nearest==null)
		{
			this.wayPoint=new Point(target.x,target.y);
			return 0;
		}
		double a1,b1,a2,b2;
		if(a<0)
		{
			Point p1=new Point(nearest.x-15,nearest.y-15);
			Point p2=new Point(nearest.x+nearest.hitBoxWidth+15,nearest.y+nearest.hitBoxWidth+15);
			for(Entity e:map)
			{
				a1=((double)p1.y-target.y)/((double)p1.x-target.x);
				b1=p1.y-a*p1.x;
				a2=((double)p2.y-target.y)/((double)p2.x-target.x);
				b2=p2.y-a*p2.x;
				
				if(ifBlocks(e,a1,b1))
				{
					
					if(onTheWay(e))
					{
						if(ifBlocks(e,a2,b2))
						{
							if(onTheWay(e))
							{
								this.wayPoint=new Point(target.x,target.y);
								return 0;
							}
						}
						this.wayPoint=p2;
						return 1;
					}
				}
				if(ifBlocks(e,a2,b2))
				{
					if(onTheWay(e))
					{
						this.wayPoint=p1;
						return 1;
					}
				}

			}
			this.wayPoint=shorter(p1,p2);
			return 1;
		}
		else
		{
			Point p1=new Point(nearest.x+nearest.hitBoxWidth,nearest.y);
			Point p2=new Point(nearest.x+nearest.hitBoxWidth,nearest.y);
			for(Entity e:map)
			{
				a1=((double)p1.y-target.y)/((double)p1.x-target.x);
				b1=p1.y-a*p1.x;
				a2=((double)p2.y-target.y)/((double)p2.x-target.x);
				b2=p2.y-a*p2.x;
				
				if(ifBlocks(e,a1,b1))
				{
					if(onTheWay(e))
					{
						if(ifBlocks(e,a2,b2))
						{
							if(onTheWay(e))
							{
								this.wayPoint=new Point(target.x,target.y);
								return 0;
							}
						}
						this.wayPoint=p2;
						return 1;
					}
				}
				if(ifBlocks(e,a2,b2))
				{
					if(onTheWay(e))
					{
						
						this.wayPoint=p1;
						return 1;
					}
				}

			}
			this.wayPoint=shorter(p1,p2);
			return 2;
		}
		
		/*
		ArrayList<Point> worklist=new ArrayList<Point>();
		worklist.add(new Point(nearest.x,nearest.y));
		Point temp;
		while(worklist.size()!=0)
		{
			temp=worklist.get(0);
			worklist.remove(0);
			a=((double)this.y-target.y)/((double)this.x-target.x);
			b=this.y-a*this.x;
			
		}
		*/
	}
	
	
	public int trackBruh()
	{
		ArrayList<Grid> worklist=new ArrayList<Grid>();
		boolean[][] walked=new boolean[101][56];
		Grid g;
		for(int i=-1;i<=1;i++)
		{
			for(int j=-1;j<=1;j++)
			{
				int[] arr= {this.x+10*i,this.y+10*j};
				g=new Grid(this.x+10*i,this.y+10*j,arr);
				if(g.containsTarget())
				{
					wayPoint=new Point(g.origin[0],g.origin[1]);
					return 0;
				}
				else if(!g.outOfBound()&&!blocks[g.gx/10][g.gy/10])
				{
					sortAdd(worklist,g);
					walked[g.gx/10][g.gy/10]=true;
				}
			}
		}
		
		Grid temp;
		while(worklist.size()>0)
		{
			temp=worklist.remove(0);
			
			for(int i=-1;i<=1;i++)
			{
				for(int j=-1;j<=1;j++)
				{
					g=new Grid(temp.gx+10*i,temp.gy+10*j,temp.origin);
					if(g.containsTarget())
					{
						wayPoint=new Point(g.origin[0],g.origin[1]);
						return 0;
					}
					else if(!g.outOfBound()&&!blocks[g.gx/10][g.gy/10]&&!walked[g.gx/10][g.gy/10])
					{
						sortAdd(worklist,g);
						walked[g.gx/10][g.gy/10]=true;
					}
				}
			}
		}
		
		return 1;
	}
	
	public int sortAdd(ArrayList<Grid> arr,Grid g)
	{
		if(arr.size()==0)
		{
			arr.add(g);
			return 0;
		}
		
		for(int i=0;i<arr.size();i++)
		{
			if(arr.get(i).weight>g.weight)
			{
				arr.add(i,g);
				return 0;
			}
		}
		
		arr.add(g);
		return 0;
	}
	
	public boolean ifBlocks(Entity e, double a,double b)
	{
		double m=e.x*a+b-e.y;
		double n=(e.x+e.getHitBox()[0])*a+b-(e.y+e.getHitBox()[1]);
		
		if((Math.abs(m)<=(double)this.hitBoxHeight*0.5) || (Math.abs(n)<=(double)this.hitBoxHeight*0.5))
			return true;
		else if(m<0 && n>0)
			return true;
		else if(m>0 && n<0)
			return true;
		
		return false;
	}
	
	public boolean onTheWay(Entity e)
	{
		boolean flag=true;
		if(target.x>x)
		{
			if(!(x<e.x+e.hitBoxWidth/2&&e.x+e.hitBoxWidth/2<target.x))
				flag=false;
		}
		else
		{
			if(!(x>e.x+e.hitBoxWidth/2&&e.x+e.hitBoxWidth/2>target.x))
				flag=false;
		}
		
		if(target.y>y)
		{
			if(!(y<e.y+e.hitBoxHeight/2&&e.y+e.hitBoxHeight/2<target.y))
				flag=false;
		}
		else
		{
			if(!(y>e.y+e.hitBoxHeight/2&&e.y+e.hitBoxHeight/2>target.y))
				flag=false;
		}
		
		return flag;
	}
	
	public Point shorter(Point a, Point b)
	{
		if(Math.pow(a.x-target.x, 2)+Math.pow(a.y-target.y, 2)<Math.pow(b.x-target.x, 2)+Math.pow(b.y-target.y, 2))
			return a;
		else
			return b;
	}
	
	public void update() {
		/*
		tickcount++;
		if(tickcount==5)
		{
			track();
			tickcount=0;
		}
		*/
		trackBruh();
		getTargetLocation();
		this.x += vx ;
		this.y += vy;
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideWith(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideHero(Entity other) {
		// TODO Auto-generated method stub
		other.collideHorizontalAlien(this);
	}

	@Override
	public void collideHorizontalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideVerticalAlien(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collideBomb(Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidePlatform(Entity other) {
		super.collidePlatform(other);
		
	}
	
	
	private class Point
	{
		public int x;
		public int y;
		
		public Point(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		
		public boolean compairWith(Entity other)
		{
			return other.getX()==this.x&&other.getY()==this.y;
		}
	}
	
	private class Grid
	{
		public int gx;
		public int gy;
		public int weight;
		public int[] origin;
		
		public Grid(int x,int y,int[] origin)
		{
			this.gx=x/10*10;
			this.gy=y/10*10;
			calWeight();
			this.origin=origin;
		}
		
		public void calWeight()
		{
			weight=Math.max(Math.abs(this.gx-target.x)/10,Math.abs(this.gy-target.y)/10)+
					Math.max(Math.abs(this.gx-x)/10,Math.abs(this.gy-y)/10);
		}
		
		public boolean outOfBound()
		{
			return !(gx>=0&&gx<=1000&&gy>=0&&gy<=550);
		}
		
		public boolean containsTarget()
		{
			return (this.gx-target.x)/10==0&&(this.gy-target.y)/10==0;
		}
	}


	@Override
	public String getType() {
		return "T";
	}
	
	
}
