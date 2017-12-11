package model_attacker;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class General extends Attacker{
	
	
	public General(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=100;
		this.DEF=30;
		this.speed=1;
		this.RADIUS=12;
		this.DIAMETER=this.RADIUS*2;
		this.HP=1500;
		this.MaxHP=HP;
		this.AttackTick=25;
		this.currentATKTick=AttackTick;
		this.WallPriority=20;
		this.TowerPriority=30;
		this.HQPriority=30;
	}

	public static int getHiringCost() {
		HiringCost=1000;
		return HiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.General,posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}

}
