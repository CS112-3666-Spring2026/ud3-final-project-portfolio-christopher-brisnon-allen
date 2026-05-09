package cs112.ud2.controller;

import cs112.ud2.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;
/**
 * FleetListController.java
 * Controls the list of ships in the fleet, handling selection and display.
 */
public class FleetListController {

    @FXML private Button shipInventoryButton00;
    @FXML private Button shipInventoryButton01;
    @FXML private Button shipInventoryButton02;
    @FXML private Button shipInventoryButton03;
    @FXML private Button shipInventoryButton04;
    @FXML private Button shipInventoryButton05;
    @FXML private Button shipInventoryButton06;
    @FXML private Button shipInventoryButton07;
    @FXML private Button shipInventoryButton08;
    @FXML private Button shipInventoryButton09;
    @FXML private Button shipInventoryButton10;
    @FXML private Button shipInventoryButton11;
    @FXML private Button shipInventoryButton12;
    @FXML private Button shipInventoryButton13;
    @FXML private Button shipInventoryButton14;

    private ActiveShipController activeShipController;
    private GameplayController gameplayController;
    private Ship currentlySelectedShip = null;
    private Button selectedButton = null;
    private Button[] shipButtons;

    public void setActiveShipController(ActiveShipController controller) {
        this.activeShipController = controller;
    }

    public void setGameplayController(GameplayController controller) {
        this.gameplayController = controller;
    }

    public void clearSelection() {
        currentlySelectedShip = null;
        selectedButton = null;
        if (activeShipController != null) {
            activeShipController.showNoSelection();
        }
    }

    public void selectShip(Ship ship) {
        if (ship == null || shipButtons == null) {
            return;
        }
        // The newest ship is added to the end of the fleet, so select the last visible slot.
        // Iterate backwards through the button array to find the last visible button
        for (int i = shipButtons.length - 1; i >= 0; i--) {
            Button button = shipButtons[i];
            if (button != null && button.isVisible()) {
                if (selectedButton != null) {
                    selectedButton.setStyle("");
                }
                selectedButton = button;
                currentlySelectedShip = ship;
                button.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;");

                if (activeShipController != null) {
                    activeShipController.updateWithShip(ship);
                }
                if (gameplayController != null) {
                    gameplayController.setCurrentlySelectedShip(ship);
                }
                return;
            }
        }
    }

    public void setFleet(List<Ship> fleet) {
        shipButtons = new Button[] { shipInventoryButton00, shipInventoryButton01, shipInventoryButton02,
                shipInventoryButton03, shipInventoryButton04, shipInventoryButton05,
                shipInventoryButton06, shipInventoryButton07, shipInventoryButton08,
                shipInventoryButton09, shipInventoryButton10, shipInventoryButton11,
                shipInventoryButton12, shipInventoryButton13, shipInventoryButton14 };

        // Reset all buttons
        for (Button btn : shipButtons) {
            btn.setVisible(false);
            btn.setGraphic(null);
            btn.setOnMouseClicked(null);
            btn.setStyle(""); // Clear any styling
        }

        // Clear selection when refreshing fleet
        currentlySelectedShip = null;
        selectedButton = null;

        // Populate buttons
        for (int i = 0; i < fleet.size() && i < shipButtons.length; i++) {
            Ship ship = fleet.get(i);
            Button button = shipButtons[i];

            ImageView icon = new ImageView();
            icon.setFitWidth(26);
            icon.setFitHeight(26);
            icon.setPreserveRatio(true);

            button.setGraphic(icon);
            button.setVisible(true);

            final Ship currentShip = ship;
            //logic for clicking on ship button and closing the active ship panel if the same ship is clicked again
            button.setOnMouseClicked((MouseEvent e) -> {
                if (activeShipController != null) {
                    if (selectedButton == button) {
                        // Toggle off - clicking the same button again
                        activeShipController.showNoSelection();
                        if (gameplayController != null) {
                            gameplayController.setCurrentlySelectedShip(null);
                        }
                        currentlySelectedShip = null;
                        selectedButton.setStyle(""); // Clear selection styling
                        selectedButton = null;
                    } else {
                        // Clear previous selection styling
                        if (selectedButton != null) {
                            selectedButton.setStyle("");
                        }
                        
                        // Show new ship
                        activeShipController.updateWithShip(currentShip);
                        currentlySelectedShip = currentShip;
                        selectedButton = button;
                        button.setStyle("-fx-border-color: #00ff00; -fx-border-width: 2;"); // Highlight selected
                    }
                }
            });
        }
    }
}