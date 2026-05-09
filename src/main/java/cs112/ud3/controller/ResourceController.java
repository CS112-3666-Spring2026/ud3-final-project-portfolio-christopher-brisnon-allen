package cs112.ud2.controller;

import cs112.ud2.manager.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * ResourceController.java
 * Controls the live resource bar
 */
public class ResourceController {
    /********** CONSTANTS **********/
    @FXML
    private Label creditValueLabel;
    @FXML
    private Label energyValueLabel;
    @FXML
    private Label salvageValueLabel;
    @FXML
    private Label mineralValueLabel;
    @FXML
    private Label fleetCapacityValueLabel;

    private GameManager gameManager;

    // Setters for updating resources from GameplayController or other controllers when needed
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        updateAllResources();        // Initial update
    }

    /**
     * Call this whenever resources change
     */
    public void updateAllResources() {
        if (gameManager == null) return;

        var res = gameManager.getResources();

        creditValueLabel.setText(String.valueOf(res.getCredits()));
        energyValueLabel.setText(String.valueOf(res.getEnergy()));
        salvageValueLabel.setText(String.valueOf(res.getSalvage()));
        mineralValueLabel.setText(String.valueOf(res.getMinerals()));
        fleetCapacityValueLabel.setText(res.getFleetCapacity() + "");
    }
}