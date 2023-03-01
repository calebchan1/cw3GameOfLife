import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 *
 * The white blood cell works to target bacteria and viruses that are known
 * to its immune system. In this simulation, we assume the white blood cell
 * only knows to target the influenza cell.
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
     * The whiteblood cell targets surrounding alive influenza or devourer cells.
     * It will only remain alive if it has two or three neighbours.
     * For any other amount of neighbours it will die.
     * 
     * If it is currently dead, two or more of it's neighbours must be 
     * influenza or devourer in order to come alive
     *
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        if (isAlive()) {
            //check for surrounding influenza/devourer cells
            for (Cell cell : neighbours){
                //if cell is influenza/devourer, one influenza/devourer cell dies at a time.
                if (cell instanceof Influenza || cell instanceof Devourer){
                    cell.setNextState(false);
                    return;
                }
            }
            if (neighbours.size() == 4 || neighbours.size()<=1)
                setNextState(false);
            else
                setNextState(true);
        }
        else{
            //two neighbours must be influenza or devourer to come alive
            int count = 0;
            for (Cell cell:neighbours){
                if (cell instanceof Influenza || cell instanceof Devourer){
                    count++;
                }
            }
            if (count >= 2)
                setNextState(true);
        }

    }
}
