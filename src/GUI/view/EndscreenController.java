package GUI.view;

import GUI.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *Class that loads the functions of the endscreen scene
 * @author Nils Kruse, Marcel Baumann
 */
public class EndscreenController {
	@FXML
	Label endMessage;
	MainWindowController mainWindowController;

//Going back if the backToMatch Button is clicked
	@FXML
	private void backToMatch(ActionEvent e)
	{
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//restart if the restart Button is clicked
	@FXML
	private void restart(ActionEvent e)
	{
		mainWindowController.restart();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//leaving the game if the closeAll Button is clicked
	@FXML
	private void closeAll(ActionEvent e)
	{
		System.exit(0);
	}
	//Displays which player won
	public void setText(String text)
	{
		endMessage.setText(text);
	}

	public void setWindowController(MainWindowController controller)
	{
		this.mainWindowController = controller;
	}
}
