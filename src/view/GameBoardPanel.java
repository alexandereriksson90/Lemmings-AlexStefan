package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.GameManager;
import shapes.TerrainPainterVisitor;

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
		addMouseAdapter();
		setBackground(Color.white);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		repaint();
		
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		TerrainPainterVisitor painter = new TerrainPainterVisitor(g);
		mediator.addTerrain(painter);
		mediator.addLemmings(painter);

	}
	
	public void addMouseAdapter()
	{
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        mediator.checkIfLemming(e.getX(),e.getY());
		    }
		});
	}
}
