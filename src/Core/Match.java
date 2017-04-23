package Core;
import java.util.ArrayList;

/**
 * Class to save all significant data for one match
 * 
 * @author Marcel
 *
 */
public class Match {

	/**
	 * Builder for a match that takes to players
	 * 
	 * @param player1 the first Player
	 * @param player2 the second Player
	 */
	public Match(Player player1, Player player2)
	{
		try
		{
			this.player1 = player1;
			this.player2 = player2;
			this.games = new ArrayList<Game>();
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}
	
	/**
	 * Builder for a match that takes just two names and creates the players itself
	 * 
	 * @param name1 the name of the first player
	 * @param name2 the name of the second player
	 */
	public Match(String name1, String name2)
	{
		try
		{
			this.player1 = new Player(name1);
			this.player2 = new Player(name2);
			this.games = new ArrayList<Game>();
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}
	
	/**
	 * the first player of the match
	 */
	Player player1;
	
	/**
	 * the second player of the match
	 */
	Player player2;
	
	/**
	 * all the games that were played in the match
	 */
	ArrayList<Game> games;
}