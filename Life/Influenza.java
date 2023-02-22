import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * 
 * This class is the influenza class
 * 
 * This class is the flavobacterium class, which is a special cell that
 * changes behaviour as time progresses. Influenza is initially dead
 * for two generations, then becomes alive in the host location killing
 * other cells.
 * 
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Influenza extends Cell {
    private int genCounts = 2;
    
    /**
     * Create a new Influenza.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Influenza(Field field, Location location, Color col) {
        super(field, location, col);
        //by default influenza is dead
        setDead();
    }

    /**
     * This is how the Influenza decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //after two generation counts virus becomes alive if it has three neighbours
        if (genCounts == 1 && neighbours.size() == 3){
            setNextState(true);
        }
        else{
            genCounts-=1;
            return;
        }
        if (isAlive()){
            setColor(Color.GRAY);
            //if no neighbours, virus dies
            if (neighbours.size() == 1)
            setNextState(false);
            
        }
        else{
            setColor(Color.LIGHT_GRAY);
        }
    }
}
