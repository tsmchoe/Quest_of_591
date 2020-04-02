package play_game;

//Creates an ice spell
public class IceSpell extends Spell {
	
	private IceSpell(String name, int cost, int level, int dmg, int rng, int mu, String type) {
		//instantiates ice spell
		nameSpell = name;
		lvlSpell = level;
		costSpell = cost;
		dmgSpell = dmg;
		rngSpell = rng;
		manaUsed = mu;
		typing = type;
	}
	
	private static void addSpell(Spell s) {
		spellCollect.add(s);
	}
	
	
	public static void createIce() {
		//hardcoded ice spells
		IceSpell i = new IceSpell("Frost_Blizzard", 700, 4, 850, 30, 300,"ice");
		IceSpell i2 = new IceSpell("Ice_Blade", 350, 1, 450, 10, 100,"ice");
		IceSpell i3 = new IceSpell("Arctic_storm", 450, 2, 600, 20, 150,"ice");
		IceSpell i4 = new IceSpell("Snow_Canon", 800, 7, 1000, 1000,550,"ice");
		addSpell(i);
		addSpell(i2);
		addSpell(i3);
		addSpell(i4);
	}

}