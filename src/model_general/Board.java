package model_general;

import Input.InputUtility;
import logic.Gamelogic;
import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import application.Main;
import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model_attacker.Bot0;
import model_attacker.Bot1;
import model_defender.HQ;
import model_defender.Wall;
import model_defender.Tower;

public class Board implements IRenderable {
	private static final int BOARD_ROW=30;
	private static final int BOARD_COLUMN=30;
	private static final double BOARD_HEIGHT=GameScreen.GAMESCREEN_HEIGHT/BOARD_ROW;
	private static final double BOARD_WIDTH=GameScreen.GAMESCREEN_WIDTH/BOARD_COLUMN;
	private static final double BOARD_RANGE=Math.sqrt(BOARD_HEIGHT*BOARD_HEIGHT+BOARD_WIDTH*BOARD_WIDTH);
	private static boolean pausedstate=false;
	private static boolean Iswin=false;
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
					{1,1,1,1,1,1,1,0,0,1,0,0,0,0,3,0,0,0,0,0,1,0,2,1,1,1,1,1,1,1},
					{1,2,2,2,2,1,1,0,0,1,2,0,0,0,0,0,0,0,0,2,1,0,0,1,1,2,2,2,2,1},
					{1,2,2,2,2,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,2,1,1,2,2,2,2,1},
					{1,1,1,1,1,1,1,2,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,1,1,1},
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
					int range=2;
					if(board[i][j]==3)range++; //HQ
					for(int k=-1*2;k<=range;k++)
					{
						for(int l=-1*2;l<=range;l++)
						{
							try {
							accessibleboard[i+k][j+l]=1;
							}
							catch (Exception e) {}
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
					Gamelogic.addNewObject(new Wall(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
				}
				if(board[i][j]==2)
				{
					Gamelogic.addNewObject(new Tower(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
				}
				if(board[i][j]==3)
				{
					Gamelogic.addNewObject(new HQ(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
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
				if(board[i][j]==-1)gc.setFill(Color.PURPLE);
				gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH+1, BOARD_HEIGHT+1);
				
			}
	}
	public static int getBOARD_ROW() {
		return BOARD_ROW;
	}
	public static int getBOARD_COLUMN() {
		return BOARD_COLUMN;
	}

	public static void update()
	{
		Checkadded();
		Checkpaused();
	}
	private static void Checkadded() {
		// TODO Auto-generated method stub
		if(Checktoadd())
		{
			String bot_type=InputUtility.currentChosed;
			if(bot_type=="Weak_1") Gamelogic.addNewObject(new Bot0(InputUtility.mouseX,InputUtility.mouseY));	
			if(bot_type=="Weak_2") Gamelogic.addNewObject(new Bot1(InputUtility.mouseX,InputUtility.mouseY));	
		}
	}
	private static void Checkpaused() {
		// TODO Auto-generated method stub
		if(InputUtility.isKeyPress()==false && InputUtility.Lastkey==KeyCode.SPACE)
		{
			if(pausedstate==false)
			{
				pausedstate=true;
				System.out.print("pause");
				application.Main.AT.stop();
				application.Main.AT2.start();
			}
			else
			{
				pausedstate=false;
				System.out.print("unpause");
				application.Main.AT2.stop();
				application.Main.AT.start();
			}
			InputUtility.Lastkey=null;
		}
	}
	private static boolean Checktoadd() {
		return InputUtility.isLeftClickTriggered() 
				&& Placeable(InputUtility.mouseX,InputUtility.mouseY) 
				&& !InputUtility.currentChosed.equals("x");
	}
	private static boolean Placeable(double mouseX, double mouseY) {
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
	public static double getBoardRange() {
		return BOARD_RANGE;
	}
	public static boolean isIswin() {
		return Iswin;
	}
	public static void setIswin(boolean iswin) {
		Iswin = iswin;
	}
	public static int[][] getBoard() {
		return board;
	}
	public static void setBoard(int i,int j,int val) {
		Board.board[i][j]=val;
	}

}
