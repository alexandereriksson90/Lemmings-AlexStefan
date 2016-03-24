package model;

import shapes.Point;
import shapes.Rectangle;
import shapes.TerrainPainterVisitor;

public class Lemming
{
	private String skill = null;
	private Rectangle lemming;

	public static int LEFT = -1;
	public static int STOP = 0;
	public static int RIGHT = 1;

	private int direction;

	private Behaviour behaviour;
	private boolean isFalling;

	public Lemming(Point p)
	{
		lemming = new Rectangle(new Point(p.getXint()-15, p.getYint()-30), p);
		direction = RIGHT;
		isFalling = true;
	}

	public Behaviour getBehaviour()
	{
		return behaviour;
	}

	public void setBehaviour(Behaviour behaviour)
	{
		this.behaviour = behaviour;
	}

	public Point getPosition()
	{
		return lemming.getP3();
	}

	public void removeSkill()
	{
		skill = null;
	}
	

	public void move()
	{
		if (!isFalling && direction == RIGHT && behaviour == null)
			lemming.setPosition(lemming.getPosition().getXint() + 1, lemming.getPosition().getYint());
		else if (!isFalling && direction == LEFT && behaviour == null)
		{
			lemming.setPosition(lemming.getPosition().getXint() - 1, lemming.getPosition().getYint());
		} else if (isFalling && behaviour == null)
		{
			lemming.setPosition(lemming.getPosition().getXint(), lemming.getPosition().getYint() + 1);
			
		}else if (behaviour != null)
		{
			behaviour.execute();
			
		}
		
	}
	
	public void fall()
	{
		lemming.setPosition(lemming.getPosition().getXint(), lemming.getPosition().getYint() + 1);
	}

	public void setSkill(String skill)
	{
		this.skill = skill;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public void changeDirection(boolean shouldTurn)
	{
		if (shouldTurn)
		{
			setDirection(getDirection() * -1);
		}
	}

	public String getSkill()
	{
		return skill;
	}

	public int getWidth()
	{
		return lemming.getP2().getXint() - lemming.getP1().getXint();
	}

	public int getHeight()
	{
		int height = lemming.getP1().getYint() - lemming.getP3().getYint();
		return height;
	}

	public int getDirection()
	{
		return direction;
	}

	public void accept(TerrainPainterVisitor visitor)
	{
		visitor.visit(lemming);

	}

	public boolean isFalling()
	{
		return isFalling;
	}
	
	public Point getRightFoot()
	{
		return lemming.getP2();
	}

	public void setFalling(boolean isFalling)
	{
		this.isFalling = isFalling;
	}

	public boolean checkIfInBounds(Point temp)
	{
		return lemming.isWithinBounds(temp);
		
	}

	public void walk()
	{
		if (!isFalling && direction == RIGHT)
		{
			lemming.setPosition(lemming.getPosition().getXint() + 1, lemming.getPosition().getYint());
		}	
		else if (!isFalling && direction == LEFT)
		{
			lemming.setPosition(lemming.getPosition().getXint() - 1, lemming.getPosition().getYint());	
		}
	}

}
