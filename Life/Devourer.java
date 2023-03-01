import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Devourer is a cell that can kill(devour) other cells
 * If the Devourer has 1 neighbour and is alive, it will devour it 
 * It cannot devour another cell if it has more than 1 neighbour
 * If the Devourer is dead and has 1 neighbour, it will come back to life
 * If the Devourer has no neighbours or has not devoured in 5 generations, it will die
 * Devourers can kill eachother
 * 
 * Due to continuity, it is necessary to create a new devoured variable in the Cell abstract class to determine death
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */
public class Devourer extends Cell
{
    private int notDevoured;
    /**
     * Create a new trojan
     */
    public Devourer(Field field, Location location, Color col) {
        super(field, location, col);
        notDevoured = 0;
    }
    
    public void act(){
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        if (isAlive()){
            // More than 1 neighbour and has devoured withing 5 generations
            if (neighbours.size() > 1 && notDevoured < 5){
                setNextState(true);
                notDevoured++;
            }
            // Has 1 neighbour
            else if (neighbours.size() == 1){
                // Get the neighbour, set as devoured
                Cell devoured = neighbours.get(0);
                devoured.setDevoured(true);
                // Reset counter
                setNextState(true);
                notDevoured = 0;
            }
            // Will die
            else{
                notDevoured = 0;
                setNextState(false);
            }
        }
        else{
            setNextState(false);
            // Has 1 neighbour
            if (neighbours.size() == 1){
                setNextState(true);
            }
        }
    }
} 
