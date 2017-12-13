package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedobject.RenderableHolder;

public class Berserker extends Attacker{
	
	
	public Berserker(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=100;
		this.DEF=10;
		this.speed=1.5;
		this.radius=8;
		this.diameter=this.radius*2;
		this.HP=200;
		this.MaxHP=HP;
		this.attackTick=25;
		this.currentATKTick=attackTick;
	}

	public static int getHiringCost() {
		hiringCost=300;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.berserker, posX-radius, posY-radius, diameter, diameter);
		drawHPBar(gc);
	}

}
