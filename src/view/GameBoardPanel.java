package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.GameManager;
import shapes.ShapePainterVisitor;

public class GameBoardPanel extends JPanel implements Observer
{
	
	private GameManager mediator;
	private static final long serialVersionUID = 1L;
	public GameBoardPanel(GameManager mediator)
	{
		super(true);
		this.mediator = mediator;
		mediator.addObserver(this);
		setSize(1000, 600);
		setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1)
	{
		repaint();
		
	}
	
	 public void paintComponent(Graphics g)
	    {
	    super.paintComponent(g);
	    ShapePainterVisitor painter = new ShapePainterVisitor(g);
	    mediator.addTerrain(painter);
	    mediator.addLemmings(painter);
	    
	    }
}
