package logic;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import SharedObject.RenderableHolder;
import model_attacker.Attacker;
import model_defender.Defender;
import model_defender.Bullet;
import model_general.*;
public class Gamelogic {
	private static CopyOnWriteArrayList<Entity> Entitycontainer;
	private static CopyOnWriteArrayList<Attacker> Attackercontainer;
	private static CopyOnWriteArrayList<Defender> Defendercontainer;
	private static CopyOnWriteArrayList<Bullet> Bulletcontainer;
	private Board board;
	public Gamelogic()
	{
		Entitycontainer=new CopyOnWriteArrayList<Entity>();
		Attackercontainer=new CopyOnWriteArrayList<Attacker>();
		Defendercontainer=new CopyOnWriteArrayList<Defender>();
		Bulletcontainer=new CopyOnWriteArrayList<Bullet>();
		board=new Board();
		RenderableHolder.getInstance().add(board);
	}
	public static void addNewObject(Entity entity){
		Entitycontainer.add(entity);
		if(entity instanceof Bullet)Bulletcontainer.add((Bullet) entity);
		if(entity instanceof Attacker)Attackercontainer.add((Attacker) entity);
		if(entity instanceof Defender)Defendercontainer.add((Defender) entity);
		RenderableHolder.getInstance().add(entity);
	}
	public void update() {
		// TODO Auto-generated method stub
		board.update();
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
			if(Defendercontainer.get(i).isDestroyed())
				Defendercontainer.remove(i);
		}	
		
		for(int i=Bulletcontainer.size()-1;i>=0;i--)
		{ 
			if(Bulletcontainer.get(i).isDestroyed())
				Bulletcontainer.remove(i);
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
	public static CopyOnWriteArrayList<Bullet> getBulletcontainer() {
		return Bulletcontainer;
	}
}
