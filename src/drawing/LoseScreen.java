package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.MenuButton;
import button.NormalButton;
import button.ReplayButton;
import button.RetryButton;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.Utility;
public class LoseScreen extends Pane{
	private ExitButton exit;
	private RetryButton retry;
	private MenuButton menu;
	private Label title;
	private final String LOSE_TEXT ="YOU LOSE";
	public LoseScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.startMusic(RenderableHolder.loseSong);
//		System.out.println("Losescreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.loseBackground, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		retry=new RetryButton("Retry");
		retry.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.4);
		menu=new MenuButton("Main Menu");
		menu.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.6);
		exit=new ExitButton("Exit");
		exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.8);
		title=new Label(LOSE_TEXT);
		title.setFont(RenderableHolder.screenTextFont);
		title.setTextFill(Color.RED);
		title.relocate(1.7*Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(LOSE_TEXT, RenderableHolder.screenTextFont)), SceneManager.screenHeight*0.2);
		title=new Label(LOSE_TEXT);
		title.setFont(RenderableHolder.screenTextFont);
		title.setTextFill(Color.RED);
		title.relocate(1.7*Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(LOSE_TEXT, RenderableHolder.screenTextFont)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(menu,retry,exit,title);
	}

}
