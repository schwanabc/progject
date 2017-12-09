package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import model_defender.Defender;
import model_defender.HQ;
import model_general.Board;
public class RenderableHolder {
	public static Image ReleaseButtonBackground;	
	public static Image PressedButtonBackground;
	public static Image Background;
	public static Image Armed_Peasant;
	public static Image Footman;
	public static Image StoneTile;
	public static Image Rubble;
	public static Image stripe;
	public static Image ReleaseButton;
	public static Image PressButton;
	public static Image HQ;
	public static AudioClip Clickedsound; 
	public static AudioClip Clickedmenu;
	public static AudioClip Buttonhover;
	public static AudioClip Attack_sword;
	public static AudioClip BuildingCollapsed;
	public static AudioClip Victory;
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
	}
	public static void Loadresource() {
		ReleaseButtonBackground=new Image(ClassLoader.getSystemResource("releasebutton.jpg").toString());	
		PressedButtonBackground=new Image(ClassLoader.getSystemResource("pressbutton.jpg").toString());
	    Background=new Image(ClassLoader.getSystemResource("Wiki-background.jpg").toString());
		Armed_Peasant=new Image(ClassLoader.getSystemResource("Armed_Peasant.png").toString());	
	    Footman=new Image(ClassLoader.getSystemResource("Footman.png").toString());	
		StoneTile=new Image(ClassLoader.getSystemResource("StoneTile.png").toString());
		stripe=new Image(ClassLoader.getSystemResource("stripe.jpg").toString());	
		ReleaseButton=new Image(ClassLoader.getSystemResource("menureleased.jpg").toString());	
		PressButton=new Image(ClassLoader.getSystemResource("menupressed.jpg").toString());	
		Rubble=new Image(ClassLoader.getSystemResource("rubble.png").toString());
		HQ=new Image(ClassLoader.getSystemResource("HQ.png").toString());
		Clickedsound = new AudioClip(ClassLoader.getSystemResource("buttonpressed.wav").toString());
	    Buttonhover = new AudioClip(ClassLoader.getSystemResource("buttonhover.wav").toString());
	    Attack_sword=new AudioClip(ClassLoader.getSystemResource("attack_sword.wav").toString());
	    Clickedmenu=new AudioClip(ClassLoader.getSystemResource("buttonmenu.wav").toString());
	    BuildingCollapsed=new AudioClip(ClassLoader.getSystemResource("buildingcollapsed.wav").toString());
	    Victory=new AudioClip(ClassLoader.getSystemResource("victory.wav").toString());
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
