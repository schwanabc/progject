package model_defender;

import SharedObject.RenderableHolder;
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
		this.Wallwidth=Board.BOARD_WIDTH*4;
		this.Wallheight=Board.BOARD_HEIGHT*4;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.HQ, posX-5, posY-5, Wallwidth+5, Wallheight+10);
		drawHPbar(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//temporary win (Actually,it will change the stage)

	}

}
