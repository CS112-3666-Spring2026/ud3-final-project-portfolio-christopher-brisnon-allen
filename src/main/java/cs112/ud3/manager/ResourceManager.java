package cs112.ud2.manager;

import java.util.Objects;

/**
 * ResourceManager.java
 * Manages all resources of the player, including minerals, salvage, energy, credits, and fleet capacity.
 */
public class ResourceManager {

    private int minerals;
    private int salvage;
    private int energy;
    private int credits;
    private int fleetCapacity;

    /********** CONSTRUCTORS **********/

    public ResourceManager() {
        this.minerals = 500;
        this.salvage = 200;
        this.energy = 300;
        this.credits = 1000;
        this.fleetCapacity = 8;
    }

    public ResourceManager(int minerals, int salvage, int energy, int credits, int fleetCapacity) {
        this.minerals = Math.max(0, minerals);
        this.salvage = Math.max(0, salvage);
        this.energy = Math.max(0, energy);
        this.credits = Math.max(0, credits);
        this.fleetCapacity = Math.max(1, fleetCapacity);
    }

    public ResourceManager(ResourceManager other) {
        this.minerals = other.minerals;
        this.salvage = other.salvage;
        this.energy = other.energy;
        this.credits = other.credits;
        this.fleetCapacity = other.fleetCapacity;
    }

    /********** GETTERS **********/

    public int getMinerals() { return minerals; }
    public int getSalvage() { return salvage; }
    public int getEnergy() { return energy; }
    public int getCredits() { return credits; }
    public int getFleetCapacity() { return fleetCapacity; }

    /********** ADDERS **********/

    public void addMinerals(int amount) {
        if (amount > 0) minerals += amount;
    }

    public void addSalvage(int amount) {
        if (amount > 0) salvage += amount;
    }

    public void addEnergy(int amount) {
        if (amount > 0) energy += amount;
    }

    public void addCredits(int amount) {
        if (amount > 0) credits += amount;
    }

    public void addFleetCapacity(int amount) {
        if (amount > 0) fleetCapacity += amount;
    }

    /********** SPENDERS **********/

    public boolean spendMinerals(int amount) {
        if (amount > 0 && minerals >= amount) {
            minerals -= amount;
            return true;
        }
        return false;
    }

    public boolean spendSalvage(int amount) {
        if (amount > 0 && salvage >= amount) {
            salvage -= amount;
            return true;
        }
        return false;
    }

    public boolean spendEnergy(int amount) {
        if (amount > 0 && energy >= amount) {
            energy -= amount;
            return true;
        }
        return false;
    }

    public boolean spendCredits(int amount) {
        if (amount > 0 && credits >= amount) {
            credits -= amount;
            return true;
        }
        return false;
    }

    public boolean spendFleetCapacity(int amount) {
        if (amount > 0 && fleetCapacity >= amount) {
            fleetCapacity -= amount;
            return true;
        }
        return false;
    }

    /********** REQUIRED METHODS **********/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceManager other)) return false;

        return minerals == other.minerals &&
                salvage == other.salvage &&
                energy == other.energy &&
                credits == other.credits &&
                fleetCapacity == other.fleetCapacity;
    }

    // hashCode and toString for better debugging and potential future use in collections
    @Override
    public int hashCode() {
        return Objects.hash(minerals, salvage, energy, credits, fleetCapacity);
    }

    @Override
    public String toString() {
        return String.format("Resources[minerals=%d, salvage=%d, energy=%d, credits=%d, fleetCapacity=%d]",
                minerals, salvage, energy, credits, fleetCapacity);
    }
}