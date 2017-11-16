package model_attacker;

import java.util.Random;

import Input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Gamelogic;
import model_defender.Defender;
import model_general.Board;
import model_general.Entity;

public class Weak_2 extends Attacker{

	public Weak_2(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=100;
		this.DEF=5;
		this.speed=2;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.HP=100;
		this.MaxHP=HP;
		this.currentATKTick=0;
		this.AttackTick=100;
		this.HiringCost=120;

	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.INDIANRED);
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
	

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 11;
	}

}
