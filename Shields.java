package play_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//creates a shield class - under weapons because you can use a shield to attack and defend :)
public class Shields extends Weapon {

	private static List<Shields> shCollect = new ArrayList<Shields>();

	
	public Shields(String name, int dmg, int level, int cost, int hands, String type) {
		//instantiates shields
		nameWeapon = name;
		lvlWeapon = level;
		dmgWeapon= dmg;
		costWeapon = cost;
		heroHands = hands;
		typing = type;
	}
	
	
	public static void addSh(Shields s) {
		shCollect.add(s);
	}
	
	public static Shields pickRandom() {
	//picks random shields
		Random r = new Random();
		int x = r.nextInt(shCollect.size());
		return shCollect.get(x);
	}

	public static void createSh() {
		//Hardcoded random shields
		Shields sh = new Shields("Iron Shield",350,3,600,1,"shield");
		Shields sh2 = new Shields("Hero's Shield",7000,10,1000,1,"shield");
		Shields sh3 = new Shields("Notebook",100,1,10,1,"shield");
		Shields sh4 = new Shields("Engineering Degree",99900,15,20000,1,"shield");
		addSh(sh);
		addSh(sh2);
		addSh(sh3);
		addSh(sh4);
	}
	
}