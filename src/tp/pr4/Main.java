package tp.pr4;

import tp.pr4.control.*;
import tp.pr4.logic.*;

import java.util.Scanner;

public class Main {
	
	//Set to true if user asked for help by argument
	static private boolean helpDisplayed;
	
	public static void main(String[] args) {
		
		Controller control;
		helpDisplayed = false;
		
		if(args.length != 0)
		{
			//Create the controller
			control = handleCommands(args);
		}
		else
		{
			//Default
			GameTypeFactory c4Fact = new Connect4Factory();
			control = new Controller(c4Fact, new Game(c4Fact.createRules()), new Scanner(System.in));
		}
		
		//Run the game until the user exit or the game finish
		if(control != null)
		{
			control.run();
			
			System.out.println("Closing the game...");			
			System.exit(0);
		}
		else
		{
			//If we have displayed the help it is not an error (but control is null) so handle it
			if(helpDisplayed)
			{
				System.exit(0);
			}
			else
			{
				//We have an exception so return 1
				System.exit(1);
			}
		}
	}
	
	static private Controller handleCommands(String[] args)
	{
		Controller control = null;
		
		//Handle cases
		switch(args[0])
		{
		case "-g":
		case "--game":
			control = gameCommand(args);
			break;
			
		case "-h":
		case "--help":
			displayCommandHelp();
			helpDisplayed = true;
			break;
			
		default:
			displayErrorInCommand("Incorrect use: Unrecognized option: ", args, 0);
			break;
		}
		
		return control;
	}
	
	static private Controller gameCommand(String[] args)
	{
		Controller control = null;
		
		//We are reading the game command. Handle options
		switch(args[1])
		{
		case "c4":
			//We are reading game c4. Just 2 arguments so if we have more its an error
			if(args.length > 2)
			{
				displayErrorInCommand("Incorrect use: illegal arguments: ", args, 2);
			}
			else
			{
				GameTypeFactory c4Fact = new Connect4Factory();
				control = new Controller(c4Fact, new Game(c4Fact.createRules()), new Scanner(System.in));
			}
			break;
		case "co":
			//Same as c4
			if(args.length > 2)
			{
				displayErrorInCommand("Incorrect use: illegal arguments: ", args, 2);
			}
			else
			{
				GameTypeFactory coFact = new ComplicaFactory();
				control = new Controller(coFact, new Game(coFact.createRules()), new Scanner(System.in));
			}
			break;
		case "gr":
			int x, y;
			//We chose gravity, so we need 4 more parameters (X, number, Y, number)
			if(args.length > 2 && (!args[2].equals("-x") || !args[2].equals("--dimX")))
			{
				if(args.length > 4 && args[4].equals("-y") || args[4].equals("--dimY"))
				{
					//If we have more than 6 parameters in total its a wrong command
					if(args.length > 6)
					{
						displayErrorInCommand("Incorrect use: illegal arguments: ", args, 6);
					}
					else
					{					
						x = Integer.parseInt(args[3]);
						y = Integer.parseInt(args[5]);
						
						GravityFactory grFact = new GravityFactory(x, y);
						control = new Controller(grFact, new Game(grFact.createRules()), new Scanner(System.in));
					}
				}
				else
				{
					displayErrorInCommand("Incorrect use: illegal arguments: ", args, 4);
				}
			}
			else
			{
				displayErrorInCommand("Incorrect use: illegal arguments: ", args, 2);
			}
			break;
			
		default:
			System.err.println("Incorrect use: game '" + args[1] + "' incorrect.");
			System.err.println("For more details, use -h|--help.");
			break;
		}
		
		return control;
	}
	
	static private void displayErrorInCommand(String msg, String[] args, int index)
	{
		String invArgs = "";
		
		for(int i = index; i < args.length; i++)
		{
			invArgs += args[i] + " ";
		}
		
		System.err.println(msg + invArgs);
		System.err.println("For more details, use -h|--help.");
	}
	
	static private void displayCommandHelp()
	{
		System.out.println("usage: tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]");
		System.out.println(" -g,--game <game>           Type of game (c4, co, gr). By default, c4.");
		System.out.println(" -h,--help                  Displays this help.");
		System.out.println(" -x,--dimX <columnNumber>   Number of columns on the board (Gravity only).");
		System.out.println("                            By default, 10.");
		System.out.println(" -y,--dimY <rowNumber>      Number of rows on the board (Gravity only). By");
		System.out.println("                            default, 10.");
	}

}
