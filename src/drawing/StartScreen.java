package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.NormalButton;
import button.PlayButton;
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
public class StartScreen extends Pane{
	private PlayButton Play;
	private ExitButton Exit;
	private Label Gametitle;
	private static InputStream fontStream = ClassLoader.getSystemResourceAsStream("Penumbra-HalfSerif-Std_35114.ttf");
	private static final Font TEXT_FONT = Font.loadFont(fontStream, 80);
	public StartScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.openSong.setCycleCount(AudioClip.INDEFINITE);
		RenderableHolder.openSong.play();
		System.out.println("startscreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,false,false,false,true))));
		Play=new PlayButton("Play");
		Play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.4);	
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.6);
		Gametitle=new Label(SceneManager.GAMENAME);
		Gametitle.setFont(TEXT_FONT);
		Gametitle.setTextFill(Color.CORAL);
		Gametitle.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(SceneManager.GAMENAME, TEXT_FONT)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(Play,Exit,Gametitle);
	}

}
