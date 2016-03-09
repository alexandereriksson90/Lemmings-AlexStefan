package controller;

import java.util.Observer;

import model.GameBoard;
import model.Lemming;
import shapes.Shape;
import shapes.ShapePainterVisitor;
import model.Skill;
import shapes.ShapeVisitor;

public class GameManager
{
	
	private Skill chosenSkill;
	private GameBoard model;
	
	public GameManager(GameBoard model)
	{
		this.model = model;
		
	}
	
	public void addObserver(Observer observer)
	{
		model.addObserver(observer);
		
	}
	
	public void setSkill(Skill skill)
	{
		chosenSkill = skill;
	}
	
	public void addTerrain(ShapeVisitor visitor)
	{
		 for (Shape shape : model.getTerrain())
		      shape.accept(visitor);
		    	
	}
	
	public void addSkillToLemming(Lemming lemming)
	{
		model.setLemmingSkill(chosenSkill, lemming);
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
