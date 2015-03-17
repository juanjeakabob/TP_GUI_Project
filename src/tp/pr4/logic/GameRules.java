package tp.pr3.logic;

public interface GameRules {

		public Counter initialPlayer();
		public boolean isDraw(Counter lastPlayer, Board board);
		public Board newBoard();
		public Counter nextTurn(Counter lastPlayer, Board board);
		public Counter winningMove(Move lastMove, Board board);
}
