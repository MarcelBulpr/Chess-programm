import java.util.ArrayList;

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
			//one board has a static size of 8x8
			this.board = new int[8][8];
			this.moves = new ArrayList<Move>();
			//white always starts a game
			this.player = 1;
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * the current position on the board
	 */
	int[][] board;
	
	/**
	 * a List with all moves that the players have made
	 */
	ArrayList<Move> moves;
	
	/**
	 * the player that has to move. positive = white to move. negative = black to move.
	 */
	int player;
	
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
}