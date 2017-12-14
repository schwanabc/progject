package drawing;
import button.PauseIcon;
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
import logic.GameState;
import model_attacker.Attacker;
import model_general.Board;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.InputUtility;
public class PlayScreen extends HBox{
	private boolean pausedState;
	public static final double MISCSCREENWIDTH=(SceneManager.screenWidth-(GameScreen.GAMESCREEN_WIDTH+Menubar.MENU_WIDTH))/2;
	public static final double MISCSCREENHEIGHT=(SceneManager.screenHeight);
	public static PlayScreen instance;
	private GameScreen gameScreen;
	private Menubar menuBar;
	private AnimationTimer playThread;
	private AnimationTimer pauseThread;
	private Gamelogic gameLogic;
	private Canvas miscScreen;
	private Canvas miscScreen2;
	public PlayScreen()
	{
		instance=this;
		initialize();
		playThread=new AnimationTimer(){
			public void handle(long now)
			{
			//	System.out.println(Gamestate.getTotaltime()/600000000L);
				gameLogic.update();
				paintUpdated();
				checkCondition();
				if(menuBar.isReset())resetGame();
				checkEnd();
			}

		};
		pauseThread=new AnimationTimer(){
			public void handle(long now)
			{
				if(menuBar.getGameState().isLose()==false)paintUpdated();
				if(menuBar.isReset())resetGame();
			}
		};

	}
	private void initialize() {
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
		pauseThread.stop();
		if(pausedState)playThread.start();
		pausedState=false;
		PauseIcon.instance.drawUnpaused();
		InputUtility.reset();
		menuBar.setDefault();
	}	
	protected void paintUpdated() {
		gameScreen.paintComponent();
		RenderableHolder.getInstance().update();
		menuBar.update();
		InputUtility.checkTick();
		checkPaused();
	}
	private void checkCondition() {
		if(Board.isWin())menuBar.getGameState().setWin(true);
		if(Board.getMoney()<=Attacker.getMinCost() && logic.Gamelogic.getAttackerContainer().size()==0)
			menuBar.getGameState().setLose(true);
		if(menuBar.getGameState().isTimeup())menuBar.getGameState().setLose(true);
	}
	void checkEnd() {
		
		if(menuBar.getGameState().isWin())
		{
			RenderableHolder.stopAudio();
			System.out.println("win");
			Board.addNumBoard();
			menuBar.getGameState().endTimethread();
			if(Board.getDefaultNumBoard()==Board.TOTALBOARD) 
			{
				playThread.stop();
				RenderableHolder.getInstance().getEntities().clear();
				SceneManager.gotoWinScreen();}
			else
			{
			resetGame();
			forceEnd();
			SceneManager.gotoWaitScreen();
			}
		}
		else if(menuBar.getGameState().isLose())
		{
			RenderableHolder.stopAudio();
			System.out.println("lose");
			menuBar.getGameState().endTimethread();
			System.out.println("lose2");
			resetGame();
			System.out.println("lose2");
			forceEnd();
			System.out.println("Ending");
			SceneManager.gotoLoseScreen();
		}
	}
	public void forceEnd() {
		playThread.stop();
		RenderableHolder.getInstance().getEntities().clear();

	}
	private void checkPaused() {
		if(InputUtility.isKeyPress()==false && InputUtility.lastKey==KeyCode.SPACE)
		{
			pause();
			InputUtility.lastKey=null;
		}
	}
	public void pause() {
		if(pausedState==false)
		{
			PauseIcon.instance.drawPaused();
			pausedState=true; 
			System.out.print("pause");
			playThread.stop();
			pauseThread.start();
		}
		else
		{
			PauseIcon.instance.drawUnpaused();
			pausedState=false;
			System.out.print("unpause");
			pauseThread.stop();
			playThread.start();
		}
	}
	public boolean isPausedstate() {
		return pausedState;
	}
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	public AnimationTimer getPlayThread() {
		return playThread;
	}


}
