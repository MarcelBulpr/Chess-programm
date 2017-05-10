package GUI.view;

import java.io.IOException;

import GUI.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *Class that loads the functions of the promotion scene
 * @author Nils Kruse, Marcel Baumann
 */
public class PromotionController {
	MainWindowController mainWindowController;

//when promoted to the queen
	@FXML
	private void promQueen(ActionEvent e) throws IOException{
		mainWindowController.queenPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//when promoted to the bishop
	@FXML
	private void promBishop(ActionEvent e) throws IOException{
		mainWindowController.bishopPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//when promoted to the knight
	@FXML
	private void promKnight(ActionEvent e) throws IOException{

		mainWindowController.knightPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//when promoted to the Rook
	@FXML
	private void promRook(ActionEvent e) throws IOException{
		mainWindowController.rookPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}


	public void setWindowController(MainWindowController mainWindowController)
	{
		this.mainWindowController = mainWindowController;
	}
}
