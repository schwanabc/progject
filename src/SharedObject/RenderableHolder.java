package SharedObject;

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
	public static Image ReleaseButtonBackground;	
	public static Image PressedButtonBackground;
	public static Image ReleaseHome;	
	public static Image PressedHome;
	public static Image ReleaseExit;	
	public static Image PressedExit;
	public static Image ReleasePaused;	
	public static Image PressedPaused;
	public static Image Background;
	public static Image LoseBackground;
	public static Image Armed_Peasant;
	public static Image Footman;
	public static Image Wardog;
	public static Image General;
	public static Image Sapper;
	public static Image Berserker;
	public static Image Saboteur;
	public static Image StoneTile;
	public static Image Rubble;
	public static Image stripe;
	public static Image ReleaseButton;
	public static Image PressButton;
	public static Image HQ;
	public static Image NormalTower;
	public static Image PenetrateTower;
	public static Image Menubackground;
	public static Image Victorybackground;
	public static Image ErrorFrame;
	public static Image Grass;
	public static Image Arrow;
	public static AudioClip Clickedsound; 
	public static AudioClip Clickedmenu;
	public static AudioClip Buttonhover;
	public static AudioClip Attack_sword;
	public static AudioClip BuildingCollapsed;
	public static AudioClip Victory;
	public static AudioClip ArrowSound;
	public static AudioClip Lose;
	public static AudioClip Opensong;
	public static AudioClip Playsong;
	public static AudioClip Losesong;
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static RenderableHolder instance = new RenderableHolder();
	static
	{
		Loadresource();
	};
	RenderableHolder()
	{
		entities=new ArrayList<IRenderable>();
		comparator= (IRenderable o1,IRenderable o2) ->
		{
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	public static void StopAudio() {
		Clickedsound.stop(); 
		Clickedmenu.stop();
		Buttonhover.stop();
		Attack_sword.stop();
		BuildingCollapsed.stop();
		Victory.stop();
		Lose.stop();
		Opensong.stop();
		Losesong.stop();
	}
	public static void Loadresource() {
		try {
		//-------------------------button and icon
		ReleaseButtonBackground=new Image(ClassLoader.getSystemResource("releasebutton.jpg").toString());	
		PressedButtonBackground=new Image(ClassLoader.getSystemResource("pressbutton.jpg").toString());
		ReleaseHome=new Image(ClassLoader.getSystemResource("releasehome.jpg").toString());	
		PressedHome=new Image(ClassLoader.getSystemResource("presshome.jpg").toString());
		ReleaseExit=new Image(ClassLoader.getSystemResource("releaseexit.jpg").toString());	
		PressedExit=new Image(ClassLoader.getSystemResource("pressexit.jpg").toString());
		ReleasePaused=new Image(ClassLoader.getSystemResource("releasepause.jpg").toString());	
		PressedPaused=new Image(ClassLoader.getSystemResource("presspause.jpg").toString());
		//-------------------------background
		Victorybackground=new Image(ClassLoader.getSystemResource("victorybackground.jpg").toString());
	    Background=new Image(ClassLoader.getSystemResource("startscreen.jpg").toString());
	    LoseBackground=new Image(ClassLoader.getSystemResource("losescreen.jpg").toString());
	    //--------------------------unit--------------------------------------------------
		Armed_Peasant=new Image(ClassLoader.getSystemResource("Armed_Peasant.png").toString());	
	    Footman=new Image(ClassLoader.getSystemResource("Footman.png").toString());
	    General=new Image(ClassLoader.getSystemResource("general.png").toString());
	    Wardog=new Image(ClassLoader.getSystemResource("wardog.png").toString());
	    Sapper=new Image(ClassLoader.getSystemResource("sapper.png").toString());
	    Berserker=new Image(ClassLoader.getSystemResource("berserker.png").toString());
	    Saboteur=new Image(ClassLoader.getSystemResource("saboteur.png").toString());
	    //--------------------------tile & etc--------------------------------------------------
		StoneTile=new Image(ClassLoader.getSystemResource("StoneTile.png").toString());
		stripe=new Image(ClassLoader.getSystemResource("stripe.jpg").toString());	
		ReleaseButton=new Image(ClassLoader.getSystemResource("menureleased.jpg").toString());	
		PressButton=new Image(ClassLoader.getSystemResource("menupressed.jpg").toString());	
		Rubble=new Image(ClassLoader.getSystemResource("rubble.png").toString());
		HQ=new Image(ClassLoader.getSystemResource("HQ.png").toString());
		NormalTower=new Image(ClassLoader.getSystemResource("tower.jpg").toString());
		PenetrateTower=new Image(ClassLoader.getSystemResource("penetratetower.jpg").toString());
		ErrorFrame=new Image(ClassLoader.getSystemResource("errorframe.jpg").toString());
		Menubackground=new Image(ClassLoader.getSystemResource("menubackground.jpg").toString());
		Grass=new Image(ClassLoader.getSystemResource("grass.png").toString());
		Arrow=new Image(ClassLoader.getSystemResource("arrow.png").toString());
		//---------------------------------sound
		Clickedsound = new AudioClip(ClassLoader.getSystemResource("buttonpressed.wav").toString());
	    Buttonhover = new AudioClip(ClassLoader.getSystemResource("buttonhover.wav").toString());
	    Attack_sword=new AudioClip(ClassLoader.getSystemResource("attack_sword.wav").toString());
	    Clickedmenu=new AudioClip(ClassLoader.getSystemResource("buttonmenu.wav").toString());
	    BuildingCollapsed=new AudioClip(ClassLoader.getSystemResource("buildingcollapsed.wav").toString());
	    Victory=new AudioClip(ClassLoader.getSystemResource("victory.mp3").toString());
	    ArrowSound=new AudioClip(ClassLoader.getSystemResource("arrow.wav").toString());
	    Lose=new AudioClip(ClassLoader.getSystemResource("lose.wav").toString());
	    Opensong=new AudioClip(ClassLoader.getSystemResource("opensong.mp3").toString());
	    Playsong=new AudioClip(ClassLoader.getSystemResource("playsong.mp3").toString());
	    Losesong=new AudioClip(ClassLoader.getSystemResource("losesong.mp3").toString());
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
					BuildingCollapsed.play();
					Defender defender=(Defender) entities.get(i);
					Board.setBoard(defender.getPosI(), defender.getPosJ(), -1);
					if(entities.get(i) instanceof HQ)
					{
						Board.setIswin(true);
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
	public void reboot() {
		instance = new RenderableHolder();
	}

	
}
