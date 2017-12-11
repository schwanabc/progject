package drawing;
import Button.PauseIcon;
import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import logic.Gamelogic;
import logic.Gamestate;
import model_attacker.Attacker;
import model_general.Board;
public class PlayScreen extends HBox{
	private static boolean pausedState;
	public static final double MISCSCREENWIDTH=(SceneManager.SCREEN_WIDTH-(GameScreen.GAMESCREEN_WIDTH+Menubar.MENU_WIDTH))/2;
	public static final double MISCSCREENHEIGHT=(SceneManager.SCREEN_HEIGHT);
	public static PlayScreen instance;
	private GameScreen gameScreen;
	private Menubar menuBar;
	private AnimationTimer AT;
	private AnimationTimer AT2;
	private Gamelogic gameLogic;
	private Canvas miscScreen;
	private Canvas miscScreen2;
	public PlayScreen()
	{
		instance=this;
		initialize();
		AT=new AnimationTimer(){
			public void handle(long now)
			{
			//	System.out.println(Gamestate.getTotaltime()/600000000L);
				gameLogic.update();
				Paintupdated();
				Checkcondition();
				if(menuBar.isReset())resetGame();
				Checkend();
			}

		};
		AT2=new AnimationTimer(){
			public void handle(long now)
			{
				if(menuBar.getGamestate().isLose()==false)Paintupdated();
				if(menuBar.isReset())resetGame();
			}
		};

	}
	private void initialize() {
		// TODO Auto-generated method stub
		fillMiscscreen();
		gameScreen=new GameScreen();
		menuBar=new Menubar();
		gameLogic=new Gamelogic();
		this.getChildren().addAll(miscScreen,gameScreen,menuBar,miscScreen2);
	}
	private void fillMiscscreen() {
		miscScreen=new Canvas(MISCSCREENWIDTH,MISCSCREENHEIGHT);
		GraphicsContext gc=miscScreen.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, MISCSCREENWIDTH, MISCSCREENHEIGHT);
		miscScreen2=new Canvas(MISCSCREENWIDTH,MISCSCREENHEIGHT);
		gc=miscScreen2.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, MISCSCREENWIDTH, MISCSCREENHEIGHT);
	}
	private void resetGame() {
		//Dont use gotoplayscreen,as it will make the game blink
		RenderableHolder.stopAudio();
		RenderableHolder.getInstance().getEntities().clear();
		gameLogic=new Gamelogic();
		menuBar.setdefault();
		InputUtility.currentChosed="x";
		AT2.stop();
		if(pausedState)AT.start();
		pausedState=false;
		PauseIcon.instance.drawUnpaused();
	}	
	protected void Paintupdated() {
		gameScreen.paintComponent();
		RenderableHolder.getInstance().update();
		menuBar.update();
		InputUtility.checkTick();
		checkPaused();
	}
	private void Checkcondition() {
		// TODO Auto-generated method stub
		if(Board.isIswin())menuBar.getGamestate().setWin(true);
		if(Board.getMoney()<=Attacker.getMinCost() && logic.Gamelogic.getAttackercontainer().size()==0)
			menuBar.getGamestate().setLose(true);
		if(menuBar.getGamestate().isTimeup())menuBar.getGamestate().setLose(true);
	}
	void Checkend() {
		// TODO Auto-generated method stub
		
		if(menuBar.getGamestate().isWin())
		{
			RenderableHolder.stopAudio();
			System.out.println("win");
			Board.addNumBoard();
			menuBar.getGamestate().endTimethread();
			if(Board.getDefaultNumBoard()==Board.TOTALBOARD) 
			{
				AT.stop();
				RenderableHolder.getInstance().getEntities().clear();
				SceneManager.gotoWinScreen();}
			else
			{
			resetGame();
			forceEnd();
			SceneManager.gotoWaitScreen();
			}
		}
		else if(menuBar.getGamestate().isLose())
		{
			RenderableHolder.stopAudio();
			System.out.println("lose");
			menuBar.getGamestate().endTimethread();
			resetGame();
			forceEnd();
			SceneManager.gotoLoseScreen();
		}
	}
	public void forceEnd() {
		AT.stop();
		RenderableHolder.getInstance().getEntities().clear();

	}
	private void checkPaused() {
		// TODO Auto-generated method stub
		if(InputUtility.isKeyPress()==false && InputUtility.lastKey==KeyCode.SPACE)
		{
			Pause();
			InputUtility.lastKey=null;
		}
	}
	public void Pause() {
		if(pausedState==false)
		{
			PauseIcon.instance.drawPaused();
			pausedState=true; 
			System.out.print("pause");
			AT.stop();
			AT2.start();
		}
		else
		{
			PauseIcon.instance.drawUnpaused();
			pausedState=false;
			System.out.print("unpause");
			AT2.stop();
			AT.start();
		}
	}
	public static boolean isPausedstate() {
		return pausedState;
	}
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	public AnimationTimer getAT() {
		return AT;
	}


}
