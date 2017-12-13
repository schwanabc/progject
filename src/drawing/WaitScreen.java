package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.MenuButton;
import button.NormalButton;
import button.PlayButton;
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
public class WaitScreen extends Pane{
	private PlayButton Play;
	private ExitButton Exit;
	private ReplayButton Replay;
	private Label Title;
	private static final String WAIT_TEXT="YOU WIN";
	public WaitScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.victory.play();
		System.out.println("Waitscreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.victoryBackground, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		Play=new PlayButton("Next Stage");
		Play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.4);	
		Replay=new ReplayButton("Play Again");
		Replay.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.6);
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.8);
		Title=new Label(WAIT_TEXT);
		Title.setFont(RenderableHolder.SCREENTEXTFONT);
		Title.setTextFill(Color.RED);
		Title.relocate(1.5*Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(WAIT_TEXT, RenderableHolder.SCREENTEXTFONT)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(Play,Replay,Exit,Title);
	}

}
