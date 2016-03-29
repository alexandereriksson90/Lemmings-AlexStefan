package model;

import shapes.Entrance;
import shapes.Exit;
import shapes.Ground;
import shapes.Point;
import shapes.TerrainList;
import shapes.Wall;

public class BasherScenario implements Scenario
{
	private final int lemmingsRequired = 6;
	private final int nbrOfLemmings = 10;
	private TerrainList terrain = new TerrainList();
	private final Point startPosition, homePosition;

	public BasherScenario()
	{
		startPosition = new Point(125, 300);
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

		terrain.add(
				new Entrance(new Point(100, startPosition.getYint() - 40), new Point(140, startPosition.getYint())));
		terrain.add(new Exit(new Point(960, 310), new Point(1000, 350)));

		terrain.add(new Wall(new Point(20, 50), new Point(50, 380)));
		terrain.add(new Wall(new Point(300, 50), new Point(330, 350)));
		terrain.add(new Ground(new Point(50, 350), new Point(1200, 380)));

	}

	@Override
	public boolean isLemmingOutOfMap(Lemming lemming)
	{
		return (lemming.getPosition().getYint() > 380 || lemming.getPosition().getXint() > 1200);
	}

}
