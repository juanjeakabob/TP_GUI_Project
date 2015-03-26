package tp.pr4.views.console;

import tp.pr4.logic.*;
import tp.pr4.control.ConsoleController;

public class ConsoleView implements GameObserver {
	
	private ConsoleController mConsoleController;
	private Observable<GameObserver> mGame;
	
	
	public ConsoleView(Observable<GameObserver> g, ConsoleController c)
	{
		mGame = g;
		mConsoleController = c;
	}
	
	public void showMenu(Counter turn)
	{
		System.out.println(""); //Skip one line
		System.out.println(turn + " to move"); //Show the current turn
		System.out.print("Please enter a command: ");
	}
	
	//Print into the screen the board
	public void printBoard(ReadOnlyBoard mGame)
	{
		System.out.println(mGame);
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		printBoard(board);
		
		if(winner == Counter.EMPTY)
		{
			System.out.println("Game over. Game ended in a draw" );
		}
		else
		{
			System.out.println("Game over. " + winner + " wins");
		}
	}

	@Override
	public void onMoveError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub

	}

}
