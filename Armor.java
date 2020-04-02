package play_game;

import java.util.*;

//Creates the general class for armor for the hero to equip
public abstract class Armor {
	//displaying armor
	private static String armFormat = "| %-6d | %-20s | %-5d | %-10d | %-8c | %-30s %n";
	private static String armstoreFormat = "| %-6d | %-20s | %-5d | %-10d | %-8c |  %-8d | %-30s %n";
	//Armor attributes
	public int lvlArmor;
	public String nameArmor;
	public int costArmor;
	public int damageArmor;
	public char positionArmor;
	public static List<Armor> armCollect = new ArrayList<Armor>();

	public static Armor pickRandom() {
		//picks random armor
		Boots.createBoots();
		ChestPiece.createCP();
		Helmet.createHelm();
		Random r = new Random();
		int x = r.nextInt(armCollect.size());
		return armCollect.get(x);
	}
	
	public static void displayArmor(Hero h) {
		//general display of armor
		System.out.format("+--------+----------------------+-------+------------+----------+%n");
		System.out.format("| Number |         Name         | Level | Protection | Position |%n");
		System.out.format("+--------+----------------------+-------+------------+----------+%n");
		for (int j = 0; j < h.HArmor.size(); j++) {
			String warn;
			Armor arm = h.HArmor.get(j);
			if(arm == h.chest || arm == h.head || arm == h.feet) {
				warn = " <- Already equipped!";
			}
			else {
				warn = "";
			}
			System.out.format(armFormat, j+1, arm.nameArmor,arm.lvlArmor,arm.damageArmor,arm.positionArmor, warn);
		}
		System.out.format("+--------+----------------------+-------+------------+----------+%n");

	}
	public static void displayStoreArmor(Hero h) {
		//Need to add cost for store
		System.out.format("+--------+----------------------+-------+------------+----------+----------+%n");
		System.out.format("| Number |         Name         | Level | Protection | Position |   Cost   |%n");
		System.out.format("+--------+----------------------+-------+------------+----------+----------+%n");
		for (int j = 0; j < h.HArmor.size(); j++) {
			String warn;
			Armor arm = h.HArmor.get(j);
			if(arm == h.chest || arm == h.head || arm == h.feet) {
				warn = " <- Already equipped!";
			}
			else {
				warn = "";
			}
			System.out.format(armstoreFormat, j+1, arm.nameArmor,arm.lvlArmor,arm.damageArmor,arm.positionArmor,arm.costArmor, warn);
		}
		System.out.format("+--------+----------------------+-------+------------+----------+----------+%n");

	}
	
}
