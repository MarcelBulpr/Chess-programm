package Core;

/**
 * Class to save all significant data for one player
 * 
 * @author Marcel
 *
 */
public class Player {

	/**
	 * Builder to make a default player with a name and zero points
	 * 
	 * @param name the name of the player
	 */
	public Player(String name)
	{
		try
		{
			this.name = name;
			this.points = 0;
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}
	
	/**
	 * the name of the player
	 */
	public String name;
	
	/**
	 * the points of a player in a match
	 */
	public double points;
}