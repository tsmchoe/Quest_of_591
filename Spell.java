package play_game;
import java.util.*;

//creates a general class for spells
public abstract class Spell {
	//formatting spell
	private static String spellFormat = "| %-6d | %-20s | %-5d | %-9d | %-6d | %-8s |%n";
	private static String spellstoreFormat = "| %-6d | %-20s | %-5d | %-9d | %-6d | %-8s | %-8d |%n";

	//Spell attributes
	public int lvlSpell;
	public int dmgSpell;
	public String nameSpell;
	public int costSpell;
	public int rngSpell;
	public int manaUsed;
	public String typing;
	public static List<Spell> spellSlots = new ArrayList<Spell>();
	public static List<Spell> spellCollect = new ArrayList<Spell>();

	public static void displaySpells(Hero h) {
		//general spell display
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+%n");
		System.out.format("| Number |      Name            | Level |   Damage  |  Mana  |   Type   |%n");
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+%n");
		for (int j = 0; j < h.HSpell.size(); j++) {
			Spell expel = h.HSpell.get(j);
			System.out.format(spellFormat, j+1, expel.nameSpell,expel.lvlSpell,expel.dmgSpell,expel.manaUsed,expel.typing);
		}
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+%n");

	}
	
	public static void displayStoreSpells(Hero h) {
		//store spell display
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+----------+%n");
		System.out.format("| Number |      Name            | Level |   Damage  |  Mana  |   Type   |   Cost   |%n");
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+----------+%n");
		for (int j = 0; j < h.HSpell.size(); j++) {
			Spell expel = h.HSpell.get(j);
			System.out.format(spellstoreFormat, j+1, expel.nameSpell,expel.lvlSpell,expel.dmgSpell,expel.manaUsed,expel.typing,expel.costSpell);
		}
		System.out.format("+--------+----------------------+-------+-----------+--------+----------+----------+%n");

	}
	
	public static Spell pickRandom() {
		//picks random spells
		FireSpell.createFire();
		LightSpell.createLight();
		IceSpell.createIce();
		Random r = new Random();
		int x = r.nextInt(spellCollect.size());
		return spellCollect.get(x);
	}
}
