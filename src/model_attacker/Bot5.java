package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_defender.Defender;

public class Bot5 extends Attacker{
	
	
	public Bot5(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=25;
		this.DEF=5;
		this.speed=2;
		this.RADIUS=8;
		this.DIAMETER=this.RADIUS*2;
		this.HP=150;
		this.MaxHP=HP;
		this.AttackTick=20;
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
		gc.setFill(Color.SADDLEBROWN);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}
	@Override
	protected void Attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(defender instanceof model_defender.HQ){
			downHP=ATK*6-defender.getDEF();
		}
		if(downHP<0)
			downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.chekdestroyed(); //temp
        currentATKTick=0;
        
	}

}
