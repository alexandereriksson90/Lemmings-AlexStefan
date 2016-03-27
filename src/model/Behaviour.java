package model;

public interface Behaviour
{
	public Lemming getLemming();

	public GameBoard getModel();

	public String getName();

	public void execute();

	public void setModel(GameBoard model);

	public void setLemming(Lemming lemming);
}
