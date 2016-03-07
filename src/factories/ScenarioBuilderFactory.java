package factories;

import model.Scenario;
import model.ScenarioBuilder;

public class ScenarioBuilderFactory
{
	private static ScenarioBuilder builderFactory;
	
	public static ScenarioBuilder getInstance(Scenario scenario)
	{
		if (builderFactory == null)
		{
			builderFactory = new ScenarioBuilder(scenario);
		}
		return builderFactory;
	}
}
