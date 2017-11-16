package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Entity;

public class Bullet extends Entity{
	protected double angle;
	protected double radius;
	protected double speed;
	protected int direction;
	public Bullet(double Shootrange,double posX,double posY,double ATK,double angle,int direction)
	{
		this.ATK=ATK;
		this.DEF=0;
		this.radius=2;
		this.HP=Shootrange*1.5*60;
		this.speed=Math.sqrt(Board.getBOARD_HEIGHT()*Board.getBOARD_HEIGHT() + Board.getBOARD_COLUMN()*Board.getBOARD_COLUMN())/20;
		this.posX=posX;
		this.posY=posY;
		this.angle=angle;
		this.direction=direction;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}
	protected void ColliedwithAttacker()
	{
		for(Attacker attacker: logic.Gamelogic.getAttackercontainer())
		{
			Circle c1=new Circle( posX, posY, radius);
			Circle c2=new Circle( attacker.getPosX(),  attacker.getPosY(), attacker.getRADIUS());
			if (c1.getBoundsInParent().intersects(c2.getBoundsInParent())) {
		        BulletAttack(attacker);
		        break;
		      }
		}
	}

	private void BulletAttack(Attacker attacker) {
		// TODO Auto-generated method stub
		attacker.setHP(attacker.getHP() - (ATK-attacker.getDEF()));//temp
		attacker.chekdestroyed();
		this.destroyed=true;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLUE);
		gc.fillOval(posX-radius, posY-radius, radius*2, radius*2);
	}
	public void foward(double xAxis,double yAxis)
	{
		posX+=xAxis*speed;
		posY+=yAxis*speed;
		HP-=4;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println(angle+" "+direction);
		if(direction<2)foward(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));
		else foward(-1*Math.cos(Math.toRadians(angle)),-1*Math.sin(Math.toRadians(angle)));
		ColliedwithAttacker();
	}

}
