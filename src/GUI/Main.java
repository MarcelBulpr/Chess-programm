package GUI;

import java.io.IOException;

import GUI.view.EndscreenController;
import GUI.view.PromotionController;
import GUI.view.RestartController;
import GUI.view.TutorialController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Class to load/display the GUI
 *
 * @author Nils Kruse, Marcel Baumann
 *
 */
public class Main extends Application {
	/**
	 *
	 * @param Setting 'Pane' as the main layout
	 */
	private Stage primaryStage;
	private static Pane mainLayout;
	private MainWindowController mainWindowController;
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

	mainWindowController = loader.getController();//function to load the Controller class
	mainWindowController.setMain(this);
	 /**
	  * @param creating a scene which will be the base for everything that will be displayed
	  */
	Scene scene = new Scene(mainLayout);
	primaryStage.setScene(scene);
	primaryStage.show();

	}
	/**
	 * Loading promotion when needed
	 * @throws IOException
	 */
	public void showPromotionScene() throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("promotion/Promotion.fxml"));
		AnchorPane root = loader.load();
		PromotionController promotionController = loader.getController();
		System.out.print(mainWindowController.toString());
		promotionController.setWindowController(mainWindowController);
		Scene scene = new Scene(root);
		Stage promotion = new Stage();
		promotion.initModality(Modality.APPLICATION_MODAL);
		promotion.initStyle(StageStyle.UNIFIED);
		promotion.setTitle("Promotion");
		promotion.setScene(scene);
		promotion.showAndWait();
		}
/**
 * loading tutorial when needed
 * @throws IOException
 */
 public void showTutorialScene()throws IOException {
	 FXMLLoader loader = new FXMLLoader();
	 loader.setLocation(Main.class.getResource("Tutorial/Tutorial.fxml"));
	 TabPane root = loader.load();
		Scene scene = new Scene(root);
		Stage Tutorial = new Stage();
		Tutorial.setTitle("Tutorial");
		Tutorial.setScene(scene);
		Tutorial.show();
 }
 /**
  *  loading Endscreen when needed
  * @param whiteWon
  * @param blackWon
  * @throws IOException
  */
 public void showEndscreenScene(boolean whiteWon, boolean blackWon)throws IOException {
	 FXMLLoader loader = new FXMLLoader();
	 loader.setLocation(Main.class.getResource("Endscreen/Endscreen.fxml"));
	 AnchorPane root = loader.load();
	 EndscreenController controller = loader.<EndscreenController>getController();
	 if (whiteWon)
		 controller.setText("White won!");
	 else if (blackWon)
		 controller.setText("Black won!");
	 else
		 controller.setText("Remise");
	 controller.setWindowController(mainWindowController);
	Stage Endscreen = new Stage();
	Scene scene = new Scene(root);
	Endscreen.setTitle("Endscreen");
	Endscreen.setScene(scene);
	Endscreen.show();
 }
 /**
  * loading Restartscene when needed
  * @throws IOException
  */
	 public void showRestartScene()throws IOException {
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(Main.class.getResource("Restart/Restart.fxml"));
		 AnchorPane root = loader.load();
		 RestartController controller = loader.<RestartController>getController();
		 controller.setController(mainWindowController);
			Scene scene = new Scene(root);
			Stage Restart = new Stage();
			Restart.setTitle("Restart?");
			Restart.setScene(scene);
			Restart.show();
	 }
	public static void main(String[] args) {
		launch(args);
	}
}
