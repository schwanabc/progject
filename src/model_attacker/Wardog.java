package model_attacker;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_defender.Defender;

public class Wardog extends Attacker{

	//HIGHSPEED ATK
	public Wardog(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=40;
		this.DEF=5;
		this.radius=5;
		this.speed=4;
		this.diameter=this.radius*2;
		this.HP=100;
		this.MaxHP=HP;
		this.AttackTick=20;
		this.currentATKTick=AttackTick;
	}

	public static int getHiringCost() {
		hiringCost = 120;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.wardog, posX-radius, posY-radius, diameter*1.2, diameter*1.2);
		drawHPBar(gc);
	}
	@Override
	protected void attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(defender instanceof model_defender.HQ){
			downHP/=2;
		}
		if(downHP<0)
			downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.chekdestroyed(); //temp
        currentATKTick=0;
        
	}

}
