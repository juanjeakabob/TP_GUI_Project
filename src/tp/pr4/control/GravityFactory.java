package tp.pr3.control;

import tp.pr3.logic.Board;
import tp.pr3.logic.Counter;
import tp.pr3.logic.GameRules;
import tp.pr3.logic.GravityMove;
import tp.pr3.logic.GravityRules;
import tp.pr3.logic.Move;

public class GravityFactory implements GameTypeFactory
{
	private int mWidth, mHeight;
	public GravityFactory(int width, int height)
	{
		mWidth = width;
		mHeight = height;
	}
	
	public Player createHumanPlayerAtConsole(final java.util.Scanner in)
	{
		//Create an anonymous class that implements Player
		Player pl = new Player()
		{
			public Move getMove(Board board, Counter colour)
			{
				System.out.print("Please provide the column number: ");
				int column = in.nextInt(); //Get the column to do the move
				String nextline = in.nextLine(); //We skip the rest of the current line so we avoid future errors
				
				System.out.print("Please provide the row number: ");
				int row = in.nextInt(); //Get the column to do the move
				nextline = in.nextLine(); //We skip the rest of the current line so we avoid future errors
				
				return createMove(column, row, colour);
			}
		};
		
		return pl;
	}
	
	public Move createMove(int col, int row, Counter colour)
	{
		return new GravityMove(col, row, colour);
	}
	
	public Player createRandomPlayer()
	{
		return new RandomGravityPlayer();
	}
	
	public GameRules createRules()
	{
		return new GravityRules(mWidth, mHeight);
	}

}
