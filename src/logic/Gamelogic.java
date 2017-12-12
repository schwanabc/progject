package logic;
import java.util.concurrent.CopyOnWriteArrayList;

import SharedObject.RenderableHolder;
import model_attacker.Attacker;
import model_defender.Defender;
import model_general.*;
public class Gamelogic {
	private static CopyOnWriteArrayList<Entity> Entitycontainer;
	private static CopyOnWriteArrayList<Attacker> Attackercontainer;
	private static CopyOnWriteArrayList<Defender> Defendercontainer;
	public Gamelogic()
	{
		Entitycontainer=new CopyOnWriteArrayList<Entity>();
		Attackercontainer=new CopyOnWriteArrayList<Attacker>();
		Defendercontainer=new CopyOnWriteArrayList<Defender>();
		RenderableHolder.getInstance().add(new Board());
	}
	public static void addNewObject(Entity entity){
		Entitycontainer.add(entity);
		if(entity instanceof Attacker)Attackercontainer.add((Attacker) entity);
		if(entity instanceof Defender)Defendercontainer.add((Defender) entity);
		RenderableHolder.getInstance().add(entity);
	}
	public void update() {
		// TODO Auto-generated method stub
		Board.update();
		for(Entity entity: Entitycontainer)
		{
			entity.update();
		}
		for(int i=Entitycontainer.size()-1;i>=0;i--)
		{
			if(Entitycontainer.get(i).isDestroyed())
				Entitycontainer.remove(i);
		}	
		for(int i=Attackercontainer.size()-1;i>=0;i--)
		{
			if(Attackercontainer.get(i).isDestroyed())
				Attackercontainer.remove(i);
		}	
		for(int i=Defendercontainer.size()-1;i>=0;i--)
		{ 
			if(Defendercontainer.get(i).isDestroyed()) {
				Board.setBoard(Defendercontainer.get(i).getPosI(), Defendercontainer.get(i).getPosJ(), -1);
				Defendercontainer.remove(i);
				for(int j=Attackercontainer.size()-1;j>=0;j--)
				{
					if(Attackercontainer.get(j) instanceof model_attacker.Sapper)
						Attackercontainer.get(j).findBestPath();
					System.out.println("Find again");
				}
			}
		}	

	}
	public static CopyOnWriteArrayList<Entity> getEntitycontainer() {
		return Entitycontainer;
	}
	public static CopyOnWriteArrayList<Attacker> getAttackercontainer() {
		return Attackercontainer;
	}
	public static CopyOnWriteArrayList<Defender> getDefendercontainer() {
		return Defendercontainer;
	}
	public static boolean isDefenderContain(Defender x) {
		return Defendercontainer.contains(x);
	}

}
