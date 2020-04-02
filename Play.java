package play_game;

import java.util.Scanner;

//The main class user runs in order to play the game
public class Play {
	
	private static Scanner Input = new Scanner(System.in);


	public static void main(String[] args) throws Exception {
		beginQuest();
	}
	
	public static void beginQuest() throws Exception {
		System.out.println("Welcome to the world of Heroes and Monsters!");
		System.out.println("This is a game of squares and what not... Honestly it's like any rpg game you've played before...");
		System.out.println("Your world is small so we begin in a 8x8 space! Feel free to change it by going to the code and changing the 8,8");
		System.out.println("Soon you will be directed to choose your hero that you will play as or company of up to 3 heroes that you will");
		System.out.println("play as! Just a heads up that you will be the character x on the board. m's are markets and & are unavailable spaces.");
		System.out.println("Try paying me next time for a DLC and I might open them up... or not lol! ");
		System.out.println();
		System.out.println("The instructions on how to move, access the menu, etc. will be shown after every move you make. After choosing your ");
		System.out.println("hero/heroes you will be directed to place yourself on the board. It goes from 0x0 to 7x7. Simply type the coordinates");
		System.out.println("you want to begin on (ex. 0 0) and then your game will start!");
		System.out.println("Are you ready to begin? (yes/no) (Please type your answers as such!): ");
		char intro = Input.next().charAt(0);
		if (intro == 'y' || intro == 'Y') {
			System.out.println("Great have fun and thanks for playing my game! Press q at any time during the game to quit.");
			System.out.println("Oh hey don't run into any monsters lol, they live in the empty spaces ;)");
			Hero.pickHeroes();
			Board.makeBoard(8, 8);
			Controller.control();
		}
		else {
			System.out.println("Ok cool, see you later then!");
		}
	}

}
