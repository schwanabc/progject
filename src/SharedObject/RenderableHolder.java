package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model_defender.Defender;
import model_defender.HQ;
import model_general.Board;
public class RenderableHolder {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static RenderableHolder instance = new RenderableHolder();
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
	public void add(IRenderable entity)
	{
		entities.add(entity);
		Collections.sort(entities,comparator);
		System.out.println(entity.getClass().getSimpleName()+" added");
	}
	public void update()
	{
		for(int i=entities.size()-1;i>=0;i--)
		{
			if(entities.get(i).isDestroyed()==true)
			{
				if(entities.get(i) instanceof Defender)
				{
					Defender defender=(Defender) entities.get(i);
					Board.setBoard(defender.getPosI(), defender.getPosJ(), -1);
					if(entities.get(i) instanceof HQ)
					{
						Board.setIswin(true);
					}
				}
				System.out.println(entities.get(i).getClass().getSimpleName()+" removed");
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
