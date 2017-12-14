package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.MenuButton;
import button.NormalButton;
import button.ReplayButton;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.Utility;
public class WinScreen extends Pane{
	private ExitButton exit;
	private ReplayButton replay;
	private MenuButton menu;
	private Label title;
	private static final String WIN_TEXT ="YOU CLEAR THE GAME!!!";
	public WinScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.victory.play();
		System.out.println("Winscreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.victoryBackground, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		replay=new ReplayButton("Play Again");
		replay.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.4);
		menu=new MenuButton("Main Menu");
		menu.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.6);
		exit=new ExitButton("Exit");
		exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.8);
		title=new Label(WIN_TEXT);
		title.setFont(RenderableHolder.SCREENTEXTFONT);
		title.setTextFill(Color.RED);
		title.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(WIN_TEXT, RenderableHolder.SCREENTEXTFONT)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(menu,replay,exit,title);
	}

}
