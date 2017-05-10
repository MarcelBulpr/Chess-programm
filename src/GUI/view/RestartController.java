package GUI.view;

import GUI.MainWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 *Class that loads the functions of the Restart scene
 * @author Nils Kruse, Marcel Baumann
 */

public class RestartController {
	MainWindowController mainWindowController;
	//restart when yes is clicked
	@FXML
	private void yes(ActionEvent e)
	{
		mainWindowController.restart();
		((Button)e.getSource()).getScene().getWindow().hide();
	}
	//close the pop-up when no is clicked
	@FXML
	private void no(ActionEvent e)
	{
		((Button)e.getSource()).getScene().getWindow().hide();
	}

	public void setController(MainWindowController controller)
	{
		mainWindowController = controller;
	}
}
