package GUI;
import Core.*;
import java.awt.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainWindowController {
private Point firstCoordinate;
private Game game = new Game();
	private Main main;

	public void setMain(Main main){
		this.main = main;

	}

	@FXML
	private void Button(ActionEvent e){
		Point coordinates = game.getArrayCoordinates(((Control)e.getSource()).getId());

		if (firstCoordinate == null)
			firstCoordinate = coordinates;
		else
		{
			Move move = new Move(game, firstCoordinate, coordinates);
			if(game.makeMove(move))
			{
				GridPane gridPane = (GridPane) ((Control)e.getSource()).getParent();
				Node image = null;
				for (Node node : gridPane.getChildren())
					if (node instanceof ImageView && nullToZero(GridPane.getRowIndex(node)) == firstCoordinate.y && nullToZero(GridPane.getColumnIndex(node)) == firstCoordinate.x)
					{
						image = node;
				 		break;
					}
				
				for (Node imageZwei : gridPane.getChildren())
					if (imageZwei instanceof ImageView && nullToZero(GridPane.getRowIndex(imageZwei)) == coordinates.y && nullToZero(GridPane.getColumnIndex(imageZwei)) == coordinates.x)
					{
						gridPane.getChildren().remove(imageZwei);
						break;
					}
				if (image != null)
				{
					gridPane.getChildren().remove(image);
					gridPane.add(image, coordinates.x, coordinates.y);

				}	
			}
			firstCoordinate = null;
		}
	}

	private int nullToZero(Integer value)
	{
		if (value == null)
			value = 0;

		return value;
	}

}
