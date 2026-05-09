package cs112.ud2.manager;

import cs112.ud2.model.Player;
import cs112.ud2.model.Ship;
/**
 * GameManager.java
 *    ─ Player
 *    ─ ResourceManager
 *    ─ Fleet adding removing logic
 * The main manager that controllers communicate with
 */
public class GameManager {

    /********** CONSTANTS **********/
    private static GameManager instance;

    private final Player player;

    private GameManager() {
        this.player = new Player("Commander");
        this.player.addRandomShip(); // Starting with random ship
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public ResourceManager getResources() {
        return player.getResources();
    }

    /********** FLEET **********/
    public boolean canAddShip() {
        return player.canAddShip();
    }

    public boolean addShip(Ship ship) {
        return player.addShip(ship);
    }

    public Ship addRandomShip() {
        return player.addRandomShip();
    }

    public boolean removeShip(Ship ship) { 
        return player.removeShip(ship); 
    }

    /********** RESOURCES **********/
    public void addMinerals(int amount) { player.getResources().addMinerals(amount); }
    public void addSalvage(int amount) { player.getResources().addSalvage(amount); }
    public void addEnergy(int amount) { player.getResources().addEnergy(amount); }
    public void addCredits(int amount) { player.getResources().addCredits(amount); }

    public boolean spendMinerals(int amount) {
        return player.getResources().spendMinerals(amount);
    }

    public boolean spendSalvage(int amount) {
        return player.getResources().spendSalvage(amount);
    }

    public boolean spendEnergy(int amount) {
        return player.getResources().spendEnergy(amount);
    }

    public boolean spendCredits(int amount) {
        return player.getResources().spendCredits(amount);
    }
}