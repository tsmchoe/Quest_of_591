package play_game;
import java.util.Scanner;

//Allows the player to move around and get information on their character or quit any time

public class Controller {
	
	private static char heroToken = 'x';
	private static Scanner Input = new Scanner(System.in);

	public static void control() throws Exception {
		//Keeps the controller continuously moving
		while(true) {
			if (movement() == false) {
				break;
			}
		}
	}
	
	
	private static boolean movement() throws Exception {
		//allows the player to move using wasd.
		char x;
		int next = 0;
		boolean ender = true;
		System.out.println("Please enter which way you want to go: ");
		System.out.println("w = up, a = left, s = down, d = right, p = display map, i = menu, q = quit game ");
		while (ender) {
			x = Input.next().charAt(0);
			if (x == 'q' || x == 'Q') {
				System.out.println("Are you sure you want to leave (y/n) ?");
				x = Input.next().charAt(0);
				if (x == 'y' || x == 'Y') {
					System.out.println("Thanks for playing!");
					return false;
				}
			}
			else if (x == 'p' || x == 'P') {
				Board.printBoard();
				System.out.println();
				break;
			}
			else if (x == 'i' || x == 'I') {
				Hero.printHero();
				for (int i = 0; i < Hero.heroParty.size(); i++) {
					Hero exer = Hero.heroParty.get(i);
					infoHero(exer);
				}
				break;
			}
			else if (x == 'w' || x == 'W') {
				if (Board.currX-1 == Board.len) {
					System.out.println("Not a valid move! Try again: ");
				}
				else if(Board.board[Board.currX-1][Board.currY] == ' ') {
					Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX-1][Board.currY] = heroToken;
					Board.currX = Board.currX-1;
					//set up monster
					next = Cell.monsterAttack();
					break;
				}
				else if(Board.board[Board.currX-1][Board.currY] == 'm') {
					//Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX-1][Board.currY] = heroToken;
					Board.currX = Board.currX-1;
					//set up market call
					for(int t = 0; t < Hero.heroParty.size(); t++) {
						Hero man = Hero.heroParty.get(t);
						Market.sell(t, man);					
						}
					break;
				}
				else {
					System.out.println("Not a valid move! Try again: ");
				}
			}
			else if (x == 'a' || x == 'A') {
				if (Board.currY-1 < 0) {
					System.out.println("Not a valid move! Try again: ");
				}
				else if(Board.board[Board.currX][Board.currY-1] == ' ') {
					Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX][Board.currY-1] = heroToken;
					Board.currY = Board.currY-1;
					//set up monster
					next = Cell.monsterAttack();
					break;
				}
				else if(Board.board[Board.currX][Board.currY-1] == 'm') {
					//Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX][Board.currY-1] = heroToken;
					Board.currY = Board.currY-1;
					//set up market call
					for(int t = 0; t < Hero.heroParty.size(); t++) {
						Hero man = Hero.heroParty.get(t);
						Market.sell(t, man);					
						}
					break;
				}
				else {
					System.out.println("Not a valid move! Try again: ");
				}
			}
			else if (x == 's' || x == 'S') {
				if (Board.currX+1 < 0) {
					System.out.println("Not a valid move! Try again: ");
				}
				else if(Board.board[Board.currX+1][Board.currY] == ' ') {
					Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX+1][Board.currY] = heroToken;
					Board.currX = Board.currX+1;
					//set up monster
					next = Cell.monsterAttack();
					break;
				}
				else if(Board.board[Board.currX+1][Board.currY] == 'm') {
					//Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX+1][Board.currY] = heroToken;
					Board.currX = Board.currX+1;
					//set up market
					for(int t = 0; t < Hero.heroParty.size(); t++) {
						Hero man = Hero.heroParty.get(t);
						Market.sell(t, man);
					}
					break;
				}
				else {
					System.out.println("Not a valid move! Try again: ");
				}
			}
			else if (x == 'd' || x == 'D') {
				if (Board.currY+1 == Board.len) {
					System.out.println("Not a valid move! Try again: ");
				}
				else if(Board.board[Board.currX][Board.currY+1] == ' ') {
					Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX][Board.currY+1] = heroToken;
					Board.currY = Board.currY+1;
					//set up monster
					next = Cell.monsterAttack();
					break;
				}
				else if(Board.board[Board.currX][Board.currY+1] == 'm') {
					//Board.board[Board.currX][Board.currY] = ' ';
					Board.board[Board.currX][Board.currY+1] = heroToken;
					Board.currY = Board.currY+1;
					//set up market
					for(int t = 0; t < Hero.heroParty.size(); t++) {
						Hero man = Hero.heroParty.get(t);
						Market.sell(t, man);
					}
					break;
				}
				else {
					System.out.println("Not a valid move! Try again: ");
				}
			}
		}
		if (next == -1) {
			return false;
		}
		return true;
	}

	
	
	private static int menu() {
		//Battle menu interface for heroes to use when attacking
		System.out.println("+-----+-----------+");
		System.out.println("|    INFO MENU    |");
		System.out.println("+-----+-----------+");			
		System.out.println("|  1. |  Potion   |");
		System.out.println("|  2. |  Armor    |");
		System.out.println("|  3. |  Weapon   |");
		System.out.println("|  4. |  Exit     |");
		System.out.println("+-----+-----------+");
		System.out.println("Press 5 for information on the hero");
		System.out.println("Pick your action: ");
		int i = Input.nextInt();
		return i;
	}
	
	
	private static void infoHero(Hero h) {
		//Battle menu logic
		System.out.println("It's " + h.nameHero + "'s inventory!");
		int x = menu();
		if (x == 1) {
			System.out.println("Here are your potions!");
			if (h.Hbag.size() == 0) {
				System.out.println("You have no potions!!");
			}
			else {
				Potion p;
				while(true) {
					Potion.displayPot(h);
					System.out.println("Choose your item number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.Hbag.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						break;
					}
					else {
						p = h.Hbag.get(l-1);
						Potion.increaseStat(h, p);
						h.Hbag.remove(l-1);
						break;
					}
				}
			}
			infoHero(h);
		}
		else if (x == 2) {
			System.out.println("Here are your armor pieces!");
			if (h.HArmor.size() == 0) {
				System.out.println("You have no equipable item!!");
			}
			else {
				Armor a;
				while(true) {
					Armor.displayArmor(h);
					System.out.println("Choose your armor number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.HArmor.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						break;
					}
					else if(h.HArmor.get(l-1) == h.chest || h.HArmor.get(l-1) == h.head || h.HArmor.get(l-1) == h.feet) {
						System.out.println("You have this already equipped!");
					}
					else {
						a = h.HArmor.get(l-1);
						h.armoredHero(a,0);
						break;
					}
				}
			}
			infoHero(h);
		}
		else if(x == 3) {
			System.out.println("Here are your weapons!");
			if (h.HWeapon.size() == 0) {
				System.out.println("You have no equipable weapons!!");
			}
			else {
				Weapon w;
				while(true) {
					Weapon.displayWeapon(h);
					System.out.println("Choose your weapon number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.HWeapon.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						break;
					}
					else if(h.HWeapon.get(l-1) == h.lHand || h.HWeapon.get(l-1) == h.rHand) {
						System.out.println("You have this already equipped!");
					}
					else {
						w = h.HWeapon.get(l-1);
						h.equipHero(w,0);
						break;
					}
				}
			}
			infoHero(h);
		}
		
		else if (x == 4) {
			return;
		}
		
		else if(x == 5) {
			//Displays information on all battling parties
			Hero.printHero();
			if (h.rHand != null) {
				System.out.println(h.nameHero + " is holding a " + h.rHand.nameWeapon + " on the right");
			}
			else {
				System.out.println(h.nameHero + " is not holding anything in their right hand");
			}
			if (h.lHand != null) {
				System.out.println(h.nameHero + " is hodling a " + h.lHand.nameWeapon + " on the left");
			}
			else {
				System.out.println(h.nameHero + " is not holding anything in their left hand");
			}
			if (h.head != null) {
				System.out.println(h.nameHero + " has a " + h.head.nameArmor + " covering their head");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their head");
			}
			if (h.chest != null) {
				System.out.println(h.nameHero + " has a " + h.chest.nameArmor + " covering their chest");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their chest");
			}
			if (h.feet != null) {
				System.out.println(h.nameHero + " has a " + h.feet.nameArmor + " covering their feet");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their feet");
			}
			System.out.println();
			infoHero(h);
		}
		
		else {
			System.out.println("Not a valid number");
			infoHero(h);
		}
		
	}
}
