package controller;

import java.util.Observer;

import model.GameBoard;
import model.Lemming;
import shapes.TerrainUnit;
import shapes.TerrainPainterVisitor;
import shapes.TerrainVisitor;

public class GameManager
{

	private GameBoard model;

	public GameManager(GameBoard model)
	{
		this.model = model;

	}

	public void addObserver(Observer observer)
	{
		model.addObserver(observer);

	}

	public void setBehaviour(String behaviour)
	{
		model.setBehaviour(behaviour);
	}

	public void addTerrain(TerrainVisitor visitor)
	{
		for (TerrainUnit shape : model.getTerrain())
			shape.accept(visitor);

	}

	public void start()
	{
		model.start();
	}

	public void pause()
	{
		model.pause();
	}

	public int getSavedLemmings()
	{
		return model.getSavedLemmings();
	}

	public void addLemmings(TerrainPainterVisitor visitor)
	{
		for (Lemming shape : model.getLemmings())
			shape.accept(visitor);

	}

	public void end() 
	{
	
		
	}

	public void checkIfLemming(int x, int y) 
	{
		model.checkIfLemming(x,y);
		
	}



}
