package logic;
import java.util.ArrayList;

import SharedObject.RenderableHolder;
import model_attacker.Attacker;
import model_defender.Defender;
import model_general.*;
public class Gamelogic {
	private static ArrayList<Entity> Entitycontainer;
	private static ArrayList<Attacker> Attackercontainer;
	private static ArrayList<Defender> Defendercontainer;
	private Board board;
	public Gamelogic()
	{
		Entitycontainer=new ArrayList<Entity>();
		Attackercontainer=new ArrayList<Attacker>();
		Defendercontainer=new ArrayList<Defender>();
		board=new Board();
		
		RenderableHolder.getInstance().add(board);
	}
	public static void addNewObject(Entity entity){
		Entitycontainer.add(entity);
		if(entity instanceof Attacker)Attackercontainer.add((Attacker) entity);
		if(entity instanceof Defender)Defendercontainer.add((Defender) entity);
		RenderableHolder.getInstance().add(entity);
	}
	public void update() {
		// TODO Auto-generated method stub
		board.update();
		for(Entity entity: Entitycontainer)
		{
		//	System.out.println("update");
			entity.update();
		}
		for(int i=Attackercontainer.size()-1;i>=0;i--)
		{
			if(Attackercontainer.get(i).isDestroyed())
				Attackercontainer.remove(i);
		}	
		for(int i=Entitycontainer.size()-1;i>=0;i--)
		{
			if(Entitycontainer.get(i).isDestroyed())
				Entitycontainer.remove(i);
		}	
		for(int i=Defendercontainer.size()-1;i>=0;i--)
		{
			if(Defendercontainer.get(i).isDestroyed())
				Defendercontainer.remove(i);
		}	
		
		
	}
	public static ArrayList<Entity> getEntitycontainer() {
		return Entitycontainer;
	}
	public static ArrayList<Attacker> getAttackercontainer() {
		return Attackercontainer;
	}
	public static ArrayList<Defender> getDefendercontainer() {
		return Defendercontainer;
	}
}
