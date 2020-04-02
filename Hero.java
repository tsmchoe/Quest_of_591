package play_game;

import java.util.*;

//creates the Hero class that can be instantiated to all three kinds of heroes
public abstract class Hero implements Comparable<Hero>{
	//formatting heroes 
	private static String heroFormat = "| %-6d | %-20s | %-5d | %-6d | %-6d | %-8d | %-9d | %-7d | %-7d | %-9s | %-8d |%n";
	private static String infoFormat = "| %-6d | %-20s | %-5d | %-6d | %-6d | %-8d | %-9d | %-7d | %-7d | %-7d | %-9s |%n";
	//hero attributes
	public String nameHero;
	public int hpHero;
	public int defHero;
	public int lvlHero;
	public int manaHero;
	public int strHero;
	public int maxSTR;
	public int dxtHero;
	public int maxDXT;
	public int aglHero;
	public int maxAGL;
	public int moneyHero;
	public int expHero;
	public int maxMana;
	public String typing;
	private int hands = 2;
	public Weapon rHand;
	public Weapon lHand;
	public Armor head;
	public Armor chest;
	public Armor feet;
	public List<Potion> Hbag;
	public List<Armor> HArmor;
	public List<Spell> HSpell;
	public List<Weapon> HWeapon;
	public static List<Hero> heroPool = new ArrayList<Hero>();
	public static List<Hero> heroParty = new ArrayList<Hero>();
	private static Scanner Input = new Scanner(System.in);
	private static Random r = new Random();

	
	  @Override     
	public int compareTo(Hero h) {        
		  //overrides to compare heroes
		  if (this.lvlHero < h.lvlHero) {
	    	return -1;
	    }
		  else if (this.lvlHero > h.lvlHero) {
	    	return 1;
	    }
		  return 0;
	  }       
	
	public static void Hattack(Hero h, Monster m) {
		//Attack probability hero hits monster
		System.out.println(h.nameHero + " attacks " + m.nameMon);
		int w = r.nextInt(100);
		double dmg = h.attackHero() - m.defMon;
		if (w <= m.dodgeMon) {
			System.out.println(m.nameMon + " dodged! Dang!");
		}
		else {
			if (dmg <= 0) {
				m.hpMon += 0;
				System.out.println(h.nameHero + " did no damage to " + m.nameMon);
			}
			else {
				m.hpMon -= dmg;
				System.out.println(h.nameHero + " did " + dmg + " damage to " + m.nameMon);
			}
		}
	}
	public static int HMattack(Hero h, Monster m) {
		//Spell logic on how a player accesses and chooses it
		Spell sp;
		System.out.println("Pick your spell!");
		if (h.HSpell.size() == 0) {
			System.out.println("You have no spells!!");
		}
		else {
			while(true) {
				Spell.displaySpells(h);
				System.out.println("Choose your spell number: ");
				System.out.println("Hit -1 to go back: ");
				int l = Input.nextInt();
				if (l > h.HSpell.size()+1) {
					System.out.println("Not valid number! Choose again!");
				}
				else if (l == -1) {
					return -1;
				}
				else {
					sp = h.HSpell.get(l-1);
					double speller = h.spellHero(sp);
					if (speller == 0) {
						System.out.println("Try a different attack!");
						break;
					}
					else if (speller == -1) {
						break;	
					}
					else {
						h.HSpell.remove(l-1);
						int dmg = (int) h.spellHero(sp) - m.defMon;
						
						if (dmg <= 0) {
							m.hpMon += 0;
							System.out.println(h.nameHero + " did no damage to " + m.nameMon);
							return 0;
						}
						else {
							m.hpMon -= dmg;
							h.manaHero -= sp.manaUsed;
							System.out.println(h.nameHero + " did " + dmg + " damage to " + m.nameMon);
							
						}
						if(sp.typing == "fire") {
							m.defMon -= (m.defMon*0.1);
							System.out.println("Spell reduced " + m.nameMon + "'s defence!");
						}
						else if(sp.typing == "ice") {
							m.dmgMon -= (m.dmgMon * 0.1);
							System.out.println("Spell reduced " + m.nameMon + "'s damage!");
						}
						else if(sp.typing == "light") {
							m.dodgeMon -= (m.dodgeMon * 0.1);
							System.out.println("Spell reduced " + m.nameMon + "'s mobility!");
						}
						return dmg;
					}
				}
			}
		}
		return -1;
	}
	  
	private double attackHero() {
		//Calculates the hero's attack
		if (rHand == null && lHand == null) {
			return strHero * 0.5;
		}
		else if (rHand != null && lHand == null) {
			return (strHero + rHand.dmgWeapon) * 0.5;
		}
		else if (rHand == null && lHand != null) {
			return (strHero + lHand.dmgWeapon) * 0.5;
		}
		return (strHero + lHand.dmgWeapon + rHand.dmgWeapon) * 0.5;
	}
	
	private double spellHero(Spell s) {
		//Calculates the power of the spell the hero uses
		if (s.lvlSpell > lvlHero) {
			System.out.println("It's too powerful to use! You need to get stronger first!");
			return -1;
		}
		else if(s.manaUsed > manaHero) {
			System.out.println("You don't have enough MP");
			return 0;
		}
		return s.dmgSpell + (dxtHero/10000) * s.dmgSpell;
	}
	
	public double dodgeHero() {
		//returns Hero's ability to dodge the monster attack
		return aglHero * 0.2;
	}
	
	public void equipHero(Weapon w, int i) {
		//Allows hero to equip items to their hands - does for both hands and adds a handle if the weapon is a two hander
		if (w.lvlWeapon > lvlHero) {
			System.out.println("It's too powerful to equip! You need to get stronger first!");
		}
		else {
			if (i == 0) {
				System.out.println("Would you like to equip " + w.nameWeapon + " now? (y/n)");
			}
			else {
				System.out.println("Are you sure you want to equip " + w.nameWeapon + " ? (y/n)");
			}
			char enter = Input.next().charAt(0);
			if(enter == 'y' || enter == 'Y') {
				if(hands == 2) {
					if (w.heroHands > 1) {
						rHand = w;
						lHand = new Swords("Handle",0,0,0,0,"handle");
						hands -= w.heroHands;
					}
					else if (w.typing == "shields") {
						rHand = w;
						hands -= w.heroHands;
						defHero += w.dmgWeapon;
					}
					else {
						rHand = w;
						hands -= w.heroHands;
					}
				}
				else if(hands == 1) {
					if(w.heroHands > 1) {
						System.out.println("You need to free a hand to equip!");
					}
					else if (w.typing == "shields") {
						lHand = w;
						hands -= w.heroHands;
						defHero += w.dmgWeapon;
					}
					else {
						lHand = w;
						hands -= w.heroHands;
					}
				}
				else {
					System.out.println("You're hands are full, do you want to equip the new weapon? (y/n)");
					char read = Input.next().charAt(0);
					if (read == 'y' || read == 'Y') {
						System.out.println("Right hand: " + rHand.nameWeapon);
						System.out.println("Left hand: " + lHand.nameWeapon);
						System.out.println("Which would you like to dequip: (right/left)");
						while(true) {
							char hander = Input.next().charAt(0);
							if (hander == 'r') {
								dequipWeap(1);
								equipHero(w,1);
								break;
							}
							else if (hander == 'l') {
								if (lHand.nameWeapon == "Handle") {
									System.out.println("This is part of the weapon in your right hand! Dequip that to dequip this!");
								}
								else {
									dequipWeap(2);
									equipHero(w,1);
									break;
								}
							}
						}
					}
				}
			}
			else {
				System.out.println("Okay putting it in the bag");
			}
		}
	}
	
	
	public void dequipWeap(int i) {
		//dequips current weapon
		if (rHand != null && i == 1) {
			rHand = null;
			hands += 1;
			if (lHand.nameWeapon == "Handle" && lHand != null) {
				lHand = null;
				hands += 1;
			}
		}
		else if (lHand != null && i == 2) {
			lHand = null;
			hands += 1;
		}
		else {
			System.out.println("You don't have any weapons equiped");
		}
	}
	
	public void armoredHero(Armor a, int i) {
		//Equips armor to the hero
		if (a.lvlArmor > lvlHero) {
			System.out.println("It's too powerful to equip! You need to get stronger first!");
		}
		else {
			if (i == 0) {
				System.out.println("Would you like to equip " + a.nameArmor + " now? (y/n)");
			}
			char enter = Input.next().charAt(0);
			if(enter == 'y' || enter == 'Y') {
				if(a.positionArmor == 'h' && head == null) {
					head = a;
					defHero += a.damageArmor;
				}
				else if(a.positionArmor == 'm' && chest == null) {
					chest = a;
					defHero += a.damageArmor;
				}
				else if(a.positionArmor == 'f' && feet == null) {
					feet = a;
					defHero += a.damageArmor;
				}
				else {
					System.out.println("You already have armor covering you! Do you want to equip the new armor? (y/n)");
					char bodyArmr = Input.next().charAt(0);
					if (bodyArmr == 'y' || bodyArmr == 'Y') {
						deArmorHero(a.positionArmor);
						armoredHero(a,1);
					}
				}
			}
			else {
				System.out.println("Okay putting it in the bag");
			}
		}
	}
	
	public void deArmorHero(char c) {
		//takes off armor
		if (c == 'h') {
			defHero -= head.damageArmor;
			head = null;
		}
		else if (c == 'm') {
			defHero -= chest.damageArmor;
			chest = null;
		}
		else if (c == 'f') {
			defHero -= feet.damageArmor;
			feet = null;
		}
	}
	
	
	private static void herOptions() {
		//Prints out the choices for heroes in the beginning of the game
		Random r = new Random();
		boolean newer = true;
		List<Hero> Hstack = new ArrayList<Hero>();
		while(newer) {
			System.out.println("Choose your Hero!");
			System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+---------+-----------+%n");
			System.out.format("| Number |      Name            | Level |   HP   |  Mana  | Strength | Dexterity | Agility | Defence |  Coins  |    Type   |%n");
			System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+---------+-----------+%n");
			for (int i = 0; i < 4; i++) {
				int x = r.nextInt(heroPool.size());
				Hero h = heroPool.get(x);
				Hstack.add(h);
			    System.out.format(infoFormat, i+1, h.nameHero,h.lvlHero, h.hpHero,h.manaHero,h.strHero, h.dxtHero, h.aglHero, h.defHero, h.moneyHero,h.typing);
			}
			System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+---------+-----------+%n");
			System.out.println("Do you like your choices?");
			System.out.println("Type yes to continue or no to get new choices");
			String j = Input.next();
			if (j.equals("y") || j.equals("Y") || j.equals("yes")|| j.equals("Yes")) {
				System.out.println("Pick which number you want:");
				int z = Input.nextInt();
				if (z == 1) {
					heroParty.add(Hstack.get(0));
					heroPool.remove(Hstack.get(0));
					break;
				}
				else if (z == 2) {
					heroParty.add(Hstack.get(1));
					heroPool.remove(Hstack.get(1));
					break;
				}
				else if (z == 3) {
					heroParty.add(Hstack.get(2));
					heroPool.remove(Hstack.get(2));
					break;
				}
				else if (z == 4) {
					heroParty.add(Hstack.get(3));
					heroPool.remove(Hstack.get(3));
					break;
				}
				else {
					System.out.println("Try again: ");
				}
			}
			else {
				Hstack.clear();
				continue;
			}
		}
	}
	
	public static void pickHeroes() {
		//gives the user the options to pick one or more heroes
		int i = 1;
		Paladin.createPal();
		Warrior.createWar();
		Sorcerer.createSor();
		herOptions();
		while(i < 3) {
			System.out.println("Do you want to add another character? (y/n)");
			char e = Input.next().charAt(0);
			if (e == 'y' || e == 'Y') {
				herOptions();
				i++;
			}
			else {
				break;
			}
		}
		System.out.println("Great! Starting your adventure!");
		
	}
	
	
	public void levelUp() {
		//creating an empty level up method that each class uses differently
	}
	
	public static void printHero() {
		//For i to access the heroes information at any time
		System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+-----------+----------+%n");
		System.out.format("| Number |      Name            | Level |   HP   |  Mana  | Strength | Dexterity | Agility | Defence |   Type    |    Exp   |%n");
		System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+-----------+----------+%n");
		for (int i = 0; i < heroParty.size(); i++) {
			Hero h = heroParty.get(i);
		    System.out.format(heroFormat, i+1, h.nameHero,h.lvlHero, h.hpHero,h.manaHero,h.strHero, h.dxtHero, h.aglHero, h.defHero, h.typing,h.expHero);
		}
		System.out.format("+--------+----------------------+-------+--------+--------+----------+-----------+---------+---------+-----------+----------+%n");
	}

}
