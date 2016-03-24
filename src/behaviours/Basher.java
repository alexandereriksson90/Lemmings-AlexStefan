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
					
					Point p = new Point(lemming.getPosition().getXint()+lemming.getWidth(), lemming.getPosition().getYint());

					if (p.between(((Wall) t).getP1(), ((Wall) t).getP4()))
					{
						w = (Wall) t;
						
					}
				}
			}
			if(w==null)
			{
				bashWalk();
			}
			if (bashWall == null && w != null)
			{

				model.getTerrain().remove(w);
				model.getTerrain().add(new Wall(new Point(w.getP1().getXint(), w.getP2().getYint()-30), w.getP3()));
				
				model.getTerrain().add(bashWall = new Wall(new Point(w.getP4().getXint() + bashDistance, w.getP2().getYint() - 30),
						w.getP2()));

				executeCounter++;
				bashWalk();


			} else if(w != null)
			{

				model.getTerrain().remove(bashWall);

				bashWall = new Wall(new Point(bashWall.getP4().getXint() + bashDistance, w.getP2().getYint() - 30),
						bashWall.getP2());

				model.getTerrain().add(bashWall);
				executeCounter++;
				bashWalk();


			}
			if (executeCounter == 30)
			{
				lemming.setBehaviour(null);
				model.getTerrain().remove(bashWall);

			}
		}

	}
	
	public void bashWalk()
	{
		lemming.walk();
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
