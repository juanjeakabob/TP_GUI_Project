package tp.pr4.logic;

public abstract class Move {
	
	//Our current player
	protected int mColumn;
	protected Counter mColour;
	
	public Counter getPlayer()
	{
		return mColour;
	}
	
	public abstract void executeMove(Board board) throws InvalidMove;
	public abstract void undo(Board board);

}
