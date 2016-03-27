package behaviours;

import shapes.Ground;
import shapes.Point;
import shapes.TerrainUnit;
import model.Behaviour;
import model.GameBoard;
import model.Lemming;

public class Digger implements Behaviour
{
	private Lemming lemming;
	private GameBoard model;
	private int executeCounter = 0;
	private int digDistance = 1;
	private Ground digGround = null;
	private Ground g = null;
	private int x, y;

	@Override
	public String getName()
	{
		return "Digger";
	}

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
	public void execute()
	{
		synchronized (model.getTerrain())
		{
			for (TerrainUnit t : model.getTerrain())
			{
				if (t instanceof Ground && g == null)
				{
					if (lemming.getPosition().between(((Ground) t).getP1(), ((Ground) t).getP3()))
					{
						g = (Ground) t;
					}
				}
			}
			if (digGround == null)
			{

				model.getTerrain().remove(g);
				model.getTerrain().add(new Ground(g.getP1(), new Point(x, y + 30)));
				model.getTerrain().add(new Ground(new Point(x + lemming.getWidth(), y), g.getP2()));
				model.getTerrain().add(new Ground(g.getP1(), new Point(x, y + 30)));

				model.getTerrain().add(digGround = new Ground(new Point(x, y + digDistance),
						new Point(x + lemming.getWidth(), y + 30)));

				executeCounter++;
				lemming.fall();

			} else
			{

				model.getTerrain().remove(digGround);

				digGround = new Ground(new Point(x, lemming.getPosition().getYint() + digDistance),
						new Point(x + lemming.getWidth(), y + 30));

				model.getTerrain().add(digGround);
				executeCounter++;
				lemming.fall();

			}
			if (executeCounter == 30)
			{
				lemming.setBehaviour(null);
				model.getTerrain().remove(digGround);

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
