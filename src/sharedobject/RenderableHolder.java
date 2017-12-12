package sharedobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import model_defender.Defender;
import model_defender.HQ;
import model_general.Board;
public class RenderableHolder {
	public static Image releaseButtonBackground;	
	public static Image pressedButtonBackground;
	public static Image releaseHome;	
	public static Image pressedHome;
	public static Image releaseExit;	
	public static Image pressedExit;
	public static Image releasePaused;	
	public static Image pressedPaused;
	public static Image background;
	public static Image loseBackground;
	public static Image victoryBackground;
	public static Image armed_Peasant;
	public static Image footman;
	public static Image wardog;
	public static Image general;
	public static Image sapper;
	public static Image berserker;
	public static Image saboteur;
	public static Image stoneTile;
	public static Image rubble;
	public static Image stripe;
	public static Image releaseButton;
	public static Image pressButton;
	public static Image HQ;
	public static Image normalTower;
	public static Image penetrateTower;
	public static Image menuBackground;
	public static Image errorFrame;
	public static Image grass;
	public static AudioClip clickedSound; 
	public static AudioClip buttonHover;
	public static AudioClip attack_Sword;
	public static AudioClip buildingCollapsed;
	public static AudioClip victory;
	public static AudioClip arrowSound;
	public static AudioClip lose;
	public static AudioClip openSong;
	public static AudioClip loseSong;
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static RenderableHolder instance = new RenderableHolder();
	static
	{
		loadResource();
	};
	public RenderableHolder()
	{
		entities=new ArrayList<IRenderable>();
		comparator= (IRenderable o1,IRenderable o2) ->
		{
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	public static void stopAudio() {
		clickedSound.stop(); 
		buttonHover.stop();
		attack_Sword.stop();
		buildingCollapsed.stop();
		victory.stop();
		lose.stop();
		openSong.stop();
		loseSong.stop();
	}
	public static void loadResource() {
		try {
		//-------------------------button and icon
		releaseButtonBackground=new Image(ClassLoader.getSystemResource("releasebutton.jpg").toString());	
		pressedButtonBackground=new Image(ClassLoader.getSystemResource("pressbutton.jpg").toString());
		releaseHome=new Image(ClassLoader.getSystemResource("releasehome.jpg").toString());	
		pressedHome=new Image(ClassLoader.getSystemResource("presshome.jpg").toString());
		releaseExit=new Image(ClassLoader.getSystemResource("releaseexit.jpg").toString());	
		pressedExit=new Image(ClassLoader.getSystemResource("pressexit.jpg").toString());
		releasePaused=new Image(ClassLoader.getSystemResource("releasepause.jpg").toString());	
		pressedPaused=new Image(ClassLoader.getSystemResource("presspause.jpg").toString());
		//-------------------------background
		victoryBackground=new Image(ClassLoader.getSystemResource("victorybackground.jpg").toString());
	    background=new Image(ClassLoader.getSystemResource("startscreen.jpg").toString());
	    loseBackground=new Image(ClassLoader.getSystemResource("losescreen.jpg").toString());
	    //--------------------------unit--------------------------------------------------
		armed_Peasant=new Image(ClassLoader.getSystemResource("Armed_Peasant.png").toString());	
	    footman=new Image(ClassLoader.getSystemResource("Footman.png").toString());
	    general=new Image(ClassLoader.getSystemResource("general.png").toString());
	    wardog=new Image(ClassLoader.getSystemResource("wardog.png").toString());
	    sapper=new Image(ClassLoader.getSystemResource("sapper.png").toString());
	    berserker=new Image(ClassLoader.getSystemResource("berserker.png").toString());
	    saboteur=new Image(ClassLoader.getSystemResource("saboteur.png").toString());
	    //--------------------------tile & etc--------------------------------------------------
		stoneTile=new Image(ClassLoader.getSystemResource("StoneTile.png").toString());
		stripe=new Image(ClassLoader.getSystemResource("stripe.jpg").toString());	
		releaseButton=new Image(ClassLoader.getSystemResource("menureleased.jpg").toString());	
		pressButton=new Image(ClassLoader.getSystemResource("menupressed.jpg").toString());	
		rubble=new Image(ClassLoader.getSystemResource("rubble.png").toString());
		HQ=new Image(ClassLoader.getSystemResource("HQ.png").toString());
		normalTower=new Image(ClassLoader.getSystemResource("tower.jpg").toString());
		penetrateTower=new Image(ClassLoader.getSystemResource("penetratetower.jpg").toString());
		errorFrame=new Image(ClassLoader.getSystemResource("errorframe.jpg").toString());
		menuBackground=new Image(ClassLoader.getSystemResource("menubackground.jpg").toString());
		grass=new Image(ClassLoader.getSystemResource("grass.png").toString());
		//---------------------------------sound
		clickedSound = new AudioClip(ClassLoader.getSystemResource("buttonpressed.wav").toString());
	    buttonHover = new AudioClip(ClassLoader.getSystemResource("buttonhover.wav").toString());
	    attack_Sword=new AudioClip(ClassLoader.getSystemResource("attack_sword.wav").toString());
	    buildingCollapsed=new AudioClip(ClassLoader.getSystemResource("buildingcollapsed.wav").toString());
	    victory=new AudioClip(ClassLoader.getSystemResource("victory.mp3").toString());
	    arrowSound=new AudioClip(ClassLoader.getSystemResource("arrow.wav").toString());
	    lose=new AudioClip(ClassLoader.getSystemResource("lose.wav").toString());
	    openSong=new AudioClip(ClassLoader.getSystemResource("opensong.mp3").toString());
	    loseSong=new AudioClip(ClassLoader.getSystemResource("losesong.mp3").toString());
		}
		catch(Exception e)
		{
			Alert ar=new Alert(AlertType.ERROR,"Can't load resource",ButtonType.CLOSE);
			ar.setTitle("Can't load resource");
			ar.showAndWait();
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void add(IRenderable entity)
	{
		entities.add(entity);
		Collections.sort(entities,comparator);
		//System.out.println(entity.getClass().getSimpleName()+" added");
	}
	public void update()
	{
		for(int i=entities.size()-1;i>=0;i--)
		{
			if(entities.get(i).isDestroyed()==true)
			{
				if(entities.get(i) instanceof Defender)
				{
					buildingCollapsed.play();
					Defender defender=(Defender) entities.get(i);
					Board.setBoard(defender.getPosI(), defender.getPosJ(), -1);
					if(entities.get(i) instanceof HQ)
					{
						Board.setIsWin(true);
					}
				}
			//	System.out.println(entities.get(i).getClass().getSimpleName()+" removed");
				entities.remove(i);
			}
		}
	}
	public static RenderableHolder getInstance() {
		return instance;
	}
	public ArrayList<IRenderable> getEntities() {
		return entities;
	}


	
}
