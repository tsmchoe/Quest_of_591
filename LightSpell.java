package play_game;

// Creates a lightning spell 
public class LightSpell extends Spell {
	
	private LightSpell(String name, int cost, int level, int dmg, int rng, int mu, String type) {
		//instantiates lighting spell
		nameSpell = name;
		lvlSpell = level;
		costSpell = cost;
		dmgSpell = dmg;
		rngSpell = rng;
		manaUsed = mu;
		typing = type;
	}
	
	private static void addSpell(Spell s) {
		//adds it to the spell list
		spellCollect.add(s);
	}
	
	
	public static void createLight() {
		//hardcoded lightning spells
		LightSpell i = new LightSpell("Thunder_Blast", 700, 4, 850, 30, 300,"light");
		LightSpell i2 = new LightSpell("LightningDagger", 350, 1, 450, 10, 100,"light");
		LightSpell i3 = new LightSpell("Electric_Arrows ", 450, 2, 600, 20, 150,"light");
		LightSpell i4 = new LightSpell("Spark_Needles", 800, 7, 1000, 1000,550,"light");
		addSpell(i);
		addSpell(i2);
		addSpell(i3);
		addSpell(i4);
	}

}