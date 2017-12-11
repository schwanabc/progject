package model_general;

import SharedObject.IRenderable;
import drawing.GameScreen;

public abstract class Entity implements IRenderable{
	protected double posX;
	protected double posY;
	protected double DEF;
	protected double ATK;
	protected double HP;
	protected double MaxHP;
	protected boolean destroyed,visible;
	protected double speed;
	protected int currentATKTick;
	protected int attackTick;
	protected Entity()
	{
		destroyed=false;
		visible=true;
	}
	@Override
	public boolean isDestroyed() {
		checkDestroyed();
		return destroyed;
	}
	@Override
	public boolean isVisible() {
		return visible;
	}
	public void checkDestroyed()
	{
	//	System.out.println("-----------");
		if(getHP()<=0||
		   posX<0||
		   posY<0||
		   posX>GameScreen.GAMESCREEN_WIDTH||
		   posY>GameScreen.GAMESCREEN_HEIGHT)
			destroyed=true;

	}
	
	public abstract void update();//need UPGRADE
	public double getPosX() {
		return posX;
	}
	public double getPosY() {
		return posY;
	}
	public double getHP() {
		return HP;
	}
	public void setHP(double hP) {
		HP = hP;
	}
	public double getDEF() {
		return DEF;
	}

}
