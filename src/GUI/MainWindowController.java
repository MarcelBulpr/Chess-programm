package GUI;
import Core.*;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Nils Kruse, Marcel Baumann
 *
 * Class that control the different functionality of the GUI
 */
public class MainWindowController {
	//Images of the figures
	private URL wPawnDirectory = Main.class.getResource("/pawn-white.png");
	private URL wKnightDirectory = Main.class.getResource("/knight-white.png");
	private URL wBishopDirectory = Main.class.getResource("/bishop-white.png");
	private URL wRookDirectory = Main.class.getResource("/rook-white.png");
	private URL wQueenDirectory = Main.class.getResource("/queen-white.png");
	private URL wKingDirectory = Main.class.getResource("/King-white.png");

	private URL bPawnDirectory = Main.class.getResource("/pawn-black.png");
	private URL bKnightDirectory = Main.class.getResource("/knight-black.png");
	private URL bBishopDirectory = Main.class.getResource("/bishop-black.png");
	private URL bRookDirectory = Main.class.getResource("/rook-black.png");
	private URL bQueenDirectory = Main.class.getResource("/queen-black.png");
	private URL bKingDirectory = Main.class.getResource("/King-black.png");

	private Point firstCoordinate;
	private Point coordinates;
	private Game game = new Game();
	private boolean running = true;
	private Main main;

	public void setMain(Main main){
		this.main = main;

	}
	 Stage stage;
	 Parent root;

	@FXML
	Label mCountWhite;
	@FXML
	Label mCountBlack;
	@FXML
	Label Notation;
	@FXML
	GridPane gp;
	/**
	 * Event when a button is clicked
	 *@param getting the ID of the clicked button
	 * @throws IOException
	 */
	@FXML
	private void Button(ActionEvent e){
		if (running)
		{
			coordinates = game.getArrayCoordinates(((Control)e.getSource()).getId());
			if (firstCoordinate == null){  //checking if the first button is clicked
			firstCoordinate = coordinates;
			}
			else
			{
			/**
			 * Checking the board if a pawn is on the enemies first row and can be promoted
			 */
				Move move = new Move(game, firstCoordinate, coordinates);
				if(Math.abs(game.position.board[firstCoordinate.x][firstCoordinate.y]) == 1 && ((coordinates.y == 0 && game.position.player == 1) || (coordinates.y == 7 && game.position.player == -1)) && move.isLegal(game.position))
				 {
					try
					{
						main.showPromotionScene(); //
					}
					catch(IOException err)
					{
						System.out.print(err.getMessage());
						firstCoordinate = null;
					}
				 }
				else
				{
					execute(move);
				}
				//Materialcounter
				int materialcountwhite = game.position.materialCounter()[1];
				 mCountBlack.setText( materialcountwhite + "");

				 int materialcountblack = game.position.materialCounter()[0];
				 mCountWhite.setText(materialcountblack + "");
				 //Notation
				 String notationText = game.getNotation();
				 if (notationText != null)
					 Notation.setText(notationText + "");
			}
		}
	}
/**
 * Function that contains all the parts that need to be controlled after every move
 * @param move
 */
	public void execute(Move move)
	{
		if(game.makeMove(move))
		{
			drawboard();

			if (game.position.isMate())
			{
				if (game.position.player == 1)
					try {
						running = false;
						main.showEndscreenScene(false, true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (game.position.player == -1)
					try {
						running = false;
						main.showEndscreenScene(true, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if (game.position.canMove() == false)
			{
				try {
					running = false;
					main.showEndscreenScene(false, false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


		}
		firstCoordinate = null;
	}
/**
 * loads the Tutorial when needed
 * @param e
 */
	@FXML
	private void Tutorial(ActionEvent e){
		try{
			main.showTutorialScene();
		}
		catch (IOException err)
		{
			System.out.println(err.getMessage());
		}
	}

/**
 * loads the promotion when needed
 */
	public void queenPromotion()
	{
		Move move = new Move(game, firstCoordinate, coordinates,5);
		execute(move);
	}
	public void bishopPromotion()
	{
		Move move = new Move(game, firstCoordinate, coordinates,3);
		execute(move);
	}
	public void knightPromotion()
	{
		Move move = new Move(game, firstCoordinate, coordinates,2);
		execute(move);
	}
	public void rookPromotion()
	{
		Move move = new Move(game, firstCoordinate, coordinates,4);
		execute(move);
	}

	private void drawboard()
	{
		GridPane gridPane = gp;
		List<Node> images = new ArrayList<Node>();
		//Clear gridpane
		for (Node node : gridPane.getChildren())
			if (node instanceof ImageView)
				images.add(node);
		gridPane.getChildren().removeAll(images);

		URL path = null; //standart piece
		//draw board
		for (int i = 0; i < game.position.board.length; i++)
		{
			for (int j = 0; j < game.position.board.length; j++)
			{
				path = null;
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
				if (path != null)
				{
					//make it an Image
				    Image image = new Image(path.toString());
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
/**
 * setting everything to null before restart
 */
	public void restart()
	{
		running = true;
		game = new Game();
		Notation.setText("");
		drawboard();
	}

	@FXML
	private void OpenRestart (ActionEvent e)
	{
		try {
			main.showRestartScene();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
