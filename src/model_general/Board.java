package model_general;

import Input.InputUtility;
import logic.Gamelogic;
import SharedObject.IRenderable;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model_attacker.Bot0;
import model_attacker.Bot1;
import model_attacker.Bot2;
import model_attacker.Bot3;
import model_attacker.Bot4;
import model_attacker.Bot5;
import model_attacker.Bot6;
import model_defender.HQ;
import model_defender.Wall;
import model_defender.Tower;
//1==wall 2==tower 3==HQ (HQ eat 2 tile)
public class Board implements IRenderable {
	public static final int BOARD_ROW=30;
	public static final int BOARD_COLUMN=30;
	public static final double BOARD_HEIGHT=GameScreen.GAMESCREEN_HEIGHT/BOARD_ROW;
	public static final double BOARD_WIDTH=GameScreen.GAMESCREEN_WIDTH/BOARD_COLUMN;
	public static final double BOARD_RANGE=Math.min(BOARD_HEIGHT, BOARD_WIDTH);
	public static final int TOTALBOARD=2;
	public static double HQPOSX;
	public static double HQPOSY;
	private static boolean Iswin;
	private static int[] DefaultMoney= {1500,3000};
	private static int DefaultNumboard=0;
	private static int Numboard=0;
	private static int Money;
//	private static Image grass1=new Image(ClassLoader.getSystemResource("grass_1.png").toString());
//	private static Image bush=new Image(ClassLoader.getSystemResource("bush.png").toString());
	private static Image StoneTile=new Image(ClassLoader.getSystemResource("StoneTile.png").toString());
	private static Image stripe=new Image(ClassLoader.getSystemResource("stripe.jpg").toString());	
	private static int templateboard[][][]=new int[TOTALBOARD][BOARD_ROW][BOARD_COLUMN];
	private static int board[][];
	private static int[][] accessibleboard;
	public Board()
	{			
		boarddata.fillboard();
		Numboard=DefaultNumboard;
		Iswin=false; 
		Money=DefaultMoney[Numboard];
		try {
		board=templateboard[Numboard];
		}
		catch (java.lang.NullPointerException e) {System.out.println("NO board left"); }
		fillacessibleboard();
		setboard();
	}
	private void fillacessibleboard() {
		// TODO Auto-generated method stub
		System.out.println("Board is refilled");
		accessibleboard=new int[BOARD_ROW][BOARD_COLUMN];
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
				if(board[i][j]==3)//HQ
				{
					HQPOSY=(i+1)*BOARD_HEIGHT;
					HQPOSX=(j+1)*BOARD_WIDTH;
					Gamelogic.addNewObject(new HQ(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
				}
			}
	}

	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{
				gc.setFill(Color.WHITE);
				gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				if(accessibleboard[i][j]==1)
				{
					gc.drawImage(StoneTile, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setGlobalAlpha(0.15);
					gc.drawImage(stripe, BOARD_WIDTH*j-1, BOARD_HEIGHT*i-1, BOARD_WIDTH+2, BOARD_HEIGHT+2);
					gc.setGlobalAlpha(1);
					
				}
				if(board[i][j]==-1)
				{
					gc.setFill(Color.PURPLE);
					gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				}
				if(accessibleboard[i][j]==0)
				{
					//gc.drawImage(grass1, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					//gc.drawImage(bush, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setFill(Color.BLANCHEDALMOND);
					gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				}
			}
	}
	public static void update()
	{
		Checkadded();
	}
	private static void Checkadded() {
		// TODO Auto-generated method stub
	//	System.out.println(InputUtility.getTick());
		if(Checktoadd())
		{
			String bot_type=InputUtility.currentChosed;
			if(bot_type=="Weak_1") 
			{ 
				if(buyable(Bot0.getHiringCost()))
				{
					decreaseMoney(Bot0.getHiringCost());
					Gamelogic.addNewObject(new Bot0(InputUtility.mouseX,InputUtility.mouseY));
				}
			}
			if(bot_type=="Weak_2") 
			{
				if(buyable(Bot1.getHiringCost()))
				{
					decreaseMoney(Bot1.getHiringCost());
					Gamelogic.addNewObject(new Bot1(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Weak_3") 
			{
				if(buyable(Bot2.getHiringCost()))
				{
					decreaseMoney(Bot2.getHiringCost());
					Gamelogic.addNewObject(new Bot2(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Weak_4") 
			{
				if(buyable(Bot3.getHiringCost()))
				{
					decreaseMoney(Bot3.getHiringCost());
					Gamelogic.addNewObject(new Bot3(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Weak_5") 
			{
				if(buyable(Bot4.getHiringCost()))
				{
					decreaseMoney(Bot4.getHiringCost());
					Gamelogic.addNewObject(new Bot4(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Weak_6") 
			{
				if(buyable(Bot5.getHiringCost()))
				{
					decreaseMoney(Bot5.getHiringCost());
					Gamelogic.addNewObject(new Bot5(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Weak_7") 
			{
				if(buyable(Bot6.getHiringCost()))
				{
					decreaseMoney(Bot6.getHiringCost());
					Gamelogic.addNewObject(new Bot6(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
		}
	}
	private static boolean buyable(int hiringCost) {
		// TODO Auto-generated method stub
		if(Money-hiringCost>=0)return true;
		return false;
	}
	private static boolean Checktoadd() {
		return isDeyployable() && Placeable(InputUtility.mouseX,InputUtility.mouseY) && !InputUtility.currentChosed.equals("x");
	}
	private static boolean isDeyployable()
	{
		if(InputUtility.isLeftDown()&& InputUtility.getTick()>=InputUtility.Maxtick)
		{
			InputUtility.setTick(0);
			return true;
		}
		return false;
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
	public static boolean isIswin() {
		return Iswin;
	}
	public static void setIswin(boolean iswin) {
		Iswin = iswin;
	}
	public static int[][] getBoard() {
		return board;
	}
	public static int getBoard(int i,int j) {
		return board[i][j];
	}
	public static void setBoard(int i,int j,int val) {
		Board.board[i][j]=val;
	}
	public static int getMoney() {
		return Money;
	}
	private static void decreaseMoney(int money) {
		if(Money-money>=0) Money -= money;
	}
	public static void setMoney(int money) {
		Money = money;
	}
	public static void setTemplateboard(int[][] board,int idx) {
		try {
			templateboard[idx] = board;
			}
			catch (java.lang.NullPointerException e) {System.out.println("Board Overflow"); }
	} 
	public static void addNumboard() {
		// TODO Auto-generated method stub
		DefaultNumboard++;
	}
	public static void resetNumboard() {
		// TODO Auto-generated method stub
		DefaultNumboard=0;
	}
	public static void decreaseNumboard() {
		// TODO Auto-generated method stub
		if(DefaultNumboard>0)DefaultNumboard--;
	}
	public static int getDefaultNumboard() {
		return DefaultNumboard;
	}

}
