package model;

import shapes.Point;
import shapes.Shape;
import shapes.TerrainList;

public class GameBoard
{
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
		addLemmings();

	}

	private void addLemmings()
	{
		for (int i = 0; i < scenario.getLemmings(); i++)
		{
			lemmingList.add(new Lemming(scenario.getStartPosition()));
		}

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
}
