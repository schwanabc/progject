package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.MenuButton;
import button.NormalButton;
import button.PlayButton;
import button.ReplayButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
public class CharacterDescribeScreen extends Pane{
	private PlayButton play;
	private MenuButton menu;
	private Label title;
	private Label characterName[] = new Label[7];
	private Label characterDescribeText[] = new Label[7];
	private final String HEADERTEXT = "Character Description";
	private Canvas characterImageSet;
	public CharacterDescribeScreen()
	{
		RenderableHolder.stopAudio();
	//	RenderableHolder.openSong.play();
//		System.out.println("Character Describe");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		play=new PlayButton("Play");
		play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*0.5/2, SceneManager.screenHeight*0.8);	
		menu=new MenuButton("Main Menu");
		menu.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.8);
		title=new Label(HEADERTEXT);
		
		String charName[] = {"Peasant","Footman","Wardog","Berserker","Sapper","Saboteur","General"};
		for(int i=0;i<7;i++) {
			characterName[i] = new Label(charName[i]);
			characterName[i].setFont(new Font(25));
			characterName[i].setTextFill(Color.RED);
			if(i<4)
				characterName[i].relocate(100, SceneManager.screenHeight*0.13*(i+1.5));
			else
				characterName[i].relocate(SceneManager.screenWidth/2+100, SceneManager.screenHeight*0.13*(i-2.5));
			this.getChildren().add(characterName[i]);
		}
		String charDes[] = {"The Cheapest - Low Price Low Return","The Tanker - High Health Low Damage",
				"The Fastest - High Speed Unit But Deal Half Damage to HQ",
				"The Destroyer - High Damage Low Health","Wall Bomber - Suicide Attack to Wall",
				"The HQ Hater - Deal Extra Massive Damage to HQ","The Strongest - High Price High Return"};
		for(int i=0;i<7;i++) {
			characterDescribeText[i] = new Label(charDes[i]);
			characterDescribeText[i].setFont(new Font(15));
			characterDescribeText[i].setTextFill(Color.RED);
			if(i<4)
				characterDescribeText[i].relocate(140, SceneManager.screenHeight*0.13*(i+2));
			else
				characterDescribeText[i].relocate(SceneManager.screenWidth/2+140, SceneManager.screenHeight*0.13*(i-2));
			this.getChildren().add(characterDescribeText[i]);
		}
		
		title.setFont(RenderableHolder.screenTextFont);
		title.setTextFill(Color.RED);
		title.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(HEADERTEXT, RenderableHolder.screenTextFont)), SceneManager.screenHeight*0.05);
		characterImageSet=new Canvas(SceneManager.screenWidth, SceneManager.screenHeight);
		GraphicsContext gc=characterImageSet.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.armed_Peasant,SceneManager.screenWidth*0.16, SceneManager.screenHeight*0.20,40, 40);
		gc.drawImage(RenderableHolder.footman,SceneManager.screenWidth*0.16, SceneManager.screenHeight*0.32,40, 40);
		gc.drawImage(RenderableHolder.wardog,SceneManager.screenWidth*0.16, SceneManager.screenHeight*0.46,40, 40);
		gc.drawImage(RenderableHolder.berserker,SceneManager.screenWidth*0.16, SceneManager.screenHeight*0.58,40, 40);
		gc.drawImage(RenderableHolder.sapper,SceneManager.screenWidth*0.66, SceneManager.screenHeight*0.20,40, 40);
		gc.drawImage(RenderableHolder.saboteur,SceneManager.screenWidth*0.66, SceneManager.screenHeight*0.32,40, 40);
		gc.drawImage(RenderableHolder.general,SceneManager.screenWidth*0.66, SceneManager.screenHeight*0.46,40, 40);
		characterImageSet.relocate(0, 0);
		this.getChildren().addAll(characterImageSet,menu,play,title);
		
	}

}
