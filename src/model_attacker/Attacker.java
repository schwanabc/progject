package model_attacker;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model_defender.Defender;
import model_general.Entity;

public abstract class Attacker extends Entity{
	protected double speed;
	protected  double RADIUS;
	protected  double DIAMETER;
	protected  int currentTick;
	protected  int AttackTick;
	public void foward(double xAxis,double yAxis)
	{
		posX+=xAxis*speed;
		posY+=yAxis*speed;
	}
	protected boolean ColliedwithDefender()
	{
		int count=0;
		currentTick++;
		System.out.println(currentTick+" "+AttackTick);
		for(Defender defender: logic.Gamelogic.getDefendercontainer())
		{
			Circle c=new Circle( posX, posY, RADIUS);
			Rectangle r=new Rectangle(defender.getPosX(),defender.getPosY(),defender.getWALL_WIDTH(),defender.getWALL_HEIGHT());
			if (c.getBoundsInParent().intersects(r.getBoundsInParent())) {
			//	System.out.println(defender.getPosX()+" "+defender.getPosY());
		        count++;
		        if(currentTick>=AttackTick)
		        {
		        	Attack(defender);
		        }
		        
		      }
		}
		if(count>0) return true;
		return false;
		
	}
	protected void Attack(Defender defender) {
		defender.setHP(defender.getHP() - (ATK-defender.getDEF()));//temp
        defender.chekdestroyed(); //temp
        currentTick=0;
	}
	protected boolean ColliedwithAttacker()
	{
		int count=0;
		for(Attacker attacker: logic.Gamelogic.getAttackercontainer())
		{
			Circle c1=new Circle( posX, posY, RADIUS);
			Circle c2=new Circle( attacker.getPosX(), attacker.getPosY(), attacker.RADIUS);
			if (c1.getBoundsInParent().intersects(c2.getBoundsInParent())) {
		        count++;
		      }
		}
		if(count>1) return true;
		return false;
	}
	protected void drawHPbar(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double ratio=HP/MaxHP;
		gc.setFill(Color.LIGHTGREEN);
		if(ratio!=1)gc.fillRect(posX-RADIUS, posY-RADIUS-3, DIAMETER, 4);
		gc.setFill(Color.ORANGERED);
		if(ratio!=1)gc.fillRect(posX-RADIUS, posY-RADIUS-3, DIAMETER*(1-ratio), 4);
	}
}
