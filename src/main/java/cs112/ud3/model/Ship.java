package cs112.ud2.model;

import java.util.Objects;

/**
 * Ship.java
 * Represents a single ship in the player's fleet.
 * The player decides the ship's role through equipment and stat allocation.
 *
 */
public class Ship {
    /********** CONSTANTS **********/
    // Main-related stats
    private String name;
    private int hull;
    private MissionStatus status;

    // Work-related stats
    private int miningPower;
    private int salvagePower;
    private int scoutPower;

    // Combat-related stats
    private int combatPower;

    /********** CONSTRUCTORS **********/

    /**
     * Default constructor - creates a basic ship.
     */
    public Ship() {
        this.name = "Unnamed Ship";
        this.hull = 100;
        this.status = MissionStatus.AVAILABLE;
        this.miningPower = 0;
        this.salvagePower = 0;
        this.scoutPower = 0;
        this.combatPower = 0;
    }

    /**
     * Full constructor to create a ship.
     */
    public Ship(String name, int hull, int miningPower, int salvagePower,
                int scoutPower, int combatPower) {
        setName(name);
        setHull(hull);
        this.miningPower = Math.max(0, miningPower);
        this.salvagePower = Math.max(0, salvagePower);
        this.scoutPower = Math.max(0, scoutPower);
        this.combatPower = Math.max(0, combatPower);
        this.status = MissionStatus.AVAILABLE;
    }

    /**
     * Copy constructor
     */
    public Ship(Ship other) {
        this.name = other.name;
        this.hull = other.hull;
        this.status = other.status;
        this.miningPower = other.miningPower;
        this.salvagePower = other.salvagePower;
        this.scoutPower = other.scoutPower;
        this.combatPower = other.combatPower;
    }
    /*
     * Creates a random ship with a unique name and randomized stats.
     */
    public static Ship randomShip() {
        String[] names = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon"};
        String name = names[(int) (Math.random() * names.length)] + " " + (int) (Math.random() * 1000);
        int hull = 50 + (int) (Math.random() * 150);
        int miningPower = (int) (Math.random() * 50);
        int salvagePower = (int) (Math.random() * 50);
        int scoutPower = (int) (Math.random() * 50);
        int combatPower = (int) (Math.random() * 50);
        return new Ship(name, hull, miningPower, salvagePower, scoutPower, combatPower);
    }

    /********** SETTERS **********/

    public void setName(String name) {
        this.name = (name == null || name.isBlank()) ? "Unnamed Ship" : name;
    }

    public void setHull(int hull) {
        this.hull = Math.max(0, hull);
    }

    public void setStatus(MissionStatus status) {
        this.status = (status != null) ? status : MissionStatus.AVAILABLE;
    }

    /********** GETTERS **********/

    public String getName() {
        return name;
    }
    public int getHull() {
        return hull;
    }
    public MissionStatus getStatus() {
        return status;
    }
    public int getMiningPower() {
        return miningPower;
    }
    public int getSalvagePower() {
        return salvagePower;
    }
    public int getScoutPower() {
        return scoutPower;
    }
    public int getCombatPower() {
        return combatPower;
    }

    /********** HELPER METHODS **********/

    public boolean isAvailable() {
        return status == MissionStatus.AVAILABLE;
    }

    /********** REQUIRED METHODS **********/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ship other)) return false;

        return hull == other.hull &&
                miningPower == other.miningPower &&
                salvagePower == other.salvagePower &&
                scoutPower == other.scoutPower &&
                combatPower == other.combatPower &&
                Objects.equals(name, other.name) &&
                status == other.status;
    }
    // hashCode and toString for better debugging and potential future use in collections
    @Override
    public int hashCode() {
        return Objects.hash(name, hull, status, miningPower,
                salvagePower, scoutPower, combatPower);
    }

    @Override
    public String toString() {
        return String.format(
                "Ship[name=%s, hull=%d, status=%s, mining=%d, salvage=%d, scout=%d, combat=%d]",
                name, hull, status, miningPower, salvagePower, scoutPower, combatPower
        );
    }
}