package GUI;
import Core.*;
import java.awt.Point;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Class for events in the GUI
 *
 * @author Nils Kruse
 *
 */
public class MainWindowController {
private Point firstCoordinate;
private Game game = new Game();
	private Main main;

	public void setMain(Main main){
		this.main = main;

	}
	 Stage stage;
	 Parent root;
	/**
	 * Event when a button is clicked
	 *@param getting the ID of the clicked button
	 * @throws IOException 
	 */
	@FXML
	private void Button(ActionEvent e) throws IOException{
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
				 * MISSING COMMENT!!
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
				 //Umwandlung hier einfügen
				for(Node node : gridPane.getChildren()){
				if(node instanceof board && [coordinates.x][7] = 1 )
				 {
				   stage = new Stage();
				   root = FXMLLoader.load(getClass().getResource("PromotionView.fxml"));
				   stage.setScene(new Scene(root));
				   stage.setTitle("Promotion");
				   stage.initModality(Modality.APPLICATION_MODAL);
				   //stage.initOwner(PromotionView.getScene().getWindow());
				   stage.showAndWait();
				 }
				}
			firstCoordinate = null;
			}
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
