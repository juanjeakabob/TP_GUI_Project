package tp.pr4.control;

import java.util.Random;

import tp.pr4.logic.Board;
import tp.pr4.logic.GravityMove;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

public class RandomGravityPlayer implements Player{
	
	public Move getMove(Board board, Counter colour)
	{
		Random rnd = new Random();
		int col = rnd.nextInt(board.getWidth()) + 1; //Generate a number between 1 and width
		int row = rnd.nextInt(board.getHeight()) + 1; //Generate a number between 1 and height
		
		//Generate a new random position until it finds an empty position
		while(board.getPosition(col, row) != Counter.EMPTY)
		{
			col = rnd.nextInt(board.getWidth()) + 1;
			row = rnd.nextInt(board.getHeight()) + 1;
		}
		
		return new GravityMove(col, row, colour);
	}

}
