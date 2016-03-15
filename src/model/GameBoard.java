package model;

import java.util.Observable;
import java.util.Observer;

import factories.BehaviourFactory;
import shapes.Ground;
import shapes.Line;
import shapes.Point;
import shapes.TerrainList;
import shapes.TerrainUnit;
import shapes.Wall;

public class GameBoard extends Observable
{
	private boolean paused = false;
	private int delayTime = 3000;
	private LemmingList lemmingList = new LemmingList();
	private Scenario scenario;
	private int savedLemmings = 0;

	public GameBoard(Scenario scenario)
	{
		this.scenario = scenario;
		
	}


	public void addObserver(Observer observer)
	{
		lemmingList.addObserver(observer);
		scenario.getTerrain().addObserver(observer);
		super.addObserver(observer);
	}

	public LemmingList getLemmings()
	{
		return lemmingList;
	}

	public void onLemmingSaved()
	{
		savedLemmings++;
	}

	public void start()
	{
		releaseLemmings();
		while(true)
		{
			moveLemmings();
			
		}
		
	
	
	}

	public void setLemmingBehaviour(String behaviour, Lemming lemming)
	{
		lemming.setBehaviour(BehaviourFactory.getInstance(behaviour));
	}

	private void moveLemmings()
	{
		for (Lemming l : lemmingList)
		{
			l.setFalling(shouldFall(l));
			l.changeDirection(shouldTurn(l));
			l.move();	
		}
		notifyObservers();

	}

	public boolean shouldFall(Lemming l)
	{

		for (TerrainUnit s : scenario.getTerrain())
		{
			if (s instanceof Ground)
			{
				if (!l.getPosition().between(((Ground) s).getP1(), ((Ground) s).getP2()))
				{

					return true;
				}
			}
		}
		return false;
	}

	public boolean shouldTurn(Lemming l)
	{
		for (TerrainUnit s : scenario.getTerrain())
		{
			if (s instanceof Wall)
			{

				if (l.getDirection() == 1)
				{
					Point temp = new Point(l.getPosition().getXint() + l.getWidth() + 1, l.getPosition().getYint());
					if (temp.between(((Wall) s).getP1(), ((Wall) s).getP2()))
						return true;
				}

				else if (l.getDirection() == -1)
				{
					Point temp = new Point(l.getPosition().getXint() - 1, l.getPosition().getYint());
					if (temp.between(((Wall) s).getP1(), ((Wall) s).getP2()))
						return true;

				} else
				{
					return false;
				}

			}

		}
		return false;

	}

	private void releaseLemmings()
	{
		for (int i = 0; i < scenario.getLemmings(); i++)
		{
			lemmingList.add(new Lemming(scenario.getStartPosition()));
			simulateTime();

		}

	}

	public TerrainList getTerrain()
	{
		return scenario.getTerrain();
	}

	public int getSavedLemmings()
	{
		return savedLemmings;
	}

	private void simulateTime()
	{
		try
		{
			Thread.sleep(delayTime);
		} catch (Exception e)
		{
		}
	}

	public void pause()
	{
		if (!paused)
		{
			paused = true;
			// pausa
		} else
		{
			paused = false;
			// starta igen

		}

	}
}
