//@author Nils Kruse 
package GUI;

import java.io.IOException;

import javafx.application.Application; //Importing the fxml libraries
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage; // Defining the stage 
	private Pane mainLayout;    // and the main Layout
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Chess");
		showMainView();
		
	}

	private void showMainView() throws IOException { //Function to make the fxml code able to load
	FXMLLoader loader = new FXMLLoader(); 
	loader.setLocation(Main.class.getResource("view/MainView.fxml"));
	mainLayout = loader.load();		
	Scene scene = new Scene(mainLayout); //definition of what will be loaded
	primaryStage.setScene(scene);
	primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
