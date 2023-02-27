import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * 
 * This class is the influenza class
 * Secial cell that changes behaviour as time progresses. 
 * Influenza is initially dead for a random number of generations, 
 * then becomes alive.
 * 
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Influenza extends Cell {
    //initial generation counts before becoming alive
    private int genCounts;
    
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
        //random generation counts that influenza is dormant for
        //random generation counts between 3 to 5
        Random rn = new Random();
        genCounts = rn.nextInt(5) + 3;
    }

    /**
     * This is how the Influenza decides if it's alive or not
     * 
     * After random number of generations, the influenza becomes 
     * alive.
     * If it is alive, it will die if it has 2 or less neighbours.
     * If it is dead, it will come alive if it has at least 1
     * neighbour AND none of it's neighbours are white blood cells
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        //after two generation counts virus becomes alive if it has three neighbours
        if (genCounts == 1 && neighbours.size() == 3){
            //comes alive next generation, if it has three neighbours
            setNextState(true);
        }
        else if (genCounts>=2){
            genCounts-=1;
            return;
        }
        if (isAlive()){
            setColor(Color.GRAY);
            //influenza dies if it has 2 or less neighbours
            if (neighbours.size() <=2)
            setNextState(false);
        }
        else{
            //influenza comes alive if it has at least 1 neighbour
            //AND the neighbours does not contain a white blood 
            //cell
            if (neighbours.size()>=1){
                for (Cell cell:neighbours){
                    if (cell instanceof Whiteblood)
                    return;
                }
                setNextState(true);
            }   
        }
    }
}
