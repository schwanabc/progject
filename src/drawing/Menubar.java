package drawing;
import java.io.InputStream;

import button.ExitIcon;
import button.HomeIcon;
import button.PauseIcon;
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
import logic.GameState;
import model_general.Board;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.InputUtility;
import utility.Utility;
public class Menubar extends VBox{
	public final static double MENU_WIDTH=GameScreen.GAMESCREEN_WIDTH*0.33;
	public final static double MENU_HEIGHT=GameScreen.GAMESCREEN_HEIGHT;
	private final int VTAB=4;
	private final int HTAB=2;
	public static final double ICONPOS=MENU_HEIGHT*0.25;
	private final double ICONHEIGHT=(MENU_HEIGHT-ICONPOS)/VTAB;
	private final double ICONWIDTH=MENU_WIDTH/HTAB;
	public static Menubar instance; 
	private Canvas[][] icon;
	private GridPane gridPane;
	private int choseRow=-1;
	private int choseColumn=-1;
	private GameState gameState;
	private Canvas menuCanvas;
	private boolean isReset=false;
	private HomeIcon homeIcon;
	private ExitIcon exitIcon;
	private PauseIcon pauseIcon;
	public Menubar()
	{
		instance=this;
		icon=new Canvas[VTAB][HTAB];
		gameState=new GameState();
		setMenuBar();
	}
	private void setMenuBar() {
		setMenuTab();
		setIcon();
	}
	private void setIcon() {
		gridPane=new GridPane();
		for(int i=0;i<VTAB;i++)
		{
			for(int j=0;j<HTAB;j++)
			{
				icon[i][j]=new Canvas(ICONWIDTH,ICONHEIGHT);
				GraphicsContext gc=icon[i][j].getGraphicsContext2D();
				gc.drawImage(RenderableHolder.releaseButton,0,0, ICONWIDTH, ICONHEIGHT);
				gc.setFont(RenderableHolder.chooseIconFont);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setTextBaseline(VPos.CENTER);
				fillText(gc,i,j);
				gridPane.add(icon[i][j], j, i);
				checkEvent(icon[i][j],i,j);
			}
		}
		this.getChildren().add(gridPane);
		homeIcon=new HomeIcon();
		homeIcon.setManaged(false);
		this.getChildren().add(homeIcon);
		homeIcon.relocate(MENU_WIDTH*0.8, 0);
		exitIcon=new ExitIcon();
		exitIcon.setManaged(false);
		this.getChildren().add(exitIcon);
		exitIcon.relocate(MENU_WIDTH*0.8, ICONPOS*0.8/3);
		pauseIcon=new PauseIcon();
		pauseIcon.setManaged(false);
		this.getChildren().add(pauseIcon);
		pauseIcon.relocate(MENU_WIDTH*0.8, ICONPOS*0.8*2/3);
	}
	private void fillText(GraphicsContext gc,int i, int j) {
		if(i+1==VTAB&& j+1==HTAB)
			gc.fillText("Reset", ICONWIDTH*0.5,ICONHEIGHT*0.5);
		else {
			if(i == 0 && j == 0){
				gc.fillText("Peasant\n\n\n"+model_attacker.Peasant.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.armed_Peasant, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 0 && j == 1){
				gc.fillText("Footman\n\n\n"+model_attacker.Footman.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.footman, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 1 && j == 0){
				gc.fillText("War dog\n\n\n"+model_attacker.Wardog.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.wardog, ICONWIDTH*0.5-15, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+10, ICONHEIGHT*0.2+20);
			}
			else if(i == 1 && j == 1){
				gc.fillText("Berserker\n\n\n"+model_attacker.Berserker.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.berserker, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 2 && j == 0){
				gc.fillText("Sapper\n\n\n"+model_attacker.Sapper.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.sapper, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
			else if(i == 2 && j == 1){
				gc.fillText("Saboteur\n\n\n"+model_attacker.Saboteur.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.saboteur, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);

			}
			else if(i == 3 && j == 0){
				gc.fillText("General\n\n\n"+model_attacker.General.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gc.drawImage(RenderableHolder.general, ICONWIDTH*0.5-20, ICONHEIGHT*0.5-20, ICONWIDTH*0.2+20, ICONHEIGHT*0.2+20);
			}
		}
	}
	private void checkEvent(Canvas canvas, int row, int column) {
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
					GraphicsContext temp=icon[choseRow][choseColumn].getGraphicsContext2D();
					setUnClick(temp,choseRow,choseColumn);
					setUnHover(temp,choseRow,choseColumn);
				}
				catch(Exception e) {}//Do nothing
				choseRow=row;
				choseColumn=column;
			}
			if(!(choseRow+1==VTAB&& choseColumn+1==HTAB))setClick(gc, row, column);
			else setUnClick(gc, row, column);
			chooseCurrentBot(row,column);
		});
	}

	private void setHover(GraphicsContext gc,int row,int column) {
		RenderableHolder.buttonHover.play();
		ColorAdjust colorAdjust=new ColorAdjust();
		colorAdjust.setBrightness(0.2);
		icon[row][column].setEffect(colorAdjust);
	}
	private void setUnHover(GraphicsContext gc,int row,int column) {
		
		ColorAdjust colorAdjust=new ColorAdjust();
		colorAdjust.setBrightness(0.0);
		icon[row][column].setEffect(colorAdjust);
	}
	private void chooseCurrentBot(int row, int column) {
		if(row == 0 && column == 0)
			InputUtility.currentChosed ="Peasant";
		else if(row == 0 && column == 1)
			InputUtility.currentChosed ="Footman";
		else if(row == 1 && column == 0)
			InputUtility.currentChosed ="Wardog";
		else if(row == 1 && column == 1)
			InputUtility.currentChosed ="Berserker";
		else if(row == 2 && column == 0)
			InputUtility.currentChosed ="Sapper";
		else if(row == 2 && column == 1)
			InputUtility.currentChosed ="Saboteur";
		else if(row == 3 && column == 0)
			InputUtility.currentChosed ="General";
		else if(row == VTAB-1 && column == HTAB-1)isReset=true;
		else InputUtility.currentChosed ="x";
	}
	private void setClick(GraphicsContext gc,int row,int column) {
		RenderableHolder.clickedSound.play();
		gc.drawImage(RenderableHolder.pressButton, 0, 0, ICONWIDTH, ICONHEIGHT);
		fillText(gc,row,column);
	}
	private void setUnClick(GraphicsContext gc,int row,int column) {
		//RenderableHolder.Clickedmenu.play();
		gc.drawImage(RenderableHolder.releaseButton, 0, 0, ICONWIDTH, ICONHEIGHT);
		fillText(gc,row,column);
	}
	
	private void paintMenuCanvas(GraphicsContext gc)
	{
		gc.drawImage(RenderableHolder.menuBackground, 0, 0, MENU_WIDTH*0.8, ICONPOS*0.8);
		gc.drawImage(RenderableHolder.errorFrame, 0, ICONPOS*0.8, MENU_WIDTH+2, ICONPOS*0.2);
		gc.setFont(RenderableHolder.menuFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.SADDLEBROWN);
		gc.fillText("MENU", MENU_WIDTH*0.4,ICONPOS*0.2);
	}
	private void setMenuTab() {
		menuCanvas=new Canvas(MENU_WIDTH,ICONPOS);
		GraphicsContext gc=menuCanvas.getGraphicsContext2D();
		paintMenuCanvas(gc);
		this.getChildren().add(menuCanvas);
	}
	public void update() {

		if(Board.isWin())gameState.setWin(true);
		new Thread(()->{
			Platform.runLater(()->
			{
				GraphicsContext gc=menuCanvas.getGraphicsContext2D();
				paintMenuCanvas(gc);
				gc.setFill(Color.SADDLEBROWN);
				gc.setFont(RenderableHolder.menuFont);
				gc.setTextAlign(TextAlignment.LEFT);
				gc.setTextBaseline(VPos.TOP);
				gc.fillText("Money: "+Board.getMoney(), 25, ICONPOS*0.25);
				gc.fillText("Stage: "+(Board.getDefaultNumBoard()+1), 25, ICONPOS*0.40);
				gc.setTextAlign(TextAlignment.LEFT);
				Long sec=gameState.getSecond();
				String second=sec.toString();
				if(sec<10)second="0"+second;
				gc.fillText("Time: "+gameState.getMinute()+" : "+second,25 , ICONPOS*0.55);
				Thread.yield();
			});
		}).start();
	}
	public GameState getGameState() {
		return gameState;
	}
	public boolean isReset() {
		return isReset;
	}
	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}
	public void setDefault() {
		gameState.initialize();
		isReset=false;
		update();
		GraphicsContext gc=icon[choseRow][choseColumn].getGraphicsContext2D();
		setUnClick(gc, choseRow, choseColumn);
		if(choseRow==VTAB-1&&choseColumn==HTAB-1)setUnClick(icon[VTAB-1][HTAB-1].getGraphicsContext2D(), VTAB-1, HTAB-1);
		choseRow=-1;
		choseColumn=-1;
	}
	public void writePoor(String text) {
		
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
				 GraphicsContext gc=menuCanvas.getGraphicsContext2D();
					gc.setFill(Color.RED);
					gc.setFont(RenderableHolder.wariningFont);
					gc.fillText(text, Utility.getTextStartWidht(MENU_WIDTH, Utility.getFont_width(text,RenderableHolder.wariningFont)), ICONPOS*0.87);
			}
		}.start();
	}
	
	
}
