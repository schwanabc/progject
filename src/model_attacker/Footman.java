package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sharedobject.RenderableHolder;

public class Footman extends Attacker{
	

	public Footman(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=30;
		this.DEF=30;
		this.speed=1;
		this.radius=8;
		this.diameter=this.radius*2;
		this.HP=1500;
		this.MaxHP=HP;
		this.attackTick=80;
		this.currentATKTick=attackTick;
	}

	public static int getHiringCost() {
		hiringCost=240;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.footman, posX-radius, posY-radius, diameter*0.9, diameter*0.9);
		drawHPBar(gc);
	}

}
