package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bot0 extends Attacker{

	
	public Bot0(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=35;
		this.DEF=5;
		this.RADIUS=6;
		this.speed=2;
		this.DIAMETER=this.RADIUS*2;
		this.HP=150;
		this.MaxHP=HP;
		this.AttackTick=20;
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
