package play_game;

import java.util.*;

//creates a general weapon class
public abstract class Weapon {
	//weapon formatting
	private static String weapFormat = "| %-6d | %-20s | %-5d | %-8d | %-5d | %-30s %n";
	private static String weapstoreFormat = "| %-6d | %-20s | %-5d | %-8d | %-5d | %-8d | %-30s %n";
	//weapon properties
	public String nameWeapon;
	public int lvlWeapon;
	public int dmgWeapon;
	public int heroHands;
	public int costWeapon;
	public String typing;
	public static List<Weapon> weaponCollect = new ArrayList<Weapon>();

	
	public static void displayWeapon(Hero h) {
		//general weapon display
		System.out.format("+--------+----------------------+-------+----------+-------+%n");
		System.out.format("| Number |         Name         | Level |  Damage  | Hands |%n");
		System.out.format("+--------+----------------------+-------+----------+-------+%n");
		for (int j = 0; j < h.HWeapon.size(); j++) {
			Weapon weap = h.HWeapon.get(j);
			String warn;
			if(weap == h.lHand || weap == h.rHand) {
				warn = " <- Already equipped!";
			}
			else {
				warn = "";
			}
			System.out.format(weapFormat, j+1, weap.nameWeapon,weap.lvlWeapon,weap.dmgWeapon,weap.heroHands,warn);
		}
		System.out.format("+--------+----------------------+-------+----------+-------+%n");

	}
	
	public static void displayStoreWeapon(Hero h) {
		//weapon display for the store selling
		System.out.format("+--------+----------------------+-------+----------+-------+----------+%n");
		System.out.format("| Number |         Name         | Level |  Damage  | Hands |   Cost   |%n");
		System.out.format("+--------+----------------------+-------+----------+-------+----------+%n");
		for (int j = 0; j < h.HWeapon.size(); j++) {
			Weapon weap = h.HWeapon.get(j);
			String warn;
			if(weap == h.lHand || weap == h.rHand) {
				warn = " <- Already equipped!";
			}
			else {
				warn = "";
			}
			System.out.format(weapstoreFormat, j+1, weap.nameWeapon,weap.lvlWeapon,weap.dmgWeapon,weap.heroHands,weap.costWeapon, warn);
		}
		System.out.format("+--------+----------------------+-------+----------+-------+----------+%n");

	}
	

	public static Weapon pickAttRandom() {
		//Randomizes weapon picking
		Swords.swordCreator();
		Daggers.dagCreator();
		Axes.axCreator();
		Random r = new Random();
		int x = r.nextInt(weaponCollect.size());
		return weaponCollect.get(x);
	}
}
