package shapes;

public abstract class TerrainUnit implements Cloneable
{
	protected Point position;

	public TerrainUnit(double x, double y)
	{
		this(new Point(x, y));
	}

	public TerrainUnit(Point p)
	{
		
		this.setPosition(p); 
	}

	public abstract void reDefine(double x, double y); // size, mm

	public TerrainUnit clone()
	{
		TerrainUnit result = null;
		try
		{
			result = (TerrainUnit) super.clone();
		} catch (CloneNotSupportedException e)
		{
		}
		return result;
	}

	public Point getPosition()
	{
		return position;
	}

	public void setPosition(double x, double y)
	{
		setPosition(new Point(x, y));
	}

	public void setPosition(Point p)
	{
	
		position = p;
	}
	
	

	public abstract void accept(TerrainVisitor visitor);

}
