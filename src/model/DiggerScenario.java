package model;

import shapes.Entrance;
import shapes.Exit;
import shapes.Ground;
import shapes.Point;
import shapes.TerrainList;
import shapes.Wall;

public class DiggerScenario implements Scenario
{
	private final int lemmingsRequired = 2;
	private final int nbrOfLemmings = 5;
	private TerrainList terrain = new TerrainList();
	private final Point startPosition, homePosition;

	public DiggerScenario()
	{
		startPosition = new Point(125, 80);
		homePosition = new Point(960, 350);
		addTerrain();
	}

	@Override
	public Point getStartPosition()
	{
		return startPosition;
	}

	@Override
	public Point getHomePosition()
	{
		return homePosition;
	}

	@Override
	public int getLemmings()
	{
		return nbrOfLemmings;
	}

	@Override
	public int getLemmingsRequired()
	{
		return lemmingsRequired;
	}

	@Override
	public TerrainList getTerrain()
	{
		return terrain;
	}

	@Override
	public void addTerrain()
	{
		terrain.add(new Entrance(new Point(100, 50), new Point(140, 90)));
		terrain.add(new Exit(new Point(960, 310), new Point(1000, 350)));

		terrain.add(new Wall(new Point(20, 50), new Point(50, 380)));
		terrain.add(new Wall(new Point(670, 50), new Point(700, 150)));

		terrain.add(new Ground(new Point(250, 250), new Point(600, 280)));
		terrain.add(new Ground(new Point(50, 150), new Point(700, 180)));
		terrain.add(new Ground(new Point(50, 350), new Point(1200, 380)));

	}

	@Override
	public boolean isLemmingOutOfMap(Lemming lemming)
	{
		return (lemming.getPosition().getYint() > 380 || lemming.getPosition().getXint() > 1200);
	}
}
