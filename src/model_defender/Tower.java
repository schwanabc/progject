package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Gamelogic;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Bullet;
import sharedobject.RenderableHolder;

public class Tower extends Defender{
	protected int currentShootingTick;
	protected int maxShootingTick;
	protected double shootRange;
	public Tower(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=50;
		this.DEF=10;
		this.setHP(500);
		this.MaxHP=getHP();
		this.shootRange=5;
		currentShootingTick=60;
		maxShootingTick=currentShootingTick;
		this.wallWidth=Board.BOARD_WIDTH;
		this.wallHeight=Board.BOARD_HEIGHT;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.normalTower,posX, posY, wallWidth, wallHeight);
		drawHPBar(gc);
	}
	protected void colliedWithAttacker()//find nearest target
	{
		double min=999999999;
		int idx=-1;
		int count=0;
		for(Attacker attacker:Gamelogic.getAttackerContainer())
		{
			double dist=Math.hypot(posX+wallWidth/2-attacker.getPosX(),posY+wallHeight/2-attacker.getPosY());
			if(dist<min)
			{
				min=dist;
				idx=count;
			}
			count++;
		}
		try
		{
			Attacker attacker=Gamelogic.getAttackerContainer().get(idx);
			double x0=posX+wallWidth/2;
			double y0=posY+wallHeight/2;
			double r0=shootRange*Board.BOARD_RANGE;
			double x1=attacker.getPosX();
			double y1=attacker.getPosY();  
			double r1=attacker.getRadius();
			
			if(Math.hypot(x0-x1, y0-y1) <= (r0 + r1))
			{
			//	System.out.println(Math.hypot(x0-x1, y0-y1)+" "+(r0 + r1));
			//	System.out.println("Shootable");
				int direction=0;
				double dy = y1 - y0;
				double dx = x1 - x0;
				if(dy<0)direction+=1;
				if(dx<0)direction+=2;
				double theta = Math.atan(dy/dx);
				 theta*= 180/Math.PI ;
			//	 System.out.println(theta);
				 if(isShootable())
					 {shoot(theta,direction);}
			}
		}
		catch(Exception e) {}
	}
	protected boolean isShootable() {
		if(currentShootingTick>=maxShootingTick)
		{
			currentShootingTick=0;
			return true;
		}
		return false;
	}
	@Override
	public void update() {
		currentShootingTick++;
		colliedWithAttacker();
	}
	protected void shoot(double theta,int direction) {
		RenderableHolder.arrowSound.setVolume(0.05);
		RenderableHolder.arrowSound.play();
		Gamelogic.addNewObject(new Bullet(shootRange,posX+wallWidth/2,posY+wallHeight/2,ATK,theta,direction));
	}
	public int getZ() {
		return 2;
	}
}
