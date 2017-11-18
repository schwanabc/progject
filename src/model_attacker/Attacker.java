package model_attacker;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model_defender.Defender;
import model_general.Entity;

public abstract class Attacker extends Entity{
	protected double RADIUS;
	protected double DIAMETER;
	protected static int HiringCost;
	protected static int MinCost=50;
	public Attacker(){}
	public Attacker(double posX,double posY)
	{
		this.posX=posX;
		this.posY=posY;
	}
	public void foward(double xAxis,double yAxis)
	{
		posX+=xAxis*speed;
		posY+=yAxis*speed;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
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
		/*if(ColliedwithAttacker()) //collide with another attacker ****BUG****
			{
				goback=true;
			}
		*/
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
			//	System.out.println(defender.getPosX()+" "+defender.getPosY());
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
	protected boolean ColliedwithAttacker()
	{
		int count=0;
		for(Attacker attacker: logic.Gamelogic.getAttackercontainer())
		{
			Circle c1=new Circle( posX, posY, RADIUS);
			Circle c2=new Circle( attacker.getPosX(),  attacker.getPosY(), attacker.RADIUS);
			if (c1.getBoundsInParent().intersects(c2.getBoundsInParent())) {
		        count++;
		      }
		}
		if(count>1) return true;
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
