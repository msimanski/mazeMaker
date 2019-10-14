package mazeMaker;

public class Main 
{
	public static Maze mainMaze = new Maze(40, 40);
	
	public static void main(String[] args) 
	{
		ViewWindow vw = new ViewWindow(mainMaze);
		printMaze();
	}
	
	public static String printMaze() 
	{
		for(int i = 0; i < 40; i++)
		{
			for(int j = 0; j < 40; j++)
			{
				if(mainMaze.cellData[i][j].hasWestWall == true) 
				{
					System.out.print("|");
				}
				else 
				{}
				
				if(mainMaze.cellData[i][j].hasNorthWall == true) 
				{
					System.out.print("-");
				}
				else 
				{}
				
				if(mainMaze.cellData[i][j].hasSouthWall == true) 
				{
					System.out.print("_");
				}
				else 
				{}
				
				if(mainMaze.cellData[i][j].hasEastWall == true) 
				{
					System.out.print("|");
				}
				else 
				{}
			}
			System.out.println("");
		}
		
		return null;
	}
}
