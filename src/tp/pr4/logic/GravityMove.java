package tp.pr4.logic;

public class GravityMove extends Move{
	
	private int mRow;
	private int finalRow, finalColumn; //Position where the counter rests after the gravity pull
	
	public GravityMove(int moveColumn, int moveRow, Counter moveColour)
	{
		mColumn = moveColumn;
		mRow = moveRow;
		mColour = moveColour;
		
		finalRow = finalColumn = -1;
	}
	
	public void executeMove(Board board) throws InvalidMove
	{		
		//We check if the column is valid
		if(mColumn < 1 || mColumn > board.getWidth() || mRow < 1 || mRow > board.getHeight())
		{
			throw new InvalidMove("Invalid move: position (" + mColumn + ", " + mRow + ") is not on the board.");
		}
		else if(board.getPosition(mColumn, mRow) != Counter.EMPTY)
		{
			throw new InvalidMove("Invalid move: position (" + mColumn + ", " + mRow + ") is already occupied.");
		}
		else
		{
			//Compute the distance of the Counter to to Top border and to the left border (yTuBorder and xToBorder)
			//To do this we compute the absolute distance from the Counter to the center and substract that to the half of the width/height
			int xToBorder = (int)((board.getWidth() + 1) / 2.0 - Math.abs(mColumn - (board.getWidth() + 1) / 2.0));
			int yToBorder = (int)((board.getHeight() + 1) / 2.0 - Math.abs((board.getHeight() + 1) / 2.0 - mRow));
			
			//If we are at the center don't move the Counter.
			if((mColumn == (board.getWidth() + 1) / 2.0) && (mRow == (board.getHeight() + 1) / 2.0))
			{
				//We are at the midddle... The counter stays there
				board.setPosition((board.getWidth() + 1) / 2, (board.getHeight() + 1) / 2, mColour);
				
				finalColumn = (board.getWidth() + 1) / 2;
				finalRow = (board.getHeight() + 1) / 2;
			}
			//Check if X is smaller than X (that means we are closer to the xBorder). Or, check if we are not in the X center but we are at the Y center.
			else if((xToBorder < yToBorder) || (xToBorder > 0 && (board.getHeight() + 1) / 2.0 - mRow == 0))
			{
				gravityInXAxis(board);				
			}
			//We are closer to a top/bottom border, or we are at the center of the X axis but not in the center of the Y center
			else if((xToBorder > yToBorder) || (mColumn - (board.getWidth() + 1) / 2.0 == 0 && yToBorder > 0))
			{
				gravityInYAxis(board);
			}
			else
			{
				//The difference is 0				
				gravityInDiagonals(board);
			}
		}
	}
	
	private void gravityInDiagonals(Board board)
	{
		double xToCenter = mColumn - (board.getWidth() + 1) / 2.0;
		double yToCenter = (board.getHeight() + 1) / 2.0 - mRow;
		
		//We are at a diagonal, handle cases
		
		//Upper left diagonal
		if((xToCenter < 0) && (yToCenter > 0))
		{
			int i = mColumn; //Column
			int j = mRow; //Row
			
			while((board.getPosition(i, j) == Counter.EMPTY) && i >= 1 && j >= 1)
			{
				i--;
				j--;
			}
			
			board.setPosition(i + 1,  j + 1, mColour);
			
			finalColumn = i + 1;
			finalRow = j + 1;
			
		}//Upper right diagonal
		else if((xToCenter > 0) && (yToCenter > 0))
		{
			int i = mColumn; //Column
			int j = mRow; //Row
			
			while((board.getPosition(i, j) == Counter.EMPTY) && i <= board.getWidth() && j  >= 1)
			{
				i++;
				j--;
			}
			
			board.setPosition(i - 1,  j + 1, mColour);
			
			finalColumn = i - 1;
			finalRow = j + 1;
			
		}//Lower left diagonal
		else if((xToCenter < 0) && (yToCenter <= 0))
		{
			int i = mColumn; //Column
			int j = mRow; //Row
			
			while((board.getPosition(i, j) == Counter.EMPTY) && i >= 1 && j  <= board.getHeight())
			{
				i--;
				j++;
			}
			
			board.setPosition(i + 1,  j - 1, mColour);
			
			finalColumn = i + 1;
			finalRow = j - 1;
		}
		else//Lower right diagonal
		{
			int i = mColumn; //Column
			int j = mRow; //Row
			
			while((board.getPosition(i, j) == Counter.EMPTY) && i <= board.getWidth() && j  <= board.getHeight())
			{
				i++;
				j++;
			}
			
			board.setPosition(i - 1,  j - 1, mColour);
			
			finalColumn = i - 1;
			finalRow = j - 1;
		}
	}
	
	private void gravityInXAxis(Board board)
	{
		//Check the sign of the distance of the X to the center, negative means we are at the left of the middle, so we are attracted by the left border, positive means right
		if(mColumn - (board.getWidth() + 1) / 2.0 > 0)
		{
			//Right border
			
			//Start from the current column and search for an empty position
			int i = mColumn;
			
			//Loop until we find an empty cell. We know that this loop will not fail because at least the user chosen cell is empty (hence valid)
			while((board.getPosition(i, mRow) == Counter.EMPTY) && (i <= board.getWidth()))
			{
				i++;
			}
			
			board.setPosition(i - 1, mRow, mColour);
			
			//Save the position that the Counter is placed after being pulled by the gravity
			finalColumn = i - 1;
			finalRow = mRow;
		}
		else
		{
			//Left border
			
			
			int i = mColumn;
			
			while((board.getPosition(i, mRow) == Counter.EMPTY) && (i >= 1))
			{
				i--;
			}
			
			board.setPosition(i + 1, mRow, mColour);
			
			finalColumn = i + 1;
			finalRow = mRow;
		}
	}
	
	private void gravityInYAxis(Board board)
	{
		//Same as gravityInXAxis but in Y axis. Positive means top border
		if((board.getHeight() + 1) / 2.0 - mRow > 0)
		{
			//Upper border
			
			
			int i = mRow;
			
			//Loop until we find an empty cell. We know that this loop will not fail (or never exit) because at least the user choosen cell is empty (hence valid)
			while((board.getPosition(mColumn, i) == Counter.EMPTY) && (i >= 1)) 
			{
				i--;
			}
			
			board.setPosition(mColumn, i + 1, mColour);
			
			finalColumn = mColumn;
			finalRow = i + 1;
		}
		else
		{
			//Bottom border
			int i = mRow;
			
			//Loop until we find an empty cell. We know that this loop will not fail (or never exit) because at least the user choosen cell is empty (hence valid)
			while((board.getPosition(mColumn, i) == Counter.EMPTY) && (i <= board.getHeight()))
			{
				i++;
			}
			
			board.setPosition(mColumn, i - 1, mColour);
			
			finalColumn = mColumn;
			finalRow = i - 1;
		}
	}
	
	//Simple undo, just set the last counter in the column to empty
	public void undo(Board board)
	{		
		board.setPosition(finalColumn, finalRow, Counter.EMPTY);
	}

}
