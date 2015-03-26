package tp.pr4.logic;

public interface ReadOnlyBoard {

	int getHeight();
	Counter getPosition(int x, int y);
	int getWidth();
	
}
