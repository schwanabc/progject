package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bot1 extends Attacker{
	
	
	public Bot1(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=200;
		this.DEF=30;
		this.speed=2;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.HP=1000;
		this.MaxHP=HP;
		this.AttackTick=100;
		this.currentATKTick=AttackTick;
		this.WallPriority=20;
		this.TowerPriority=30;
		this.HQPriority=30;
	}

	public static int getHiringCost() {
		HiringCost=240;
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
