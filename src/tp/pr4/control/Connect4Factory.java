package tp.pr4.control;

import tp.pr4.logic.Board;
import tp.pr4.logic.Connect4Rules;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;
import tp.pr4.logic.Connect4Move;

public class Connect4Factory implements GameTypeFactory{
	
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
		return new Connect4Move(col, colour);
	}
	
	public Player createRandomPlayer()
	{
		return new RandomConnect4Player();
	}
	
	public GameRules createRules()
	{
		return new Connect4Rules();
	}

}
