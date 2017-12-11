package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import Button.ExitButton;
import Button.NormalButton;
import Button.PlayButton;
import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.Utility;
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
		this.setPrefSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT,true,true,true,true))));
		Play=new PlayButton("Play");
		Play.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)/2, SceneManager.SCREEN_HEIGHT*0.4);	
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)/2, SceneManager.SCREEN_HEIGHT*0.6);
		Gametitle=new Label(SceneManager.GAMENAME);
		Gametitle.setFont(TEXT_FONT);
		Gametitle.setTextFill(Color.CORAL);
		Gametitle.relocate(Utility.getTextStartWidht(SceneManager.SCREEN_WIDTH, Utility.getFont_width(SceneManager.GAMENAME, TEXT_FONT)), SceneManager.SCREEN_HEIGHT*0.2);
		this.getChildren().addAll(Play,Exit,Gametitle);
	}

}
