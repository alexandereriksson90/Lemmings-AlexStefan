package shapes;

public class Ground extends Rectangle
{

	public Ground(Point p1, Point p2)
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

}
