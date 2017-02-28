
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
}