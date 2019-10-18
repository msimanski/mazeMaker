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
		
		setSize(800,800);
		
		setUndecorated(true);
		
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
				// print out coordinate markers, just for the top and left wall
				g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
				if((i == 0) || (j == 1)) 
				{
					g.drawString(Integer.toString(i) + "," + Integer.toString(j), i * 20, j * 20);
				}
				
				// if this is one with all walls, make it a solid square
				if(mainMaze.cellData[i][j].hasWestWall == true 
						&& mainMaze.cellData[i][j].hasEastWall == true 
						&& mainMaze.cellData[i][j].hasNorthWall == true 
						&& mainMaze.cellData[i][j].hasSouthWall == true) 
				{
					//g.setColor(Color.white);
					g.drawRect(i * 20, j * 20, 20, 20);
					//g.setColor(Color.black);
				}
				
				// otherwise handle each individual wall
				else 
				{
					if(mainMaze.cellData[i][j].hasWestWall == true) 
					{
						g.drawLine((i * 20), (j * 20), (i * 20), (j * 20) + 20);
					}
					
					if(mainMaze.cellData[i][j].hasEastWall == true) 
					{
						g.drawLine((i * 20) + 20, (j * 20), (i * 20), (j * 20));
					}
					
					if(mainMaze.cellData[i][j].hasNorthWall == true) 
					{
						g.drawLine((i * 20), (j * 20), (i * 20) + 20, (j * 20));
					}
					
					if(mainMaze.cellData[i][j].hasSouthWall == true) 
					{
						g.drawLine((i * 20), (j * 20) + 20, (i * 20), (j * 20) + 20);
					}
				}
			}
		}
	}
}
