package shapes;

public class Point // immutable
  {
  protected double x, y;

  public Point(double x, double y)
    {
    this.x = x;
    this.y = y;
    }

  public double getX()
    {
    return x;
    }

  public int getXint()
    {
    return (int) (x + 0.5);
    }

  public double getY()
    {
    return y;
    }

  public int getYint()
    {
    return (int) (y + 0.5);
    }

  public Point moveRelative(double dx, double dy)
    {
    return new Point(x + dx, y + dy); // immutable!
    }

  public Point getMiddle(Point other)
    {
    return new Point((x + other.x) / 2.0, (y + other.y) / 2.0);
    }

  public double distanceTo(Point other)
    {
    double dx = (this.x - other.x); // tentativt
    dx *= dx; // definitivt
    double dy = (this.y - other.y); // tentativt
    dy *= dy; // definitivt
    return Math.sqrt(dx + dy);
    }
  
  public boolean between(Point a, Point b)
    {
    double minX = (a.x<b.x)? a.x : b.x;
    double minY = (a.y<b.y)? a.y : b.y;
    double maxX = (a.x<b.x)? b.x : a.x;
    double maxY = (a.y<b.y)? b.y : a.y;
    return minX <= x && x <= maxX && minY <= y && y <= maxY;
    }
  @Override
	public String toString()
	{
		return "x= " + x +"y= "+y;	
	}

  public double dx(Point other)
    {
    return x - other.x;
    }

  public double dy(Point other)
    {
    return y - other.y;
    }
  }