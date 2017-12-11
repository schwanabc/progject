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
	private static boolean pausedstate;
	public static double MiscScreenWIDTH=(SceneManager.SCREEN_WIDTH-(GameScreen.GAMESCREEN_WIDTH+Menubar.MENU_WIDTH))/2;
	public static double MiscScreenHEIGHT=(SceneManager.SCREEN_HEIGHT);
	public static PlayScreen instance;
	private GameScreen gamescreen;
	private Menubar menubar;
	private AnimationTimer AT;
	private AnimationTimer AT2;
	private Gamelogic Gamelogic;
	private Canvas MiscScreen;
	private Canvas MiscScreen2;
	public PlayScreen()
	{
		instance=this;
		Initialize();
		AT=new AnimationTimer(){
			public void handle(long now)
			{
			//	System.out.println(Gamestate.getTotaltime()/600000000L);
				Gamelogic.update();
				Paintupdated();
				Checkcondition();
				if(menubar.isReset())Resetgame();
				Checkend();
			}

		};
		AT2=new AnimationTimer(){
			public void handle(long now)
			{
				if(menubar.getGamestate().isLose()==false)Paintupdated();
				if(menubar.isReset())Resetgame();
			}
		};

	}
	private void Initialize() {
		// TODO Auto-generated method stub
		FillMiscscreen();
		gamescreen=new GameScreen();
		menubar=new Menubar();
		Gamelogic=new Gamelogic();
		this.getChildren().addAll(MiscScreen,gamescreen,menubar,MiscScreen2);
	}
	private void FillMiscscreen() {
		MiscScreen=new Canvas(MiscScreenWIDTH,MiscScreenHEIGHT);
		GraphicsContext gc=MiscScreen.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, MiscScreenWIDTH, MiscScreenHEIGHT);
		MiscScreen2=new Canvas(MiscScreenWIDTH,MiscScreenHEIGHT);
		gc=MiscScreen2.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, MiscScreenWIDTH, MiscScreenHEIGHT);
	}
	private void Resetgame() {
		//Dont use gotoplayscreen,as it will make the game blink
		RenderableHolder.StopAudio();
		RenderableHolder.getInstance().getEntities().clear();
		Gamelogic=new Gamelogic();
		menubar.setdefault();
		InputUtility.currentChosed="x";
		AT2.stop();
		if(pausedstate)AT.start();
		pausedstate=false;
		PauseIcon.instance.drawUnpaused();
	}	
	protected void Paintupdated() {
		gamescreen.PaintComponent();
		RenderableHolder.getInstance().update();
		menubar.update();
		InputUtility.checkTick();
		Checkpaused();
	}
	private void Checkcondition() {
		// TODO Auto-generated method stub
		if(Board.isIswin())menubar.getGamestate().setWin(true);
		if(Board.getMoney()<=Attacker.getMinCost() && logic.Gamelogic.getAttackercontainer().size()==0)
			menubar.getGamestate().setLose(true);
		if(menubar.getGamestate().isTimeup())menubar.getGamestate().setLose(true);
	}
	void Checkend() {
		// TODO Auto-generated method stub
		
		if(menubar.getGamestate().isWin())
		{
			RenderableHolder.StopAudio();
			System.out.println("win");
			Board.addNumboard();
			menubar.getGamestate().EndTimethread();
			if(Board.getDefaultNumboard()==Board.TOTALBOARD) 
			{
				AT.stop();
				RenderableHolder.getInstance().getEntities().clear();
				SceneManager.gotoWinScreen();}
			else
			{
			Resetgame();
			Forceend();
			SceneManager.gotoWaitScreen();
			}
		}
		else if(menubar.getGamestate().isLose())
		{
			RenderableHolder.StopAudio();
			System.out.println("lose");
			menubar.getGamestate().EndTimethread();
			Resetgame();
			Forceend();
			SceneManager.gotoLoseScreen();
		}
	}
	public void Forceend() {
		AT.stop();
		RenderableHolder.getInstance().getEntities().clear();

	}
	private void Checkpaused() {
		// TODO Auto-generated method stub
		if(InputUtility.isKeyPress()==false && InputUtility.lastKey==KeyCode.SPACE)
		{
			Pause();
			InputUtility.lastKey=null;
		}
	}
	public void Pause() {
		if(pausedstate==false)
		{
			PauseIcon.instance.drawPaused();
			pausedstate=true; 
			System.out.print("pause");
			AT.stop();
			AT2.start();
		}
		else
		{
			PauseIcon.instance.drawUnpaused();
			pausedstate=false;
			System.out.print("unpause");
			AT2.stop();
			AT.start();
		}
	}
	public static boolean isPausedstate() {
		return pausedstate;
	}
	public GameScreen getGamescreen() {
		return gamescreen;
	}
	public AnimationTimer getAT() {
		return AT;
	}


}
