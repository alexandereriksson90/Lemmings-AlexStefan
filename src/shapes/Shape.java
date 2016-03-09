package shapes;

public abstract class Shape implements Cloneable
{
	protected Point position;

	public Shape(double x, double y)
	{
		this(new Point(x, y));
	}

	public Shape(Point p)
	{
		setPosition(p);
	}

	public abstract void reDefine(double x, double y); // size, mm

	public Shape clone()
	{
		Shape result = null;
		try
		{
			result = (Shape) super.clone();
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

	public abstract void accept(ShapeVisitor visitor);

}
