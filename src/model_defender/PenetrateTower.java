package model_defender;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import logic.Gamelogic;
import model_general.Bullet;
import model_general.PenetrateBullet;

public class PenetrateTower extends Tower{

	public PenetrateTower(double posX, double posY, int posI, int posJ) {
		super(posX, posY, posI, posJ);
		this.Shootrange=4;
		Currentshootingtick=100;
		Shootingtick=Currentshootingtick;
	}
	@Override
	protected void shoot(double theta,int direction) {
		RenderableHolder.ArrowSound.setVolume(0.05);
		RenderableHolder.ArrowSound.play();
		Gamelogic.addNewObject(new PenetrateBullet(Shootrange,posX+Wallwidth/2,posY+Wallheight/2,ATK,theta,direction));
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.PenetrateTower,posX, posY, Wallwidth, Wallheight);
		drawHPbar(gc);
	}

}
