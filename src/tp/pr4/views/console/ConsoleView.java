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
		
		g.addObserver(this);
	}
	
	//Print into the screen the board
	public void printBoard(ReadOnlyBoard mGame)
	{
		System.out.println(mGame);
	}
	
	//Print into the screen the board
	public void printBoardWithTurn(ReadOnlyBoard mGame, Counter turn)
	{
		System.out.println(mGame);
		System.out.println(""); //Skip one line
		System.out.println(turn + " to move"); //Show the current turn
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer)
	{
		printBoardWithTurn(board, nextPlayer);
	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner)
	{
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
	public void onMoveError(String msg) 
	{
		System.err.println(msg);
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		printBoardWithTurn(board, nextPlayer);
	}

	@Override
	public void onUndoNotPossible() {
		System.err.println("Nothing to undo.");
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		System.out.println("Game restarted");
		printBoardWithTurn(board, player);
	}

}
