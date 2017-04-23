package Core;
import java.awt.Point;


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
	 * @param promotion the piece a pawn promoted to
	 */
	public Move(Game game, Point origin, Point destination, int promotion, boolean...fillVariables)
	{
		assert fillVariables.length <= 1;
		try
		{
			this.origin = origin;
			this.destination = destination;
			this.piece = game.position.board[origin.x][origin.y];
			this.afterPiece = promotion;

			if (fillVariables.length == 0 || fillVariables[0] == true)
			{
				if (game.position.board[destination.x][destination.y] != 0 && Math.abs(game.position.board[destination.x][destination.y]) < 7)
					this.took = true;
				else
					this.took = false;

				//create a new position to test the specific variables of a move
				//this is done to minimize computing power in later use (like the Notation)
				Position position = new Position();
				position.copy(game.position);
				position.makeMove(this);

				this.check = position.isCheck();
				this.mate = position.isMate();
			}
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * Builder to create a move out of a game an origin and an destination
	 *
	 * @param game the game where the move is made
	 * @param origin the position the piece came from
	 * @param destination the position the piece went to
	 */
	public Move(Game game, Point origin, Point destination, boolean... fillVariables)
	{
		assert fillVariables.length <= 1;
		try
		{
			this.origin = origin;
			this.destination = destination;
			this.piece = game.position.board[origin.x][origin.y];
			this.afterPiece = this.piece;

			if (fillVariables.length == 0 || fillVariables[0] == true)
			{
				if (game.position.board[destination.x][destination.y] != 0 && Math.abs(game.position.board[destination.x][destination.y]) < 7)
					this.took = true;
				else
					this.took = false;

				//create a new position to test the specific variables of a move
				//this is done to minimize computing power in later use (like the Notation)
				Position position = new Position();
				position.copy(game.position);
				position.makeMove(this);

				this.check = position.isCheck();
				this.mate = position.isMate();
			}
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * Builder to create a move out of a position an origin and an destination
	 *
	 * @param position the position where the move is made
	 * @param origin the position the piece came from
	 * @param destination the position the piece went to
	 * @param promotion the piece a pawn promoted to
	 */
	public Move(Position position, Point origin, Point destination, int promotion, boolean... fillVariables)
	{
		assert fillVariables.length <= 1;
		try
		{
			this.origin = origin;
			this.destination = destination;
			this.piece = position.board[origin.x][origin.y];
			this.afterPiece = promotion;
			if (fillVariables.length == 0 || fillVariables[0] == true)
			{
				if (position.board[destination.x][destination.y] != 0 && Math.abs(position.board[destination.x][destination.y]) < 7)
					this.took = true;
				else
					this.took = false;

				//create a new position to test the specific variables of a move
				//this is done to minimize computing power in later use (like the Notation)
				Position testPosition = new Position();
				testPosition.copy(testPosition);
				testPosition.makeMove(this);

				this.check = testPosition.isCheck();
				this.mate = testPosition.isMate();
			}
		}
		catch (Error r)
		{
			System.out.println(r.getMessage());
		}
	}

	/**
	 * Builder to create a move out of a game an origin and an destination
	 *
	 * @param game the game where the move is made
	 * @param origin the position the piece came from
	 * @param destination the position the piece went to
	 */
	public Move(Position position, Point origin, Point destination, boolean... fillVariables)
	{
		assert fillVariables.length <= 1;
		try
		{
			this.origin = origin;
			this.destination = destination;
			this.piece = position.board[origin.x][origin.y];
			this.afterPiece = this.piece;

			if (fillVariables.length == 0 || fillVariables[0] == true)
			{
				if (position.board[destination.x][destination.y] != 0 && Math.abs(position.board[destination.x][destination.y]) < 7)
					this.took = true;
				else
					this.took = false;

				//create a new position to test the specific variables of a move
				//this is done to minimize computing power in later use (like the Notation)
				Position testPosition = new Position();
				testPosition.copy(testPosition);
				testPosition.makeMove(this);

				this.check = testPosition.isCheck();
				this.mate = testPosition.isMate();
			}
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
	boolean took;

	/**
	 * the piece type that moved
	 */
	int piece;

	/**
	 * indicates the piece type after the move (relevant if a pawn promotes)
	 */
	int afterPiece;

	/**
	 * check if a move would be legal
	 *
	 * @param game the game in witch the  move should be executed
	 * @return boolean if the move is legal
	 */
	public boolean isLegal(Position position)
	{
		try
		{
			if (this.isPossible(position))
			{
				boolean success = true;

				//check if a King castled
				if (Math.abs(this.piece)==6 && Math.abs(this.origin.x - this.destination.x) == 2)
				{
					success = false;
					if (this.destination.x == 2)
						if (new Move(position,new Point(this.origin), new Point(3, this.destination.y)).isLegal(position))
							success=true;
					if (this.destination.x == 6)
						if (new Move(position,new Point(this.origin), new Point(5, this.destination.y)).isLegal(position))
							success=true;
				}

				//test if it is check after the move was executed
				Position testPosition = new Position();
				testPosition.copy(position);

				testPosition.makeMove(this, false);
				//change the player so the isCheck method checks for the right player
				testPosition.player*=-1;

				if (testPosition.isCheck() == false && success)
				{
					return true;
				}
				else
					return false;
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
	 * Check if a move is possible
	 *
	 * @param game the game the move is made in
	 * @return boolean that indicates if the move is possible
	 */
	public boolean isPossible(Position position)
	{
		try
		{
			//get the piece that is moved
			int piece = position.board[this.origin.x][this.origin.y];

			switch (Math.abs(piece))
			{
				//check witch kind of piece stands on the origin field
				case 1: return pawnMove(position);
				case 2: return knightMove(position);
				case 3: return bishopMove(position);
				case 4: return rookMove(position);
				case 5: return queenMove(position);
				case 6: return kingMove(position);
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
	private boolean pawnMove(Position position)
	{
		try
		{
			//if the piece is the right color
			if (position.board[this.origin.x][this.origin.y] * position.player > 0)
			{
				//if the pawn moved one square in the direction of the opponent
				if (this.origin.y - this.destination.y == position.player)
				{
					//if the pawn moves straight
					if (this.origin.x == this.destination.x)
						//if the destination has no piece on it (7 = en passant tag)
						if (position.board[this.destination.x][this.destination.y] == 0 || Math.abs(position.board[this.destination.x][this.destination.y]) == 7)
							return true;
						else
							return false;
					else
					//if the pawn moves diagonally (taking)
					if (Math.abs(this.origin.x - this.destination.x) == 1)
						//if the destination has an enemy piece on it
						if (position.board[this.destination.x][this.destination.y] * position.player < 0)
							return true;
						else
							return false;
					else
						return false;

				}
				else if (this.origin.y - this.destination.y == position.player * 2)
				{
					//if the pawn moves straight
					if (this.origin.x == this.destination.x)
						//if the destination has no piece on it and if the path is clear
						if (position.board[this.destination.x][this.destination.y] == 0 && new Move(position, new Point(this.origin.x, this.origin.y), new Point(this.destination.x, this.destination.y + position.player)).isPossible(position))
							return true;
						else
							return false;
					else
						return false;
				}
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
	private boolean knightMove(Position position)
	{
		try
		{
			int differenceX = Math.abs(this.origin.x - this.destination.x);
			int differenceY = Math.abs(this.origin.y - this.destination.y);

			//the difference in the x and y position (must be three)
			int difference = differenceX + differenceY;

			//check if the difference is three and if the right colored piece wants to move
			if (difference == 3 && position.board[this.origin.x][this.origin.y] * position.player > 0)
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
	private boolean bishopMove(Position position)
	{
		int differenceX = this.origin.x - this.destination.x;
		int differenceY = this.origin.y - this.destination.y;

		//the difference in both axis must be the same and if the destination is empty or an enemy piece and the piece has the right color
		if (Math.abs(differenceX) == Math.abs(differenceY) && position.board[this.destination.x][this.destination.y]*position.player <= 0 && position.board[this.origin.x][this.origin.y] * position.player > 0)
		{
			//check the path to the destination and check if it is free
			for (int i = 1; i < Math.abs(differenceX); i++)
				if (position.board[this.origin.x + (i * (Math.abs(differenceX)/differenceX) * -1)][this.origin.y + (i * (Math.abs(differenceY)/differenceY) *-1)] != 0 && Math.abs(position.board[this.origin.x + (i * (Math.abs(differenceX)/differenceX) * -1)][this.origin.y + (i * (Math.abs(differenceY)/differenceY) *-1)]) != 7)
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
	private boolean rookMove(Position position)
	{
		try
		{
			int differenceX = this.origin.x - this.destination.x;
			int differenceY = this.origin.y - this.destination.y;

			//a rook can only move on one axis at a time and if the destination is empty or an enemy piece
			if ((differenceX == 0 || differenceY == 0) && position.board[this.destination.x][this.destination.y]*position.player <= 0 && position.board[this.origin.x][this.origin.y] * position.player > 0)
			{
					//check in witch direction the rook is moving
					if (differenceX != 0)
					{
						//check if the path is free
						for (int i = 1; i < Math.abs(differenceX); i++)
							if (position.board[this.origin.x + (i * (Math.abs(differenceX)/differenceX) * -1)][this.origin.y] != 0 && Math.abs(position.board[this.origin.x][this.origin.y + (i * (Math.abs(differenceX)/differenceX) * -1)]) != 7)
								return false;
					}
					else if (differenceY != 0)
					{
						//check if the path is free
						for (int i = 1; i < Math.abs(differenceY); i++)
							if (position.board[this.origin.x][this.origin.y + (i * (Math.abs(differenceY)/differenceY) * -1)] != 0 && Math.abs(position.board[this.origin.x][this.origin.y + (i * (Math.abs(differenceY)/differenceY) * -1)]) != 7)
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
	private boolean queenMove(Position position)
	{
		try
		{
			//if a rook or a bishop can perform a move a queen can too
			if (rookMove(position) || bishopMove(position))
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
	private boolean kingMove(Position position)
	{
		try
		{
			int differenceX = Math.abs(this.origin.x - this.destination.x);
			int differenceY = Math.abs(this.origin.y - this.destination.y);

			//a King can move a maximum of one square in each direction and the piece that wants to move is from the player that is to move
			if (differenceX <= 1 && differenceY <= 1 && position.board[this.destination.x][this.destination.y] * position.player <= 0 && position.board[this.origin.x][this.origin.y] * position.player > 0)
				return true;

			//check if the King tries to castle and if the destination is empty
			if (differenceX == 2 && differenceY == 0 && position.board[this.destination.x][this.destination.y] == 0 && position.board[this.origin.x][this.origin.y] * position.player > 0)
			{
					//if the player castles long
					if (this.destination.x == 2)
					{
						//if the path is free
						if (position.board[this.destination.x+1][this.destination.y] == 0)
							//is castle still allowed
							if (position.canCastle(position.player, "l"))
								return true;
					}
					//if the player wants to castle short
					else if (this.destination.x == 6)
						//if the path is free
						if (position.board[this.destination.x-1][this.destination.y] == 0)
							//is castle still allowed
							if (position.canCastle(position.player, "s"))
								return true;
			}
			return false;
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}
}