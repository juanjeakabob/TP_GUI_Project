package tp.pr3.control;

import tp.pr3.logic.Board;
import tp.pr3.logic.Counter;
import tp.pr3.logic.GameRules;
import tp.pr3.logic.ComplicaRules;
import tp.pr3.logic.Move;
import tp.pr3.logic.ComplicaMove;

public class ComplicaFactory implements GameTypeFactory {
	
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
				
				return createMove(column, -1, colour);
			}
		};
		
		return pl;
	}
	
	public Move createMove(int col, int row, Counter colour)
	{ 		
		return new ComplicaMove(col, colour);
	}
	
	public Player createRandomPlayer()
	{
		return new RandomComplicaPlayer();
	}
	
	public GameRules createRules()
	{
		return new ComplicaRules();
	}

}
