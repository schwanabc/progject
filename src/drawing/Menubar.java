package drawing;
import Button.HomeIcon;
import Button.ExitIcon;

import java.io.InputStream;

import Input.InputUtility;
import Scenemanager.PlayScreen;
import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.Utility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.Gamestate;
import model_general.Board;
public class Menubar extends VBox{
	public static double MENU_WIDTH=GameScreen.GAMESCREEN_WIDTH*0.33;
	public static double MENU_HEIGHT=GameScreen.GAMESCREEN_HEIGHT;
	private static InputStream fontStream = ClassLoader.getSystemResourceAsStream("Aaargh.ttf");
	private static InputStream fontStream2 = ClassLoader.getSystemResourceAsStream("Pamela.ttf");
	private static InputStream fontStream3 = ClassLoader.getSystemResourceAsStream("Aaargh.ttf");
	private static final Font TEXT_FONT = Font.loadFont(fontStream,20);
	private static final Font TIME_TEXT_FONT = Font.loadFont(fontStream2, 20);
	private static final Font WARN_FONT = Font.loadFont(fontStream3, 14);
	private static final int VTAB=4;
	private static final int HTAB=2;
	public static double ICONPOS;
	public static double ICONHEIGHT;
	public static double ICONWIDTH;
	public static Menubar instance; 
	private Canvas[][] menu=new Canvas[VTAB][HTAB];
	private GridPane gp;
	private int choseRow;
	private int choseColumn;
	private Gamestate Gamestate;
	private Canvas Menucanvas;
	private boolean isReset;
	private HomeIcon HomeIcon;
	private ExitIcon ExitIcon;
	public Menubar()
	{
	//	this.setBackground(new Background(new BackgroundImage(RenderableHolder.Menubackground, null, null, null, new BackgroundSize(MENU_WIDTH, ICONPOS,true,false,true,false))));
		instance=this;
		isReset=false;
		choseRow=-1;
		choseColumn=-1;
		Gamestate=new Gamestate();
		ICONPOS= MENU_HEIGHT*0.25;
		ICONHEIGHT=(MENU_HEIGHT-ICONPOS)/VTAB;
		ICONWIDTH=MENU_WIDTH/HTAB;
		setMenu();
		
	}
	private void setMenu() {
		setMenutab();
		setIcon();
	}
	private void setIcon() {
		gp=new GridPane();
		for(int i=0;i<VTAB;i++)
		{
			for(int j=0;j<HTAB;j++)
			{
				menu[i][j]=new Canvas(ICONWIDTH,ICONHEIGHT);
				GraphicsContext gc=menu[i][j].getGraphicsContext2D();
				gc.drawImage(RenderableHolder.ReleaseButton,0,0, ICONWIDTH, ICONHEIGHT);
				gc.setFont(TEXT_FONT);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setTextBaseline(VPos.CENTER);
				Filltext(gc,i,j);
				
				gp.add(menu[i][j], j, i);
				Checkevent(menu[i][j],i,j);
			}
		}
		this.getChildren().add(gp);
		HomeIcon=new HomeIcon();
		HomeIcon.setManaged(false);
		this.getChildren().add(HomeIcon);
		HomeIcon.relocate(MENU_WIDTH*0.8, 0);
		ExitIcon=new ExitIcon();
		ExitIcon.setManaged(false);
		this.getChildren().add(ExitIcon);
		ExitIcon.relocate(MENU_WIDTH*0.8, ICONPOS*0.8*0.33);
	
	}
	private void Filltext(GraphicsContext gc,int i, int j) {
		// TODO Auto-generated method stub
		if(i+1==VTAB&& j+1==HTAB)
			gc.fillText("Reset", ICONWIDTH*0.5,ICONHEIGHT*0.5);
		else {
			if(i == 0 && j == 0){
				gc.fillText("Normal\n\n\n"+model_attacker.Bot0.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.Armed_Peasant, ICONWIDTH*0.3, ICONHEIGHT*0.35, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 0 && j == 1){
				gc.fillText("Tank\n\n\n"+model_attacker.Bot1.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.Footman, ICONWIDTH*0.3, ICONHEIGHT*0.35, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 1 && j == 0){
				gc.fillText("Fast\n"+model_attacker.Bot2.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
			}
			else if(i == 1 && j == 1){
				gc.fillText("Destroy\n"+model_attacker.Bot3.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
			}
			else if(i == 2 && j == 0){
				gc.fillText("Wall\nBomber\n"+model_attacker.Bot4.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
			}
			else if(i == 2 && j == 1){
				gc.fillText("HQ\nAttack\n"+model_attacker.Bot5.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
			}
			else if(i == 3 && j == 0){
				gc.fillText("Boss\n"+model_attacker.Bot6.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
			}
		}
	}
	private void Checkevent(Canvas canvas, int row, int column) {
		GraphicsContext gc=canvas.getGraphicsContext2D();
		canvas.setOnMouseEntered(ev->
		{
			if(!(row==choseRow &&column==choseColumn))
			{
				setHover(gc,row,column);
			}
		});
		canvas.setOnMouseExited(ev->
		{
			if(!(row==choseRow &&column==choseColumn))
			{
				setUnHover(gc,row,column);
			}
		});
		canvas.setOnMouseClicked(ev->
		{
			
			if(row!=choseRow ||column!=choseColumn)
			{
				try
				{
					GraphicsContext temp=menu[choseRow][choseColumn].getGraphicsContext2D();
					setUnClick(temp,choseRow,choseColumn);
					setUnHover(temp,choseRow,choseColumn);
				}
				catch(Exception e) {}//Do nothing
				choseRow=row;
				choseColumn=column;
			}
			if(!(choseRow+1==VTAB&& choseColumn+1==HTAB))setClick(gc, row, column);
			else setUnClick(gc, row, column);
			Choosecurrentbot(row,column);
		});
	}

	private void setHover(GraphicsContext gc,int row,int column) {
		RenderableHolder.Buttonhover.play();
		ColorAdjust colorAdjust=new ColorAdjust();
		colorAdjust.setBrightness(0.2);
		menu[row][column].setEffect(colorAdjust);
	}
	private void setUnHover(GraphicsContext gc,int row,int column) {
		
		ColorAdjust colorAdjust=new ColorAdjust();
		colorAdjust.setBrightness(0.0);
		menu[row][column].setEffect(colorAdjust);
	}
	private void Choosecurrentbot(int row, int column) {
		if(row == 0 && column == 0)
			InputUtility.currentChosed ="Weak_1";
		else if(row == 0 && column == 1)
			InputUtility.currentChosed ="Weak_2";
		else if(row == 1 && column == 0)
			InputUtility.currentChosed ="Weak_3";
		else if(row == 1 && column == 1)
			InputUtility.currentChosed ="Weak_4";
		else if(row == 2 && column == 0)
			InputUtility.currentChosed ="Weak_5";
		else if(row == 2 && column == 1)
			InputUtility.currentChosed ="Weak_6";
		else if(row == 3 && column == 0)
			InputUtility.currentChosed ="Weak_7";
		else if(row == VTAB-1 && column == HTAB-1)isReset=true;
		else InputUtility.currentChosed ="x";
	}
	private void setClick(GraphicsContext gc,int row,int column) {
		RenderableHolder.Clickedsound.play();
		gc.drawImage(RenderableHolder.PressButton, 0, 0, ICONWIDTH, ICONHEIGHT);
		Filltext(gc,row,column);
	}
	private void setUnClick(GraphicsContext gc,int row,int column) {
		//RenderableHolder.Clickedmenu.play();
		gc.drawImage(RenderableHolder.ReleaseButton, 0, 0, ICONWIDTH, ICONHEIGHT);
		Filltext(gc,row,column);
	}
	
	private void PaintMenucanvas(GraphicsContext gc)
	{
		gc.drawImage(RenderableHolder.Menubackground, 0, 0, MENU_WIDTH*0.8, ICONPOS*0.8);
		gc.drawImage(RenderableHolder.ErrorFrame, 0, ICONPOS*0.8, MENU_WIDTH+2, ICONPOS*0.2);
		gc.setFont(TIME_TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.BLACK);
		gc.fillText("MENU", MENU_WIDTH*0.4,ICONPOS*0.2);
	}
	public void setMenutab() {
		Menucanvas=new Canvas(MENU_WIDTH,ICONPOS);
		GraphicsContext gc=Menucanvas.getGraphicsContext2D();
		PaintMenucanvas(gc);
		
		this.getChildren().add(Menucanvas);
	}
	public void update() {
		// TODO Auto-generated method stub

		if(Board.isIswin())Gamestate.setWin(true);
		Thread t1= new Thread(()->{
			Platform.runLater(()->
			{
				GraphicsContext gc=Menucanvas.getGraphicsContext2D();
				PaintMenucanvas(gc);
				gc.setFill(Color.BLACK);
				gc.setFont(TIME_TEXT_FONT);
				gc.setTextAlign(TextAlignment.LEFT);
				gc.setTextBaseline(VPos.TOP);
				gc.fillText("Money: "+Board.getMoney(), 25, ICONPOS*0.25);
				gc.fillText("Stage: "+(Board.getDefaultNumboard()+1), 25, ICONPOS*0.40);
				gc.setTextAlign(TextAlignment.LEFT);
				Long sec=Gamestate.getsecond();
				String second=sec.toString();
				if(sec<10)second="0"+second;
				gc.fillText("Time: "+Gamestate.getMinute()+" : "+second,25 , ICONPOS*0.55);
				Thread.yield();
			});
		});
		try {
		//	t1.join();
			t1.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public Gamestate getGamestate() {
		return Gamestate;
	}
	public boolean isReset() {
		return isReset;
	}
	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}
	public void setdefault() {
		Gamestate.initialize();
		update();
		isReset=false;
		GraphicsContext gc=menu[choseRow][choseColumn].getGraphicsContext2D();
		setUnClick(gc, choseRow, choseColumn);
		if(choseRow==VTAB-1&&choseColumn==HTAB-1)setUnClick(menu[VTAB-1][HTAB-1].getGraphicsContext2D(), VTAB-1, HTAB-1);
		choseRow=-1;
		choseColumn=-1;
	}
	public void writepoor(String text) {
		
		new AnimationTimer(){	
			long totaltime=0;
			long currenttime=System.nanoTime();
			long prevtime=System.nanoTime();
			public void handle(long now)
			{
				currenttime=System.nanoTime();	
				totaltime+=currenttime-prevtime;
				//System.out.println(totaltime);
				if(totaltime>=0.5*1000000000L)
				{
					this.stop();
				}
				prevtime=currenttime;
				 GraphicsContext gc=Menucanvas.getGraphicsContext2D();
					gc.setFill(Color.RED);
					gc.setFont(WARN_FONT);
					gc.fillText(text, Utility.TextStartWidht(MENU_WIDTH, Utility.getFont_width(text,WARN_FONT)), ICONPOS*0.87);
			}
		}.start();
	}
	
	
}
