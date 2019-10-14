package mazeMaker;

import java.util.Random;
import java.util.Stack;

public class Maze 
{
	public int xSize = 0;
	public int ySize = 0;
	public int totalDimensions = 0;
	
	Random randomGenerator = new Random();
	
	public Cell[][] cellData;
	
	public Stack<Cell> cellStack = new Stack<Cell>();
	
	Cell tempCell; // Temporary variable used for maze generation
	
	public Maze(int xSize, int ySize) 
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
		generateMaze();
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
	
	private void generateMaze(int x, int y) 
	{
		// Set current cell as visited
		cellData[x][y].hasBeenVisited = true;
		
		// While there are unvisited neighbors
		while (!cellData[x][y+1].hasBeenVisited || !cellData[x+1][y].hasBeenVisited || !cellData[x][y-1].hasBeenVisited || !cellData[x-1][y].hasBeenVisited) 
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
                    generateMaze(x, y + 1);
                    break;
                }
                else if (r == 1 && !cellData[x+1][y].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasEastWall = false;
                    cellData[x+1][y].hasWestWall = false;
                    generateMaze(x+1, y);
                    break;
                }
                else if (r == 2 && !cellData[x][y-1].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasSouthWall = false;
                    cellData[x][y-1].hasNorthWall = false;
                    generateMaze(x, y-1);
                    break;
                }
                else if (r == 3 && !cellData[x-1][y].hasBeenVisited) 
                {
                	cellStack.push(cellData[x][y]);
                    cellData[x][y].hasWestWall = false;
                    cellData[x-1][y].hasEastWall = false;
                    generateMaze(x-1, y);
                    break;
                }
			}
		}
		
		// There are no unvisited neighbors
		if (!cellStack.isEmpty()) 
		{
			tempCell = cellStack.pop();
			generateMaze(tempCell.xPos, tempCell.yPos);
		}
	}
	
	// Begin generating maze at top left corner
	private void generateMaze() 
	{
		generateMaze(1,1);
	}
	
}
