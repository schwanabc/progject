package model_defender;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;

public class HQ extends Defender{
	private static final int Wallwidth = 0;

	public HQ(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=0;
		this.DEF=0;
		this.setHP(5000);
		this.MaxHP=getHP();
		this.shootRange=0;
		this.wallWidth=Board.BOARD_WIDTH*4;
		this.wallHeight=Board.BOARD_HEIGHT*4;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.HQ, posX-5, posY-5, wallWidth+5, wallHeight+10);
		drawHPbar(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//temporary win (Actually,it will change the stage)

	}

}
