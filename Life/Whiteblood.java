import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 *
 * The white blood cell works to target bacteria and viruses that are known
 * to its immune system. In this simulation, we assume the white blood cell
 * only knows to target the influenza cell
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Whiteblood extends Cell {

    /**
     * Create a new Whiteblood.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Whiteblood(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Whiteblood decides if it's alive or not.
     * The whiteblood cell targets surrounding alive influenza cells
     * 
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //assume all cells will not live onto next state
        setNextState(false);
        if (isAlive()) {
            
        }
        else{
        }
    }
}
