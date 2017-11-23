package model_attacker;

import java.util.Random;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Gamelogic;
import logic.Ismovable;
import model_defender.Defender;
import model_general.Board;
import model_general.Entity;

public abstract class Attacker extends Entity implements Ismovable{
	protected double RADIUS;
	protected double DIAMETER;
	protected double WallPriority;
	protected double TowerPriority;
	protected double HQPriority;
	protected static int HiringCost;
	protected static int MinCost=50;
	public Attacker(){}
	public Attacker(double posX,double posY)
	{
		this.posX=posX;
		this.posY=posY;
	}
	@Override
	public void foward(double xAxis,double yAxis)
	{
		double Calibrator=Math.abs(xAxis)+Math.abs(yAxis);
		posX+=Calibrate(xAxis,Calibrator)*speed;
		posY+=Calibrate(yAxis,Calibrator)*speed;
	}
	@Override
	public double Calibrate(double velocity, double speed) {
		if(speed!=0)return velocity/speed;
		else return 0;
	}
	@Override
	public int getZ() {
		return 10;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}
	@Override
	public void update() {
		// UPGRADING
		//need decent moving algorithm
	//	foward(0,1);//only test (can be delete)
		double Xregion=GameScreen.GAMESCREEN_WIDTH/2;
		double Yregion=GameScreen.GAMESCREEN_HEIGHT/2;
		ColliedwithAttacker();
		 if(ColliedwithDefender()) {return;}
		 else foward(Board.HQPOSX-posX,Board.HQPOSY-posY); //if collied go backward
	}
	protected boolean ColliedwithDefender()
	{
		int count2=0;
		currentATKTick++;
		double min=999999999;
		int idx=-1;
		boolean iscollide=false;
		for(Defender defender: Gamelogic.getDefendercontainer())
		{
			Circle c=new Circle( posX, posY, RADIUS);
			Rectangle r=new Rectangle(defender.getPosX(),defender.getPosY(),defender.getWallwidth(),defender.getWallheight());
			if (c.getBoundsInParent().intersects(r.getBoundsInParent())) {
		        double dist=Math.hypot(defender.getPosX()+defender.getWallwidth()/2-getPosX(),defender.getPosY()+defender.getWallheight()/2-getPosY());
				if(dist<min)
				{
					min=dist;
					idx=count2;
				}
		        iscollide=true;
		      }
			count2++;
		}
		if(currentATKTick>=AttackTick&&iscollide)
		{
			currentATKTick=0;
			Attack(Gamelogic.getDefendercontainer().get(idx));
		}
		return iscollide;
		
	}
	protected void ColliedwithAttacker()
	{
		// push 2 time to reduced chance that it will move forward and it only take O(n) time
		Fluidpush();
		Fluidpush();
	}
	private void Fluidpush() 
	{
		for(Attacker attacker:Gamelogic.getAttackercontainer())
		{
			try
			{
					double x0=posX;
					double y0=posY;
					double r0=RADIUS;
					double x1=attacker.getPosX();
					double y1=attacker.getPosY();
					double r1=attacker.getRADIUS();
					
					if(Math.hypot(x0-x1, y0-y1) <= (r0 + r1) && Math.hypot(x0-x1, y0-y1)!=0)
					{
						Random rand = new Random();
						int  n = rand.nextInt(2) + 1;
						if(n%2==0)
						{
							attacker.foward(-1*(x0-x1+0.01),-1*(y0-y1));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1+0.01),(y0-y1));
						}
						else
						{
							attacker.foward(-1*(x0-x1-0.01),-1*(y0-y1));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1-0.01),(y0-y1));
						}
					}
			}
			catch(Exception e) {}
		}
		
	}
	protected void drawHPbar(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double ratio=(HP/MaxHP);
		if(ratio<0)ratio=0;
		gc.setFill(Color.LIGHTGREEN);
		if(ratio!=1)gc.fillRect(posX-RADIUS, posY-RADIUS-3, DIAMETER, 4);
		gc.setFill(Color.ORANGERED);
		if(ratio!=1)gc.fillRect(posX-RADIUS+DIAMETER*(ratio), posY-RADIUS-3, DIAMETER*(1-ratio), 4);
	}
	protected void Attack(Defender defender) {
		defender.setHP(defender.getHP() - (ATK-defender.getDEF()));//temp
        defender.chekdestroyed(); //temp
        currentATKTick=0;
	}
	public double getRADIUS() {
		return RADIUS;
	}
	public static int getMinCost() {
		return MinCost;
	}

}
