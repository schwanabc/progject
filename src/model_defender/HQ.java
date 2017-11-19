package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;

public class HQ extends Defender{
	public HQ(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=0;
		this.DEF=0;
		this.setHP(5000);
		this.MaxHP=getHP();
		this.Shootrange=0;
		this.Wallwidth=Board.getBOARD_WIDTH()*2;
		this.Wallheight=Board.getBOARD_HEIGHT()*2;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);
		gc.fillRect(posX, posY, Wallwidth, Wallheight);
		drawHPbar(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//temporary win (Actually,it will change the stage)

	}

}
