package logic;
import java.util.concurrent.CopyOnWriteArrayList;

import model_attacker.Attacker;
import model_defender.Defender;
import model_general.*;
import sharedobject.RenderableHolder;
public class Gamelogic {
	private static CopyOnWriteArrayList<Entity> entityContainer;
	private static CopyOnWriteArrayList<Attacker> attackerContainer;
	private static CopyOnWriteArrayList<Defender> defenderContainer;
	public Gamelogic()
	{
		entityContainer=new CopyOnWriteArrayList<Entity>();
		attackerContainer=new CopyOnWriteArrayList<Attacker>();
		defenderContainer=new CopyOnWriteArrayList<Defender>();
		RenderableHolder.getInstance().add(new Board());
	}
	public static void addNewObject(Entity entity){
		entityContainer.add(entity);
		if(entity instanceof Attacker)attackerContainer.add((Attacker) entity);
		if(entity instanceof Defender)defenderContainer.add((Defender) entity);
		RenderableHolder.getInstance().add(entity);
	}
	public void update() {
		// TODO Auto-generated method stub
		Board.update();
		for(Entity entity: entityContainer)
		{
			entity.update();
		}
		for(int i=entityContainer.size()-1;i>=0;i--)
		{
			if(entityContainer.get(i).isDestroyed())
				entityContainer.remove(i);
		}	
		for(int i=attackerContainer.size()-1;i>=0;i--)
		{
			if(attackerContainer.get(i).isDestroyed())
				attackerContainer.remove(i);
		}	
		for(int i=defenderContainer.size()-1;i>=0;i--)
		{ 
			if(defenderContainer.get(i).isDestroyed()) {
				Board.setBoard(defenderContainer.get(i).getPosI(), defenderContainer.get(i).getPosJ(), -1);
				defenderContainer.remove(i);
				for(int j=attackerContainer.size()-1;j>=0;j--)
				{
					if(attackerContainer.get(j) instanceof model_attacker.Sapper)
						attackerContainer.get(j).findBestPath();
					System.out.println("Find again");
				}
			}
		}	

	}
	public static CopyOnWriteArrayList<Entity> getEntityContainer() {
		return entityContainer;
	}
	public static CopyOnWriteArrayList<Attacker> getAttackerContainer() {
		return attackerContainer;
	}
	public static CopyOnWriteArrayList<Defender> getDefenderContainer() {
		return defenderContainer;
	}
	public static boolean isDefenderContain(Defender x) {
		return defenderContainer.contains(x);
	}

}
