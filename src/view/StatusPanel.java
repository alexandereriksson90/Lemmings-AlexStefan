package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameManager;

public class StatusPanel extends JPanel implements Observer
{
	GameManager manager;
	JLabel lemmingsOut = new JLabel();
	JLabel lemmingsToSave = new JLabel();
	JLabel lemmingsHome = new JLabel();
	private static final long serialVersionUID = 1L;

	public StatusPanel(GameManager manager)
	{
		this.manager = manager;
		setVisible(true);
		setBackground(Color.gray);
		manager.addObserver(this);
		add(lemmingsOut);
		add(lemmingsToSave);
		add(lemmingsHome);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		lemmingsOut.setText("Lemmings Out: " + manager.getLemmingsOut());
		lemmingsToSave.setText("Required Lemmings: " + manager.getRequiredLemmings());
		lemmingsHome.setText("Saved Lemmings: " + manager.getSavedLemmings());

	}
}
