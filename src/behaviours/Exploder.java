package behaviours;

import model.GameBoard;
import model.Lemming;

public class Exploder extends DefaultBehaviour
{

	public Exploder(Lemming lemming, GameBoard model)
	{
		super(lemming, model);
	}

	@Override
	public String getName()
	{
		return "Exploder";
	}

}
