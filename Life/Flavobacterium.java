import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
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
     * If bacteria is dead and there are exactly 2 neighbours, bacteria comes
     * back to life
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //assume all cells will not live onto next state
        setNextState(false);
        if (isAlive()) {
            if (neighbours.size()==1 || neighbours.size()==2){
                setNextState(true);
                if (neighbours.size() == 1)
                //one neighbour, changes to red
                setColor(Color.RED);
                else
                //two or more neighbours, changes to pink
                setColor(Color.PINK);
            }
            else{
                //no neighbours, dies
                setNextState(false);
            }
            
        }
        else{
            //when flavobacterium is dead and there are exactly three neighbours
            //flavobactierum revives
            if (neighbours.size() == 3)
                setNextState(true);
        }
    }
}
