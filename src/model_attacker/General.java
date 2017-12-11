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
		this.radius=12;
		this.diameter=this.radius*2;
		this.HP=1500;
		this.MaxHP=HP;
		this.AttackTick=25;
		this.currentATKTick=AttackTick;
	}

	public static int getHiringCost() {
		hiringCost=1000;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.general,posX-radius, posY-radius, diameter, diameter);
		drawHPBar(gc);
	}

}
