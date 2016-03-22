package behaviours;

import model.Behaviour;
import model.GameBoard;
import model.Lemming;
import shapes.Point;
import shapes.TerrainUnit;
import shapes.Wall;

public class Basher implements Behaviour
{
	private Lemming lemming;
	private GameBoard model;
	private Wall bashWall = null;
	private Wall w = null;
	private int executeCounter = 0;
	private int bashDistance = 1;
	private int x, y;
	

	@Override
	public Lemming getLemming()
	{
		
		return lemming;
	}

	@Override
	public GameBoard getModel()
	{		
		return model;
	}

	@Override
	public String getName()
	{		
		return "Basher";
	}

	@Override
	public void execute()
	{
		synchronized (model.getTerrain())
		{
			for (TerrainUnit t : model.getTerrain())
			{
				if (t instanceof Wall && w == null)
				{
					if (lemming.getPosition().between(((Wall) t).getP1(), ((Wall) t).getP3()))
					{
						w = (Wall) t;
					}
				}
			}
			if (bashWall == null)
			{

				
				model.getTerrain().remove(w);
				model.getTerrain().add(new Wall(w.getP1(), new Point(x, y + 30)));
				model.getTerrain().add(new Wall(new Point(x + lemming.getWidth() + 5, y), w.getP2()));
				model.getTerrain().add(new Wall(w.getP1(), new Point(x, y + 30)));

				model.getTerrain().add(bashWall = new Wall(new Point(x, y + bashDistance),
						new Point(x + lemming.getWidth() + 5, y + 30)));
				
				executeCounter++;
				lemming.fall();

			} else
			{

				model.getTerrain().remove(bashWall);

				bashWall = new Wall(new Point(x, lemming.getPosition().getYint() + bashDistance),
						new Point(x + lemming.getWidth() + 5, y + 30));
				
				model.getTerrain().add(bashWall);
				executeCounter++;
				lemming.fall();

			}
			if (executeCounter == 30)
			{
				lemming.setBehaviour(null);
				model.getTerrain().remove(bashWall);
				
				
			}
		}
		
	}

	@Override
	public void setModel(GameBoard model)
	{
		this.model = model;
		
	}

	@Override
	public void setLemming(Lemming lemming)
	{
		this.lemming = lemming;
		x = lemming.getPosition().getXint();
		y = lemming.getPosition().getYint();
		
	}

}
