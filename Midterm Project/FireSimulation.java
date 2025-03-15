import java.awt.Color;                 // handles colors
import java.awt.Graphics;              // basic graphics class
import java.awt.event.ActionEvent;     // timer event handling
import java.awt.event.ActionListener;  // listens for action events (timer)
import javax.swing.JPanel;             // panel for rendering graphics
import javax.swing.Timer;              
import javax.swing.JFrame;             

/**
 * determines the state of a single cell
 */
class Cell {
    enum State { EMPTY, TREE, BURNING }
    State state;

    // initializes the state of the cell
    Cell(State state) {
        this.state = state;  // setting initial state
    }

    // returns color associated with the cell's state
    Color getColor() {
        switch (state) {
            case EMPTY: return Color.YELLOW;  // empty cells yellow
            case TREE: return Color.GREEN;    // tree cells green
            case BURNING: return Color.RED;   // burning cells red
            default: return Color.BLACK;      // just in case
        }
    }
}

/**
 *  grid world and its logic
 */
class World {
    Cell[][] grid;
    int gridSize;
    double probCatch;

    // sets up the grid size and probability of fire spread
    World(int gridSize, double probCatch) {
        this.gridSize = gridSize;
        this.probCatch = probCatch;
        grid = new Cell[gridSize + 2][gridSize + 2];  // extra for boundary
        initializeGrid();

        // unit test - verified grid initialized correctly with yellow boundaries and green trees
        // gridSize=5 - checked corners (passed)
    }

    // initializes grid with boundary and trees
    void initializeGrid() {
        for (int i = 0; i < gridSize + 2; i++) {
            for (int j = 0; j < gridSize + 2; j++) {
                if (i == 0 || j == 0 || i == gridSize + 1 || j == gridSize + 1)
                    grid[i][j] = new Cell(Cell.State.EMPTY);  // boundary cells empty
                else
                    grid[i][j] = new Cell(Cell.State.TREE);   // cells on the inside with trees
            }
        }
        grid[gridSize / 2][gridSize / 2].state = Cell.State.BURNING; // middle cell is burning

        // unit test - confirmed only the middle cell is set to burning initially (passed)
    }

    // updates grid state based on fire spreading rules
    void applySpread() {
        Cell[][] nextGrid = new Cell[gridSize + 2][gridSize + 2];

        // copy current grid states to temporary nextGrid
        for (int i = 0; i < gridSize + 2; i++) {
            for (int j = 0; j < gridSize + 2; j++) {
                nextGrid[i][j] = new Cell(grid[i][j].state);  // copy state
            }
        }

        // go through each cell to apply spread logic
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                nextGrid[i][j].state = spread(i, j);  // apply logic to each cell
            }
        }
        grid = nextGrid;  // update grid

        // unit test - manually verified spread function
        // gridSize=3, probCatch=1.0, checked spread from center (passed)
        // probCatch=0.0, confirmed that the fire didn't spread over a few tests (passed)
    }

    // determines the new state of a cell based on if a neighbor cell is burning or not
    Cell.State spread(int i, int j) {
        if (grid[i][j].state == Cell.State.EMPTY)
            return Cell.State.EMPTY;  // empty cells should empty

        if (grid[i][j].state == Cell.State.BURNING)
            return Cell.State.EMPTY;  // burning trees become empty next step

        if (grid[i][j].state == Cell.State.TREE) {
            // check if any neighbors are burning
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (grid[i + di][j + dj].state == Cell.State.BURNING && Math.random() < probCatch)
                        return Cell.State.BURNING;  // catches fire if a neighbor cell is burning and random prob passes
                }
            }
        }
        return Cell.State.TREE;  // remains a tree
    }
}

/**
 * fire spreading simulation using cellular automata
 * yellow border is empty cells, green is trees, red is burning
 */
public class FireSimulation extends JPanel implements ActionListener {

    private World world;
    private final int cellSize = 20;
    private Timer timer;

    // constructor initializes world and timer
    public FireSimulation(int gridSize, double probCatch) {
        world = new World(gridSize, probCatch);  // create the grid world
        timer = new Timer(800, this);  // timer controls simulation speed, can adjust to make sim run faster or slower if u wanna see better
        timer.start();                 // starts timer
    }

    // paints the simulation grid with colors
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < world.gridSize + 2; i++) {
            for (int j = 0; j < world.gridSize + 2; j++) {
                g.setColor(world.grid[i][j].getColor());  // set color based on the state of a cell
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);  // draw cell
            }
        }
    }

    // updates grid each timer tick
    @Override
    public void actionPerformed(ActionEvent e) {
        world.applySpread();  // updates world each tick
        repaint();            // refreshes display
    }

    /**
     * runs simulation
     * usage command - java FireSimulation gridSize
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("use command: java FireSimulation <gridSize>");
            return;
        }

        int gridSize = Integer.parseInt(args[0]);  // get grid size from command line
        double probCatch = 0.15;                   // probability of spread

        JFrame frame = new JFrame("fire spread simulation");
        FireSimulation sim = new FireSimulation(gridSize, probCatch);
        frame.add(sim);
        frame.setSize((gridSize + 2) * sim.cellSize + 15, (gridSize + 2) * sim.cellSize + 38);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // centers the frame
        frame.setVisible(true);             // makes frame visible

        // unit test - ran simulation visually multiple times
        // gridSize=20, probCatch=0.15 - fire spreads moderately (passed)
        // probCatch=0.55 - fire almost took over the entire world (pretty fast spread) (passed)
    }
}