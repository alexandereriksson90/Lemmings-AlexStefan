package shapes;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Line;
import shapes.Rectangle;
import shapes.ShapeVisitor;

public class ShapePainterVisitor implements ShapeVisitor
{
	private Graphics graphics;
	private Color originalColor;

	public ShapePainterVisitor(Graphics g)
	{
		graphics = g;
	}

	public void emphasize()
	{
		originalColor = graphics.getColor();
		graphics.setColor(Color.blue);
	}

	public void normal()
	{
		graphics.setColor(originalColor);
	}

	@Override
	public void visit(Rectangle rectangle)
	{
		int x1 = (int) (rectangle.getP1().getX() + 0.5);
		int x2 = (int) (rectangle.getP2().getX() + 0.5);
		int y1 = (int) (rectangle.getP1().getY() + 0.5);
		int y2 = (int) (rectangle.getP2().getY() + 0.5);
		graphics.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void visit(Line line)
	{
		int x1 = (int) (line.getP1().getX() + 0.5);
		int x2 = (int) (line.getP2().getX() + 0.5);
		int y1 = (int) (line.getP1().getY() + 0.5);
		int y2 = (int) (line.getP2().getY() + 0.5);
		graphics.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void visit(Triangle triangle)
	{
		graphics.drawLine(triangle.getLine1().p1.getXint(), triangle.getLine1().p1.getYint(), triangle.getLine1().p2.getXint(), triangle.getLine1().p2.getYint());
		graphics.drawLine(triangle.getLine2().p1.getXint(), triangle.getLine2().p1.getYint(), triangle.getLine2().p2.getXint(), triangle.getLine2().p2.getYint());
		graphics.drawLine(triangle.getLine3().p1.getXint(), triangle.getLine3().p1.getYint(), triangle.getLine3().p2.getXint(), triangle.getLine3().p2.getYint());
	}
}
