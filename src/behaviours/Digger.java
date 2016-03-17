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
	private int digDistance = 1;
	private Ground digGround = null;
	private Ground g = null;
	private int x,y;

	
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
		
		for (TerrainUnit t : model.getTerrain())
		{
			if (t instanceof Ground && g == null)
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
					new Ground(g.getP1(), new Point(x, y + 30)));
			model.getTerrain().add(
					new Ground(new Point(x
							+ lemming.getWidth() + 5, y), g.getP2()));
			digGround = new Ground(new Point(x,
					y + 200), new Point(
					x + lemming.getWidth() + 15,
					y + 150));
			model.getTerrain().add(digGround);
			executeCounter++;
			
		} else
		{
			
			model.getTerrain().remove(digGround);
//			digGround.setP1(new Point(digGround.getP1().getXint(), digGround
//					.getP1().getYint() + digDistance));
			
			digGround = new Ground(new Point(x,
					lemming.getPosition().getYint() + digDistance), new Point(
					x + lemming.getWidth() + 5,
					y + 30));
			model.getTerrain().add(digGround);
			executeCounter++;
		}
		if (executeCounter == 30)
			lemming.setBehaviour(null);
			model.getTerrain().remove(digGround);

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
