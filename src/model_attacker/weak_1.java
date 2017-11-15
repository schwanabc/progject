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

public class Weak_1 extends Attacker{

	public Weak_1(double x,double y)
	{
		//all stat is temporary
		this.ATK=10;
		this.DEF=5;
		this.posX=x;
		this.posY=y;
		this.speed=2.5;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.setHP(100);
		this.MaxHP=getHP();
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
		Random rand = new Random();

		int  n = rand.nextInt(100) + 1;
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
	
	
	public static double getRADIUS() {
		return RADIUS;
	}
	public static double getDIAMETER() {
		return DIAMETER;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

}
