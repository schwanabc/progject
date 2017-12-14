package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.NormalButton;
import button.PlayButton;
import button.CharacterButton;
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
	private PlayButton play;
	private ExitButton exit;
	private CharacterButton character;
	private Label gametitle;
	public StartScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.startMusic(RenderableHolder.openSong);
//		System.out.println("startscreen");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,false,false,false,true))));
		play=new PlayButton("Play");
		play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.4);	
		character=new CharacterButton("Character");
		character.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.6);
		exit=new ExitButton("Exit");
		exit.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)/2, SceneManager.screenHeight*0.8);
		gametitle=new Label(SceneManager.GAMENAME);
		gametitle.setFont(RenderableHolder.screenTextFont);
		gametitle.setTextFill(Color.CORAL);
		gametitle.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(SceneManager.GAMENAME, RenderableHolder.screenTextFont)), SceneManager.screenHeight*0.2);
		this.getChildren().addAll(play,character,exit,gametitle);
	}
}
