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
	 * 1=pawn
	 * 2=knight
	 * 3=Bishop
	 * 4=rook
	 * 5=Queen
	 * 6=King
	 * positive values = white, negative values = black
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
	
	/**
	 * Get a List of all position the piece is placed
	 * 
	 * @param piece the piece to search for
	 * @return the List of all positions the piece was found
	 */
	public ArrayList<Point> getPositions(int piece)
	{
		ArrayList<Point> positions = new ArrayList<Point>();
		
		//for each field add position if the piece matches the searched piece
		for (int i = 0; i < this.board.length; i++)
			for (int j = 0; j < this.board[i].length;j++)
				if (this.board[i][j] == piece)
					positions.add(new Point(i, j));
		
		return positions;
	}
}