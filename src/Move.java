import java.awt.Point;
import java.util.ArrayList;

/**
 * Class to save all significant data for one move
 * 
 * @author Marcel
 *
 */
public class Move {
	
	/**
	 * Builder to create a move out of a game an origin and an destination
	 * 
	 * @param game the game where the move is made
	 * @param origin the position the piece came from
	 * @param destination the position the piece went to
	 */
	public Move(Game game, Point origin, Point destination, int promotion)
	{
		try
		{
			this.origin = origin;
			this.destination = destination;
			//code to be added
			//the code should take the game, the Points and the promotion and calculate the other values of this Class
			//.x=buttonID/8
			//.y=buttonID%8
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * the coordinates of the origin of the piece
	 */
	Point origin;
	
	/**
	 * the coordinates of the point the piece is moving to
	 */
	Point destination;
	
	/**
	 * if the player checked his opponent
	 */
	boolean check;
	
	/**
	 * if the player mated his opponent
	 */
	boolean mate;
	
	/**
	 * if the player took 
	 */
	boolean taken;
	
	/**
	 * the piece type that moved
	 */
	int piece;
	
	/**
	 * indicates the piece type after the move (relevant if a pawn promotes)
	 */
	int afterPiece;
	
	/**
	 * Check if a move is possible
	 * 
	 * @param game the game the move is made in
	 * @return boolean that indicates if the move is possible
	 */
	public boolean isPossible(Game game)
	{
		try
		{
			//get the piece that is moved
			int piece = game.board[this.origin.x][this.origin.y];

			switch (Math.abs(piece))
			{
				//check witch kind of piece stands on the origin field 
				case 1: return pawnMove(game);
				case 2: return knightMove(game);
				case 3: return bishopMove(game);
				case 4: return rookMove(game);
				case 5: return queenMove(game);
				case 6: return kingMove(game);
				default: return false;
			}
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}

	/**
	 *  check if the move is possible if a pawn performers it
	 *  
	 * @param game the game the move is made in
	 * @return boolean that indicates if the pawn move is possible
	 */
	private boolean pawnMove(Game game)
	{
		try
		{
			//if the pawn moved one square in the direction of the opponent
			if (this.origin.y - this.destination.y == game.player)
			{
				//if the pawn moves straight
				if (this.origin.x == this.destination.x)
					//if the destination has no piece on it
					if (game.board[this.destination.x][this.destination.y] == 0)
						return true;
					else
						return false;
				else
				//if the pawn moves diagonally (taking)
				if (Math.abs(this.origin.x - this.destination.x) == 1)
					//if the destination has an enemy piece on it
					if (game.board[this.destination.x][this.destination.y] * game.player < 0)
						return true;
					else
						return false;
				else
					return false;
				
			}
			else if (this.origin.y - this.destination.y == game.player * 2)
			{
				//if the pawn moves straight
				if (this.origin.x == this.destination.x)
					//if the destination has no piece on it
					if (game.board[this.destination.x][this.destination.y] == 0)
						return true;
					else
						return false;
				else
					return false;
			}
			return false;
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}

	/**
	 * check if the move is possible if a knight performers it
	 *  
	 * @param game the game the move is made in
	 * @return boolean that indicates if the knight move is possible
	 */
	private boolean knightMove(Game game)
	{
		try
		{
			int differenceX = Math.abs(this.origin.x - this.destination.x);
			int differenceY = Math.abs(this.origin.y - this.destination.y);
			
			//the difference in the x and y position (must be three)
			int difference = differenceX + differenceY;
			
			//check if the difference is three
			if (difference == 3)
			{
				if (differenceX == 2 || differenceY == 2)
					return true;
				else
					return false;
			}
			
			return false;
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
		
	}

	/**
	 *  check if the move is possible if a bishop performers it
	 *  
	 * @param game the game the move is made in
	 * @return boolean that indicates if the bishop move is possible
	 */
	private boolean bishopMove(Game game)
	{
		int differenceX = this.origin.x - this.destination.x;
		int differenceY = this.origin.y - this.destination.y;
		
		//the difference in both axis must be the same and if the destination is empty or an enemy piece
		if (Math.abs(differenceX) == Math.abs(differenceY) && game.board[this.destination.x][this.destination.y]*game.player <= 0)
		{
			//check the path to the destination and check if it is free
			for (int i = 1; i < Math.abs(differenceX); i++)				
				if (game.board[this.origin.x + (i * (Math.abs(differenceX)/differenceX) * -1)][this.origin.y + (i * (Math.abs(differenceY)/differenceY) *-1)] != 0)
					return false;
			return true;
		}
		else
			return false;
	}

	/**
	 *  check if the move is possible if a rook performers it
	 *  
	 * @param game the game the move is made in
	 * @return boolean that indicates if the rook move is possible
	 */
	private boolean rookMove(Game game)
	{
		try
		{
			int differenceX = this.origin.x - this.destination.x;
			int differenceY = this.origin.y - this.destination.y;
			
			//a rook can only move on one axis at a time and if the destination is empty or an enemy piece
			if ((differenceX == 0 || differenceY == 0) && game.board[this.destination.x][this.destination.y]*game.player <= 0)
			{
					//check in witch direction the rook is moving
					if (differenceX != 0)
					{
						//check if the path is free
						for (int i = 1; i < differenceX; i++)
							if (game.board[this.origin.x + (i * (Math.abs(differenceX)/differenceX) * -1)][this.origin.y] != 0)
								return false;
					}
					else if (differenceY != 0)
					{
						//check if the path is free
						for (int i = 1; i < differenceY; i++)
							if (game.board[this.origin.x][this.origin.y + (i * (Math.abs(differenceY)/differenceY) * -1)] != 0)
								return false;
					}
					else
						return false;
						
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
	 * Combination of the rookMove and bishopMove method to determine if a Queen can perform this move
	 * 
	 * @param game the game the move is made in
	 * @return boolean that indicates if the rook move is possible
	 */
	private boolean queenMove(Game game)
	{
		try
		{
			//if a rook or a bishop can perform a move a queen can too
			if (rookMove(game) || bishopMove(game))
				return true;
			return false;
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}

	/**
	 *  check if the move is possible if a king performers it
	 *  
	 * @param game the game the move is made in
	 * @return boolean that indicates if the king move is possible
	 */
	private boolean kingMove(Game game)
	{
		int differenceX = Math.abs(this.origin.x - this.destination.x);
		int differenceY = Math.abs(this.origin.y - this.destination.y);
		
		//a King can move a maximum of one square in each direction
		if (differenceX <= 1 && differenceY <= 1 && game.board[this.destination.x][this.destination.y] * game.player <= 0)
			return true;
		return false;
	}
}