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
	private boolean gameWon = false;
	private int releaseDelay = 3000;
	private int moveDelay = 5;
	private LemmingList lemmingList = new LemmingList();
	private Scenario scenario;
	private int savedLemmings = 0;
	private int lemmingsOut = 0;
	private String chosenBehaviour = null;
	private int killedLemmings = 0;

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

		for (int i = 0; i < lemmingList.size(); i++)
		{
			shouldKillLemming(scenario.isLemmingOutOfMap(lemmingList.get(i)), lemmingList.get(i));
			lemmingList.get(i).setFalling(shouldFall(lemmingList.get(i)));
			lemmingList.get(i).changeDirection(shouldTurn(lemmingList.get(i)));
			lemmingList.get(i).move();
			isAtExit(lemmingList.get(i));
		}

		if (lemmingList.size() == 0
				&& savedLemmings >= scenario.getLemmingsRequired())
		{
			gameWon();

			return;
		} else if (savedLemmings < scenario.getLemmingsRequired()
				&& killedLemmings > scenario.getLemmings() - scenario.getLemmingsRequired())
		{
			System.out.println("förlust");
		}

		setChanged();
		notifyObservers();

	}

	private void shouldKillLemming(boolean lemmingOutOfMap, Lemming lemming)
	{
		if(lemmingOutOfMap == true)
		{
			System.out.println("död");
			lemmingList.remove(lemming);
			killedLemmings++;
		}
		
	}

	public boolean hasWon()
	{
		return gameWon;
	}

	public void gameWon()
	{
		releaseThread.interrupt();
		moveThread.interrupt();
		gameWon = true;

	}

	private void isAtExit(Lemming l)
	{
		if (l.getPosition().getXint() == scenario.getHomePosition().getXint()
				&& l.getPosition().getYint() == scenario.getHomePosition()
						.getYint())
		{
			lemmingList.remove(l);
			savedLemmings++;
			System.out.println("lemming go home");
		}

	}

	public boolean shouldFall(Lemming l)
	{

		for (TerrainUnit s : scenario.getTerrain())
		{

			if (s instanceof Ground && l.getDirection() == 1)
			{
				if (l.getPosition().between(((Ground) s).getP1(),
						((Ground) s).getP3()))
				{
					return false;
				}
			} else if (s instanceof Ground && l.getDirection() == -1)
			{
				if (new Point(l.getPosition().getXint() + 30, l.getPosition()
						.getYint()).between(((Ground) s).getP1(),
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

			while (!paused && !gameWon)
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

			while (!paused && !gameWon)
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

	public boolean hasLost()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
