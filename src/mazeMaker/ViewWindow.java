package mazeMaker;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewWindow extends Frame
{
	public static Maze mainMaze;
	
	public ViewWindow(Maze maze) 
	{	
		// calling super sets bar title
		super("Maze");
		
		mainMaze = maze;
		
		setSize(400,400);
		
		setVisible(true);
		
		// idk what this does
		addWindowListener
		(new WindowAdapter()
	        {public void windowClosing(WindowEvent e)
	        	{dispose(); System.exit(0);}
	        }
        );
	}
	
	public void paint(Graphics g) 
	{
		g.setColor(Color.black);
		
		// for each cell draw sides with 10x10 walls, filled in blocks
		for(int i = 0; i < mainMaze.xSize; i++) 
		{
			for(int j = 0; j < mainMaze.ySize; j++) 
			{
				if(mainMaze.cellData[i][j].hasWestWall == true 
						&& mainMaze.cellData[i][j].hasEastWall == true 
						&& mainMaze.cellData[i][j].hasNorthWall == true 
						&& mainMaze.cellData[i][j].hasSouthWall == true) 
				{
					g.fillRect(i * 10, j * 10, 10, 10);
				}
				
				if(mainMaze.cellData[i][j].hasWestWall == true) 
				{
					g.drawLine((i * 10), (j * 10), (i * 10), (j * 10) + 10);
				}
				
				if(mainMaze.cellData[i][j].hasEastWall == true) 
				{
					g.drawLine((i * 10) + 10, (j * 10), (i * 10), (j * 10));
				}
				
				if(mainMaze.cellData[i][j].hasNorthWall == true) 
				{
					g.drawLine((i * 10), (j * 10), (i * 10) + 10, (j * 10));
				}
				
				if(mainMaze.cellData[i][j].hasSouthWall == true) 
				{
					g.drawLine((i * 10), (j * 10) + 10, (i * 10), (j * 10) + 10);
				}
			}
		}
	}
}
