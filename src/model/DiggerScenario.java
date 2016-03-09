package model;

import java.util.ArrayList;
import java.util.List;

import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.TerrainList;

public class DiggerScenario implements Scenario
{
	private final int lemmingsRequired = 2;
	private final int nbrOfLemmings = 1;
	private List<Skill> allowedSkills = new ArrayList<Skill>();
	private TerrainList terrain = new TerrainList();
	private final Point startPosition, homePosition;
	
	public DiggerScenario()
	{
		startPosition = new Point(100,50);
		homePosition = new Point(1000,350);
		allowedSkills.add(Skill.DIGGER);
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
	public List<Skill> getSkills()
	{
		return allowedSkills;
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
	
	private void addTerrain()
	{
		terrain.add(new Rectangle(new Point(100,50),new Point(140,90)));
		terrain.add(new Rectangle(new Point(960,310),new Point(1000,350)));
		
		terrain.add(new Line(50,50,50,350));
		terrain.add(new Line(250,250,600,250));
		terrain.add(new Line(50,150,700,150));
		terrain.add(new Line(700,10,700,150));
		
		
		terrain.add(new Line(50,350,1200,350));
		
	}
}
