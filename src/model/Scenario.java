package model;

import shapes.Point;
import shapes.TerrainList;

public interface Scenario
{
	public int getLemmings();

	public int getLemmingsRequired();

	public TerrainList getTerrain();

	public Point getStartPosition();

	public Point getHomePosition();

	public boolean isLemmingOutOfMap(Lemming lemming);

	public void addTerrain();

}
