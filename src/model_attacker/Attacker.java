package model_attacker;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Ismovable;
import model_defender.Defender;
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
		foward(0,1);//only test (can be delete)
		boolean goback=false;

		 if(ColliedwithDefender())//collide with another Defender
			{
				goback=true;
			}
		if(goback==true) 
			{
				foward(0,-1); //if collied go backward
			}
	}
	protected boolean ColliedwithDefender()
	{
		int count=0;
		currentATKTick++;
		for(Defender defender: logic.Gamelogic.getDefendercontainer())
		{
			Circle c=new Circle( posX, posY, RADIUS);
			Rectangle r=new Rectangle(defender.getPosX(),defender.getPosY(),defender.getWallwidth(),defender.getWallheight());
			if (c.getBoundsInParent().intersects(r.getBoundsInParent())) {
		        count++;
		        if(currentATKTick>=AttackTick)
		        {
		        	Attack(defender);
		        }
		        
		      }
		}
		if(count>0) return true;
		return false;
		
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
