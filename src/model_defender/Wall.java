package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;

public class Wall extends Defender{
	public Wall(double x,double y)
	{
		this.ATK=0;
		this.DEF=0;
		this.posX=x;
		this.posY=y;
		this.setHP(1000);
		this.MaxHP=getHP();
		this.Shootrange=0;
		this.Wallwidth=Board.getBOARD_WIDTH();
		this.Wallheight=Board.getBOARD_WIDTH();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.SLATEGRAY);
		gc.fillRect(posX, posY, Wallwidth, Wallheight);
		drawHPbar(gc);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public int getZ() {
		return 1;
	}

}
