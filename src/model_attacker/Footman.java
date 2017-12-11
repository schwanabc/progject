package model_attacker;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Footman extends Attacker{
	

	public Footman(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=30;
		this.DEF=30;
		this.speed=1;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.HP=1500;
		this.MaxHP=HP;
		this.AttackTick=80;
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
		gc.drawImage(RenderableHolder.Footman, posX-RADIUS, posY-RADIUS, DIAMETER*1.2, DIAMETER*1.2);
		drawHPbar(gc);
	}

}
