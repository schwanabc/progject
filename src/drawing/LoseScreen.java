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
	private ExitButton Exit;
	private RetryButton Retry;
	private MenuButton Menu;
	private Label Title;
	private static final String WIN_TEXT ="YOU LOSE";
	public LoseScreen()
	{
		RenderableHolder.stopAudio();
		if(!RenderableHolder.lose.isPlaying())RenderableHolder.lose.play();
		Thread t2 =new Thread(()->
		 {
			 while(true)
			 {
				 if(!RenderableHolder.lose.isPlaying())
				 {
					 if(!RenderableHolder.loseSong.isPlaying())RenderableHolder.loseSong.play();
					 break;
				 }
			 }
		});
		t2.start();
		System.out.println("Losescreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.loseBackground, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		Retry=new RetryButton("Retry");
		Retry.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.4);
		Menu=new MenuButton("Main Menu");
		Menu.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.6);
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.7/2, SceneManager.screenHeight*0.8);
		Title=new Label(WIN_TEXT);
		Title.setFont(RenderableHolder.SCREENTEXTFONT);
		Title.setTextFill(Color.RED);
		Title.relocate(1.7*Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(WIN_TEXT, RenderableHolder.SCREENTEXTFONT)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(Menu,Retry,Exit,Title);
	}

}
