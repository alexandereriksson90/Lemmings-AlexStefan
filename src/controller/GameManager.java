package controller;

import model.Lemming;
import model.Skill;

public class GameManager
{
	private Lemming lemming;
	
	public GameManager()
	{
		this.lemming = lemming;
	}
	
	public void dig()
	{
		lemming.setSkill(Skill.DIGGER);
		
	}
}
