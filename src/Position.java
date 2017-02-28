
/**
 * class to save the position
 * 
 * @author Marcel
 *
 */
public class Position {
	
	public Position()
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
}