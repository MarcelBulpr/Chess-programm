package GUI.view;

import java.io.IOException;

import Core.Game;
import Core.Move;
import GUI.Main;
import GUI.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PromotionController {
	private Main main;
	MainWindowController mainWindowController;
	@FXML
	private void promQueen(ActionEvent promQueen) throws IOException{
		mainWindowController.queenPromotion();
	}
	@FXML
	private void promBishop(ActionEvent promBishop) throws IOException{

	}
	@FXML
	private void promKnight(ActionEvent promKnight) throws IOException{


	}
	@FXML
	private void promRook(ActionEvent promRook) throws IOException{

	}

	public void setWindowController(MainWindowController mainWindowController)
	{
		this.mainWindowController = mainWindowController;
	}
}
