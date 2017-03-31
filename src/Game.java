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
	 * Copy the game class to another ram address
	 *
	 * @param game the game in another ram address
	 */
	public void copy(Game game)
	{
		try
		{
			//copy the position
			this.position.copy(game.position);

			//add each move to the moves Array
			for (int i = 0; i < game.moves.size(); i++)
				this.moves.add(game.moves.get(i));
		}
		catch(Error r)
		{
			System.out.print(r.getMessage());
		}
	}

	public boolean makeMove(Move move)
	{
		try
		{
			if (move.isLegal(this.position))
			{
				this.position.makeMove(move);
				//add the move to the move history
				this.moves.add(move);
				return true;
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
	 * methodeto get the game coordinates (like d4) from array coordinates
	 *
	 * @param coordinates the coordinates from the array
	 * @return String containing a square name (like d4)
	 */
	public String getGameCoordinates(Point coordinates)
	{
		String gameCoordinates = "";
		try
		{
		//check the x row
		switch(coordinates.x)
		{
			case 0: gameCoordinates += "a"; break;
			case 1: gameCoordinates += "b"; break;
			case 2: gameCoordinates += "c"; break;
			case 3: gameCoordinates += "d"; break;
			case 4: gameCoordinates += "e"; break;
			case 5: gameCoordinates += "f"; break;
			case 6: gameCoordinates += "g"; break;
			case 7: gameCoordinates += "h"; break;
		}
		//calculate the column
		gameCoordinates += (8 - coordinates.y);
		return gameCoordinates;
		}
		catch(Exception r)
		{
			System.out.print(r.getMessage());
			return null;
		}
	}

	/**
	 * method to get the Array coordinates from a square name (like d4)
	 *
	 * @param coordinates the square name
	 * @return Point with the Array coordinates
	 */
	public Point getArrayCoordinates(String coordinates)
	{
		//check if the coordinates string really consists of 2 chars
		assert coordinates.length() == 2;

		Point arrayCoordinates = new Point();

		//check the letter (a-h)
		switch (coordinates.charAt(0)) {
			case 'a': arrayCoordinates.x = 0; break;
			case 'b': arrayCoordinates.x = 1; break;
			case 'c': arrayCoordinates.x = 2; break;
			case 'd': arrayCoordinates.x = 3; break;
			case 'e': arrayCoordinates.x = 4; break;
			case 'f': arrayCoordinates.x = 5; break;
			case 'g': arrayCoordinates.x = 6; break;
			case 'h': arrayCoordinates.x = 7; break;
		}
		//calculate the column
		arrayCoordinates.y = 7 - (Character.getNumericValue(coordinates.charAt(1))-1);

		return arrayCoordinates;
	}
}