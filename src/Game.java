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
	 * 7=en passant
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

	/**
	 * checks if the player that has to move is in check
	 * 
	 * @return whether or not the position is check
	 */
	public boolean isCheck()
	{
		try
		{
			ArrayList<Point> kingpositions = this.getPositions(6*this.player);
			//check if no king is found
			if (kingpositions.size() < 1)
				return false;
			
			Point kingposition = kingpositions.get(0);
			
			//change the player
			this.player *= -1;
			
			for (int i = 0; i < this.board.length; i++)
				for (int j = 0; j < this.board[i].length; j++)
					//if a piece of the player that is not to move is on the field
					if (this.player * this.board[i][j] < 0)
						//check if the piece can move to the King
						if(new Move(this, new Point(i,j), kingposition).isPossible(this))
						{
							//change the player back
							this.player *= 1;
							return true;
						}
			
			//change the player back
			this.player *= 1;
			return false;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
}