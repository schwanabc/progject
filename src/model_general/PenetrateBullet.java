package model_general;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_attacker.Attacker;

public class PenetrateBullet extends Bullet{

	public PenetrateBullet(double Shootrange, double posX, double posY, double ATK, double angle, int direction) {
		super(Shootrange, posX, posY, ATK, angle, direction);
		this.ATK=ATK/6;
		this.radius=3;
	}
	@Override
	protected void bulletAttack(Attacker attacker) {
		double downHP=ATK-attacker.getDEF();
		if(downHP<=0)downHP=1;
		attacker.setHP(attacker.getHP() -downHP);//temp		
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillOval(posX-radius, posY-radius, radius*2, radius*2);
	}

}
