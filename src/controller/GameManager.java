package controller;

import java.util.Observer;

import model.GameBoard;
import model.Lemming;
import shapes.TerrainUnit;
import shapes.ShapePainterVisitor;
import shapes.ShapeVisitor;

public class GameManager
{

	private String chosenBehaviour;
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
		chosenBehaviour = behaviour;
	}

	public void addTerrain(ShapeVisitor visitor)
	{
		for (TerrainUnit shape : model.getTerrain())
			shape.accept(visitor);

	}

	public void addBehaviourToLemming(Lemming lemming)
	{
		model.setLemmingBehaviour(chosenBehaviour, lemming);
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

	public void addLemmings(ShapePainterVisitor visitor)
	{
		for (Lemming shape : model.getLemmings())
			shape.accept(visitor);

	}

}
