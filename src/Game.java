import java.util.ArrayList;
import java.awt.Point;

/**
 * Class to save all significant data for one game
 * 
 * @author Marcel
 *
 */
public class Game {
	
	public Game()
	{
		try
		{
			//add the start position
			this.position = new Position();
			this.moves = new ArrayList<Move>();
			//white always starts a game
			this.position.player = 1;
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * the current position
	 */
	Position position;
	
	/**
	 * a List with all moves that the players have made
	 */
	ArrayList<Move> moves;
	
	/**
	 * Method to generate a Notation String
	 * 
	 * @return the Notation
	 */
	public String getNotation ()
	{
		//code to be added
		//the code should take all moves of the game and then generate a String that represents the Notation
		return null;
	}
	
	/**
	 * Copy the game class to another ram address
	 * 
	 * @param game the game in another ram address
	 */
	public void copy(Game game)
	{
		try
		{
			//copy the position
			this.position.copy(game.position);
			
			//add each move to the moves Array
			for (int i = 0; i < game.moves.size(); i++)
				this.moves.add(game.moves.get(i));
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
		}
	}

	public void makeMove(Move move)
	{
		try
		{
			if (move.isLegal(this.position))
			{
				this.position.makeMove(move);
				//add the move to the move history
				this.moves.add(move);
			}
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
		}
	}
}