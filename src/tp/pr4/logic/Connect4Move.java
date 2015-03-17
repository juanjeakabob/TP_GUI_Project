package tp.pr3.logic;

public class Connect4Move extends Move{
	
	public Connect4Move(int moveColumn, Counter moveColour)
	{
		mColumn = moveColumn;
		mColour = moveColour;
	}
	
	public void executeMove(Board board) throws InvalidMove
	{
		if(!insertNewCounterIntoFirstFreeColumnPosition(mColumn, mColour, board))
		{
			throw new InvalidMove("Invalid move: column number " + mColumn + " is already full.");
		}
	}
	
	//Simple undo, just set the last counter in the column to empty
	public void undo(Board board)
	{
		int row = board.getLastRowFilledInColumn(mColumn); //We get the first row that is empty
		
		board.setPosition(mColumn, row + 1, Counter.EMPTY); //Row + 1 so we get the row that is not empty (last counter placed)
	}
	
	//function to insert a new Counter in the desired column of the board with a free position
	static public boolean insertNewCounterIntoFirstFreeColumnPosition(int column, Counter color, Board board) throws InvalidMove {
		boolean validMove = false;
		
		if (column < 1 || column > board.getWidth()) {
			throw new InvalidMove("Invalid move: column number " + column + " is not on the board.");
		} else {
			int row = board.getLastRowFilledInColumn(column); //Get the first empty row of the board in that column
			
			//If the row is 0 the column is full (Range from 1 to N) - Where N is the width or height depending on the axis
			if (row == 0) {
				validMove = false;
			} else {
				//If the row and the column is valid set the new counter
				board.setPosition(column, row, color);
				validMove = true;
			}
		}

		return validMove;
	}

}
