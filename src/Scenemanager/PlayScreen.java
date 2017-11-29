package Scenemanager;

import Input.InputUtility;
import SharedObject.RenderableHolder;
import drawing.GameScreen;
import drawing.Menubar;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import logic.Gamelogic;
import logic.Gamestate;
import model_attacker.Attacker;
import model_general.Board;
public class PlayScreen extends HBox{
	private static boolean pausedstate;
	GameScreen gamescreen;
	Menubar menubar;
	AnimationTimer AT,AT2;
	Gamelogic Gamelogic;
	PlayScreen()
	{
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
		gamescreen=new GameScreen();
		menubar=new Menubar(SceneManager.SCREEN_WIDTH*0.25,SceneManager.SCREEN_HEIGHT);
		Gamelogic=new Gamelogic();
		this.getChildren().add(gamescreen);
		this.getChildren().add(menubar);
	}
	private void Resetgame() {
		//Dont use gotoplayscreen,as it will make the game blink
		RenderableHolder.getInstance().reboot();
		Gamelogic=new Gamelogic();
		menubar.setdefault();
		InputUtility.currentChosed="x";
		AT2.stop();
		if(pausedstate)AT.start();
		pausedstate=false;
	}	
	protected void Paintupdated() {
		// TODO Auto-generated method stub
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
			System.out.println("win");
			Board.addNumboard();
			menubar.getGamestate().EndTimethread();
			if(Board.getDefaultNumboard()==Board.TOTALBOARD) {AT.stop();SceneManager.gotoWinScreen();}
			else
			{
			Resetgame();
			AT.stop();
			RenderableHolder.getInstance().getEntities().clear();
			SceneManager.gotoWaitScreen();
			}
		}
		else if(menubar.getGamestate().isLose())
		{
			gamescreen.PaintLoseScreen();
			AT.stop();
			AT2.start();
			pausedstate=true; 
		}
		
	}
	private void Checkpaused() {
		// TODO Auto-generated method stub
		if(InputUtility.isKeyPress()==false && InputUtility.Lastkey==KeyCode.SPACE)
		{
			if(pausedstate==false)
			{
				pausedstate=true; 
				System.out.print("pause");
				AT.stop();
				AT2.start();
			}
			else
			{
				pausedstate=false;
				System.out.print("unpause");
				AT2.stop();
				AT.start();
			}
			InputUtility.Lastkey=null;
		}
	}
	public static boolean isPausedstate() {
		return pausedstate;
	}
}
