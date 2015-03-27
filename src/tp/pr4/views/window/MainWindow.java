package tp.pr4.views.window;

import tp.pr4.logic.*;
import tp.pr4.control.WindowController;;

public class MainWindow implements GameObserver {
	
	private WindowController mConsoleController;
	private Observable<GameObserver> mGame;
	
	public MainWindow(Observable<GameObserver> g, WindowController c)
	{
		mGame = g;
		mConsoleController = c;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveError(String msg) {
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
