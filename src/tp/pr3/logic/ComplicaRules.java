package tp.pr3.logic;

public class ComplicaRules implements GameRules {

	private final Counter StartPlayer;
	private final int BoardHeight, BoardWidth;
	
	public ComplicaRules()
	{
		StartPlayer = Counter.WHITE;
		BoardWidth = 4;
		BoardHeight = 7;
	}
	
	public Counter initialPlayer()
	{
		return StartPlayer;
	}
	public boolean isDraw(Counter lastPlayer, Board board)
	{
		return false; //Can't be a draw
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
		if(board.checkForWinner()) //Check if we have a winner
		{
			winner = lastMove.getPlayer(); //Save the winner
		}
		
		return winner;
	}
	
}
