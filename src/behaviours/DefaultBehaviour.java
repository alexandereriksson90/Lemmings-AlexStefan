package behaviours;

import model.Behaviour;
import model.GameBoard;
import model.Lemming;

public abstract class DefaultBehaviour implements Behaviour
{
	private Lemming lemming;
	private GameBoard model;
	
	public DefaultBehaviour(Lemming lemming, GameBoard model)
	{
		this.lemming = lemming;
		this.model = model;
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
	abstract public String getName();
	
	
	

}
