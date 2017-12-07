package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_defender.Defender;

public class Bot4 extends Attacker{
	
	
	public Bot4(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=1;
		this.DEF=30;
		this.speed=4;
		this.RADIUS=7;
		this.DIAMETER=this.RADIUS*2;
		this.HP=500;
		this.MaxHP=HP;
		this.AttackTick=40;
		this.currentATKTick=AttackTick;
		this.WallPriority=20;
		this.TowerPriority=30;
		this.HQPriority=30;
	}

	public static int getHiringCost() {
		HiringCost=200;
		return HiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}
	@Override
	protected void Attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(defender instanceof model_defender.Wall)
		{
			downHP = 999999;
			HP = 0;
		}
		if(downHP<0)
			downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.chekdestroyed(); //temp
        currentATKTick=0;
        
	}

}
