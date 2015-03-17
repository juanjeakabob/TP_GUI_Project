package tp.pr4.control;

import java.util.Scanner;


import tp.pr4.logic.*;
import tp.pr4.logic.Game;


public class Controller {
	//Internal pointers to the game and scanner objects passed in the constructor
	private Game mGame;
	private Scanner in;
	//Game rules for the different games, also the rules that are currently being used
	private GameTypeFactory mGameFactory;
	//TYpe of player in each color
	Player mWhitePlayer, mBlackPlayer;
	
	public Controller(GameTypeFactory factory, Game g, java.util.Scanner in)
	{
		mGameFactory = factory;
		mGame = g;
		this.in = in;
		
		mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
		mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in); 
	}
	
	
	public void run()
	{
		//Run until the game is finished
		while(!mGame.isFinished())
		{
			//Print the board
			mGame.printBoard();
			//Show the menu and wait for input
			showMenu();
		}
	}
	
	private void showMenu()
	{
		System.out.println(""); //Skip one line
		System.out.println(mGame.getTurn() + " to move"); //Show the current turn
		System.out.print("Please enter a command: ");
		String command = in.nextLine();
		handleCommand(command); //Handle the user input
	}
	
	private void handleCommand(String command)
	{
		String originalCommand = command; //Save the original command for printing it if its invalid (see default switch part)
		
		//Make all the command with lower case.
		command = command.toLowerCase();
		//Make a string with only 1 space
		command = command.trim();
		
		//Get all the words separated by spaces
		String[] words = command.split(" ");
		
		switch(words[0])
		{
		case "player":
			if(!handlePlayerEvent(words))
			{
				System.err.println(originalCommand + ": command not understood, please try again");
			}
			break;
		case "play":
			if(!handlePlayEvent(words))
			{
				System.err.println(originalCommand + ": command not understood, please try again");
			}
			break;
		case "make": //Make a move in the current game
			if(words.length == 3)
			{
				if(words[1].equals("a") && words[2].equals("move"))
				{
					makeMove();
				}
				else
				{
					System.err.println(originalCommand + ": command not understood, please try again");
				}
			}
			break;
		case "undo":
			if(!mGame.undo())
			{
				System.err.println("Nothing to undo.");
			}
			break;
		case "restart":
			mGame.reset(mGameFactory.createRules()); //Reset the game according to the current rules
			break;
		case "help":
			displayCommandsHelp();
			break;
		case "exit":
			mGame.setFinished(true);
			System.out.println("Exit requested.");
			break;
		default: //Invalid command
			System.err.println(originalCommand + ": command not understood, please try again");
			break;
		}
	}
	
	private boolean handlePlayerEvent(String[] words)
	{
		boolean valid = false;
		
		//If the command is valid (can't have less/more than 3 words)
		if(words.length == 3)
		{
			//Handle cases
			switch(words[1])
			{
			case "white":
				
				switch(words[2])
				{
				case "human":
					mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
					valid = true;
					break;
				case "random":
					mWhitePlayer = mGameFactory.createRandomPlayer();
					valid = true;
					break;						
				}
				
			break;
			case "black":
				
				switch(words[2])
				{
				case "human":
					mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in);
					valid = true;
					break;
				case "random":
					mBlackPlayer = mGameFactory.createRandomPlayer();
					valid = true;
					break;						
				}
				
			break;	
			}
		}
		
		return valid;
	}

	private boolean handlePlayEvent(String[] words)
	{
		boolean valid = false;
		
		if(words.length > 1)
		{
			switch(words[1])
			{
				case "c4":
					mGameFactory = new Connect4Factory();
					mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
					mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in);
					mGame.reset(mGameFactory.createRules());
					valid = true;
					break;
					
				case "co":
					mGameFactory = new ComplicaFactory();
					mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
					mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in);
					mGame.reset(mGameFactory.createRules());
					valid = true;
					break;
					
				case "gr":
					if(words.length == 4)
					{
						int width = Integer.parseInt(words[2]);
						int height = Integer.parseInt(words[3]);
						
						//If the w/h is correct create it normally
						if(height > 0 && width > 0)
						{
							mGameFactory = new GravityFactory(width, height);
							mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
							mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in);
							mGame.reset(mGameFactory.createRules());
							valid = true;
						}
						else
						{
							//If not generate a  1 x 1 board
							mGameFactory = new GravityFactory(1, 1);
							mWhitePlayer = mGameFactory.createHumanPlayerAtConsole(in);
							mBlackPlayer = mGameFactory.createHumanPlayerAtConsole(in);
							mGame.reset(mGameFactory.createRules());
							valid = true;
						}
					}
					break;
			}
		}
		
		return valid;
	}
	
	private void makeMove()
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
			System.err.println(e.getMessage());
		}

	}
	
	private void displayCommandsHelp()
	{
		System.out.println("The available commands are:");
		System.out.println();
		System.out.println("MAKE A MOVE: place a counter on the board.");
		System.out.println("UNDO: undo the last move of the game.");
		System.out.println("RESTART: restart the game.");
		System.out.println("PLAY [c4|co|gr] [dimX dimY]: change the type of game.");
		System.out.println("PLAYER [white|black] [human|random]: change the type of player.");
		System.out.println("EXIT: exit the application.");
		System.out.println("HELP: show this help.");
		System.out.println();
	}
}
