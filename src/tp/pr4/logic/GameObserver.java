package tp.pr4.logic;

public interface GameObserver {

	void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer);
	void moveExecStart(Counter player);
	void onGameOver(ReadOnlyBoard board, Counter winner);
	void onMoveError(String msg);
	void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);
	void onUndoNotPossible();
	void reset(ReadOnlyBoard board, Counter player, java.lang.Boolean undoPossible);
}
