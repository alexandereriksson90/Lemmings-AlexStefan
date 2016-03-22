package view;

import java.awt.*;

import javax.swing.*;

import controller.GameManager;
import model.BasherScenario;
import model.DiggerScenario;
import model.GameBoard;
import model.Scenario;

public class LemmingsApp extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Scenario diggerScenario = new DiggerScenario();
	private Scenario basherScenario = new BasherScenario();
	private GameBoard gameBoard = new GameBoard(basherScenario);
	private GameManager manager = new GameManager(gameBoard);
	private GameBoardPanel gamePanel = new GameBoardPanel(manager);
	private ButtonPanel skillPanel = new ButtonPanel(manager);
	private StatusPanel statusPanel = new StatusPanel(manager);
	private Container c;
	StatusThread status = new StatusThread();

	public LemmingsApp()
	{
		super("Lemmings");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(gamePanel, BorderLayout.CENTER);
		c.add(skillPanel, BorderLayout.SOUTH);
		c.add(statusPanel, BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		manager.start();
		status.start();
	}

	public static void main(String args[])
	{
		new LemmingsApp();
	}
	
	private void gameFinished()
	{
		String message =
		null;
		if(manager.hasWon()) 
		{
			message = "Congratulations you have won!";
		} else if(manager.hasLost())
		{
			message = "Too bad man you have lost!";
		}
		JOptionPane.showMessageDialog (null, message , "Game Over", JOptionPane.INFORMATION_MESSAGE);
		status.interrupt();
		
	}
	
	private class StatusThread implements Runnable
	{
		Thread t;

		StatusThread()
		{
			super();
			t = new Thread(this);

		}

		public void start()
		{
			t.start();
		}

		public void run()
		{

			while (!manager.hasWon() && !manager.hasLost())
			{

				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{

				}
			}
			gameFinished();
		}

		public void interrupt()
		{
			t.interrupt();
		}

	}
}
