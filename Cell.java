package play_game;
import java.util.*;

//Gives all the information that can be found in each Cell
public class Cell {
	//cell attributes
	private static char heroToken = 'x';
	private static char notAvailable = '&';
	private static char market = 'm';
	private static AsciiArt artGen = new AsciiArt();
	private static Scanner Input = new Scanner(System.in);
	
	public static void randomPlace() {
		//Sets up the board with markets, unavailable, and common spaces
		Random r = new Random();
		int markMax = 0;
		int unAMax = 0;
		int m = 0;
		int u = 0;
		long counter = Math.round(Board.area * 0.3);
		long second = Math.round(Board.area * 0.2);
		
		while(markMax < counter || unAMax < second) {
			 m = r.nextInt(Board.len);
			 u = r.nextInt(Board.len);
			 if (Board.board[m][u] == ' ' && markMax < counter) {
				 Board.board[m][u] = market;
				 markMax += 1;
			 }
			 else if (Board.board[m][u] == ' ' && unAMax < second) {
				 Board.board[m][u] = notAvailable;
				 unAMax += 1;
			 }
			 else {
				 continue;
			 }
		}
	}
	
	public static int monsterAttack() throws Exception {
		//Probability that common space is a monster space
		Random r = new Random();
		int result = 0;
		int prob = r.nextInt(101);
		if (prob <= 75) {
			System.out.println();
			artGen.printTextArt("Monster Attacks!", AsciiArt.ART_SIZE_SMALL);
			//set up monster attack movement
			Collections.sort(Hero.heroParty);
			result = Battle.initiateBattle(Hero.heroParty);
		}
		else {
			System.out.println("Safe! Whew...");
		}
		return result;
				
	}
	
	public static void setUpPlayer() {
		//gives your player the starting choice of where to go
		boolean finisher = true;
		int x;
		int y;
		System.out.println("Choose which spot you want your team to start on: ");
		while (finisher){
			x = Input.nextInt();
			y = Input.nextInt();
			if ((x >= 0) && (x < Board.len)) {
				if ((y >= 0) && (y < Board.width)) {
					if (Board.board[x][y] == ' ') {
						Board.currX = x;
						Board.currY = y;
						Board.board[x][y] = heroToken;
						break;
					}
				}
			}
			System.out.println("\nNot accepted. Try again: ");
		}
	}
	
}
