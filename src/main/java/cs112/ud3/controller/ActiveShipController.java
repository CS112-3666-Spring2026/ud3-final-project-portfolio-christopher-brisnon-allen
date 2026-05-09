package cs112.ud2.controller;

import cs112.ud2.model.Ship;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
/**
 * ActiveShipController.java
 * Controls the display and updates for the active ship panel.
 */
public class ActiveShipController {
    @FXML
    private VBox activeShipView;

    @FXML
    private Label activeShipNameLabel;
    @FXML
    private ProgressBar hullBar;
    @FXML
    private Label hullValueLabel;

    // Stat Value Labels
    @FXML
    private Label activeShipStatCombatPowerValue;
    @FXML
    private Label activeShipStatMiningPowerValue;
    @FXML
    private Label activeShipStatSalvagePowerValue;
    @FXML
    private Label activeShipStatScoutPowerValue;

    private GameplayController gameplayController;

    public void setGameplayController(GameplayController controller) {
        this.gameplayController = controller;
    }
    public void showActiveShipPanel() {
        if (activeShipView != null) {
            activeShipView.setVisible(true);
        }
    }
    public void hideActiveShipPanel() {
        if (activeShipView != null) {
            activeShipView.setVisible(false);
        }
    }

    public void showNoSelection() {
        if (activeShipNameLabel != null) {
            activeShipNameLabel.setText("No Selection");
        }

        if (hullBar != null) {
            hullBar.setProgress(0.0);
        }

        if (hullValueLabel != null) {
            hullValueLabel.setText("-");
        }

        if (activeShipStatCombatPowerValue != null) {
            activeShipStatCombatPowerValue.setText("-");
        }
        if (activeShipStatMiningPowerValue != null) {
            activeShipStatMiningPowerValue.setText("-");
        }
        if (activeShipStatSalvagePowerValue != null) {
            activeShipStatSalvagePowerValue.setText("-");
        }
        if (activeShipStatScoutPowerValue != null) {
            activeShipStatScoutPowerValue.setText("-");
        }

        System.out.println("Active Ship Panel updated: No Selection");
        hideActiveShipPanel();
    }
        /**
         * Updates the Active Ship Panel with the selected ship's data
         */
        public void updateWithShip (Ship ship){

            if (ship == null) {
                showNoSelection();
                return;
            }

            // Basic Info
            if (activeShipNameLabel != null) {
                activeShipNameLabel.setText(ship.getName());
            }

            if (hullBar != null) {
                hullBar.setProgress(Math.min(1.0, ship.getHull() / 200.0));
            }

            if (hullValueLabel != null) {
                hullValueLabel.setText(ship.getHull() + " / 200"); // placeholder, need to adjust for currentHull / maxHull
            }

            // Update activeShipStatValues
            if (activeShipStatCombatPowerValue != null) {
                activeShipStatCombatPowerValue.setText(String.valueOf(ship.getCombatPower()));
            }
            if (activeShipStatMiningPowerValue != null) {
                activeShipStatMiningPowerValue.setText(String.valueOf(ship.getMiningPower()));
            }
            if (activeShipStatSalvagePowerValue != null) {
                activeShipStatSalvagePowerValue.setText(String.valueOf(ship.getSalvagePower()));
            }
            if (activeShipStatScoutPowerValue != null) {
                activeShipStatScoutPowerValue.setText(String.valueOf(ship.getScoutPower()));
            }
            System.out.println("Active Ship Panel updated: " + ship.getName());

            // Tell GameplayController which ship is selected
            if (gameplayController != null) {
                gameplayController.setCurrentlySelectedShip(ship);
            }

            showActiveShipPanel(); // Show the panel when updating with a ship
        }
}
