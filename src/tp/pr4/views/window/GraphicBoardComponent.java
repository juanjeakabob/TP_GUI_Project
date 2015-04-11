package tp.pr4.views.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import tp.pr4.logic.Counter;

/**
 * Code of this class is a modified version of BoardComponent example given and created by Samir Genaim
 * @author Jorge
 *
 */

public class GraphicBoardComponent extends JComponent {
	private int mCellHeight;
	private int mCellWidth;

	private int rows;
	private int cols;
	private Counter[][] mBoard;

	public GraphicBoardComponent(int rows, int cols) {
		mCellHeight = 50;
		mCellWidth = 50;
		
		initBoard(rows, cols);
		initGUI();
	}

	private void initBoard(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		mBoard = new Counter[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				mBoard[i][j] = Counter.EMPTY;
			}
		}
	}

	private void initGUI() {

		/*addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released: " + "(" + e.getX() + ","
						+ e.getY() + ")");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed: " + "(" + e.getX() + ","
						+ e.getY() + ")");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse Exited Component: " + "(" + e.getX()
						+ "," + e.getY() + ")");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse Entered Component: " + "(" + e.getX()
						+ "," + e.getY() + ")");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Clicked:: " + "(" + e.getX() + ","
						+ e.getY() + ")");
			}
		});*/
		
		this.setSize(new Dimension(rows * mCellHeight, cols * mCellWidth));
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		mCellWidth = this.getWidth() / cols;
		mCellHeight = this.getHeight() / rows;

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				drawCell(i, j, g);
			}
		}
	}

	private void drawCell(int row, int col, Graphics g) {
		int x = col * mCellWidth;
		int y = row * mCellHeight;

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 2, y + 2, mCellWidth - 4, mCellHeight - 4);

		g.setColor(counterToColor(mBoard[row][col]));
		g.fillOval(x + 4, y + 4, mCellWidth - 8, mCellHeight - 8);

		g.setColor(Color.black);
		g.drawOval(x + 4, y + 4, mCellWidth - 8, mCellHeight - 8);

	}

	public void setBoardSize(int rows, int cols) {
		initBoard(rows, cols);
		repaint();
	}
	
	private Color counterToColor(Counter c)
	{
		switch(c)
		{
		case BLACK:
			return Color.black;
		case WHITE:
			return Color.white;
		default:
			return Color.lightGray;
		}
	}
}
