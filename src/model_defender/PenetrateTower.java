package model_defender;

import javafx.scene.canvas.GraphicsContext;
import logic.Gamelogic;
import model_general.Bullet;
import model_general.PenetrateBullet;
import sharedobject.RenderableHolder;

public class PenetrateTower extends Tower{

	public PenetrateTower(double posX, double posY, int posI, int posJ) {
		super(posX, posY, posI, posJ);
		this.shootRange=4;
		currentShootingTick=100;
		maxShootingTick=currentShootingTick;
	}
	@Override
	protected void shoot(double theta,int direction) {
		RenderableHolder.arrowSound.setVolume(0.05);
		RenderableHolder.arrowSound.play();
		Gamelogic.addNewObject(new PenetrateBullet(shootRange,posX+wallWidth/2,posY+wallHeight/2,ATK,theta,direction));
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.penetrateTower,posX, posY, wallWidth, wallHeight);
		drawHPBar(gc);
	}

}
