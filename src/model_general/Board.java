package model_general;

import Input.InputUtility;
import logic.Gamelogic;
import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import application.Main;
import drawing.GameScreen;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model_attacker.weak_1;
import model_defender.Wall;
import model_defender.weak_tower;

public class Board implements IRenderable {
	private static int BOARD_ROW=20;
	private static int BOARD_COLUMN=20;
	private static double BOARD_HEIGHT=GameScreen.GAMESCREEN_HEIGHT/BOARD_ROW;
	private static double BOARD_WIDTH=GameScreen.GAMESCREEN_WIDTH/BOARD_COLUMN;
	private boolean paused=false;
	private static int board[][]=
			{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,2,0,0,0,0,2,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			};
	public Board()
	{
		setboard();
	}
	public void setboard() {
		// TODO Auto-generated method stub
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{
				if(board[i][j]==1)
				{
					System.out.println("wall " +BOARD_WIDTH*j +" " +BOARD_HEIGHT*i);
					Gamelogic.addNewObject(new Wall(BOARD_WIDTH*j, BOARD_HEIGHT*i));
				}
				if(board[i][j]==2)
				{
					System.out.println("noobtower");
					Gamelogic.addNewObject(new weak_tower(BOARD_WIDTH*j, BOARD_HEIGHT*i));
				}
			}
	}

	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{

				if((i+j)%2==0) gc.setFill(Color.ANTIQUEWHITE);
				else gc.setFill(Color.ROSYBROWN);
				gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				
			}
	}
	public static int getBOARD_ROW() {
		return BOARD_ROW;
	}
	public static void setBOARD_ROW(int bOARD_ROW) {
		BOARD_ROW = bOARD_ROW;
	}
	public static int getBOARD_COLUMN() {
		return BOARD_COLUMN;
	}
	public static void setBOARD_COLUMN(int bOARD_COLUMN) {
		BOARD_COLUMN = bOARD_COLUMN;
	}
	public void update()
	{
		if(InputUtility.isLeftClickTriggered() && GameScreen.isIngamescreen())
		{
			Gamelogic.addNewObject(new weak_1(InputUtility.mouseX,InputUtility.mouseY));	
			if(InputUtility.getKeyPressed(KeyCode.SHIFT))
					{
						Gamelogic.addNewObject(new weak_1(InputUtility.mouseX+weak_1.getDIAMETER(),InputUtility.mouseY));
						Gamelogic.addNewObject(new weak_1(InputUtility.mouseX-weak_1.getDIAMETER(),InputUtility.mouseY));
						Gamelogic.addNewObject(new weak_1(InputUtility.mouseX,InputUtility.mouseY+weak_1.getDIAMETER()));
						Gamelogic.addNewObject(new weak_1(InputUtility.mouseX,InputUtility.mouseY-weak_1.getDIAMETER()));
					}
		}
		
	}
	@Override
	public int getZ() {
		return -9999;
	}
	@Override
	public boolean isDestroyed() {
		return false;
	}
	@Override
	public boolean isVisible() {
		return true;
	}
	public static double getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}
	public static double getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}

}
