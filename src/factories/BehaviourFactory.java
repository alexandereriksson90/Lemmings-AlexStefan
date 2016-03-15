package factories;


import model.Behaviour;

public class BehaviourFactory
{

	public static Behaviour getInstance(String id)
	{
		 try {
	            return (Behaviour) Class.forName("behaviour."+ id).newInstance();
	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
	            throw new RuntimeException(ex);
	        }
	}

}
