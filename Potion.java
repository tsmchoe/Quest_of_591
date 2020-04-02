package play_game;
import java.util.*;

//Creates a potion object and all it's attributes
public class Potion {
	//potion formatting
	private static String potFormat = "| %-6d | %-20s | %-5d | %-10d | %-9s |%n";
	private static String potstoreFormat = "| %-6d | %-20s | %-5d | %-10d | %-9s | %-9d |%n";
	//potion attributes
	public int lvlPotion;
	public String namePotion;
	public int costPotion;
	public String incPotion;
	public int attribPotion;
	private static List<Potion> potCollect = new ArrayList<Potion>();

	
	private Potion(String name, int level, int cost, String inc, int att) {
		//instantiates potions
		namePotion = name;
		lvlPotion = level;
		costPotion = cost;
		incPotion = inc;
		attribPotion = att;
	}
	
	private static void addPot(Potion p) {
		//adds to general list
		potCollect.add(p);
	}
	
	public static Potion pickRandom() {
		//randomizes potions
		createPotions();
		Random r = new Random();
		int x = r.nextInt(potCollect.size());
		return potCollect.get(x);
	}
	
	public static void increaseStat(Hero h, Potion p){
		//increases certain stats
		if (p.lvlPotion > h.lvlHero) {
			System.out.println("Not experienced enough to use it!");
		}
		else if (p.incPotion == "HP") {
			if (h.hpHero >= h.lvlHero * 100) {
				System.out.println("You're at max health! No need!");
			}
			else {
				h.hpHero += p.attribPotion;
				if (h.hpHero > h.lvlHero * 100) {
					h.hpHero -= h.hpHero-(h.lvlHero * 100);
				}
				System.out.println("You're at " + h.hpHero + " HP");
			}
		}
		else if (p.incPotion == "MP") {
			if (h.manaHero >= h.maxMana) {
				System.out.println("You're at max mana! No need!");
			}
			else {
				h.manaHero += p.attribPotion;
				if (h.manaHero > h.maxMana) {
					h.manaHero -= h.manaHero-h.maxMana;
				}
				System.out.println("You're at " + h.manaHero + " MP");
			}
		}
		else if (p.incPotion == "STR") {
			h.strHero += p.attribPotion;
			System.out.println("You're at " + h.strHero + " strength");
		}
		else if (p.incPotion == "AGL") {
			h.aglHero += p.attribPotion;
			System.out.println("You're at " + h.aglHero + " agility");
		}
		else if (p.incPotion == "DXT") {
			h.dxtHero += p.attribPotion;
			System.out.println("You're at " + h.dxtHero + " dexterity");
		}
	}
	public static void normalizeStat(Hero h){
		//changing back the stats of heroes after using potions
		if (h.aglHero > h.maxAGL) {
			h.aglHero = h.maxAGL;
		}
		if (h.strHero > h.maxSTR) {
			h.strHero = h.maxSTR;
		}
		if (h.dxtHero > h.maxDXT) {
			h.dxtHero = h.maxDXT;
		}
	}
	
	public static void displayPot(Hero h) {
		//General display for potions
		System.out.format("+--------+----------------------+-------+------------+-----------+%n");
		System.out.format("| Number |      Name            | Level |  Increase  | Attribute |%n");
		System.out.format("+--------+----------------------+-------+------------+-----------+%n");
		for (int j = 0; j < h.Hbag.size(); j++) {
			Potion laps = h.Hbag.get(j);
			System.out.format(potFormat, j+1, laps.namePotion,laps.lvlPotion,laps.attribPotion,laps.incPotion);
		}
		System.out.format("+--------+----------------------+-------+------------+-----------+%n");

	}

	public static void displayStorePot(Hero h) {
		//store display for your potions
		System.out.format("+--------+----------------------+-------+------------+-----------+-----------+%n");
		System.out.format("| Number |      Name            | Level |  Increase  | Attribute |    Cost   |%n");
		System.out.format("+--------+----------------------+-------+------------+-----------+-----------+%n");
		for (int j = 0; j < h.Hbag.size(); j++) {
			Potion laps = h.Hbag.get(j);
			System.out.format(potstoreFormat, j+1, laps.namePotion,laps.lvlPotion,laps.attribPotion,laps.incPotion,laps.costPotion);
		}
		System.out.format("+--------+----------------------+-------+------------+-----------+-----------+%n");

	}
	
	private static void createPotions() {
		//hard coding potions
		Potion d = new Potion("Healing_Potion", 1, 250, "HP", 100);
		Potion d2 = new Potion("Strength_Potion", 1, 200, "STR", 75);
		Potion d3 = new Potion("Magic_Potion", 2, 350, "MP", 100);
		Potion d4 = new Potion("Luck_Elixir", 4, 500, "AGL", 65);
		Potion d5 = new Potion("Mermaid_Tears", 5, 850, "MP", 100);
		Potion d6 = new Potion("Ambrosia", 8, 1000, "HP", 150);
		addPot(d);
		addPot(d2);
		addPot(d3);
		addPot(d4);
		addPot(d5);
		addPot(d6);
	}
}
