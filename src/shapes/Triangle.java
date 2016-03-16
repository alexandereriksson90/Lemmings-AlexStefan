package shapes;

public class Triangle extends TerrainUnit
{
	private Line line1, line2, line3;
	private double height, sideLength;

	public Triangle(double sideLength, Point p)
	{
		super(p);
		this.sideLength = sideLength;
		height = Math.sqrt(Math.pow(sideLength, 2)
				- Math.pow(sideLength / 2, 2));

		line1 = new Line(p, new Point(p.getX() + (sideLength / 2), p.getY()
				- height));
		line2 = new Line(new Point(p.getX() + (sideLength / 2), p.getY()
				- height), new Point(p.getX() + sideLength, p.getY()));
		line3 = new Line(p, new Point(p.getX() + sideLength, p.getY()));

	}

	@Override
	public void reDefine(double x, double y)
	{

	}

	public boolean isWithinBounds(Point point)
	{
		int as_x = point.getXint() - line1.getP1().getXint();
		int as_y = point.getYint() - line1.getP1().getYint();

		boolean s_ab = (line1.getP2().getXint() - line1.getP1().getXint()) * as_y - (line1.getP2().getYint() - line1.getP1().getYint()) * as_x > 0;

		if ((line2.getP2().getXint() - line1.getP1().getXint()) * as_y - (line2.getP2().getYint() - line1.getP1().getYint()) * as_x > 0 == s_ab)
			return false;

		if ((line2.getP2().getXint() - line1.getP2().getXint()) * (point.getYint() - line1.getP2().getYint()) - (line2.getP2().getYint() - line1.getP2().getYint()) * (point.getXint() - line1.getP2().getXint()) > 0 != s_ab)
			return false;

		return true;
	}

	public Line getLine1()
	{
		return line1;
	}

	public Line getLine2()
	{
		return line2;
	}

	public Line getLine3()
	{
		return line3;
	}

	public void setPosition(Point p)
	{
		super.setPosition(p);
		if (line1 != null && line2 != null && line3 != null)
		{
			line1.setP1(p);
			line1.setP2(new Point(p.getX() + (sideLength / 2), p.getY()
					- height));
			line2.setP1(new Point(p.getX() + (sideLength / 2), p.getY()
					- height));
			line2.setP2(new Point(p.getX() + sideLength, p.getY()));
			line3.setP1(p);
			line3.setP2(new Point(p.getX() + sideLength, p.getY()));
		}

	}

	@Override
	public void accept(TerrainVisitor visitor)
	{
		visitor.visit(this);

	}

}
