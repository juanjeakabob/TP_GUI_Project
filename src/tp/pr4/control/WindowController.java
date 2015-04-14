package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Game;
import tp.pr4.views.window.MainWindow;

public class WindowController extends Controller {

	public WindowController(GameTypeFactory factory, Game g) {
		mGame = g;
		mGameFactory = factory;
	}

	@Override
	public void run() {
		MainWindow view = new MainWindow(mGame, this);
		
		
	}
	
	public void GUImakeMove(int col, int row)
	{
		mWhitePlayer = mGameFactory.createHumanPlayerAtGUI(col, row);
		mBlackPlayer = mGameFactory.createHumanPlayerAtGUI(col, row);
		
		makeMove();
	}
	
	public void GUImakeUndo()
	{
		mGame.undo();
	}
	
	public void GUImakeReset()
	{
		mGame.reset(mGameFactory.createRules());
	}
	
	public void GUImakeRandomMove()
	{
		mWhitePlayer = mGameFactory.createRandomPlayer();
		mBlackPlayer = mGameFactory.createRandomPlayer();
		
		makeMove();
	}
	
	public void GUImakeChangeGame(int game, int cols, int rows)
	{
		switch(game)
		{
			case 0: //c4
				mGameFactory = new Connect4Factory();
				mGame.reset(mGameFactory.createRules());
				break;
				
			case 1: //co
				mGameFactory = new ComplicaFactory();
				mGame.reset(mGameFactory.createRules());
				break;
				
			case 2: //gr
				mGameFactory = new GravityFactory(cols, rows);
				mGame.reset(mGameFactory.createRules());

				break;
		}
		
	}

}
