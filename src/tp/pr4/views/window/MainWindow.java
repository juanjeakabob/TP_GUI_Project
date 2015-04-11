package tp.pr4.views.window;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import tp.pr4.logic.*;
import tp.pr4.control.WindowController;;

public class MainWindow extends javax.swing.JFrame implements GameObserver {
	
	private WindowController mConsoleController;
	private Observable<GameObserver> mGame;
	
	public MainWindow(Observable<GameObserver> g, WindowController c)
	{
		super("Project 4");
		
		mGame = g;
		mConsoleController = c;
		
		initGUI();
	}
	
	//Panels of the GUI
	private JPanel mainPanel, bottomPanel, rightPanel, gamePanel, changeGamePanel,centerPanel, turnPanel;
	//Buttons of the GUI
	private JButton randomMoveButton, exitButton, undoButton, resetButton, changeButton;
	
	private void initGUI()
	{
		//JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1080, 720);
		this.setMinimumSize(new Dimension(640, 360));
		
		//Create the mainPanel
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.LIGHT_GRAY);		
		this.setContentPane(mainPanel);
		
		//Bottom Panel
		bottomPanel = new JPanel();
		bottomPanel.setSize(new Dimension(this.getWidth(), 65));
		bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 65));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		
		randomMoveButton = new JButton("Random Move");
		randomMoveButton.setPreferredSize(new Dimension(180, 55));
		bottomPanel.add(randomMoveButton);
		
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(90, 55));
		bottomPanel.add(exitButton);		
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//Right Panel
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setSize(new Dimension(this.getWidth() / 2, this.getHeight() - bottomPanel.getHeight()));
		rightPanel.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() - bottomPanel.getHeight()));
		rightPanel.setBackground(Color.LIGHT_GRAY);		
		mainPanel.add(rightPanel, BorderLayout.LINE_END);
		
		//Border
		Border rightPanelsBorder = BorderFactory.createLineBorder(Color.black, 1);
		//Game Panel
		gamePanel = new JPanel();
		gamePanel.setSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 2));
		gamePanel.setPreferredSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 2));
		gamePanel.setBackground(Color.LIGHT_GRAY);
		gamePanel.setBorder(BorderFactory.createTitledBorder(rightPanelsBorder, "Game"));
		rightPanel.add(gamePanel, BorderLayout.CENTER);
		
		undoButton = new JButton("Undo");
		undoButton.setPreferredSize(new Dimension(90, 85));
		gamePanel.add(undoButton);
		
		resetButton = new JButton("Reset");
		resetButton.setPreferredSize(new Dimension(90, 85));
		gamePanel.add(resetButton);	
		
		//ChangeGame Panel
		changeGamePanel = new JPanel();

		changeGamePanel.setSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 2));
		changeGamePanel.setPreferredSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight() / 2));
		changeGamePanel.setBackground(Color.LIGHT_GRAY);
		changeGamePanel.setBorder(BorderFactory.createTitledBorder(rightPanelsBorder, "Change game"));
		rightPanel.add(changeGamePanel, BorderLayout.PAGE_END);
		
		JComboBox<String> gamesList;
		String gamesNames[] = {"Connect-4", "Complica", "Gravity"};
		gamesList = new JComboBox<String>(gamesNames);
		gamesList.setSelectedIndex(0);
		gamesList.setSize(changeGamePanel.getWidth(), 10);
		gamesList.setPreferredSize(new Dimension(changeGamePanel.getWidth() - 25, 25));
		changeGamePanel.add(gamesList);
		
		changeButton = new JButton("Change");
		changeButton.setPreferredSize(new Dimension(90, 45));
		changeButton.setLocation(changeGamePanel.getWidth() - 25, changeGamePanel.getHeight() - 25);
		changeGamePanel.add(changeButton);	
		

		
		//Center Panel
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setSize(new Dimension(this.getWidth() / 2, this.getHeight() - bottomPanel.getHeight()));
		centerPanel.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() - bottomPanel.getHeight()));
		centerPanel.setBackground(Color.BLACK);		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		GraphicBoardComponent g = new GraphicBoardComponent(10, 10);
		centerPanel.add(g, BorderLayout.CENTER);
		
		turnPanel = new JPanel();
		turnPanel.setSize(new Dimension(centerPanel.getWidth(), centerPanel.getHeight() / 12));
		turnPanel.setPreferredSize(new Dimension(centerPanel.getWidth(), centerPanel.getHeight() / 12));
		turnPanel.setBackground(Color.white);		
		centerPanel.add(turnPanel, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub

	}

}
