package play_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Creates the market class
public class Market {
	
	private static Scanner Input = new Scanner(System.in);
	private static boolean finish = true;
	//Used to keep track of items on shelf
	private static List<Weapon> Wstack = new ArrayList<Weapon>();
	private static List<Potion> Pstack = new ArrayList<Potion>();
	private static List<Armor> Astack = new ArrayList<Armor>();
	private static List<Shields> Sstack = new ArrayList<Shields>();
	private static List<Spell> spStack = new ArrayList<Spell>();
	//Used to print the menus nicely
	private static AsciiArt artGen = new AsciiArt();
	private static String potFormat = "| %-6d | %-20s | %-5d | %-6d | %-10s | %-5d |%n";
	private static String weapFormat = "| %-6d | %-20s | %-5d | %-6d | %-10d | %-5d | %-5s |%n";
	private static String armFormat = "| %-6d | %-20s | %-5d | %-6d | %-10d | %-5s |%n";
	private static String shieldFormat = "| %-6d | %-20s | %-5d | %-6d | %-10d | %-5s |%n";
	private static String spFormat = "| %-6d | %-20s | %-5d | %-6d | %-10d | %-8d | %-5d | %-7s |%n";
	
	
	public static void sell(int q, Hero h) throws Exception{
		//Main market menu
		int x;
		char y;
		//makes sure items don't change until heroes enter a new market
		int ptoken = 0;
		int wtoken = 0;
		int atoken = 0;
		int stoken = 0;
		System.out.println();
		if (q == 0) {
			//so Market doesn't repetitively show up
			artGen.printTextArt("Market", AsciiArt.ART_SIZE_SMALL);
			System.out.println("Check out our wares!\n");
		}
		while(true) {
			System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			System.out.println("What would " + h.nameHero +" like to buy?");
			System.out.println("+-----+-----------+");
			System.out.println("| Num |  Options  |");
			System.out.println("+-----+-----------+");			
			System.out.println("|  1. |  Potions  |");
			System.out.println("|  2. |  Weapons  |");
			System.out.println("|  3. |  Armor    |");
			System.out.println("|  4. |  Spells   |");
			System.out.println("|  5. |  Sell IM  |");
			System.out.println("|  6. |  Exit     |");
			System.out.println("+-----+-----------+");	
			System.out.println("Enter number:");
			while(true) {
				x = Input.nextInt();
				if (x == 1) {
					//go to potion options
					potAssort(h,ptoken);
					ptoken += 1;
					break;
				}
				else if (x == 2) {
					//go to Weapon options
					weaponAssort(h,wtoken);
					wtoken += 1;
					break;
				}
				else if (x == 3) {
					//go to Armor options
					armAssort(h,atoken);
					atoken += 1;
					break;
				}
				else if (x == 4) {
					//go to Spell options
					spellAssort(h,stoken);
					stoken += 1;
					break;
				}
				else if (x == 5) {
					sellBack(h);
					break;
				}
				else if (x == 6) {
					break;
				}
				else {
					System.out.println("Not a valid number! Try again!");
					System.out.println("What would you like to buy (enter number): ");
				}
			}
			System.out.println("Would you like to leave the shop? (y/n)");
			y = Input.next().charAt(0);
			if (y == 'y' || y == 'Y') {
				System.out.println("Thanks for coming!");
				break;
			}
			else {
				continue;
			}
		}
	}
	
	private static void sellBack(Hero h) {
		//Menu for selling items back at half price
		while(true) {
			while(true) {
				System.out.println("Choose what you want to sell: ");
				System.out.println("+-----+-----------+");
				System.out.println("| Num |  Options  |");
				System.out.println("+-----+-----------+");			
				System.out.println("|  1. |  Potions  |");
				System.out.println("|  2. |  Weapons  |");
				System.out.println("|  3. |  Armor    |");
				System.out.println("|  4. |  Spells   |");
				System.out.println("|  5. |  Exit     |");
				System.out.println("+-----+-----------+");
				System.out.println("Enter number: ");
				int x = Input.nextInt();
		
				if (x == 1) {
					System.out.println("Here are your potions: ");
					Potion p;
					while(true) {
						Potion.displayStorePot(h);
						System.out.println("Choose the item number you want to sell: ");
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
							h.moneyHero += (p.costPotion/2);
							h.Hbag.remove(l-1);
							break;
						}
					}
				}
				else if (x == 2) {
					System.out.println("Here are your Weapons and Shields: ");
					Weapon w;
					while(true) {
						Weapon.displayStoreWeapon(h);
						System.out.println("Choose the weapon number you want to sell: ");
						System.out.println("Type -1 to go back!");
						int l = Input.nextInt();
						if (l > h.HWeapon.size()+1) {
							System.out.println("Not valid number! Choose again!");
						}
						else if(l == -1) {
							break;
						}
						else {
							w = h.HWeapon.get(l-1);
							h.moneyHero += (w.costWeapon/2);
							if(w.heroHands == 2 || h.rHand == w) {
								h.dequipWeap(1);
							}
							else if (h.lHand == w){
								h.dequipWeap(2);
							}
							h.HWeapon.remove(l-1);
							break;
						}
					}
				}
				else if (x == 3) {
					System.out.println("Here is your armor: ");
					Armor a;
					while(true) {
						Armor.displayStoreArmor(h);
						System.out.println("Choose the item number you want to sell: ");
						System.out.println("Type -1 to go back!");
						int l = Input.nextInt();
						if (l > h.HArmor.size()+1) {
							System.out.println("Not valid number! Choose again!");
						}
						else if(l == -1) {
							break;
						}
						else {
							a = h.HArmor.get(l-1);
							h.moneyHero += (a.costArmor/2);
							if(a.positionArmor == 'h' && h.head == a) {
								h.deArmorHero('h');
							}
							else if(a.positionArmor == 'm' && h.chest == a) {
								h.deArmorHero('m');
							}
							else if(a.positionArmor == 'f' && h.feet == a) {
								h.deArmorHero('f');
							}
							h.HArmor.remove(l-1);
							break;
						}
					}
				}
				else if (x == 4) {
					System.out.println("Here are your spells: ");
					Spell s;
					while(true) {
						Spell.displayStoreSpells(h);
						System.out.println("Choose the spell number you want to sell: ");
						System.out.println("Type -1 to go back!");
						int l = Input.nextInt();
						if (l > h.Hbag.size()+1) {
							System.out.println("Not valid number! Choose again!");
						}
						else if(l == -1) {
							break;
						}
						else {
							s = h.HSpell.get(l-1);
							h.moneyHero += (s.costSpell/2);
							h.HSpell.remove(l-1);
							break;
						}
					}
				}
				else if (x == 5) {
					break;
				}
				else {
					System.out.println("Not a valid number! Try again!");
				}
				System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			}
			System.out.println("Are you done selling stuff? (y/n)");
			char y = Input.next().charAt(0);
			if (y == 'y' || y == 'Y') {
				break;
			}
			else {
				continue;
			}
		}
	}
	
	
	private static void potAssort(Hero h, int token) throws Exception {
		//Menu for items
		int x;
		System.out.println();
		artGen.printTextArt("Potions", AsciiArt.ART_SIZE_SMALL);
		System.out.println("Here are your choices of mass power: ");
		System.out.println();
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		System.out.format("| Number |      Name            | Level |  Cost  |  Inc Stat  | Boost |%n");
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		if(token < 1) {
			for (int i = 0; i < 4; i++) {
				Potion p = Potion.pickRandom();
				Pstack.add(p);
				System.out.format(potFormat, i+1, p.namePotion,p.lvlPotion,p.costPotion,p.incPotion,p.attribPotion);
			}
		}
		else {
			for (int i = 0; i < 4; i++) {
				Potion p = Pstack.get(i);
				System.out.format(potFormat, i+1, p.namePotion,p.lvlPotion,p.costPotion,p.incPotion,p.attribPotion);
			}
		}
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		System.out.println("Hit 5 to go back");
		System.out.println("What would you like to buy (enter number): ");
		while(true) {
			while(true) {
				x = Input.nextInt();
				if (x == 1) {
					Potion p = Pstack.get(0);
					if (h.moneyHero < p.costPotion) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < p.lvlPotion) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.Hbag.add(p);
						h.moneyHero -= p.costPotion;
					}
					break;
				}
				else if (x == 2) {
					Potion p2 = Pstack.get(1);
					if (h.moneyHero < p2.costPotion) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < p2.lvlPotion) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.Hbag.add(p2);
						h.moneyHero -= p2.costPotion;
					}
					break;
				}
				else if (x == 3) {
					Potion p3 = Pstack.get(2);
					if (h.moneyHero < p3.costPotion) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < p3.lvlPotion) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.Hbag.add(p3);
						h.moneyHero -= p3.costPotion;
					}
					break;
				}
				else if (x == 4) {
					Potion p4 = Pstack.get(3);
					if (h.moneyHero < p4.costPotion) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < p4.lvlPotion) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.Hbag.add(p4);
						h.moneyHero -= p4.costPotion;
					}
					break;
				}
				else if (x == 5) {
					break;
				}
				else {
					System.out.println("Not a valid number! Try again!");
					System.out.println("What would you like to buy (enter number): ");
				}
			}
			System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			System.out.println("Want to keep looking? (y/n)");
			int b = Input.next().charAt(0);
			if(b == 'n'|| b == 'N') {
				break;
			}
			else {
				System.out.println("Excellent choice! Pick another number:");
				continue;
			}
		}
	}
	
	private static void weaponAssort(Hero h, int token) throws Exception {
		//Menu for weapons
		int x;
		artGen.printTextArt("Weapons", AsciiArt.ART_SIZE_SMALL);
		System.out.println("Here are your choices of mass destruction: ");
		System.out.format("+--------+----------------------+-------+--------+------------+-------+-------+%n");
		System.out.format("| Number |      Name            | Level |  Cost  |   Damage   | Hands |  Type |%n");
		System.out.format("+--------+----------------------+-------+--------+------------+-------+-------+%n");
		if(token < 1) {
			for (int i = 0; i < 6; i++) {
				Weapon w = Weapon.pickAttRandom();
				Wstack.add(w);
		    	System.out.format(weapFormat, i+1, w.nameWeapon,w.lvlWeapon,w.costWeapon,w.dmgWeapon,w.heroHands,w.typing);
			}
		}
		else {
			for (int i = 0; i < 6; i++) {
				Weapon w = Wstack.get(i);
		    	System.out.format(weapFormat, i+1, w.nameWeapon,w.lvlWeapon,w.costWeapon,w.dmgWeapon,w.heroHands,w.typing);
			}
		}
		System.out.format("+--------+----------------------+-------+--------+------------+-------+-------+%n");
		System.out.println("Hit 7 to go back");
		System.out.println("What would you like to buy (enter number): ");
		while(true) {
			while(finish) {
				x = Input.nextInt();
				if (x == 1) {
					Weapon w = Wstack.get(0);
					if (h.HWeapon.contains(w)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w);
						h.equipHero(w,0);
						h.moneyHero -= w.costWeapon;
					}
					break;
				}
				else if (x == 2) {
					Weapon w2 = Wstack.get(1);
					if (h.HWeapon.contains(w2)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w2.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w2.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w2);
						h.equipHero(w2,0);
						h.moneyHero -= w2.costWeapon;
					}
					break;
				}
				else if (x == 3) {
					Weapon w3 = Wstack.get(2);
					if (h.HWeapon.contains(w3)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w3.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w3.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w3);
						h.equipHero(w3,0);
						h.moneyHero -= w3.costWeapon;
					}
					break;
				}
				else if (x == 4) {
					Weapon w4 = Wstack.get(3);
					if (h.HWeapon.contains(w4)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w4.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w4.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w4);
						h.equipHero(w4,0);
						h.moneyHero -= w4.costWeapon;
					}
					break;
				}
				else if (x == 5) {
					Weapon w5 = Wstack.get(4);
					if (h.HWeapon.contains(w5)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w5.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w5.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w5);
						h.equipHero(w5,0);
						h.moneyHero -= w5.costWeapon;
					}
					break;
				}
				else if (x == 6) {
					Weapon w6 = Wstack.get(5);
					if (h.HWeapon.contains(w6)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < w6.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < w6.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(w6);
						h.equipHero(w6,0);
						h.moneyHero -= w6.costWeapon;
					}
					break;
				}
				else if (x == 7) {
					break;
				}
			
			else {
				System.out.println("Not a valid number! Try again!");
				System.out.println("What would you like to buy (enter number): ");
			}
		}
			System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			System.out.println("Want to keep looking? (y/n)");
			int b = Input.next().charAt(0);
			if(b == 'n'|| b == 'N') {
				break;
			}
			else {
				System.out.println("Excellent choice! Pick another number:");
				continue;
			}
		}
	}
	
	
	private static void armAssort(Hero h, int token) throws Exception {
		//Menu for armor
		int x;
		Shields.createSh();
		artGen.printTextArt("Armor", AsciiArt.ART_SIZE_SMALL);
		System.out.println("Here are your choices of mass protection: ");
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		System.out.format("| Number |      Name            | Level |  Cost  |   Damage   | Pos/H |%n");
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		if (token < 1) {
			for (int i = 0; i < 4; i++) {
				Armor a = Armor.pickRandom();
				Astack.add(a);
				System.out.format(armFormat, i+1, a.nameArmor,a.lvlArmor,a.costArmor,a.damageArmor,a.positionArmor);
			}
			for (int k = 0; k < 2; k++) {
				Shields s = Shields.pickRandom();
				Sstack.add(s);
				System.out.format(shieldFormat, k+5, s.nameWeapon,s.lvlWeapon,s.costWeapon,s.dmgWeapon,s.heroHands);
			}
		}
		else {
			for (int i = 0; i < 4; i++) {
				Armor a = Astack.get(i);
				System.out.format(armFormat, i+1, a.nameArmor,a.lvlArmor,a.costArmor,a.damageArmor,a.positionArmor);
			}
			for (int k = 0; k < 2; k++) {
				Shields s = Sstack.get(k);
				System.out.format(shieldFormat, k+5, s.nameWeapon,s.lvlWeapon,s.costWeapon,s.dmgWeapon,s.heroHands);
			}
		}
		System.out.format("+--------+----------------------+-------+--------+------------+-------+%n");
		System.out.println("Hit 7 to go back");
		System.out.println("What would you like to buy (enter number): ");
		while(true) {
			while(finish) {
				x = Input.nextInt();
				if (x == 1) {
					Armor a = Astack.get(0);
					if (h.HArmor.contains(a)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < a.costArmor) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < a.lvlArmor) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HArmor.add(a);
						h.armoredHero(a,0);
						h.moneyHero -= a.costArmor;
					}
					break;
				}
				else if (x == 2) {
					Armor a2 = Astack.get(1);
					if (h.HArmor.contains(a2)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < a2.costArmor) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < a2.lvlArmor) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HArmor.add(a2);
						h.armoredHero(a2,0);
						h.moneyHero -= a2.costArmor;
					}
					break;
				}
				else if (x == 3) {
					Armor a3 = Astack.get(2);
					if (h.HArmor.contains(a3)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < a3.costArmor) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < a3.lvlArmor) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HArmor.add(a3);
						h.armoredHero(a3,0);
						h.moneyHero -= a3.costArmor;
					}
					break;
				}
				else if (x == 4) {
					Armor a4 = Astack.get(3);
					if (h.HArmor.contains(a4)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < a4.costArmor) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < a4.lvlArmor) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HArmor.add(a4);
						h.armoredHero(a4,0);
						h.moneyHero -= a4.costArmor;
					}
					break;
				}
				else if (x == 5) {
					Shields s = Sstack.get(0);
					if (h.HWeapon.contains(s)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < s.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(s);
						h.equipHero(s,0);
						h.moneyHero -= s.costWeapon;
					}
					break;
				}
				else if (x == 6) {
					Shields s2 = Sstack.get(1);
					if (h.HWeapon.contains(s2)) {
						System.out.println("You already have this item! Buy something else!");
					}
					else if (h.moneyHero < s2.costWeapon) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s2.lvlWeapon) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HWeapon.add(s2);
						h.equipHero(s2,0);
						h.moneyHero -= s2.costWeapon;
					}
					break;
				}
				else if (x == 7) {
					break;
				}
				else {
					System.out.println("Not a valid number! Try again!");
					System.out.println("What would you like to buy (enter number): ");
				}
			}
			System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			System.out.println("Want to keep looking? (y/n)");
			int b = Input.next().charAt(0);
			if(b == 'n'|| b == 'N') {
				break;
			}
			else {
				System.out.println("Excellent choice! Pick another number:");
				continue;
			}
		}
	}
	
	
	private static void spellAssort(Hero h , int token) throws Exception {
		//Menu for spells
		int x;
		artGen.printTextArt("Spells", AsciiArt.ART_SIZE_SMALL);
		System.out.println("Here are your choices of mass destruction: ");
		System.out.format("+--------+----------------------+-------+--------+------------+----------+-------+---------+%n");
		System.out.format("| Number |      Name            | Level |  Cost  |   Damage   |   Mana   | Range |   Type  |%n");
		System.out.format("+--------+----------------------+-------+--------+------------+----------+-------+---------+%n");
		if (token < 1) {
			for (int i = 0; i < 4; i++) {
				Spell s = Spell.pickRandom();
				spStack.add(s);
				System.out.format(spFormat, i+1, s.nameSpell, s.lvlSpell, s.costSpell, s.dmgSpell, s.manaUsed, s.rngSpell,s.typing);
			}
		}
		else {
			for (int i = 0; i < 4; i++) {
				Spell s = spStack.get(i);
				System.out.format(spFormat, i+1, s.nameSpell, s.lvlSpell, s.costSpell, s.dmgSpell, s.manaUsed, s.rngSpell,s.typing);
			}
		}
		System.out.format("+--------+----------------------+-------+--------+------------+----------+-------+---------+%n");
		System.out.println("Hit 5 to go back");
		System.out.println("What would you like to buy (enter number): ");
		while(true) {
			while(finish) {
				x = Input.nextInt();
				if (x == 1) {
					Spell s = spStack.get(0);
					if (h.moneyHero < s.costSpell) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s.lvlSpell) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HSpell.add(s);
						h.moneyHero -= s.costSpell;
					}
					break;
				}
				else if (x == 2) {
					Spell s2 = spStack.get(1);
					if (h.moneyHero < s2.costSpell) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s2.lvlSpell) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HSpell.add(s2);
						h.moneyHero -= s2.costSpell;
					}
					break;
				}
				else if (x == 3) {
					Spell s3 = spStack.get(2);
					if (h.moneyHero < s3.costSpell) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s3.lvlSpell) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HSpell.add(s3);
						h.moneyHero -= s3.costSpell;
					}
					break;
				}
				else if (x == 4) {
					Spell s4 = spStack.get(3);
					if (h.moneyHero < s4.costSpell) {
						System.out.println("Not enough money to buy!");
					}
					else if (h.lvlHero < s4.lvlSpell) {
						System.out.println("Too powerful to buy!");
					}
					else {
						h.HSpell.add(s4);
						h.moneyHero -= s4.costSpell;
					}
					break;
				}
				else if (x == 5) {
					break;
				}
				else {
					System.out.println("Not a valid number! Try again!");
					System.out.println("What would you like to buy (enter number): ");
				}
			}
			System.out.println(h.nameHero + " has " + h.moneyHero + " coins");
			System.out.println("Want to keep looking? (y/n)");
			int b = Input.next().charAt(0);
			if(b == 'n'|| b == 'N') {
				break;
			}
			else {
				System.out.println("Excellent choice! Pick another number:");
				continue;
			}
		}
	}
	
	
}
