import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author Caleb Chan, Alexander Wickman | original:David J. Barnes, 
 * Michael Kölling & Jeffery Raphael
 */

public class Simulator {
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;

    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    // The probability that a Mycoplasma is alive
    private static final double MYCOPLASMA_ALIVE_PROB = 0.2;
     // The probability that a Flavobacterium is alive
    private static final double FLAVOBACTERIUM_ALIVE_PROB = 0.05;
    // The probability that a Influenza is alive
    private static final double INFLUENZA_ALIVE_PROB = 0.15;
    // The probability that a White blood cell is alive
    private static final double WHITEBLOOD_ALIVE_PROB = 0.1;
    // The probability that a Melanocyte cell is alive
    private static final double MELANOCYTE_ALIVE_PROB = 0.06;
    // The probability that a cell will be infected with Disease
    private static final double INFECT_PROB = 0.01;
    // The probability that a Devourer is alive
    private static final double DEVOURER_ALIVE_PROB = 0.01;

    // List of cells in the field.
    private List<Cell> cells;

    // The current state of the field.
    private Field field;

    // The current generation of the simulation.
    private int generation;

    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Execute simulation
     */
    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate(100);
    }

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        cells = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 generations).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of
     * generations.  Stop before the given number of generations if the
     * simulation ceases to be viable.
     * @param numGenerations The number of generations to run for.
     */
    public void simulate(int numGenerations) {
        for (int gen = 1; gen <= numGenerations && view.isViable(field); gen++) {
            simOneGeneration();
            delay(500);   // comment out to run simulation faster
        }
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        Random rand = Randomizer.getRandom();
        generation++;
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();

            // Disease conditional logic
            if (cell.isInfected()){
                cell.infectedAct();
            }
            else{
                // Determines infection status
                cell.setWillInfect(willInfect(rand, cell.isAlive()));
                cell.act();
            }
        }

        for (Cell cell : cells) {
            cell.updateState();
        }
        view.showStatus(generation, field);
    }

    /**
     * Determines whether a cell becomes infected
     * The cell must be alive in order to be infected with Disease
     */
    private boolean willInfect(Random rand, boolean alive){
        if ((rand.nextDouble() <= INFECT_PROB) && (alive == true)){
            return true;
        }
        return false;
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(generation, field);
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                //adding mycoplasma
                Location location = new Location(row, col);
                Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
                if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
                    cells.add(myco);
                }
                else {
                    myco.setDead();
                    cells.add(myco);
                    //adding flavobacterium if dead cell was created
                    Flavobacterium flavo = new Flavobacterium(field, location, Color.RED);
                    if (rand.nextDouble() <= FLAVOBACTERIUM_ALIVE_PROB) {
                        cells.add(flavo);
                    }
                    else {
                        flavo.setDead();
                        cells.add(flavo);
                        //adding influenza if dead cell was created
                        Influenza infl = new Influenza(field, location, Color.GRAY);
                        if (rand.nextDouble() <= INFLUENZA_ALIVE_PROB) {
                            cells.add(infl);
                        }
                        else {
                            infl.setDead();
                            cells.add(infl);
                            //adding white blood cell if dead cell was created
                            Whiteblood wb = new Whiteblood(field, location, Color.GREEN);
                            if (rand.nextDouble() <= WHITEBLOOD_ALIVE_PROB) {
                                cells.add(wb);
                            }
                            else {
                                wb.setDead();
                                cells.add(wb);
                                //adding melanocyte cell if dead cell was created
                                Melanocyte mc = new Melanocyte(field, location, Color.BLACK);
                                if (rand.nextDouble() <= MELANOCYTE_ALIVE_PROB) {
                                    cells.add(mc);
                                }
                                else {
                                    mc.setDead();
                                    cells.add(mc);
                                    //adding devourer is dead cell was created
                                    Devourer dv = new Devourer(field, location, Color.MAGENTA);
                                    if (rand.nextDouble() <= DEVOURER_ALIVE_PROB) {
                                        cells.add(dv);
                                    }
                                    else{
                                        dv.setDead();
                                        cells.add(dv);
                                    }
                                }

                            }
                        }
                    }
                }

            }
        }

    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}
