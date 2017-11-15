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
import model_attacker.Weak_1;
import model_defender.Wall;
import model_defender.weak_tower;

public class Board implements IRenderable {
	private static final int BOARD_ROW=30;
	private static final int BOARD_COLUMN=30;
	private static final double BOARD_HEIGHT=GameScreen.GAMESCREEN_HEIGHT/BOARD_ROW;
	private static final double BOARD_WIDTH=GameScreen.GAMESCREEN_WIDTH/BOARD_COLUMN;
	private boolean paused=false;
	private static int board[][]=
			{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,0,2,2,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,2,0,0,0,2,0,0,0,0,2,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,2,0,1,0,0,0,0,2,2,0,0,0,0,1,0,2,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,1,2,0,0,0,2,2,0,0,0,2,1,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,2,0,1,0,0,0,0,0,0,0,0,0,0,1,0,2,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,2,0,0,0,2,0,0,0,0,2,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,0,2,2,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
					
					
					
					
			};
	private static int[][] accessibleboard=new int[BOARD_ROW][BOARD_COLUMN];
	public Board()
	{
		fillacessibleboard();
		setboard();
	}
	private void fillacessibleboard() {
		// TODO Auto-generated method stub
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{
				if(board[i][j]!=0)
				{
					for(int k=-1*2;k<=2;k++)
					{
						for(int l=-1*2;l<=2;l++)
						{
							accessibleboard[i+k][j+l]=1;
						}
					}
				}
				else
				{
					if(accessibleboard[i][j]!=1)accessibleboard[i][j]=0;
				}
			}
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

				 gc.setFill(Color.ANTIQUEWHITE);				
				if(accessibleboard[i][j]==1)gc.setFill(Color.LIGHTGOLDENRODYELLOW);
				gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH+1, BOARD_HEIGHT+1);
				
			}
	}
	public static int getBOARD_ROW() {
		return BOARD_ROW;
	}
	public static int getBOARD_COLUMN() {
		return BOARD_COLUMN;
	}

	public void update()
	{
		if(InputUtility.isLeftClickTriggered() && Placeable(InputUtility.mouseX,InputUtility.mouseY))
		{
			Gamelogic.addNewObject(new Weak_1(InputUtility.mouseX,InputUtility.mouseY));	
			if(InputUtility.getKeyPressed(KeyCode.SHIFT))
					{
						Gamelogic.addNewObject(new Weak_1(InputUtility.mouseX+Weak_1.getDIAMETER(),InputUtility.mouseY));
						Gamelogic.addNewObject(new Weak_1(InputUtility.mouseX-Weak_1.getDIAMETER(),InputUtility.mouseY));
						Gamelogic.addNewObject(new Weak_1(InputUtility.mouseX,InputUtility.mouseY+Weak_1.getDIAMETER()));
						Gamelogic.addNewObject(new Weak_1(InputUtility.mouseX,InputUtility.mouseY-Weak_1.getDIAMETER()));
					}
		}
		
	}
	private boolean Placeable(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		return GameScreen.isIngamescreen() && accessibleboard[(int) (mouseY/BOARD_HEIGHT)][(int) (mouseX/BOARD_WIDTH)]==0;
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
