package model_general;

import SharedObject.IRenderable;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity implements IRenderable{
	protected double posX;
	protected double posY;
	protected double DEF;
	protected double ATK;
	protected double HP;
	protected double MaxHP;
	protected boolean destroyed,visible;
	protected Entity()
	{
		destroyed=false;
		visible=true;
	}
	@Override
	public boolean isDestroyed() {
		chekdestroyed();
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	public void chekdestroyed()
	{
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

}