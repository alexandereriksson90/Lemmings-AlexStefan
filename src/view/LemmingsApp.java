package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.GameManager;
import model.BasherScenario;
import model.DiggerScenario;
import model.GameBoard;


public class LemmingsApp extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private GameBoard gameBoard;
	private GameManager manager;
	private GameBoardPanel gamePanel;
	private ButtonPanel skillPanel;
	private StatusPanel statusPanel;
	private Container c;
	StatusThread status = new StatusThread();

	public LemmingsApp()
	{
		super("Lemmings");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		createMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		status.start();
	
	}
	
	public void addNewScenario()
	{
		c.add(gamePanel, BorderLayout.CENTER);
		c.add(skillPanel, BorderLayout.SOUTH);
		c.add(statusPanel, BorderLayout.NORTH);
		manager.start();
		
		
	}
	
	public void createMenu()
	{
		JMenu menu = new JMenu("Choose Scenario");
		JMenuItem diggerScenario = new JMenuItem("Digger Scenario");
		JMenuItem basherScenario = new JMenuItem("Basher Scenario");
		JMenuBar menuBar = new JMenuBar();
		menu.add(diggerScenario);
		menu.add(basherScenario);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		diggerScenario.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				c.removeAll();
				gameBoard = new GameBoard(new DiggerScenario());
				manager = new GameManager(gameBoard);
				gamePanel = new GameBoardPanel(manager);
				skillPanel = new ButtonPanel(manager);
				statusPanel = new StatusPanel(manager);
				addNewScenario();
			}
		});	

		basherScenario.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				c.removeAll();
				gameBoard = new GameBoard(new BasherScenario());
				manager = new GameManager(gameBoard);
				gamePanel = new GameBoardPanel(manager);
				skillPanel = new ButtonPanel(manager);
				statusPanel = new StatusPanel(manager);
				addNewScenario();
			}
		});	
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
