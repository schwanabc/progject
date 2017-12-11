package model_general;


import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

import Exception.PoorException;
import Scenemanager.SceneManager;
import logic.Gamelogic;
import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import Utility.InputUtility;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model_attacker.Peasant;
import model_attacker.Footman;
import model_attacker.Wardog;
import model_attacker.Berserker;
import model_attacker.Sapper;
import model_attacker.Saboteur;
import model_attacker.General;
import model_defender.HQ;
import model_defender.PenetrateTower;
import model_defender.Wall;
import model_defender.Tower;
//1==wall 2==tower 3==HQ (HQ eat 2 tile)
public class Board implements IRenderable {
	public static final int BOARD_ROW=30;
	public static final int BOARD_COLUMN=30;
	public static final double BOARD_HEIGHT=GameScreen.GAMESCREEN_HEIGHT/BOARD_ROW;
	public static final double BOARD_WIDTH=GameScreen.GAMESCREEN_WIDTH/BOARD_COLUMN;
	public static final double BOARD_RANGE=Math.min(BOARD_HEIGHT, BOARD_WIDTH);
	public static final int TOTALBOARD=3;
	public static double HQPOSX;
	public static double HQPOSY;
	private static boolean Iswin;
	private static int[] DefaultMoney= {1500,4000,3000};
	private static int DefaultNumboard=0;
	private static int Numboard=0;
	private static int Money;
	private static int templateboard[][][]=new int[TOTALBOARD][BOARD_ROW][BOARD_COLUMN];
	private static int board[][];
	private static int[][] accessibleboard;
	public Board()
	{	

		Numboard=DefaultNumboard;
		Iswin=false; 
		try {
		Money=DefaultMoney[Numboard];
		board=ReadBoard(Numboard);
		fillacessibleboard();
		setboard();
		}
		catch (ArrayIndexOutOfBoundsException e) {SceneManager.gotoWinScreen();System.exit(0); }
	}
	private int[][] ReadBoard(int Numboard) {
		
		int[][] tempboard=new int[BOARD_ROW][BOARD_COLUMN];
			String fileName="/Stage_"+Integer.toString(Numboard)+".txt";
			try {
				Scanner in = new Scanner(new InputStreamReader(Files.class.getResourceAsStream(fileName)));
				int numline=0;
				while(in.hasNextLine())
				{
					System.out.println(numline);
					String line;
						line = in.nextLine().trim();
						if(line==null)break;
						for(int i=0;i<line.length();i++)
						{
							tempboard[numline][i]=Integer.parseInt(line.substring(i, i+1));
						}
						numline++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return tempboard;
	}
	private void fillacessibleboard() {
		// TODO Auto-generated method stub
		accessibleboard=new int[BOARD_ROW][BOARD_COLUMN];
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{
				if(board[i][j]!=0)
				{
					int range=2;
					if(board[i][j]==3)range+=3; //HQ
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
	public static int getTowerAttack(int posY,int posX) {
		//Not finish
		int countTower = 0;
		for(int i=0;i<BOARD_ROW;i++) {
			for(int j=0;j<BOARD_COLUMN;j++) {
				int diff = (posX-i>0)?(posX-i):(i-posX);
				diff += (posY-j>0)?(posY-j):(j-posY);
				if(board[i][j] == 2 || board[i][j]==4 && diff <= 5) {
					countTower++;
				}
			}
		}
		
		int towerATK = countTower;
		if(board[posX][posY] == 1)
			towerATK *= 6;
//		else if(board[posX][posY] == -1) {
//			System.out.println("Find Hole");
//			towerATK *= 0;
//		}
		return towerATK+3;
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
					HQPOSY=(i+2)*BOARD_HEIGHT;
					HQPOSX=(j+2)*BOARD_WIDTH;
					Gamelogic.addNewObject(new HQ(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
				}
				if(board[i][j]==4)//HQ
				{
					Gamelogic.addNewObject(new PenetrateTower(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
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
					gc.drawImage(RenderableHolder.StoneTile, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setGlobalAlpha(0.15);
					gc.drawImage(RenderableHolder.stripe, BOARD_WIDTH*j-1, BOARD_HEIGHT*i-1, BOARD_WIDTH+2, BOARD_HEIGHT+2);
					gc.setGlobalAlpha(1);
					
				}
				if(board[i][j]==-1)
				{
			
					gc.drawImage(RenderableHolder.Rubble,BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setGlobalAlpha(0.15);
					gc.drawImage(RenderableHolder.stripe, BOARD_WIDTH*j-1, BOARD_HEIGHT*i-1, BOARD_WIDTH+2, BOARD_HEIGHT+2);
					gc.setGlobalAlpha(1);

				}
				if(accessibleboard[i][j]==0)
				{
					gc.drawImage(RenderableHolder.Grass, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
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
			if(bot_type=="Peasant") 
			{ 
				if(buyable(Peasant.getHiringCost()))
				{
					decreaseMoney(Peasant.getHiringCost());
					Gamelogic.addNewObject(new Peasant(InputUtility.mouseX,InputUtility.mouseY));
				}
			}
			if(bot_type=="Footman") 
			{
				if(buyable(Footman.getHiringCost()))
				{
					decreaseMoney(Footman.getHiringCost());
					Gamelogic.addNewObject(new Footman(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Wardog") 
			{
				if(buyable(Wardog.getHiringCost()))
				{
					decreaseMoney(Wardog.getHiringCost());
					Gamelogic.addNewObject(new Wardog(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Berserker") 
			{
				if(buyable(Berserker.getHiringCost()))
				{
					decreaseMoney(Berserker.getHiringCost());
					Gamelogic.addNewObject(new Berserker(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Sapper") 
			{
				if(buyable(Sapper.getHiringCost()))
				{
					decreaseMoney(Sapper.getHiringCost());
					Gamelogic.addNewObject(new Sapper(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="Saboteur") 
			{
				if(buyable(Saboteur.getHiringCost()))
				{
					decreaseMoney(Saboteur.getHiringCost());
					Gamelogic.addNewObject(new Saboteur(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
			if(bot_type=="General") 
			{
				if(buyable(General.getHiringCost()))
				{
					decreaseMoney(General.getHiringCost());
					Gamelogic.addNewObject(new General(InputUtility.mouseX,InputUtility.mouseY));	
				}
			}
		}
	}
	private static boolean buyable(int hiringCost) {
		// TODO Auto-generated method stub
		if(Money-hiringCost>=0)return true;
		try {
			throw new PoorException();
		} catch (PoorException e) {
			return false;
		}
	}
	private static boolean Checktoadd() {
		return isDeyployable() && Placeable(InputUtility.mouseX,InputUtility.mouseY) && !InputUtility.currentChosed.equals("x");
	}
	private static boolean isDeyployable()
	{
		if(InputUtility.isLeftDown()&& InputUtility.getTick()>=InputUtility.MAXTICK)
		{
			InputUtility.setTick(0);
			return true;
		}
		return false;
	}
	private static boolean Placeable(double mouseX, double mouseY) {
		if( GameScreen.isIngamescreen() && accessibleboard[(int) (mouseY/BOARD_HEIGHT)][(int) (mouseX/BOARD_WIDTH)]==0)
			return true;
		else 
		{
			return false;
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
