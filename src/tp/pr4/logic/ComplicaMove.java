package tp.pr4.logic;

public class ComplicaMove extends Move{
	
	private Counter mEliminatedCounter;

	public ComplicaMove(int moveColumn, Counter moveColour)
	{
		mColumn = moveColumn;
		mColour = moveColour;
		mEliminatedCounter = Counter.EMPTY;
	}
	
	public void executeMove(Board board) throws InvalidMove
	{		
		//We check if the column is valid (As if insertNewCounter return true that doesn't means execute move is at an invalid position
		if(mColumn < 1 || mColumn > board.getWidth())
		{
			throw new InvalidMove("Invalid move: column number " + mColumn + " is not on the board.");
		}
		else
		{
			//If the movement is invalid (we have already checked that the inserted column is valid) it must be because the column is full, thus we force the insertion, moving the whole column down
			if(!Connect4Move.insertNewCounterIntoFirstFreeColumnPosition(mColumn, mColour, board))
			{
				mEliminatedCounter = forceInsertNewCounterIntoFirstFreeColumnPosition(mColumn, mColour, board);
			}
		}

	}
	
	public void undo(Board board)
	{
		//If we had eliminated a counter is because the column is full so we have to do a special undo
		if(mEliminatedCounter != Counter.EMPTY)
		{
			//Move the whole column 1 position up
			for(int i = 1; i < board.getHeight(); i++)
			{
				board.setPosition(mColumn, i, board.getPosition(mColumn, i + 1));
			}
			
			board.setPosition(mColumn, board.getHeight(), mEliminatedCounter);
		}
		else
		{
			//normal undo
			int row = board.getLastRowFilledInColumn(mColumn); //We get the first row that is empty
			
			board.setPosition(mColumn, row + 1, Counter.EMPTY); //Row + 1 so we get the row that is not empty (last counter placed)
		}
	}
	
	//function to insert a new Counter in the desired column of the board, if the column is FULL it will insert it at the top and eliminate the first one. Returns the counter that is eliminated
	static public Counter forceInsertNewCounterIntoFirstFreeColumnPosition(int column, Counter color, Board board) {
			Counter eliminated = Counter.EMPTY;
			
			if (column < 1 || column > board.getWidth()) {
			} else {
				int row = board.getLastRowFilledInColumn(column); //Get the first empty row of the board in that column
				
				//If the row is 0 the column is full (Range from 1 to N) - Where N is the width or height depending on the axis
				if (row == 0) {
					eliminated = board.getPosition(column, board.getHeight());
					//Move the whole column 1 position down
					for(int i = board.getHeight(); i > 0; i--)
					{
						board.setPosition(column, i, board.getPosition(column, i - 1));
					}
					
					board.setPosition(column, 1, color);
				} else {
					//If the row and the column is valid set the new counter
					board.setPosition(column, row, color);;
				}
			}

			return eliminated;
		}
}
