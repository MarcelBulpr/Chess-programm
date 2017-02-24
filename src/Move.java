import com.sun.javafx.scene.paint.GradientUtils.Point;

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
			this.destiniation = destination;
			//code to be added
			//the code should take the game, the Points and the promotion and calculate the other values of this Class
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
	Point destiniation;
	
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
}