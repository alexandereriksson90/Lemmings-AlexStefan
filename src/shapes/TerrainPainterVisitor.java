package shapes;

import java.awt.Color;
import java.awt.Graphics;

import shapes.Line;
import shapes.Rectangle;
import shapes.TerrainVisitor;

public class TerrainPainterVisitor implements TerrainVisitor
{
	private Graphics graphics;


	public TerrainPainterVisitor(Graphics g)
	{
		graphics = g;
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
		graphics.setColor(Color.BLACK);
		graphics.drawLine(triangle.getLine1().p1.getXint(), triangle.getLine1().p1.getYint(), triangle.getLine1().p2.getXint(), triangle.getLine1().p2.getYint());
		graphics.drawLine(triangle.getLine2().p1.getXint(), triangle.getLine2().p1.getYint(), triangle.getLine2().p2.getXint(), triangle.getLine2().p2.getYint());
		graphics.drawLine(triangle.getLine3().p1.getXint(), triangle.getLine3().p1.getYint(), triangle.getLine3().p2.getXint(), triangle.getLine3().p2.getYint());
	}

	@Override
	public void visit(Ground ground)
	{
		int x1 = (int) (ground.getP1().getX() + 0.5);
		int x2 = (int) (ground.getP2().getX() + 0.5);
		int y1 = (int) (ground.getP1().getY() + 0.5);
		int y2 = (int) (ground.getP2().getY() + 0.5);
		
		graphics.setColor(new Color(139,69,19));
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
		
	}

	@Override
	public void visit(Wall wall)
	{
		int x1 = (int) (wall.getP1().getX() + 0.5);
		int x2 = (int) (wall.getP2().getX() + 0.5);
		int y1 = (int) (wall.getP1().getY() + 0.5);
		int y2 = (int) (wall.getP2().getY() + 0.5);
		
		graphics.setColor(new Color(139,69,19));
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
		
	}

	@Override
	public void visit(Entrance entrance)
	{
		int x1 = (int) (entrance.getP1().getX() + 0.5);
		int x2 = (int) (entrance.getP2().getX() + 0.5);
		int y1 = (int) (entrance.getP1().getY() + 0.5);
		int y2 = (int) (entrance.getP2().getY() + 0.5);
		
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x1, y1, x2 - x1, y2 - y1);
		
	}

	@Override
	public void visit(Exit exit)
	{
		int x1 = (int) (exit.getP1().getX() + 0.5);
		int x2 = (int) (exit.getP2().getX() + 0.5);
		int y1 = (int) (exit.getP1().getY() + 0.5);
		int y2 = (int) (exit.getP2().getY() + 0.5);
		
		graphics.setColor(new Color(0,191,255));
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);		
		
	}

	@Override
	public void visit(Hole hole)
	{
		int x1 = (int) (hole.getP1().getX() + 0.5);
		int x2 = (int) (hole.getP2().getX() + 0.5);
		int y1 = (int) (hole.getP1().getY() + 0.5);
		int y2 = (int) (hole.getP2().getY() + 0.5);
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);	
		
	}
}
