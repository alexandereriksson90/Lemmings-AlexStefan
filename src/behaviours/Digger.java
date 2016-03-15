package behaviours;

import model.GameBoard;
import model.Lemming;

public class Digger extends DefaultBehaviour
{

	public Digger(Lemming lemming, GameBoard model)
	{
		super(lemming, model);
		dig();
	}

	@Override
	public String getName()
	{
		return "Digger";
	}
	
	public void dig()
	{
		
	}

}
