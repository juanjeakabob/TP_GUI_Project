package tp.pr4.logic;

public enum Counter
{
	EMPTY("Empty"), WHITE("White"), BLACK("Black");
	
	private String name;
	
	Counter(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	static public Counter swap(Counter  col)
	{
		if(col == WHITE)
		{
			col = BLACK;
		}
		else if(col == BLACK)
		{
			col = WHITE;
		}
		
		return col;
	}

}
