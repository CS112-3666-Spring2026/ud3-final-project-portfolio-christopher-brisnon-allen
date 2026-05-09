package cs112.ud2.controller;

import cs112.ud2.manager.GameManager;
import cs112.ud2.model.Ship;
import javafx.fxml.FXML;
/**
 * GamePlayController.java
 * Controls game state directly, currently debug test view primarily
 * Basically testing for game logic and UI integration.
 */
public class GameplayController {

    private GameManager gameManager;
    private ResourceController resourceController;
    private FleetListController fleetListController;
    private ActiveShipController activeShipController;
    private Ship currentlySelectedShip;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setResourceController(ResourceController resourceController) {
        this.resourceController = resourceController;
    }

    public void setFleetListController(FleetListController fleetListController) {
        this.fleetListController = fleetListController;
    }

    public void setActiveShipController(ActiveShipController activeShipController) {
        this.activeShipController = activeShipController;
    }

    public void setCurrentlySelectedShip(Ship ship) {
        this.currentlySelectedShip = ship;
    }

    private void refreshAll() {
        refreshResources();
        refreshFleet();
    }

    private void refreshResources() {
        if (resourceController != null) {
            resourceController.updateAllResources();
        }
    }

    private void refreshFleet() {
        if (fleetListController != null && gameManager != null) {
            fleetListController.setFleet(gameManager.getPlayer().getShips());
        }
    }
    /********** RESOURCES BUTTONS **********/

    @FXML
    public void addMinerals() {
        if (gameManager != null) {
            gameManager.addMinerals(100);
            refreshResources();
        }
    }

    @FXML
    public void addSalvage() {
        if (gameManager != null) {
            gameManager.addSalvage(100);
            refreshResources();
        }
    }

    @FXML
    public void addEnergy() {
        if (gameManager != null) {
            gameManager.addEnergy(100);
            refreshResources();
        }
    }

    @FXML
    public void addCredits() {
        if (gameManager != null) {
            gameManager.addCredits(200);
            refreshResources();
        }
    }

    @FXML
    public void subtractMinerals() {
        if (gameManager != null) {
            gameManager.spendMinerals(50);
            refreshResources();
        }
    }

    @FXML
    public void subtractSalvage() {
        if (gameManager != null) {
            gameManager.spendSalvage(50);
            refreshResources();
        }
    }
    @FXML
    public void subtractEnergy() {
        if (gameManager != null) {
            gameManager.spendEnergy(50);
            refreshResources();
        }
    }
    @FXML
    public void subtractCredits() {
        if (gameManager != null) {
            gameManager.spendCredits(50);
            refreshResources();
        }
    }
    /********** ADD/REMOVE SHIP BUTTONS **********/
    @FXML
    public void addShip() {
        if (gameManager != null && gameManager.canAddShip()) {
            Ship newShip = Ship.randomShip();
            gameManager.addShip(newShip);
            System.out.println("Added new ship");
            refreshAll();
            if (fleetListController != null) {
                fleetListController.selectShip(newShip);
            }
        } else {
            System.out.println("Cannot add ship - Fleet is full");
        }
    }
    @FXML
    public void removeSelectedShip() {
        if (gameManager != null && currentlySelectedShip != null) {
            String shipName = currentlySelectedShip.getName();
            
            if (gameManager.removeShip(currentlySelectedShip)) {
                System.out.println("Removed ship: " + shipName);
                currentlySelectedShip = null;
                
                // Clear selection from the fleet list view
                if (fleetListController != null) {
                    fleetListController.clearSelection();
                }
                
                // Hide the panel
                if (activeShipController != null) {
                    activeShipController.hideActiveShipPanel();
                }
                
                refreshFleet(); // Only refresh the fleet, not resources
            }
        } else {
            System.out.println("No ship selected to remove");
        }
    }

    @FXML
    public void printFleet() {
        if (gameManager != null) {
            System.out.println("=== Current Fleet ===");
            gameManager.getPlayer().getShips().forEach(System.out::println);
            System.out.println("Total ships: " + gameManager.getPlayer().getShips().size());
        }
    }
}