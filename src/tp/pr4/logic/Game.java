package tp.pr4.logic;

import java.util.ArrayList;
import java.util.Stack;

public class Game implements Observable<GameObserver>{
	
	//Private variables
	private Board mBoard;
	private Counter mTurn;
	private boolean mFinished;
	private Counter mWinner;
	private GameRules mRules;
	private Move mLastMove;
	
	//Undo stack
	private Stack<Move> mUndoStack;
	
	//Oberservers list
	private ArrayList<GameObserver> mObserversList;
	
	public Game(GameRules rules)
	{
		mBoard = rules.newBoard();
		mFinished = false;
		mTurn = rules.initialPlayer();
		
		mUndoStack = new Stack<Move>();
		
		mObserversList = new ArrayList<GameObserver>();
		
		mRules = rules;
	}

	
	//Return the board class
	public Board getBoard()
	{
		return mBoard;
	}
	
	//Return the current turn
	public Counter getTurn()
	{
		return mTurn;
	}
	
	//If the game is finished it returns the winner
	public Counter getWinner()
	{
		if(isFinished())
		{
			return mWinner;
		}
		else
		{
			return Counter.EMPTY;
		}
	}
	
	//Return if the game is finished or not
	public boolean isFinished()
	{
		return mFinished;
	}
	
	//Sets of the game is finished or not
	public void setFinished(boolean fin)
	{
		mFinished = fin;
	}
	
	//Reset the whole board, the turn and the counter list
	public void reset(GameRules rules)
	{
		mRules = rules;
		
		mBoard = rules.newBoard();
		mTurn = rules.initialPlayer();
		
		mUndoStack.removeAllElements();
		
		System.out.println("Game restarted");
	}

	//Goes to the next turn
	public void nextTurn()
	{
		mTurn = mRules.nextTurn(mTurn, mBoard);
	}
	
	//Execute a new move if the game is not finished, its made by the correct player and the column is valid.
	public void executeMove(Move move) throws InvalidMove
	{
		if(isFinished())
		{
			throw new InvalidMove("Game has finished");
		}
		else if(move.getPlayer() != mTurn)
		{
			throw new InvalidMove("Not your turn!");
		}
		else
		{
			//Notify the start of a move
			for(GameObserver o: mObserversList)
			{
				o.moveExecStart(mTurn);
			}
			
		 	//Do the move (if valid)
			move.executeMove(mBoard);
			
			//Add the move to the stack
			mUndoStack.push(move);
			
			mLastMove = move;
			
			//Notify the start of a move
			for(GameObserver o: mObserversList)
			{
				o.moveExecFinished(mBoard, mTurn,  mRules.nextTurn(mTurn, mBoard));
			}
			
			if(mRules.winningMove(mLastMove, mBoard) != Counter.EMPTY) //If we have a winner exit
			{
				mWinner = mLastMove.getPlayer();
				setFinished(true);
				
				for(GameObserver o: mObserversList)
				{
					o.onGameOver(mBoard, mWinner);
				}
			}
			else if(mRules.isDraw(mLastMove.getPlayer(), mBoard)) //If the board full is a draw, exit
			{
				mWinner = Counter.EMPTY; //No winner
				setFinished(true);
				
				for(GameObserver o: mObserversList)
				{
					o.onGameOver(mBoard, mWinner);
				}
			}
			else
			{
				//If we haven't finish yet go to the next turn.
				nextTurn();
			}
		}
	}
	
	//Undo the last movement, false if there is no movement to undo
	public boolean undo()
	{
		boolean correctUndo;
		
		//If the stack is empty return false
		if(mUndoStack.empty())
		{
			correctUndo = false;
			
			//Notify undo fail
			for(GameObserver o: mObserversList)
			{
				o.onUndoNotPossible();
			}
		}
		else
		{
			mUndoStack.pop().undo(mBoard);
			
			nextTurn(); //After undo the turn should change
			
			//Notify correct undo
			for(GameObserver o: mObserversList)
			{
				o.onUndo(mBoard, getTurn(), true);
			}
			
			correctUndo = true;
		}
		
		return correctUndo;
	}


	@Override
	public void addObserver(GameObserver o) {
		mObserversList.add(o);	
	}


	@Override
	public void removeObserver(GameObserver o) {
		mObserversList.remove(o);		
	}
	
	

}
