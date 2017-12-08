package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bot2 extends Attacker{

	//HIGHSPEED ATK
	public Bot2(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=40;
		this.DEF=5;
		this.RADIUS=4;
		this.speed=4;
		this.DIAMETER=this.RADIUS*2;
		this.HP=100;
		this.MaxHP=HP;
		this.AttackTick=20;
		this.currentATKTick=AttackTick;
		this.WallPriority=10;
		this.TowerPriority=10;
		this.HQPriority=80;
	}

	public static int getHiringCost() {
		HiringCost = 120;
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
