package model;

import shapes.Point;
import shapes.Shape;
import shapes.ShapePainterVisitor;
import shapes.Triangle;

public class Lemming
{
	private Skill skill = null;
	private Triangle lemming;
	
	public static int LEFT = -1;
	public static int STOP = 0;
	public static int RIGHT = 1;
	
	private int direction;
	
	public Lemming(Point p)
	{
		
		lemming = new Triangle(30.0, p);
		direction = RIGHT;
	}
	
	public Point getPosition()
	{
		return lemming.getPosition();
	}
	
	public void removeSkill()
	{
		skill = null;
	}
	
	public void move(int dx, int dy)
	{
		lemming.setPosition(lemming.getPosition().getXint()+dx,lemming.getPosition().getYint()+dy);
	}
	
	public void setSkill(Skill skill)
	{
		this.skill = skill;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	public void changeDirection()
	{
		setDirection(getDirection() * -1);
	}
	
	public Skill getSkill()
	{
		return skill;
	}

	public int getWidth()
	{
		return 30;
	}

	public int getDirection()
	{	
		return direction;
	}

	public void accept(ShapePainterVisitor visitor)
	{
		visitor.visit(lemming);
		
	}


}
