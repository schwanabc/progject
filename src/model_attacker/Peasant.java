package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sharedobject.RenderableHolder;

public class Peasant extends Attacker{

	public Peasant(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=30;
		this.DEF=5;
		this.radius=8;
		this.speed=2;
		this.diameter=this.radius*2;
		this.HP=100;
		this.MaxHP=HP;
		this.attackTick=30;
		this.currentATKTick=attackTick;
	}

	public static int getHiringCost() {
		hiringCost=minCost;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.armed_Peasant, posX-radius, posY-radius, diameter, diameter);
		drawHPBar(gc);
	}

}
