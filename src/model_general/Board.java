package model_general;


import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

import logic.Gamelogic;
import drawing.GameScreen;
import exception.PoorException;
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
import scenemanager.SceneManager;
import sharedobject.IRenderable;
import sharedobject.RenderableHolder;
import utility.InputUtility;
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
	private static boolean isWin;
	private static int[] defaultMoney= {1500,4000,3000};
	private static int defaultNumBoard=0;
	private static int numBoard=0;
	private static int money;
	private static int board[][];
	private static int[][] accessibleBoard;
	public Board()
	{	

		numBoard=defaultNumBoard;
		isWin=false; 
		try {
		money=defaultMoney[numBoard];
		board=readBoard(numBoard);
		fillAccessibleBoard();
		setBoard();
		}
		catch (ArrayIndexOutOfBoundsException e) {SceneManager.gotoWinScreen();System.exit(0); }
	}
	private int[][] readBoard(int numBoard) {
		
		int[][] tempboard=new int[BOARD_ROW][BOARD_COLUMN];
			String fileName="/Stage_"+Integer.toString(numBoard)+".txt";
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
	private void fillAccessibleBoard() {
		accessibleBoard=new int[BOARD_ROW][BOARD_COLUMN];
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
							accessibleBoard[i+k][j+l]=1;
							}
							catch (Exception e) {}
						}
					}
				}
				else
				{
					if(accessibleBoard[i][j]!=1)accessibleBoard[i][j]=0;
				}
			}
	}
	public static int getTowerAttack(int posY,int posX) {
		//Not finish
		int countTower = 0;
		for(int i=0;i<BOARD_ROW;i++) {
			for(int j=0;j<BOARD_COLUMN;j++) {
				int diff = (posY-j>0)?(posY-j):(j-posY);
				diff += (posX-i>0)?(posX-i):(i-posX);
				if(board[j][i] == 2 || board[j][i] ==4 && diff <= 5) {
					countTower++;
				}
			}
		}
		
		int towerATK = countTower;
		if(board[posX][posY] == 1)
			towerATK *= 5;
//		else if(board[posX][posY] == -1) {
//			System.out.println("Find Hole");
//			towerATK *= 0;
//		}
		return towerATK+3;
	}
	public void setBoard() {
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
				if(board[i][j]==4)
				{
					Gamelogic.addNewObject(new PenetrateTower(BOARD_WIDTH*j, BOARD_HEIGHT*i,i,j));
				}
			}
	}

	public void draw(GraphicsContext gc) {
		for(int i=0;i<BOARD_ROW;i++)
			for(int j=0;j<BOARD_COLUMN;j++)
			{
				gc.setFill(Color.WHITE);
				gc.fillRect(BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				if(accessibleBoard[i][j]==1)
				{
					gc.drawImage(RenderableHolder.stoneTile, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setGlobalAlpha(0.15);
					gc.drawImage(RenderableHolder.stripe, BOARD_WIDTH*j-1, BOARD_HEIGHT*i-1, BOARD_WIDTH+2, BOARD_HEIGHT+2);
					gc.setGlobalAlpha(1);
					
				}
				if(board[i][j]==-1)
				{
			
					gc.drawImage(RenderableHolder.rubble,BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
					gc.setGlobalAlpha(0.15);
					gc.drawImage(RenderableHolder.stripe, BOARD_WIDTH*j-1, BOARD_HEIGHT*i-1, BOARD_WIDTH+2, BOARD_HEIGHT+2);
					gc.setGlobalAlpha(1);

				}
				if(accessibleBoard[i][j]==0)
				{
					gc.drawImage(RenderableHolder.grass, BOARD_WIDTH*j, BOARD_HEIGHT*i, BOARD_WIDTH, BOARD_HEIGHT);
				}
			}
	}
	public static void update()
	{
		checkAdded();
	}
	private static void checkAdded() {
	//	System.out.println(InputUtility.getTick());
		if(checkToAdd())
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
		if(money-hiringCost>=0)return true;
		try {
			throw new PoorException();
		} catch (PoorException e) {
			return false;
		}
	}
	private static boolean checkToAdd() {
		return isDeyployable() && isPlaceable(InputUtility.mouseX,InputUtility.mouseY) && !InputUtility.currentChosed.equals("x");
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
	private static boolean isPlaceable(double mouseX, double mouseY) {
		if( GameScreen.isIngamescreen() && accessibleBoard[(int) (mouseY/BOARD_HEIGHT)][(int) (mouseX/BOARD_WIDTH)]==0)
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
	public static boolean isWin() {
		return isWin;
	}
	public static void setIsWin(boolean isWin) {
		Board.isWin = isWin;
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
		return money;
	}
	private static void decreaseMoney(int decreaseMoney) {
		if(Board.money-decreaseMoney>=0) Board.money -= decreaseMoney;
	}
	public static void setMoney(int newMoney) {
		money = newMoney;
	}
	public static void addNumBoard() {
		defaultNumBoard++;
	}
	public static void resetNumBoard() {
		defaultNumBoard=0;
	}
	public static void decreaseNumBoard() {
		if(defaultNumBoard>0)defaultNumBoard--;
	}
	public static int getDefaultNumBoard() {
		return defaultNumBoard;
	}

}
