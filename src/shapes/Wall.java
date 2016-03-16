package shapes;

public class Wall extends Rectangle
{

	public Wall(Point p1, Point p2)
	{
		super(p1, p2);
	}

	@Override
	public void accept(TerrainVisitor visitor)
	{
		visitor.visit(this);
	}

	public Point getP3()
	{
		return new Point(getP2().getXint(), getP1().getYint());
	}
	
	public Point getP4()
	{
		return new Point(getP1().getXint(), getP2().getYint());
	}

}
