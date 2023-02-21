import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Flavobacterium are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * This class is the flavobacterium class, which is a special cell that changes
 * colour in its rule set
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 * 
 */

public class Flavobacterium extends Cell {

    /**
     * Create a new Flavobacterium.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Flavobacterium(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Flavobacterium decides if it's alive or not
     * Following rule set applies:
     * If no neighbours it dies
     * If 2 or more neighbours it lives on and changes to pink
     * If 1 neighbour it lives on and changes to red
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //assume all cells will not live onto next state
        setNextState(false);
        if (isAlive()) {
            
        }
    }
}
