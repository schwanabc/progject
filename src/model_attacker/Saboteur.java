package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_defender.Defender;
import sharedobject.RenderableHolder;

public class Saboteur extends Attacker{
	
	
	public Saboteur(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=25;
		this.DEF=5;
		this.speed=2;
		this.radius=8;
		this.diameter=this.radius*2;
		this.HP=150;
		this.MaxHP=HP;
		this.attackTick=20;
		this.currentATKTick=attackTick;
	}

	public static int getHiringCost() {
		hiringCost=200;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.saboteur, posX-radius, posY-radius, diameter, diameter);
		drawHPBar(gc);
	}
	@Override
	protected void attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(defender instanceof model_defender.HQ){
			downHP=ATK*6-defender.getDEF();
		}
		if(downHP<0)
			downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.checkDestroyed(); //temp
        currentATKTick=0;
        
	}

}
