package model_attacker;

import SharedObject.RenderableHolder;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Gamelogic;
import model_defender.Defender;
import model_general.Board;

public class Sapper extends Attacker{
	
	
	public Sapper(double posX,double posY)
	{
		//all stat is temporary
		super(posX,posY);
		this.ATK=1;
		this.DEF=30;
		this.speed=3;
		this.radius=7;
		this.diameter=this.radius*2;
		this.HP=40;
		this.MaxHP=HP;
		this.attackTick=25;
		this.currentATKTick=attackTick;
	}

	public static int getHiringCost() {
		hiringCost=200;
		return hiringCost;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.sapper, posX-radius, posY-radius, diameter, diameter);
		drawHPBar(gc);
	}
	@Override
	protected void attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(defender instanceof model_defender.Wall && defender.getHP() != 1000)
		{
			downHP = 999999;
			HP = 0;
		}
		if(downHP<0)
			downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.checkDestroyed(); //temp
        currentATKTick=0;
        
	}
	@Override
	public void update() {
		// UPGRADING
		//need decent moving algorithm
		colliedWithAttacker();
		if(colliedWithDefender()) {
			 return;
		}
		else if(currentTarget != null && Gamelogic.isDefenderContain(currentTarget)) {
			if(currentTarget instanceof model_defender.HQ)
				forward((currentTarget.getPosX()+Board.BOARD_WIDTH)-getPosX(),(currentTarget.getPosY()+Board.BOARD_HEIGHT)-getPosY());
			else
				forward((currentTarget.getPosX()+Board.BOARD_WIDTH/2)-getPosX(),(currentTarget.getPosY()+Board.BOARD_HEIGHT/2)-getPosY());
			//System.out.println("Save time");
		}
		else {
			double min = 999999999;
			double walkX = 0,walkY = 0;
			for(Defender defender: Gamelogic.getDefendercontainer()) {
				if(!(defender instanceof model_defender.Wall))
					continue;
				double dist = Math.hypot((defender.getPosX()+Board.BOARD_WIDTH/2)-getPosX(), (defender.getPosY()+Board.BOARD_HEIGHT/2)-getPosY());
				if(dist < min) {
					min = dist;
					currentTarget = defender;
					walkX = (defender.getPosX()+Board.BOARD_WIDTH/2)-getPosX();
					walkY = (defender.getPosY()+Board.BOARD_HEIGHT/2)-getPosY();
					if(defender instanceof model_defender.HQ) {
						//System.out.println("HQ " +walkX +" "+ walkY +" " + Board.BOARD_WIDTH+" "+Board.BOARD_HEIGHT);
						walkX = (defender.getPosX()+Board.BOARD_WIDTH)-getPosX();
						walkY = (defender.getPosY()+Board.BOARD_HEIGHT)-getPosY();
						//System.out.println("HQ2 " +walkX +" "+ walkY);
					}
				}
			}
			forward(walkX,walkY);
		}
	}

}
