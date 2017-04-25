package GUI;
import Core.*;
import java.awt.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * Class for events in the GUI
 *
 * @author Nils Kruse / Marcel Baumann
 *
 */
public class MainWindowController {
private Point firstCoordinate;
private Game game = new Game();
	private Main main;

	public void setMain(Main main){
		this.main = main;

	}
	/**
	 * Event when a button is clicked
	 *@param getting the ID of the clicked button  
	 */
	@FXML
	private void Button(ActionEvent e){
		Point coordinates = game.getArrayCoordinates(((Control)e.getSource()).getId());

		if (firstCoordinate == null)  //checking if the first button is clicked 
			firstCoordinate = coordinates;
		else
		{
			Move move = new Move(game, firstCoordinate, coordinates);
			if(game.makeMove(move))
			{
				GridPane gridPane = (GridPane) ((Control)e.getSource()).getParent();
				Node image = null;
				/**
				 * MISSING COMMENT 
				 */
				for (Node node : gridPane.getChildren())
					if (node instanceof ImageView && nullToZero(GridPane.getRowIndex(node)) == firstCoordinate.y && nullToZero(GridPane.getColumnIndex(node)) == firstCoordinate.x)
					{
						image = node;
				 		break;
					}
				/**
				 * Checking the board if a figure was beaten with the last move and removing the beaten figure  
				 */
				for (Node imageZwei : gridPane.getChildren())
					if (imageZwei instanceof ImageView && nullToZero(GridPane.getRowIndex(imageZwei)) == coordinates.y && nullToZero(GridPane.getColumnIndex(imageZwei)) == coordinates.x)
					{
						gridPane.getChildren().remove(imageZwei); // removing the image of the beaten figure
						break;
					}
				if (image != null)
				{
					/**
					 * Removing the image of the moving figure and adding it to the new coordinate
					 */
					gridPane.getChildren().remove(image);				
					gridPane.add(image, coordinates.x, coordinates.y);

				}	
				
			}
			firstCoordinate = null;
		}
	}
	/**
	 *@param setting "null" to "0", so that the coordinate can be true  
	 */
	private int nullToZero(Integer value)
	{
		if (value == null)
			value = 0;

		return value;
	}

}
