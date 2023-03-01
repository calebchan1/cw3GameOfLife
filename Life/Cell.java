import java.awt.Color;
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public abstract class Cell {
    // Whether the cell is alive or not.
    private boolean alive;

    // Whether the cell will be alive in the next generation.
    private boolean nextAlive;

    // The cell's field.
    private Field field;

    // The cell's position in the field.
    private Location location;

    // The cell's color
    private Color color = Color.white;
    
    // The cell's future infection status
    private boolean willInfect;
    
    // The cell's current infection status
    private boolean isInfected;
    
    // The cell's devour status
    private boolean isDevoured;

    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        isInfected = false;
        willInfect = false;
        isDevoured = false;
        this.field = field;
        setLocation(location);
        setColor(col);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();
    
    /** Set infection status
     * Only set value as true to overcome a logic issue
     */
    public void setWillInfect(boolean val) {
        if (val){
            willInfect = true;
        }
    }
    
    /** Return infection status
     * 
     */
    public boolean isInfected(){
        return isInfected;
    }
    
    /**
     * How an infected cell acts
     * Ran once the cell is infected for 1 generation
     */
    public void infectedAct() {
        nextAlive = false;
        isInfected = false;
        // Only infects other cells if the cell itself is alive
        if (alive){
            List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
            // Infects all living neighbours
            for (Cell cell: neighbours){
                cell.setWillInfect(true);
            }
        }
    }
    
    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }
    
    /**
     * Set's devoured value
     */
    public void setDevoured(boolean val) {
        isDevoured = val;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
      nextAlive = value;
    }

    /**
     * Changes the state of the cell
     */
    public void updateState() {
      // Begin devour action
      actDevour();
      alive = nextAlive;
      // Update infect state
      isInfected = willInfect;
      willInfect = false;
    }
    
    /**
     * Kills devoured cells
     * The devour process begins after each cell has completed their actions
     */
    private void actDevour() {
        if (isDevoured){
            nextAlive = false;
        }
        isDevoured = false;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
      color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
      return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
}
