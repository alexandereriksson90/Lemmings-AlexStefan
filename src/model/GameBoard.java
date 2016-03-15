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
	private int releaseDelay = 3000;
	private int moveDelay = 10;
	private LemmingList lemmingList = new LemmingList();
	private Scenario scenario;
	private int savedLemmings = 0;
	private int lemmingsOut = 0;
	private int nbr;
	
	ReleaseThread releaseThread = new ReleaseThread();
	MoveThread moveThread = new MoveThread();

	public GameBoard(Scenario scenario)
	{
		this.scenario = scenario;
		nbr = scenario.getLemmings();
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
		// if(paused)
		releaseThread.start();
		moveThread.start();

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
		setChanged();
		notifyObservers();

	}

	public boolean shouldFall(Lemming l)
	{

		for (TerrainUnit s : scenario.getTerrain())
		{
			if (s instanceof Ground)
			{

				if (l.getPosition().between(((Ground) s).getP1(), ((Ground) s).getP2()))
				{

					return false;
				}
			}
		}
		return true;
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
		System.out.println("lemmings out: "+lemmingsOut);
		System.out.println("scenario lemmings: "+nbr);
		if(lemmingsOut < nbr)
		{
			lemmingList.add(new Lemming(scenario.getStartPosition()));
			lemmingsOut++;
			
		}
		else
		{
			releaseThread.interrupt();
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

	public void pause()
	{
		if (!paused)
		{
			paused = true;

		} else
		{
			paused = false;

		}

	}
	
	private class ReleaseThread extends Thread implements Runnable
	{
		Thread t;

		ReleaseThread()
		{
			super();
			t = new Thread(this);
			t.start();
		}

		public void run()
		{
			
			while (!paused)
			{
				releaseLemmings();
				
				try
				{
					Thread.sleep(releaseDelay);
				} catch (InterruptedException e)
				{
					
				}
			}
		}

	}
	
	private class MoveThread extends Thread implements Runnable
	{
		Thread t;

		MoveThread()
		{
			super();
			t = new Thread(this);
			t.start();
		}

		public void run()
		{
			
			while (!paused)
			{
				moveLemmings();
				
				try
				{
					Thread.sleep(moveDelay);
				} catch (InterruptedException e)
				{
					
				}
			}
		}

	}

}
