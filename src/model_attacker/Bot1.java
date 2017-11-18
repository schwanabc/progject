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

public class Bot1 extends Attacker{
	
	
	public Bot1(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=500;
		this.DEF=5;
		this.speed=2;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.HP=1000;
		this.MaxHP=HP;
		this.AttackTick=100;
		this.currentATKTick=AttackTick;

	}

	public static int getHiringCost() {
		HiringCost=300;
		return HiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.SADDLEBROWN);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}

}
