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

public class RestartController {
	private Main main;
	MainWindowController mainWindowController;

	@FXML
	private void yes(ActionEvent e)
	{
		mainWindowController.restart();
		((Button)e.getSource()).getScene().getWindow().hide();
	}

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
