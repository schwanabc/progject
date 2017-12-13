package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.NormalButton;
import button.PlayButton;
import button.characterButton;
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
	private characterButton Character;
	private Label Gametitle;
	public StartScreen()
	{
		RenderableHolder.stopAudio();
		if(!RenderableHolder.openSong.isPlaying())RenderableHolder.openSong.play();
		System.out.println("startscreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,false,false,false,true))));
		Play=new PlayButton("Play");
		Play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.4);	
		Character=new characterButton("Character");
		Character.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.6);
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.8);
		Gametitle=new Label(SceneManager.GAMENAME);
		Gametitle.setFont(RenderableHolder.SCREENTEXTFONT);
		Gametitle.setTextFill(Color.CORAL);
		Gametitle.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(SceneManager.GAMENAME, RenderableHolder.SCREENTEXTFONT)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(Play,Character,Exit,Gametitle);
	}

}
