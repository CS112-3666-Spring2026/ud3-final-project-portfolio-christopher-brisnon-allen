package cs112.ud2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cs112.ud2.manager.ResourceManager;

/**
 * Player.java
 * Represents the player/commander and their overall progress.
 * Owns the fleet of ships and resources.
 */
public class Player {
    /********** CONSTANTS **********/
    private String name;
    private int commandHull;           // The player's main command ship / base health

    private ResourceManager resources;
    private List<Ship> ships;

    private int experience;
    private int level;

    /********** CONSTRUCTORS **********/
    public Player(String name) {
        this.name = name;
        this.commandHull = 1000;
        this.resources = new ResourceManager();
        this.ships = new ArrayList<>();
        this.experience = 0;
        this.level = 1;
    }

    /********** GETTERS **********/

    public String getName() {
        return name;
    }

    public int getCommandHull() {
        return commandHull;
    }

    public ResourceManager getResources() {
        return resources;
    }

    public List<Ship> getShips() {
        return Collections.unmodifiableList(ships);
    }

    public int getExperience() { return experience; }
    public int getLevel() { return level; }

    /********** HELPER METHODS **********/

    public boolean canAddShip() {
        return ships.size() < resources.getFleetCapacity();
    }

    public boolean addShip(Ship ship) {
        if (ship == null || !canAddShip()) {
            return false;
        }
        ships.add(ship);
        return true;
    }

    public Ship addRandomShip() {
        if (!canAddShip()) {
            return null;
        }
        Ship randomShip = Ship.randomShip();
        ships.add(randomShip);
        return randomShip;
    }

    public boolean removeShip(Ship ship) {
        return ships.remove(ship);
    }

    public void addExperience(int amount) {
        if (amount > 0) {
            experience += amount;
            // TODO: Level up logic later
        }
    }

    public void takeDamage(int damage) {
        commandHull = Math.max(0, commandHull - damage);
    }

    /********** REQUIRED METHODS **********/

    @Override
    public String toString() {
        return String.format("Player[name=%s, level=%d, ships=%d, commandHull=%d]",
                name, level, ships.size(), commandHull);
    }
}