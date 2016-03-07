package shapes;

public class Triangle extends Shape
{
	private Line line1,line2,line3;
	private double height,sideLength;
	public Triangle(double sideLength, Point p)
	{
		super(p);
		this.sideLength = sideLength;
		height = Math.sqrt(Math.pow(sideLength, 2) - Math.pow(sideLength/2,2));
		
		line1 = new Line(p, new Point(p.getX()+ (sideLength/2), p.getY()+height));
		line2 = new Line(new Point(p.getX()+ (sideLength/2), p.getY()+height), new Point(p.getX()+ sideLength, p.getY()));
		line3 = new Line(p, new Point(p.getX()+ sideLength, p.getY()));
		
	}

	@Override
	public void reDefine(double x, double y)
	{
		
		
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
		line1.setP1(p);
		line1.setP2(new Point(p.getX()+ (sideLength/2), p.getY()+height));
		line2.setP1(new Point(p.getX()+ (sideLength/2), p.getY()+height));
		line2.setP2(new Point(p.getX()+ sideLength, p.getY()));
		line3.setP1(p);
		line3.setP2(new Point(p.getX()+ sideLength, p.getY()));		
		super.setPosition(p);
    }
	
	@Override
	public void accept(ShapeVisitor visitor)
	{
		visitor.visit(this);
		
	}
	
	
	
}
