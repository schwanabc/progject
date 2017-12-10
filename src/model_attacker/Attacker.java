package model_attacker;

import java.util.Random;

import SharedObject.RenderableHolder;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Gamelogic;
import logic.Ismovable;
import model_defender.Defender;
import model_defender.HQ;
import model_defender.Tower;
import model_defender.Wall;
import model_general.Board;
import model_general.Entity;

public abstract class Attacker extends Entity implements Ismovable{
	protected double RADIUS;
	protected double DIAMETER;
	protected double WallPriority;
	protected double TowerPriority;
	protected double HQPriority;
	protected model_defender.Defender currentTarget;
	protected static int HiringCost;
	protected static int MinCost=50;
	protected int posXOnBoard,posYOnBoard,countPathLength;
	protected int boardDMG[][] = new int[Board.BOARD_ROW][Board.BOARD_COLUMN];
	protected boolean boardVisited[][] = new boolean[Board.BOARD_ROW][Board.BOARD_COLUMN];
	protected int lastRow[][] = new int[Board.BOARD_ROW][Board.BOARD_COLUMN];
	protected int lastColumn[][] = new int[Board.BOARD_ROW][Board.BOARD_COLUMN];
	protected int posToGoX[] = new int[Board.BOARD_ROW*Board.BOARD_COLUMN];
	protected int posToGoY[] = new int[Board.BOARD_ROW*Board.BOARD_COLUMN];
	protected int countNotMove;
	public Attacker(){}
	public Attacker(double posX,double posY)
	{
		this.posX=posX;
		this.posY=posY;
		posXOnBoard = (int) (posX/Board.BOARD_WIDTH);
		posYOnBoard = (int) (posY/Board.BOARD_HEIGHT);
		if(posXOnBoard >= Board.BOARD_COLUMN/2)
			posXOnBoard--;
		else
			posXOnBoard++;
		if(posYOnBoard >= Board.BOARD_ROW/2)
			posYOnBoard--;
		else
			posYOnBoard++;
		findBestPath();
		//HQPOSY/BOARD_HEIGHT-1;
		//HQPOSX/BOARD_WIDTH-1*;
//		System.out.println("Start Pos " +posXOnBoard+" "+posYOnBoard);
	}
	protected void findBestPath() {
		int inQueueX[] = new int[Board.BOARD_COLUMN*Board.BOARD_ROW];
		int inQueueY[] = new int[Board.BOARD_COLUMN*Board.BOARD_ROW];
		int queueFront = 0,queueLastPos = 1;
		boardVisited[posXOnBoard][posYOnBoard] = true;
		inQueueX[0] = posXOnBoard;
		inQueueY[0] = posYOnBoard;
		while(queueFront != queueLastPos) {
			//Choose the lowest DMG
			int minPos = queueFront;
			for(int i = queueFront; i < queueLastPos; i++) {
				if(boardDMG[inQueueX[i]][inQueueY[i]] < boardDMG[inQueueX[minPos]][inQueueY[minPos]]) {
					minPos = i;
				}
			}
			int temp = inQueueX[minPos];
			inQueueX[minPos] = inQueueX[queueFront];
			inQueueX[queueFront] = temp;
			temp = inQueueY[minPos];
			inQueueY[minPos] = inQueueY[queueFront];
			inQueueY[queueFront] = temp;
			
			int nowX = inQueueX[queueFront];
			int nowY = inQueueY[queueFront];
			queueFront++;
			
			if(Board.getBoard(nowY,nowX) == -1)
				System.out.println("Hole "+boardDMG[nowX][nowY]);
			
			if(Board.getBoard(nowX, nowY) == 3 || (nowX > 1 && nowY > 1 && Board.getBoard(nowX-2,nowY-2) == 3)) { //HQ
				countPathLength = 0;
				for(int i=0;i<Board.BOARD_ROW;i++) {
					for(int j=0;j<Board.BOARD_COLUMN;j++) {
						if(Board.getBoard(i,j) == -1)
							System.out.println(i+" "+j+" *"+boardDMG[i][j]+"* ");	
//							System.out.print(boardDMG[i][j]+" ");	
//						else
//							System.out.print(boardDMG[i][j]+" ");	
					}
					System.out.printf("\n");
				}
				while(nowX != posXOnBoard || nowY != posYOnBoard) {
					System.out.println(nowX +" "+ nowY +" "+Board.getBoard(nowY, nowX));
					posToGoX[countPathLength] = nowX;
					posToGoY[countPathLength] = nowY;
					countPathLength++;
					int tempX = lastRow[nowX][nowY];
					int tempY = lastColumn[nowX][nowY];
					nowX = tempX;
					nowY = tempY;
				}
				countPathLength--;
				break;
			}
			if(nowX != 0 && !boardVisited[nowX-1][nowY] && nowX >= Board.BOARD_COLUMN/2) {
				inQueueX[queueLastPos] = nowX-1;
				inQueueY[queueLastPos] = nowY;
				boardVisited[nowX-1][nowY] = true;
				lastRow[nowX-1][nowY] = nowX;
				lastColumn[nowX-1][nowY] = nowY;
				boardDMG[nowX-1][nowY] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX-1,nowY);
				queueLastPos++;
			}
			else if(nowX != 0 && boardDMG[nowX-1][nowY] > boardDMG[nowX][nowY]+Board.getTowerAttack(nowX-1,nowY) && nowX >= Board.BOARD_COLUMN/2) {
				boardDMG[nowX-1][nowY] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX-1,nowY);
				lastRow[nowX-1][nowY] = nowX;
				lastColumn[nowX-1][nowY] = nowY;
			}
			if(nowY != 0 && !boardVisited[nowX][nowY-1]  && nowY >= Board.BOARD_ROW/2) {
				inQueueX[queueLastPos] = nowX;
				inQueueY[queueLastPos] = nowY-1;
				boardVisited[nowX][nowY-1] = true;
				lastRow[nowX][nowY-1] = nowX;
				lastColumn[nowX][nowY-1] = nowY;
				boardDMG[nowX][nowY-1] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY-1);
				queueLastPos++;
			}
			else if(nowY != 0 && boardDMG[nowX][nowY-1] > boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY-1) && nowY >= Board.BOARD_ROW/2) {
				boardDMG[nowX][nowY-1] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY-1);
				lastRow[nowX][nowY-1] = nowX;
				lastColumn[nowX][nowY-1] = nowY;
			}
			if(nowX+1 != Board.BOARD_ROW && !boardVisited[nowX+1][nowY] && nowX < Board.BOARD_COLUMN/2) {
				inQueueX[queueLastPos] = nowX+1;
				inQueueY[queueLastPos] = nowY;
				boardVisited[nowX+1][nowY] = true;
				lastRow[nowX+1][nowY] = nowX;
				lastColumn[nowX+1][nowY] = nowY;
				boardDMG[nowX+1][nowY] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX+1,nowY);
				queueLastPos++;
			}
			else if(nowX+1 != Board.BOARD_ROW && boardDMG[nowX+1][nowY] > boardDMG[nowX][nowY]+Board.getTowerAttack(nowX+1,nowY) && nowX < Board.BOARD_COLUMN/2) {
				boardDMG[nowX+1][nowY] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX+1,nowY);
				lastRow[nowX+1][nowY] = nowX;
				lastColumn[nowX+1][nowY] = nowY;
			}
			if(nowY+1 != Board.BOARD_COLUMN && !boardVisited[nowX][nowY+1] && nowY < Board.BOARD_ROW/2) {
				inQueueX[queueLastPos] = nowX;
				inQueueY[queueLastPos] = nowY+1;
				boardVisited[nowX][nowY+1] = true;
				lastRow[nowX][nowY+1] = nowX;
				lastColumn[nowX][nowY+1] = nowY;
				boardDMG[nowX][nowY+1] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY+1);
				queueLastPos++;
			}
			else if(nowY+1 != Board.BOARD_COLUMN && boardDMG[nowX][nowY+1] > boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY+1) && nowY < Board.BOARD_ROW/2) {
				boardDMG[nowX][nowY+1] = boardDMG[nowX][nowY]+Board.getTowerAttack(nowX,nowY+1);
				lastRow[nowX][nowY+1] = nowX;
				lastColumn[nowX][nowY+1] = nowY;
			}
			//System.out.println(nowX + " " + nowY);
		}
	}
	@Override
	public void foward(double xAxis,double yAxis)
	{
		double Calibrator=Math.abs(xAxis)+Math.abs(yAxis);
		posX += Calibrate(xAxis,Calibrator)*speed;
		posY += Calibrate(yAxis,Calibrator)*speed;
	}
	@Override
	public double Calibrate(double velocity, double speed) {
		if(speed != 0)
			return velocity/speed;
		else return 0;
	}
	@Override
	public int getZ() {
		return 10;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(posX-RADIUS, posY-RADIUS, DIAMETER, DIAMETER);
		drawHPbar(gc);
	}
	@Override
	public void update() {
		// UPGRADING
		//need decent moving algorithm
		double Xregion=GameScreen.GAMESCREEN_WIDTH/2;
		double Yregion=GameScreen.GAMESCREEN_HEIGHT/2;
		ColliedwithAttacker();
		if(ColliedwithDefender()) {
//			posXOnBoard = (int) (getPosX()/Board.BOARD_WIDTH);
//			posYOnBoard = (int) (getPosY()/Board.BOARD_HEIGHT);
//			System.out.println("atk "+posXOnBoard+" "+posYOnBoard);
			 return;
		}
		
		
		if(countPathLength >= 0) {
			double walkX = (posToGoX[countPathLength])*Board.BOARD_WIDTH+Board.BOARD_WIDTH/2-getPosX();
			double walkY = (posToGoY[countPathLength])*Board.BOARD_HEIGHT+Board.BOARD_HEIGHT/2-getPosY();
			double disWalkX = (walkX > 0)?walkX:-walkX;
			double disWalkY = (walkY > 0)?walkY:-walkY;
			countNotMove++;
			System.out.println("count"+countNotMove);
			if((disWalkX <= 5 && disWalkY <= 5) || countNotMove*speed >= 35.0) {
//				if(Board.getBoard(posToGoY[countPathLength],posToGoX[countPathLength]) == -1) {
//					System.out.println("Now" + posToGoX[countPathLength]+" "+posToGoY[countPathLength]);
//					//while(true);
//				}else
				countPathLength--;
				countNotMove = 0;
			}
			foward(walkX,walkY);
		}
		else {
			double walkX = Board.HQPOSX+Board.BOARD_WIDTH-getPosX();
			double walkY = Board.HQPOSY+Board.BOARD_HEIGHT-getPosY();
			foward(walkX,walkY);
		}
		/*
		else if(currentTarget != null && Gamelogic.isDefenderContain(currentTarget)) {
			if(currentTarget instanceof model_defender.HQ)
				foward((currentTarget.getPosX()+Board.BOARD_WIDTH)-getPosX(),(currentTarget.getPosY()+Board.BOARD_HEIGHT)-getPosY());
			else
				foward((currentTarget.getPosX()+Board.BOARD_WIDTH/2)-getPosX(),(currentTarget.getPosY()+Board.BOARD_HEIGHT/2)-getPosY());
			//System.out.println("Save time");
		}
		else {
			double min = 999999999;
			double walkX = 0,walkY = 0;
			for(Defender defender: Gamelogic.getDefendercontainer()) {
				double dist = Math.hypot((defender.getPosX()+Board.BOARD_WIDTH/2)-getPosX(), (defender.getPosY()+Board.BOARD_HEIGHT/2)-getPosY());
				if(defender instanceof model_defender.HQ) {
					dist = Math.hypot((defender.getPosX()+Board.BOARD_WIDTH)-getPosX(), (defender.getPosY()+Board.BOARD_HEIGHT)-getPosY());
					dist *= 0.6; //HQ High Priority
					dist -= 10;
				}
				if(dist < min && !(defender instanceof model_defender.Wall)) {
					min = dist;
					currentTarget = defender;
					walkX = (defender.getPosX()+Board.BOARD_WIDTH/2)-getPosX();
					walkY = (defender.getPosY()+Board.BOARD_HEIGHT/2)-getPosY();
					if(defender instanceof model_defender.HQ) {
						//System.out.println("HQ " +walkX +" "+ walkY +" " + Board.BOARD_WIDTH+" "+Board.BOARD_HEIGHT);
						walkX = (defender.getPosX()+Board.BOARD_WIDTH)-getPosX();
						walkY = (defender.getPosY()+Board.BOARD_HEIGHT)-getPosY();
						//System.out.println("HQ2 " +walkX +" "+ walkY);
					}
				}
			}
			foward(walkX,walkY);
		}
		*/
	}
	protected boolean ColliedwithDefender()
	{
		int count2=0;
		currentATKTick++;
		double min=999999999;
		int idx=-1;
		boolean iscollide=false;
		for(Defender defender: Gamelogic.getDefendercontainer())
		{
			Circle c=new Circle( posX, posY, RADIUS);
			Rectangle r=new Rectangle(defender.getPosX(),defender.getPosY(),defender.getWallwidth(),defender.getWallheight());
			if (c.getBoundsInParent().intersects(r.getBoundsInParent())) {
		        double dist=Math.hypot(defender.getPosX()+defender.getWallwidth()/2-getPosX(),defender.getPosY()+defender.getWallheight()/2-getPosY());
				if(dist<min)
				{
					min=dist;
					idx=count2;
				}
		        iscollide=true;
		      }
			count2++;
		}
		if(currentATKTick>=AttackTick&&iscollide)
		{
			currentATKTick=0;
			Attack(Gamelogic.getDefendercontainer().get(idx));
		}
		return iscollide;
		
	}
	protected void ColliedwithAttacker()
	{
		// push 2 time to reduced chance that it will move forward and it only take O(n) time
		Fluidpush();
		Fluidpush();
	}
	private void Fluidpush() 
	{
		for(Attacker attacker:Gamelogic.getAttackercontainer())
		{
			try
			{
					double x0=posX;
					double y0=posY;
					double r0=RADIUS;
					double x1=attacker.getPosX();
					double y1=attacker.getPosY();
					double r1=attacker.getRADIUS();
					
					if(Math.hypot(x0-x1, y0-y1) <= (r0 + r1) && Math.hypot(x0-x1, y0-y1)!=0)
					{
						
						Random rand = new Random();
						int  n = rand.nextInt(4) + 1;
						if(n%2==0)
						{
							attacker.foward(-1*(x0-x1+0.01),-1*(y0-y1));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1+0.01),(y0-y1));
						}
						else if(n%2==1)
						{
							attacker.foward(-1*(x0-x1-0.01),-1*(y0-y1));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1-0.01),(y0-y1));
						}
						else if(n%2==2)
						{
							attacker.foward(-1*(x0-x1),-1*(y0-y1-0.01));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1),(y0-y1-0.01));
						}
						else if(n%2==3)
						{
							attacker.foward(-1*(x0-x1),-1*(y0-y1+0.01));
							if(attacker.ColliedwithDefender())attacker.foward((x0-x1),(y0-y1+0.01));
						}
					}
			}
			catch(Exception e) {}
		}
		
	}
	protected void drawHPbar(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double ratio=(HP/MaxHP);
		if(ratio<0)ratio=0;
		gc.setFill(Color.DARKGREEN);
		if(ratio!=1)gc.fillRect(posX-RADIUS, posY-RADIUS, DIAMETER-1, 4);
		gc.setFill(Color.ORANGERED);
		if(ratio!=1)gc.fillRect(posX-RADIUS+DIAMETER*(ratio)-0.3, posY-RADIUS, DIAMETER*(1-ratio), 4);

		gc.setStroke(Color.BLACK);
		if(ratio!=1)gc.strokeRect(posX-RADIUS, posY-RADIUS, DIAMETER-1, 4);
	}
	protected void Attack(Defender defender) {
		double downHP=ATK-defender.getDEF();
		if(downHP<0)downHP=1;
		defender.setHP(defender.getHP()-downHP);//temp
        defender.chekdestroyed(); //temp
        RenderableHolder.Attack_sword.setVolume(0.1);
        RenderableHolder.Attack_sword.play();
        currentATKTick=0;
	}
	public double getRADIUS() {
		return RADIUS;
	}
	public static int getMinCost() {
		return MinCost;
	}
}
