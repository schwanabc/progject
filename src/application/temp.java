
/*package model_attacker;

import Input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Gamelogic;
import model_defender.Defender;
import model_general.Entity;

public class weak_1 extends Attacker{

	public weak_1(double x,double y)
	{
		//all stat is temporary
		this.radius=8;
		this.ATK=10;
		this.DEF=5;
		this.posX=x;
		this.posY=y;
		this.speed=2.5;
		this.HP=100.0;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.RED);
		gc.fillOval(posX-radius, posY-radius, diameter, diameter);
	}
	
	@Override
	public void update() {
		// UPGRADING
		//need decent moving algorithm
		foward(1,0);//only test (can be delete)
		boolean goback=false;
		if(ColliedwithAttacker()) //collide with another attacker
			{
				goback=true;
			}
		else if(ColliedwithDefender())//collide with another Defender
			{
				goback=true;
			}
		if(goback==true) 
			{
				foward(-1,0); //if collied go backward
			}
	}
	protected boolean ColliedwithAttacker()
	{
		int count=0;
		for(Attacker attacker: logic.Gamelogic.getAttackercontainer())
		{
			return Math.hypot(this.posX-attacker.getPosX(), this.posY-attacker.getPosY()) <= radius+attacker.getRadius();
		}
		if(count>1) return true;
		return false;
		
	}
	protected boolean ColliedwithDefender()
	{
		int count=0;
		for(Defender defender: logic.Gamelogic.getDefendercontainer())
		{
			
		}
		if(count>0) return true;
		return false;
		
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

}
*/