package GUI;

import java.io.IOException;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Class to load/display the GUI
 *
 * @author Nils Kruse
 *
 */
public class Main extends Application {
	/**
	 *
	 * @param Setting 'Pane' as the main layout
	 */
	private Stage primaryStage;
	private Pane mainLayout;
	 /**
	  * @param Setting 'Stage' as the primary stage
	  */
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Chess");
		showMainView();

	}

	private void showMainView() throws IOException { //Function to make the fxml code loadable
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(Main.class.getResource("view/MainView.fxml"));// choosing the fxml-file which will be laoded

	mainLayout = loader.load();

	MainWindowController mainWindowController = loader.getController();//function to load the Controller class
	mainWindowController.setMain(this);
	 /**
	  * @param creating a scene which will be the base for everything that will be displayed
	  */
	Scene scene = new Scene(mainLayout);
	primaryStage.setScene(scene);
	primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
