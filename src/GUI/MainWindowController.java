package GUI;
import Core.*;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Nils Kruse, Marcel Baumann
 *
 * Class that control the different functionality of the GUI
 */
public class MainWindowController {
	private String wPawnDirectory = new File("").getAbsolutePath() + "\\bin\\pawn-white.png";
	private String wKnightDirectory = new File("").getAbsolutePath() + "\\bin\\knight-white.png";
	private String wBishopDirectory = new File("").getAbsolutePath() + "\\bin\\bishop-white.png";
	private String wRookDirectory = new File("").getAbsolutePath() + "\\bin\\rook-white.png";
	private String wQueenDirectory = new File("").getAbsolutePath() + "\\bin\\queen-white.png";
	private String wKingDirectory = new File("").getAbsolutePath() + "\\bin\\King-white.png";

	private String bPawnDirectory = new File("").getAbsolutePath() + "\\bin\\pawn-black.png";
	private String bKnightDirectory = new File("").getAbsolutePath() + "\\bin\\knight-black.png";
	private String bBishopDirectory = new File("").getAbsolutePath() + "\\bin\\bishop-black.png";
	private String bRookDirectory = new File("").getAbsolutePath() + "\\bin\\rook-black.png";
	private String bQueenDirectory = new File("").getAbsolutePath() + "\\bin\\queen-black.png";
	private String bKingDirectory = new File("").getAbsolutePath() + "\\bin\\King-black.png";

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
				List<Node> images = new ArrayList();
				//Clear gridpane
				for (Node node : gridPane.getChildren())
					if (node instanceof ImageView)
						images.add(node);
				gridPane.getChildren().removeAll(images);

				String path = "";
				//draw board
				for (int i = 0; i < game.position.board.length; i++)
				{
					for (int j = 0; j < game.position.board.length; j++)
					{
						path = "";
						//select piece type
						switch(game.position.board[i][j])
						{
							case 1: path = wPawnDirectory; break;
							case 2: path = wKnightDirectory; break;
							case 3: path = wBishopDirectory; break;
							case 4: path = wRookDirectory; break;
							case 5: path = wQueenDirectory; break;
							case 6: path = wKingDirectory; break;
							case -1: path = bPawnDirectory; break;
							case -2: path = bKnightDirectory; break;
							case -3: path = bBishopDirectory; break;
							case -4: path = bRookDirectory; break;
							case -5: path = bQueenDirectory; break;
							case -6: path = bKingDirectory; break;
						}

						//if a piece was selected
						if (path != "")
						{
							//create a new File
							File file = new File(path);
							//make it an Image
						    Image image = new Image(file.toURI().toString());
						    //put it in to an image view
						    ImageView iv = new ImageView(image);
						    //so we can click the buttons beneath
						    iv.setMouseTransparent(true);
						    //set the row and column it should appear in
						    GridPane.setRowIndex(iv, j);
						    GridPane.setColumnIndex(iv, i);
						    gridPane.getChildren().add(iv);
						}
					}
				}
			}
			firstCoordinate = null;
		}
	}
}
