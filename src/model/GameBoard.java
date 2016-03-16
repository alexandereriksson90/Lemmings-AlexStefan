package model;

import java.util.Observable;
import java.util.Observer;

import factories.BehaviourFactory;
import shapes.Ground;
import shapes.Hole;
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
	private String chosenBehaviour = null;

	ReleaseThread releaseThread;
	MoveThread moveThread;

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

		releaseThread = new ReleaseThread();
		moveThread = new MoveThread();

		releaseThread.start();
		moveThread.start();

	}

	private void setLemmingBehaviour(String behaviour, Lemming lemming)
	{
		Behaviour b = BehaviourFactory.getInstance(behaviour);
		b.setLemming(lemming);
		b.setModel(this);
		lemming.setBehaviour(b);
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
				if (l.getPosition().between(((Ground) s).getP1(),
						((Ground) s).getP3()))
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
					Point temp = new Point(l.getPosition().getXint()
							+ l.getWidth() + 1, l.getPosition().getYint());
					if (temp.between(((Wall) s).getP1(), ((Wall) s).getP4()))
						return true;
				}

				else if (l.getDirection() == -1)
				{
					Point temp = new Point(l.getPosition().getXint() - 1, l
							.getPosition().getYint());
					if (temp.between(((Wall) s).getP3(), ((Wall) s).getP2()))
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

		if (lemmingsOut < scenario.getLemmings())
		{
			lemmingList.add(new Lemming(scenario.getStartPosition()));
			lemmingsOut++;

		} else
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
			releaseThread.interrupt();
			moveThread.interrupt();

		} else
		{
			paused = false;
			start();

		}

	}

	private class ReleaseThread implements Runnable
	{
		Thread t;

		ReleaseThread()
		{
			super();
			t = new Thread(this);

		}

		public void start()
		{
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

		public void interrupt()
		{
			t.interrupt();
		}

	}

	private class MoveThread implements Runnable
	{
		Thread t;

		MoveThread()
		{
			super();
			t = new Thread(this);

		}

		public void start()
		{
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

		public void interrupt()
		{
			t.interrupt();
		}

	}

	public void checkIfLemming(int x, int y)
	{
		Point temp = new Point(x, y);
		for (Lemming l : lemmingList)
		{
			if (l.checkIfInBounds(temp))
			{

				if (chosenBehaviour != null)
				{
					setLemmingBehaviour(chosenBehaviour, l);
				}
			}
		}

	}

	public void setBehaviour(String behaviour)
	{
		chosenBehaviour = behaviour;

	}

	public void destroy(Point position)
	{

	}

}
