package play_game;

//Creates chestpiece armor
public class ChestPiece extends Armor {
	
	public ChestPiece(String name, int cost, int level, int dmg, char pos) {
		//instantiates chest armor
		nameArmor = name;
		lvlArmor = level;
		damageArmor= dmg;
		costArmor = cost;
		positionArmor = pos;
	}
	
	public static void addArm(ChestPiece c) {
		armCollect.add(c);
	}
	
	public static void createCP() {
		//hardcoded chest armor
		ChestPiece c = new ChestPiece("Breastplate",350,3,600,'m');
		ChestPiece c2 = new ChestPiece("Full_Body_Armor",7000,8,1000,'m');
		ChestPiece c3 = new ChestPiece("Shirt",100,1,10,'m');
		ChestPiece c4 = new ChestPiece("Supreme_hoodie",99900,9,20000,'m');
		addArm(c);
		addArm(c2);
		addArm(c3);
		addArm(c4);
	}
	
}