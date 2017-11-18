package Scenemanager;

import Input.InputUtility;
import SharedObject.RenderableHolder;
import drawing.GameScreen;
import drawing.Menubar;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import logic.Gamelogic;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Gamestate;
public class PlayScreen extends HBox{
	private static boolean pausedstate;
	GameScreen gamescreen;
	Menubar menubar;
	AnimationTimer AT,AT2;
	Gamelogic Gamelogic;
	Gamestate Gamestate;
	PlayScreen()
	{
		gamescreen=new GameScreen();
		menubar=new Menubar(SceneManager.SCREEN_WIDTH*0.25,SceneManager.SCREEN_HEIGHT);
		this.getChildren().add(gamescreen);
		this.getChildren().add(menubar);
		Gamelogic=new Gamelogic();
		Gamestate=new Gamestate();
		AT=new AnimationTimer(){
			public void handle(long now)
			{
				Gamelogic.update();
				gamescreen.PaintComponent();
				InputUtility.updateInputState();
				RenderableHolder.getInstance().update();
				menubar.update();
				Checkpaused();
				Checkcondition();
				if(!Gamestate.isOngoing())
					{stop();Checkend();}
			}

			private void Checkcondition() {
				// TODO Auto-generated method stub
				if(Board.isIswin())Gamestate.setWin(true);
				if(Board.getMoney()<=Attacker.getMinCost() 
						&& logic.Gamelogic.getAttackercontainer().size()==0)
					Gamestate.setLose(true);
			}
		};
		AT2=new AnimationTimer(){
			public void handle(long now)
			{
				Board.update();
				gamescreen.PaintComponent();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
				menubar.update();
				Checkpaused();
			}
		};
	}
	void Checkend() {
		// TODO Auto-generated method stub
		if(Gamestate.isWin())
		{
			gamescreen.PaintWinScreen();
		}
		if(Gamestate.isLose())
		{
			gamescreen.PaintLoseScreen();
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
}
