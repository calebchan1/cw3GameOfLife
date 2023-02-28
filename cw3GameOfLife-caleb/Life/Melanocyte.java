import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life. This is the Melanocyte class.
 *
 * Non deterministic cell that executes a rule froma  given set in
 * a random manner.
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Melanocyte extends Cell {

    /**
     * Create a new Melanocyte.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Melanocyte(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Melanocyte decides if it's alive or not
     * Executes a rule randomly from a given set of rules. 
     * 
     * Ruleset whilst alive:
     * 10% - Changes colour to blue
     * 10% - Changes colour to black (default colour)
     * 20% - If two or more of its neighbours is mycoplasma it will
     * die 
     * 30% - If it has exactly 1 neighbour it will die
     * 30% - If it has exactly 3 neighbours it will die.
     * 
     * Ruleset whilst dead:
     * 40% - Comes back to alive in presence of white blood cell
     * 60% - Comes back to alive with 2 or more neighbours.
     */
    public void act() {
       
    }
}
