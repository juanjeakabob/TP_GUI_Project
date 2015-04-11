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
		
		while(true)
		{
			
		}
		
	}

}
