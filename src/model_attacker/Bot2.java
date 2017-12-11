package model_attacker;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_defender.Defender;

public class Bot2 extends Attacker{

	//HIGHSPEED ATK
	public Bot2(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=40;
		this.DEF=5;
		this.RADIUS=5;
		this.speed=4;
		this.DIAMETER=this.RADIUS*2;
		this.HP=100;
		this.MaxHP=HP;
		this.AttackTick=20;
		this.currentATKTick=AttackTick;
		this.WallPriority=10;
		this.TowerPriority=10;
		this.HQPriority=80;
	}

	public static int getHiringCost() {
		HiringCost = 120;
		return HiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.Wardog, posX-RADIUS, posY-RADIUS, DIAMETER*1.2, DIAMETER*1.2);
		drawHPbar(gc);
	}
	@Override
	protected void Attack(Defender defender) {
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
