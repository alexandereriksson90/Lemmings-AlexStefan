package shapes;



public class Line extends Shape
  {
  public Point p1, p2;

  public Line(Point p1, Point p2)
    {
    super(p1.getMiddle(p2));
    this.p1 = p1;
    this.p2 = p2;
    }

  public Line(double x1, double y1, double x2, double y2)
  	{
	  this(new Point(x1, y1), new Point(x2, y2));
  	}
  
  public Point getP1()
    {
    return p1;
    }

  public void setP1(Point p1)
    {
    this.p1 = p1;
    position = p1.getMiddle(p2);
    }

  public Point getP2()
    {
    return p2;
    }

  public void setP2(Point p2)
    {
    this.p2 = p2;
    position = p1.getMiddle(p2);
    }

  public void reDefine(double x, double y)
    {
    setP2( new Point(x,y) );
    }

  public Point getPosition() 
    {
    return p1.getMiddle(p2);
    }

  public void setPosition(Point p)
    {
    if (p1 != null && p2 != null)
      {
      p1 = p1.moveRelative(p.dx(position), p.dy(position));
      p2 = p2.moveRelative(p.dx(position), p.dy(position));
      }
    super.setPosition(p);
    }

  @Override
  public void accept(ShapeVisitor visitor)
    {
    visitor.visit(this);
    }

  
  
  }
