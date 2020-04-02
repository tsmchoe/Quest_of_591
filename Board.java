package play_game;

public class Board {
	//This is the class that creates the interactive board so that we can see what is going on
	
	public static char[][] board;
	public static int winNum;
	public static int width;
	public static int len;
	public static int area;
	public static int currX;
	public static int currY;
	
	private static void initBoard(int n, int m) {
		//Creates the board for objects to be placed in
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	private static void outBorder(int n) {
		//creates upper and lower boundaries
		System.out.print("+ == +");
		if (n > 1) {
			for (int i = 1; i < n; i++) {
				System.out.print(" == +");
			}
		}
		System.out.print("\n");
	}
	
	private static void middleBorder(int n, int m) {
		//creates middle boundaries between tokens and also keeps current state of board
		outBorder(m);
		for (int i = 0; i < n; i++) {
			System.out.print("| ");
			for (int j = 0; j < m; j++) {
				System.out.print(board[i][j] + "  | ");
			}
			System.out.print("\n");
			outBorder(m);
		}
	}
	
	public static void printBoard() {
		//creates a board size n x m
		middleBorder(len, width);
	}
	
	public static void makeBoard(int n, int m) {
		//creates a board size n x m
		board = new char[n][m];
		len = n;
		width = m;
		area = n*m;
		initBoard(len,width);
		Cell.randomPlace();
		printBoard();
		Cell.setUpPlayer();
		printBoard();
	}
	
	
}
