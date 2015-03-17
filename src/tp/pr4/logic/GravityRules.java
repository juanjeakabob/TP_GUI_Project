package tp.pr3.logic;

public class GravityRules implements GameRules{
	
	private final Counter StartPlayer;
	private final int BoardHeight, BoardWidth;
	
	
	public GravityRules()
	{
		StartPlayer = Counter.WHITE;
		BoardWidth = 10;
		BoardHeight = 10;
	}
	
	public GravityRules(int width, int height)
	{
		StartPlayer = Counter.WHITE;
		BoardWidth = width;
		BoardHeight = height;
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
		//If we have a winner save it and return it
		if(board.checkForWinner())
		{
			winner = lastMove.getPlayer();
		}
		
		return winner;
	}

}
