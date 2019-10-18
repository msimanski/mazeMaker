package mazeMaker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze 
{
	public int xSize = 0;
	public int ySize = 0;
	public int totalDimensions = 0;
	
	Random randomGenerator = new Random(System.currentTimeMillis());
	
	public Cell[][] cellData;
	
	public Stack<Cell> cellStack = new Stack<Cell>();
	
	// used for prim's algorithm
	public ArrayList<Cell> primsWallList = new ArrayList<Cell>();
	
	Cell tempCell; // Temporary variable used for maze generation
	
	// METHODS
	// RBT = 0 (default)
	// Prim = 1
	// Kruskal = 2
	public Maze(int xSize, int ySize, int method) 
	{
		cellData = new Cell[xSize][ySize];
		this.xSize = xSize;
		this.ySize = ySize;
		this.totalDimensions = this.xSize * this.ySize;
		//cellStack.setSize(xSize * ySize);
		
		// Initialize array objects
		for (int i = 0; i < this.xSize; i++) 
		{
			for (int j = 0; j < this.ySize; j++) 
			{
				cellData[i][j] = new Cell();
				// Initialize the walls of the cell
				cellData[i][j].hasNorthWall = true;
				cellData[i][j].hasSouthWall = true;
				cellData[i][j].hasEastWall = true;
				cellData[i][j].hasWestWall = true;
			}
		}
		
		// Assign x and y positions
		for (int i = 0; i < this.xSize; i++) 
		{
			for (int j = 0; j < this.ySize; j++) 
			{
				cellData[i][j].xPos = i;
				cellData[i][j].yPos = j;
			}
		}
		
		initBoundries();
		
		if (method == 0) 
		{
			generateMazeRBT();
		}
	}
	
	private void initBoundries() 
	{
		// Initialize the border cells as visited so we don't go out of bounds
        for (int i = 0; i < this.xSize; i++) 
        { 
            for (int j = 0; j < this.ySize; j++) 
            { 
                if (i == 0 || j == 0 || i == this.xSize - 1 || j == this.ySize - 1) 
                    cellData[i][j].hasBeenVisited = true;
            } 
        } 
	}
	
	private void generateMazeRBT(int x, int y) 
	{
		// Set current cell as visited
		cellData[x][y].hasBeenVisited = true;
		
		// While there are unvisited neighbors
		while (!cellData[x + 1][y].hasBeenVisited || !cellData[x - 1][y].hasBeenVisited || !cellData[x][y + 1].hasBeenVisited || !cellData[x][y - 1].hasBeenVisited) 
		{
			// Select a random neighbor
			while (true) 
			{
                int r = randomGenerator.nextInt(4);
                if (r == 0 && !cellData[x][y+1].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasNorthWall = false;
                    cellData[x][y+1].hasSouthWall = false;
                    generateMazeRBT(x, y + 1);
                    break;
                }
                else if (r == 1 && !cellData[x+1][y].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasEastWall = false;
                    cellData[x+1][y].hasWestWall = false;
                    generateMazeRBT(x+1, y);
                    break;
                }
                else if (r == 2 && !cellData[x][y-1].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasSouthWall = false;
                    cellData[x][y-1].hasNorthWall = false;
                    generateMazeRBT(x, y-1);
                    break;
                }
                else if (r == 3 && !cellData[x-1][y].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasWestWall = false;
                    cellData[x-1][y].hasEastWall = false;
                    generateMazeRBT(x-1, y);
                    break;
                }
			}
		}
		
		// There are no unvisited neighbors
		if (!cellStack.isEmpty()) 
		{
			tempCell = cellStack.pop();
			generateMazeRBT(tempCell.xPos, tempCell.yPos);
		}
	}
	
	// Begin generating maze at top left corner
	private void generateMazeRBT() 
	{
		generateMazeRBT(15,22);
	}
	
	// Prim's algorithm for mazes
	//    Start with a grid full of walls.
	//    Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
	//    While there are walls in the list:
	//        Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
	//            Make the wall a passage and mark the unvisited cell as part of the maze.
	//            Add the neighboring walls of the cell to the wall list.
	//        Remove the wall from the list.
	private void generateMazePrim() 
	{
		// Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
		primsWallList.add(cellData[randomGenerator.nextInt(xSize)][randomGenerator.nextInt(ySize)]);
		
		// While there are walls in the list:
		while (!(primsWallList.isEmpty())) 
		{
			
		}
	}
	
	// Kruskal's algorithm for mazes
	private void generateMazeKruskal() 
	{
		
	}
	
}
