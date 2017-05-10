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
import javafx.scene.control.Label;

public class EndscreenController {
	@FXML
	Label endMessage;
	MainWindowController mainWindowController;

	private Main main;

	@FXML
	private void backToMatch(ActionEvent e)
	{
		((Button)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	private void restart(ActionEvent e)
	{
		mainWindowController.restart();
		((Button)e.getSource()).getScene().getWindow().hide();
	}

	@FXML
	private void closeAll(ActionEvent e)
	{
		System.exit(0);
	}

	public void setText(String text)
	{
		endMessage.setText(text);
	}

	public void setWindowController(MainWindowController controller)
	{
		this.mainWindowController = controller;
	}
}
