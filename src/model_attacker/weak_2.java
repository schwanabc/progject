package model_attacker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class weak_2 extends Attacker {
	protected static double RADIUS=8;
	protected static double DIAMETER=RADIUS*2;
	public weak_2(double x,double y)
	{
		this.ATK=5;
		this.DEF=10;
		this.posX=x;
		this.posY=y;
		this.speed=2.5;
		this.setHP(100);
		this.MaxHP=getHP();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.GREEN);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}
	
	@Override
	public void update() {
		// UPGRADING
		foward(1,0);//only test (can be delete)
	}
	public static double getRADIUS() {
		return RADIUS;
	}
	public static double getDIAMETER() {
		return DIAMETER;
	}
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}
}
