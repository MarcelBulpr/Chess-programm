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
					if (this.player * this.position.board[i][j] > 0)
						//check if the piece can move to the King
						if(new Move(this, new Point(i,j), kingposition).isPossible(this))
						{
							this.player*=-1;
							return true;
						}
			
			this.player*=-1;
			
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
				return executeMove(move);
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
				return this.executeMove(move);
			else
			{				
				return executeMove(move);
			}
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}

	/**
	 * checks if the current player can move
	 * 
	 * @return if the player can move
	 */
	public boolean canMove()
	{
		try
		{
			//for each field
			for (int i = 0; i < this.position.board.length; i++)
			{
				for (int j = 0; j < this.position.board[i].length; j++)
				{
					//check if a piece is located on the square
					if (this.position.board[i][j] * this.player > 0 && Math.abs(this.position.board[i][j]) != 7)
					{
						//check if the piece can move to any square on the board
						for (int k = 0; k < this.position.board.length; k++)
						{
							for (int l = 0; l < this.position.board[k].length; l++)
							{
								Move move = new Move(this, new Point(i,j), new Point(k,l));
								if (move.isLegal(this))
								{
									return true;
								}
							}
						}
					}
				}
			}
			return false;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
	
	/**
	 * Copy the game class to another ram address (it only has the player and the position in it)
	 * 
	 * @param game the game in another ram address
	 */
	public void copy(Game game)
	{
		try
		{
			//copy each square value
			for (int i = 0; i < game.position.board.length; i++)
				for (int j = 0; j < game.position.board[i].length; j++)
					this.position.board[i][j] = game.position.board[i][j];
					
			//copy the player that has to move
			this.player = game.player;
			this.position.castle = game.position.castle;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
		}
	}
	
	/**
	 * execute a move
	 * 
	 * @param move the move that needs to be executed
	 * @return success of failure
	 */
	private boolean executeMove (Move move)
	{
		try
		{
			//if the pawn takes en passant
			if (this.position.board[move.destination.x][move.destination.y]==-7*this.player)
			{
				//the field the piece came from must be empty after the move
				this.position.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.position.board[move.destination.x][move.destination.y] = move.afterPiece;
				//take pawn
				this.position.board[move.destination.x+this.player][move.destination.y] = 0;
				//add the move to the move history
				this.moves.add(move);
				//change the player to move
				this.player *= -1;
			}
			//if a King castles
			else if (Math.abs(move.piece) == 6 && Math.abs(move.origin.x - move.destination.x)==2)
			{
				//the field the piece came from must be empty after the move
				this.position.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.position.board[move.destination.x][move.destination.y] = move.afterPiece;
	
				//check if the king castled long
				if (move.destination.x == 2)
				{
					//remove rook
					this.position.board[0][move.destination.y] = 0;
					//place rook next to King
					this.position.board[3][move.destination.y] = 4*this.player;
				}
				//check if the king castled short
				else if (move.destination.x == 6)
				{
					//remove rook
					this.position.board[7][move.destination.y] = 0;
					//place rook next to King
					this.position.board[5][move.destination.y] = 4*this.player;
				}
				
				//add the move to the move history
				this.moves.add(move);
				//change the player to move
				this.player *= -1;
			}					
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
			}
			//clear the board from en passant fields
			for (int i = 0; i < this.position.board.length; i++)
				for (int j = 0; j < this.position.board[i].length; j++)
					if (Math.abs(this.position.board[i][j]) == 7)
						this.position.board[i][j] = 0;
			
			return true;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
}