package tp.pr4.control;

import java.util.Scanner;








import tp.pr4.logic.*;


public abstract class Controller {	
	
	public abstract void run();
	
	protected Game mGame;
	//Game rules for the different games, also the rules that are currently being used
	protected GameTypeFactory mGameFactory;
	//TYpe of player in each color
	protected Player mWhitePlayer, mBlackPlayer;
	
	protected void makeMove()
	{
		Move newMove;
		
		if(mGame.getTurn() == Counter.WHITE)
		{
			newMove = mWhitePlayer.getMove(mGame.getBoard(), Counter.WHITE);
		}
		else
		{
			newMove = mBlackPlayer.getMove(mGame.getBoard(), Counter.BLACK);
		}
		
		try
		{
			mGame.executeMove(newMove);
			
		}catch(InvalidMove e)
		{
			mGame.moveErrorTriggered(e.getMessage());
		}

	}

}
