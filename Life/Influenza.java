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
 * for three generations, then becomes alive.
 * 
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Influenza extends Cell {
    //initial generation counts before becoming alive
    private int genCounts = 3;
    
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
            //comes alive next generation, if it has three neighbours
            setNextState(true);
        }
        else if (genCounts ==2 || genCounts == 3){
            genCounts-=1;
            return;
        }
        if (isAlive()){
            setColor(Color.GRAY);
            //influenza dies if it has 2 or less neighbours
            if (neighbours.size() <=2)
            setNextState(false);
        }
    }
}
