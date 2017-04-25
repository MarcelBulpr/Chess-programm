package Core;
import java.awt.Point;
import java.util.ArrayList;

/**
 * class to save the position
 *
 * @author Marcel, Kevin.K
 *
 */
public class Position {

	/**
	 * constructor for default position
	 *
	 * @param handicap remove the pieces that are specified (from queen to King side, the f pawn is the first that is removed)
	 */
	public Position(int... handicap)
	{
		try
		{
			this.board = new int[8][8];
			//place the pawns
			for (int i = 0; i < 8; i++)
			{
				this.board[i][6] = 1;
				this.board[i][1] = -1;
			}

			//place rooks
			this.board[0][7]=4;
			this.board[7][7]=4;
			this.board[0][0]=-4;
			this.board[7][0]=-4;

			//place knights
			this.board[1][7]=2;
			this.board[6][7]=2;
			this.board[1][0]=-2;
			this.board[6][0]=-2;

			//place bishops
			this.board[2][7]=3;
			this.board[5][7]=3;
			this.board[2][0]=-3;
			this.board[5][0]=-3;

			//place queens
			this.board[3][7]=5;
			this.board[3][0]=-5;

			//place Kings
			this.board[4][7]=6;
			this.board[4][0]=-6;

			this.castle = 1111;
			this.player = 1;

			for (int h:handicap)
			{
				//white
				int row = 6;
				//black
				if (h<0)
					row = 1;

				if (Math.abs(h) == 1)
				{
					if (Math.abs(this.board[5][row]) == 1)
					{
						this.board[5][row] = 0;
						break;
					}

					for (int i = 0; i <= 7; i++)
					{
						if (Math.abs(this.board[i][row]) == 1)
						{
							this.board[i][row] = 0;
							break;
						}
					}
				}

				//white
				row = 7;
				//black
				if (h<0)
					row = 0;

				for (int i = 0; i <= 7; i++)
				{
					if (this.board[i][h] == 1)
					{
						this.board[i][h] = 0;
						break;
					}
				}
			}
		}
		catch (Error r)
		{
			System.out.print(r.getMessage());
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
	int[][]board;

	/**
	 * variable that indicates if one player can castle
	 *
	 * ws 1000
	 * bs 0100
	 * wl 0010
	 * bl 0001
	 */
	int castle;

	/**
	 * the player that has to move. positive = white to move. negative = black to move.
	 */
	int player;

	/**
	 * check if a specific player can castle
	 *
	 * @param castleType w/b=color; s/l=short long (ws bs wl bl)
	 * @return if the castle is allowed
	 */
	public boolean canCastle(String castleType)
	{
		try
		{
			//if white can castle short
			if (castleType.toLowerCase() == "ws" || castleType.toLowerCase() == "whiteshort")
				if (this.castle >= 1000)
					return true;

			//if black can castle short
			if (castleType.toLowerCase() == "bs" || castleType.toLowerCase() == "blackshort")
				if (this.castle%1000 >= 100)
					return true;

			//if white can castle long
			if (castleType.toLowerCase() == "wl" || castleType.toLowerCase() == "whitelong")
				if (this.castle%100 >= 10)
					return true;

			//if black can castle long
			if (castleType.toLowerCase() == "bl" || castleType.toLowerCase() == "blacklong")
				if (this.castle%10 >= 1)
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
	 * check if a specific player can castle
	 *
	 * @param player the player that wants to castle
	 * @param direction the direction the player want to castle to (l/s)
	 * @return if the castle is allowed
	 */
	public boolean canCastle(int player, String direction)
	{
		try
		{
			if (player == 1)
			{
				//if white can castle short
				if (direction.toLowerCase() == "s" || direction.toLowerCase() == "short")
					if (this.castle >= 1000)
						return true;

				//if white can castle long
				if (direction.toLowerCase() == "l" || direction.toLowerCase() == "long")
					if (this.castle%100 >= 10)
						return true;

			}
			else
			{
				//if black can castle short
				if (direction.toLowerCase() == "s" || direction.toLowerCase() == "short")
					if (this.castle%1000 >= 100)
						return true;

				//if black can castle long
				if (direction.toLowerCase() == "l" || direction.toLowerCase() == "long")
					if (this.castle%10 >= 1)
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
//			test debug = new test();
//			debug.printPosition(this);

			ArrayList<Point> kingpositions = this.getPositions(6*this.player);
			//check if no king is found
			if (kingpositions.size() < 1)
				return false;

			Point kingposition = kingpositions.get(0);

			//change the player so that the opponents moves get checked
			this.player *= -1;

			for (int i = 0; i < this.board.length; i++)
				for (int j = 0; j < this.board[i].length; j++)
					//if a piece of the player that is to move is on the field
					if (this.player * this.board[i][j] > 0)
						//check if the piece can move to the King
						if(new Move(this, new Point(i,j), kingposition, false).isPossible(this))
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
			for (int i = 0; i < this.board.length; i++)
			{
				for (int j = 0; j < this.board[i].length; j++)
				{
					//check if a piece is located on the square
					if (this.board[i][j] * this.player > 0 && Math.abs(this.board[i][j]) != 7)
					{
						//check if the piece can move to any square on the board
						for (int k = 0; k < this.board.length; k++)
						{
							for (int l = 0; l < this.board[k].length; l++)
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
	 * copy a position in a different ram address
	 *
	 * @param position
	 */
	public void copy(Position position)
	{
		//copy each square value
		for (int i = 0; i < position.board.length; i++)
			for (int j = 0; j < position.board[i].length; j++)
				this.board[i][j] = position.board[i][j];

		//copy the player that has to move
		this.player = position.player;
		//copy castle rights
		this.castle = position.castle;
	}

	/**
	 * check if the current player is mate
	 *
	 * @return true = he is mate false = he is not
	 */
	public boolean isMate()
	{
		try
		{
			if (isCheck() && canMove() == false)
				return true;

			return false;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
			return false;
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
			if (this.board[move.destination.x][move.destination.y]==-7*this.player)
			{
				//the field the piece came from must be empty after the move
				this.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.board[move.destination.x][move.destination.y] = move.afterPiece;
				//take pawn
				this.board[move.destination.x][move.destination.y+this.player] = 0;
			}

			//clear the board from en passant fields
			for (int i = 0; i < this.board.length; i++)
				for (int j = 0; j < this.board[i].length; j++)
					if (Math.abs(this.board[i][j]) == 7)
						this.board[i][j] = 0;

			//if a King castles
			if (Math.abs(move.piece) == 6 && Math.abs(move.origin.x - move.destination.x)==2)
			{
				//the field the piece came from must be empty after the move
				this.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.board[move.destination.x][move.destination.y] = move.afterPiece;

				//check if the king castled long
				if (move.destination.x == 2)
				{
					//remove rook
					this.board[0][move.destination.y] = 0;
					//place rook next to King
					this.board[3][move.destination.y] = 4*this.player;
				}
				//check if the king castled short
				else if (move.destination.x == 6)
				{
					//remove rook
					this.board[7][move.destination.y] = 0;
					//place rook next to King
					this.board[5][move.destination.y] = 4*this.player;
				}
			}
			//if a pawn moves 2 squares
			else if(Math.abs(move.piece) == 1 && Math.abs(move.origin.y - move.destination.y) == 2 && Math.abs(move.origin.x - move.destination.x) == 0)
			{
				//remove the pawn
				this.board[move.origin.x][move.origin.y] = 0;
				//add en passant square
				this.board[move.origin.x][move.destination.y + this.player] = 7 * this.player;
				//set pawn to new location
				this.board[move.destination.x][move.destination.y] = move.afterPiece;
			}
			else
			{
				//the field the piece came from must be empty after the move
				this.board[move.origin.x][move.origin.y]=0;
				//that the piece to the destination
				this.board[move.destination.x][move.destination.y] = move.afterPiece;
			}


			//change the player to move
			this.player *= -1;

			return true;
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
			return false;
		}
	}

	/**
	 * This program gives the value of the characters that are on the board back in an Array
	 * where the first position in the Array is the counter for Black and the second position is for white
	 */
	public int[] materialCounter() {

		//Set the value to zero and generates an array with a maximum space of two
		int black = 0, white = 0;
		int materialcount [] = new int [2];

		//Two "for" loops to get through the whole board from A1 to H8
		for (int i = 0; i <= 7; i++)
		{
			for (int j = 0; j <= 7; j++)
			{
				//this.board is to get Information from the class board
				switch (this.board[i][j])
				{
				//It checks what charakter is on the field and if there is an charakter he will add on the Number of points that is the charakter worth
				case 1: white += 1; break;
				case 2: white += 3; break;
				case 3: white += 3; break;
				case 4: white += 5; break;
				case 5: white += 9; break;

				case -1: black += 1; break;
				case -2: black += 3; break;
				case -3: black += 3; break;
				case -4: black += 5; break;
				case -5: black += 9; break;
				}
			}
		}
		// Saves the number of points in the array
		// 0 for black and 1 for white
		materialcount[0] = black;
		materialcount[1] = white;
		return materialcount;
	}

}