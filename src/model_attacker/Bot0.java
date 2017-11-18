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

public class Bot0 extends Attacker{

	
	public Bot0(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=40;
		this.DEF=5;
		this.RADIUS=4;
		this.speed=1.5;
		this.DIAMETER=this.RADIUS*2;
		this.HP=100;
		this.MaxHP=HP;
		this.AttackTick=60;
		this.currentATKTick=AttackTick;
		this.WallPriority=10;
		this.TowerPriority=10;
		this.HQPriority=80;
	}

	public static int getHiringCost() {
		HiringCost=MinCost;
		return HiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.RED);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}

}
