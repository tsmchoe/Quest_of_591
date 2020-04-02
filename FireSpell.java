package play_game;

//Creates a fire spell
public class FireSpell extends Spell{
	
	private FireSpell(String name, int cost, int level, int dmg, int rng, int mu, String type) {
		//instantiates fire spell
		nameSpell = name;
		lvlSpell = level;
		costSpell = cost;
		dmgSpell = dmg;
		rngSpell = rng;
		manaUsed = mu;
		typing = type;
	}
	
	private static void addSpell(Spell s) {
		//adds to general list
		spellCollect.add(s);
	}
	
	
	public static void createFire() {
		//hardcoded fire spells
		FireSpell f = new FireSpell("Flame_Tornado", 700, 4, 850, 30, 300,"fire");
		FireSpell f2 = new FireSpell("Breath_of_Fire", 350, 1, 450, 10, 100,"fire");
		FireSpell f3 = new FireSpell("Heat_Wave", 450, 2, 600, 20, 150,"fire");
		FireSpell f4 = new FireSpell("Lava_Commet", 800, 7, 1000, 1000,550,"fire");
		addSpell(f);
		addSpell(f2);
		addSpell(f3);
		addSpell(f4);
	}

	
}