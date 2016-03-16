package factories;


import model.Behaviour;

public class BehaviourFactory
{

	public static Behaviour getInstance(String id)
	{
		 try {
	            return (Behaviour) Class.forName("behaviours."+ id).newInstance();
	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
	            throw new RuntimeException(ex);
	        }
	}

}
