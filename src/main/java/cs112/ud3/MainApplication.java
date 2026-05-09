package cs112.ud3;

import cs112.ud3.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Fleet Commander is a game where you explore nodes, collect resources, and fight
 * enemy ships!
 *
 * @author Christopher Brinson-Allen
 * @collaborators Nadia Arani
 * @version 05/09/2026
 */
public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view and get its controller
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/cs112/ud3/main-view.fxml")
        );
        
        BorderPane root = loader.load();
        MainController controller = loader.getController();

        // Set up the scene and stage
        Scene scene = new Scene(root, 860, 510);
        primaryStage.setTitle("Fleet Commander");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(860);
        primaryStage.setMinHeight(510);
        primaryStage.show();

        // Initialize game data
        if (controller != null) {
            controller.initializeData();
        }
    }
    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}