package behaviours;

import shapes.Ground;
import shapes.Hole;
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
	private int digDistance = 5;
	private Ground digGround = null;

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
		Ground g = null;
		for (TerrainUnit t : model.getTerrain())
		{
			if (t instanceof Ground)
			{
				if (lemming.getPosition().between(((Ground) t).getP1(),
						((Ground) t).getP3()))
				{
					g = (Ground) t;
				}
			}
		}
		if (digGround == null)
		{
			model.getTerrain().remove(g);
			model.getTerrain().add(
					new Ground(g.getP1(), new Point(lemming.getPosition()
							.getXint(), lemming.getPosition().getYint()
							+ digDistance)));
			model.getTerrain().add(
					new Ground(new Point(lemming.getPosition().getXint()
							+ lemming.getWidth() + 5, lemming.getPosition()
							.getYint()), g.getP2()));
			digGround = new Ground(new Point(lemming.getPosition().getXint(),
					lemming.getPosition().getY() + digDistance), new Point(
					lemming.getPosition().getXint() + lemming.getWidth() + 5,
					lemming.getPosition().getYint() + 30));
			model.getTerrain().add(digGround);
			executeCounter++;
		}
		else {
//			model.getTerrain().remove(digGround);
			
			digGround.setP1(new Point(digGround.getP1().getXint(),digGround.getP1().getYint()+digDistance));
			executeCounter++;
		}
		if(executeCounter == 6)
		lemming.setBehaviour(null);

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

	}

}
