package model;

import java.util.List;

import shapes.Point;
import shapes.TerrainList;

public interface Scenario
{
	public List<String> getSkills();
	
	public int getLemmings();
	
	public int getLemmingsRequired();
	
	public TerrainList getTerrain();
	
	public Point getStartPosition();
	
	public Point getHomePosition();

	public boolean isLemmingOutOfMap(Lemming lemming);

}
