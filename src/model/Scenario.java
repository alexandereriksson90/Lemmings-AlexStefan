package model;

import java.util.List;

import shapes.Point;
import shapes.TerrainList;

public interface Scenario
{
	public List<Skill> getSkills();
	
	public int getLemmings();
	
	public int getLemmingsRequired();
	
	public TerrainList getTerrain();
	
	public Point getStartPosition();
	
	public Point getHomePosition();

}
