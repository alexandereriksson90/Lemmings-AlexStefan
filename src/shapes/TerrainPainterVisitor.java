package shapes;

import java.awt.Color;
import java.awt.Graphics;
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
		graphics.setColor(Color.GREEN);
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);
	}

	@Override
	public void visit(Ground ground)
	{
		int x1 = (int) (ground.getP1().getX() + 0.5);
		int x2 = (int) (ground.getP2().getX() + 0.5);
		int y1 = (int) (ground.getP1().getY() + 0.5);
		int y2 = (int) (ground.getP2().getY() + 0.5);

		graphics.setColor(new Color(139, 69, 19));
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);

	}

	@Override
	public void visit(Wall wall)
	{
		int x1 = (int) (wall.getP1().getX() + 0.5);
		int x2 = (int) (wall.getP2().getX() + 0.5);
		int y1 = (int) (wall.getP1().getY() + 0.5);
		int y2 = (int) (wall.getP2().getY() + 0.5);

		graphics.setColor(new Color(139, 69, 19));
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

		graphics.setColor(new Color(0, 191, 255));
		graphics.fillRect(x1, y1, x2 - x1, y2 - y1);

	}

}
