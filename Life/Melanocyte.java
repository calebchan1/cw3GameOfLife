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
     * Assumed to always die unless rule says otherwise
     * 10% - Changes colour to blue
     * 10% - Changes colour to black (default colour)
     * 20% - If two or more of its neighbours is mycoplasma it will
     * die 
     * 30% - If it has exactly 1 neighbour it will live on
     * 30% - If it has exactly 3 neighbours it will live on.
     * 
     * Ruleset whilst dead:
     * 40% - Comes back to alive in presence of white blood cell
     * 60% - Comes back to alive with 3 or more neighbours.
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        Random rand = new Random();
        double randDouble = rand.nextDouble();
        if (isAlive()){
            if (randDouble<=0.1)
                setColor(Color.BLUE);
            else if (0.11<=randDouble && randDouble<=0.2)
                setColor(Color.BLACK);
            else if (0.21<=randDouble && randDouble<=0.4){
                //if two  or more neighbours is mycolpasma it will die
                int count = 0;
                for (Cell cell: neighbours){
                    if (cell instanceof Mycoplasma){
                        count++;
                    }
                }
                if (count>=2) setNextState(true);
                else setNextState(false);        
            }
            else if (0.41<= randDouble && randDouble<=0.7 &&neighbours.size()==1)
                setNextState(true);
            else if (neighbours.size()==3) 
            setNextState(true);
        }
        else{
            //when dead
            if (randDouble<=0.4){
                for (Cell cell:neighbours){
                    if (cell instanceof Whiteblood){
                        setNextState(true); 
                        return;
                    }
                }
            }
            else if (neighbours.size()>=3) 
            setNextState(true);
        }
    }
}
