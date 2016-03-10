package model;

public class DefaultBehaviour implements Behaviour
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
	public String getName()
	{
		return null;
	}
	
	public void fall()
	{
		while(model.shouldFall(lemming))
		{
			lemming.move(0, 1);
		}
	}

}
