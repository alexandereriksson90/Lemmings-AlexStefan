package model;

import java.util.Observable;
import java.util.Observer;

import shapes.Point;
import shapes.TerrainList;

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
		setUpGameBoard();
	}

	private void setUpGameBoard()
	{
		releaseLemmings();

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
		moveLemmings();
	}
	
	public void setLemmingSkill(Skill skill, Lemming lemming)
	{
		lemming.setSkill(skill);
	}

	private synchronized void moveLemmings()
	{
		for (Lemming l : lemmingList)
		{
			if (l.getDirection() != 0 && isCollisionFree(l) && !shouldFall(l))
			{
				l.move(1, 0);
			} else
			{

			}
		}
		notifyObservers();

	}

	private boolean shouldFall(Lemming l)
	{
		return false;
	}

	public boolean isCollisionFree(Lemming l)
	{
		if (l.getDirection() == 1)
		{
			if (getObstaclePosition(l).getX() - l.getPosition().getX() <= l.getWidth())
			{
				return false;
			} else
			{
				return true;
			}
		} else
		{
			if (l.getPosition().getX() - getObstaclePosition(l).getX() <= l.getWidth())
			{
				return false;
			} else
			{
				return true;
			}
		}

	}

	private Point getObstaclePosition(Lemming l)
	{

		return null;
	}

	private synchronized void releaseLemmings()
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
			//starta igen
			
		}

	}
}
