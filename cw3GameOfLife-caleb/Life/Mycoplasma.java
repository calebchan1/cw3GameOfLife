import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //assume all cells will not live onto next state
        setNextState(false);
        if (isAlive()) {
            //if cell has two or more live nieghbours it will live on to next gen
            if (neighbours.size()==2 || neighbours.size()==3)
                setNextState(true);
            //in all other cases, cell will die
        }
        else{
            //dead cell becomes alive if has exactly three neighbours
            if (neighbours.size() == 3) 
                setNextState(true);
        }
    }
}
