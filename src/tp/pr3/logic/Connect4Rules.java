package tp.pr3.logic;

public class Connect4Rules implements GameRules {
	
	private final Counter StartPlayer;
	private final int BoardHeight, BoardWidth;
	
	public Connect4Rules()
	{
		StartPlayer = Counter.WHITE;
		BoardWidth = 7;
		BoardHeight = 6;
	}
	
	public Counter initialPlayer()
	{
		return StartPlayer;
	}
	public boolean isDraw(Counter lastPlayer, Board board)
	{
		return board.isFull();
	}
	public Board newBoard()
	{
		return new Board(BoardWidth, BoardHeight);
	}
	public Counter nextTurn(Counter lastPlayer, Board board)
	{
		return Counter.swap(lastPlayer);
	}
	public Counter winningMove(Move lastMove, Board board)
	{
		Counter winner = Counter.EMPTY;
		if(board.checkForWinner())
		{
			winner = lastMove.getPlayer();
		}
		
		return winner;
	}
	
	
}
