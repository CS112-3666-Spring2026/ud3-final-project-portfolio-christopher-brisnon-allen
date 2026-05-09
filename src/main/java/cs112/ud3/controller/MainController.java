package cs112.ud2.controller;

import cs112.ud2.manager.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import java.io.IOException;
/**
 * MainController.java
 * Loads views, and initializes data connections between controllers
 */
public class MainController {

    @FXML
    private StackPane mainContentArea;

    @FXML
    private FleetListController fleetListViewController;

    @FXML
    private ActiveShipController activeShipViewController;

    @FXML
    private ResourceController resourceViewController;

    private GameplayController gameplayController;

    @FXML
    public void initialize() throws IOException {
        loadGameplayView();
    }

    // Loads the gameplay view into the main content area and gets its controller
    private void loadGameplayView() throws IOException {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/cs112/ud2/gameplay-view.fxml")
            );

            javafx.scene.Parent gameplayView = loader.load();
            gameplayController = loader.getController();

            mainContentArea.getChildren().clear();
            mainContentArea.getChildren().add(gameplayView);
    }

    public void initializeData() {
        GameManager gm = GameManager.getInstance();

        // Connect Fleet List to Game Manager for initial data and updates
        if (fleetListViewController != null && activeShipViewController != null) {
            fleetListViewController.setActiveShipController(activeShipViewController);
            fleetListViewController.setFleet(gm.getPlayer().getShips());
        }

        // Make sure panel starts hidden
        if (activeShipViewController != null) {
            activeShipViewController.hideActiveShipPanel();
        }

        // Connect ActiveShipController to GameplayController
        if (activeShipViewController != null && gameplayController != null) {
            activeShipViewController.setGameplayController(gameplayController);
            gameplayController.setActiveShipController(activeShipViewController);
        }

        // Connect Resource Bar
        if (resourceViewController != null) {
            resourceViewController.setGameManager(gm);
        }

        // Connect Gameplay Controller
        if (gameplayController != null) {
            gameplayController.setGameManager(gm);
            gameplayController.setResourceController(resourceViewController);
            gameplayController.setFleetListController(fleetListViewController);
        }

        // Connect Fleet List to Gameplay Controller for proper selection clearing
        if (fleetListViewController != null && gameplayController != null) {
            fleetListViewController.setGameplayController(gameplayController);
        }
    }
}