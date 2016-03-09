package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.DiggerScenario;
import model.GameBoard;
import model.Scenario;
import shapes.TerrainList;

public class LemmingsApp extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Scenario diggerScenario = new DiggerScenario();
	private GameBoard gameBoard = new GameBoard(diggerScenario);
	private Container c;
	private JPanel gamePanel, skillPanel, statusPanel;

	public LemmingsApp()
	{
		super("Lemmings");
		
		gamePanel = createGamePanel();
		skillPanel = createSkillPanel();
		statusPanel = createStatusPanel();
	}
	
	private JPanel createGamePanel()
	{		
		JPanel temp = new JPanel();
		temp.add(addTerrain(gameBoard.getTerrain()));
		addLemmings();
		return null;
	}

	private Shape addTerrain(TerrainList terrainList)
	{
		if(terrainList.size() != 0)
			
		
	}

	public static void main(String args[])
	{
		new LemmingsApp();
	}
}
