package shapes;

public class Exit extends Rectangle
{

	public Exit(Point p1, Point p2)
	{
		super(p1, p2);

	}

	@Override
	public void accept(TerrainVisitor visitor)
	{
		visitor.visit(this);
	}

}
