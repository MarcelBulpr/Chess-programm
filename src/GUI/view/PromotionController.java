package GUI.view;

import java.io.IOException;

import Core.Game;
import Core.Move;
import GUI.Main;
import GUI.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.*;

public class PromotionController {
	MainWindowController mainWindowController;


	@FXML
	private void promQueen(ActionEvent e) throws IOException{
		mainWindowController.queenPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	@FXML
	private void promBishop(ActionEvent e) throws IOException{
		mainWindowController.bishopPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	@FXML
	private void promKnight(ActionEvent e) throws IOException{

		mainWindowController.knightPromotion();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
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
