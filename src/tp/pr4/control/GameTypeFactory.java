package tp.pr4.control;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;

public interface GameTypeFactory {
	
	public Player createHumanPlayerAtConsole(final java.util.Scanner in);
	public Player createHumanPlayerAtGUI(final int col, final int row);
	public Move createMove(int col, int row, Counter colour);
	public Player createRandomPlayer();
	public GameRules createRules();

}
