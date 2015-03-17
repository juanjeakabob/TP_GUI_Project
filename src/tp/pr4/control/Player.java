package tp.pr4.control;

import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

public interface Player {
	
	public Move getMove(Board board, Counter colour);

}
