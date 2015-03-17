package tp.pr4.control;

import java.util.Random;

import tp.pr4.logic.Board;
import tp.pr4.logic.Connect4Move;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

public class RandomConnect4Player implements Player{
	
	public Move getMove(Board board, Counter colour)
	{
		Random rnd = new Random();
		int col = rnd.nextInt(board.getWidth()) + 1; //Generate a number between 1 and width
		
		//Generate a new random number until its true (at least one column has a free position)
		while(board.isColumnFull(col))
		{
			col = rnd.nextInt(board.getWidth()) + 1;
		}
		
		return new Connect4Move(col, colour);
	}

}
