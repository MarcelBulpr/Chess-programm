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
			this.player = 1;
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
		for (int i = 0; i < this.position.board.length; i++)
			for (int j = 0; j < this.position.board[i].length;j++)
				if (this.position.board[i][j] == piece)
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
			
			for (int i = 0; i < this.position.board.length; i++)
				for (int j = 0; j < this.position.board[i].length; j++)
					//if a piece of the player that is not to move is on the field
					if (this.player * this.position.board[i][j] < 0)
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

	/**
	 * make a move if it is legal
	 * 
	 * @param move the move that needs to be executed
	 * @return returns true if the move could be made; false if it failed
	 */
	public boolean makeMove(Move move)
	{
		try
		{
			if (move.isLegal(this))
			{
				//the field the piece came from must be empty after the move
				this.position.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.position.board[move.destination.x][move.destination.y] = move.afterPiece;
				//add the move to the move history
				this.moves.add(move);
				//change the player to move
				this.player *= -1;
				
				return true;
			}
			else
				return false;
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
	
	/**
	 * make a move
	 * 
	 * @param move the move that needs to be executed 
	 * @param check check if the move is legal first
	 * @return returns true if the move could be made; false if it failed
	 */
	public boolean makeMove(Move move, boolean check)
	{
		try
		{			
			if (check)
				return this.makeMove(move);
			else
			{
				//the field the piece came from must be empty after the move
				this.position.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.position.board[move.destination.x][move.destination.y] = move.afterPiece;
				//add the move to the move history
				this.moves.add(move);
				//change the player to move
				this.player *= -1;
				
				return true;
			}
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
}