package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bot0 extends Attacker{

	private static Image Armed_Peasant=new Image(ClassLoader.getSystemResource("Armed_Peasant.png").toString());	
	public Bot0(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=35;
		this.DEF=5;
		this.RADIUS=7;
		this.speed=2;
		this.DIAMETER=this.RADIUS*2;
		this.HP=150;
		this.MaxHP=HP;
		this.AttackTick=25;
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
		gc.drawImage(Armed_Peasant, posX-RADIUS, posY-RADIUS, DIAMETER*1.2, DIAMETER*1.2);
		drawHPbar(gc);
	}

}
