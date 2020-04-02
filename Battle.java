package play_game;

import java.util.*;
//This is the class where the logic for battles take place

public class Battle {
	private static AsciiArt artGen = new AsciiArt();
	private static Scanner Input = new Scanner(System.in);
	
	private static char menu() {
		//Battle menu interface for heroes to use when attacking
		System.out.println("+-----+-----------+");
		System.out.println("|   BATTLE MENU   |");
		System.out.println("+-----+-----------+");			
		System.out.println("|  z. |  Attack   |");
		System.out.println("|  x. |  Spell    |");
		System.out.println("|  c. |  Potion   |");
		System.out.println("|  v. |  Armor    |");
		System.out.println("|  b. |  Weapon   |");
		System.out.println("+-----+-----------+");
		System.out.println("Press i for infomation on the fight!");
		System.out.println("Pick your action: ");
		char i = Input.next().charAt(0);
		return i;
	}
	
	
	private static void strike(Hero h, Monster m) {
		//Battle menu logic
		System.out.println("It's " + h.nameHero + "'s turn!");
		char x = menu();
		if (x == 'z' || x == 'Z') {
			Hero.Hattack(h, m);
		}
		else if (x == 'x' || x == 'X') {
			int late = Hero.HMattack(h, m);
			if (late == 0) {
				System.out.println("Realizing the spell didn't do damage, " + h.nameHero + " tries a regular attack!");
				Hero.Hattack(h, m);
			}
			else if (late == -1 ){
				strike(h,m);
			}
		}
		else if (x == 'c' || x == 'C') {
			System.out.println("Here are your potions!");
			if (h.Hbag.size() == 0) {
				System.out.println("You have no potions!!");
				strike(h,m);
			}
			else {
				Potion p;
				while(true) {
					Potion.displayPot(h);
					System.out.println("Choose your item number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.Hbag.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						strike(h,m);
						break;
					}
					else {
						p = h.Hbag.get(l-1);
						Potion.increaseStat(h, p);
						h.Hbag.remove(l-1);
						break;
					}
				}
			}
		}
		else if (x=='v' || x == 'V') {
			System.out.println("Here are your armor pieces!");
			if (h.HArmor.size() == 0) {
				System.out.println("You have no equipable item!!");
				strike(h,m);
			}
			else {
				Armor a;
				while(true) {
					Armor.displayArmor(h);
					System.out.println("Choose your armor number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.HArmor.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						strike(h,m);
						break;
					}
					else if(h.HArmor.get(l-1) == h.chest || h.HArmor.get(l-1) == h.head || h.HArmor.get(l-1) == h.feet) {
						System.out.println("You have this already equipped!");
					}
					else {
						a = h.HArmor.get(l-1);
						h.armoredHero(a,0);
						break;
					}
				}
			}
		}
		else if(x == 'b' || x == 'B') {
			System.out.println("Here are your weapons!");
			if (h.HWeapon.size() == 0) {
				System.out.println("You have no equipable weapons!!");
				strike(h,m);
			}
			else {
				Weapon w;
				while(true) {
					Weapon.displayWeapon(h);
					System.out.println("Choose your weapon number: ");
					System.out.println("Type -1 to go back!");
					int l = Input.nextInt();
					if (l > h.HWeapon.size()+1) {
						System.out.println("Not valid number! Choose again!");
					}
					else if(l == -1) {
						strike(h,m);
						break;
					}
					else if(h.HWeapon.get(l-1) == h.lHand || h.HWeapon.get(l-1) == h.rHand) {
						System.out.println("You have this already equipped!");
					}
					else {
						w = h.HWeapon.get(l-1);
						h.equipHero(w,0);
						break;
					}
				}
			}
		}
		
		else if(x =='i' || x == 'I') {
			//Displays information on all battling parties
			Hero.printHero();
			if (h.rHand != null) {
				System.out.println(h.nameHero + " is holding a " + h.rHand.nameWeapon + " on the right");
			}
			else {
				System.out.println(h.nameHero + " is not holding anything in their right hand");
			}
			if (h.lHand != null) {
				System.out.println(h.nameHero + " is hodling a " + h.lHand.nameWeapon + " on the left");
			}
			else {
				System.out.println(h.nameHero + " is not holding anything in their left hand");
			}
			if (h.head != null) {
				System.out.println(h.nameHero + " has a " + h.head.nameArmor + " covering their head");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their head");
			}
			if (h.chest != null) {
				System.out.println(h.nameHero + " has a " + h.chest.nameArmor + " covering their chest");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their chest");
			}
			if (h.feet != null) {
				System.out.println(h.nameHero + " has a " + h.feet.nameArmor + " covering their feet");
			}
			else {
				System.out.println(h.nameHero + " has nothing on their feet");
			}
			System.out.println();
			Monster.printMon();
			strike(h,m);
		}
		
		else {
			System.out.println("No other option but to fight! ");
			strike(h,m);
		}
		
	}
	
	private static void endHturn(Hero h) {
		//Gives heroes a boost at the end of their turn but is sure not to go past max hp/mp
		if(h.hpHero < 0) {
			System.out.println(h.nameHero + " fainted...");
			h.hpHero = 0;
		}
		else if(h.manaHero < 0) {
			h.manaHero = 0;
		}
		else if (h.hpHero < h.lvlHero * 100) {
			h.hpHero += ((h.lvlHero * 100)*0.05);
			if (h.hpHero > h.lvlHero * 100) {
				h.hpHero -= h.hpHero-(h.lvlHero * 100);
			}
			System.out.println(h.nameHero + " gained back some health");
		}
		else if (h.manaHero < h.maxMana) {
			h.manaHero += (h.maxMana*0.05);
			if (h.manaHero > h.maxMana) {
				h.manaHero -= h.manaHero-h.maxMana;
			}
			System.out.println(h.nameHero + " gained back some mana");
		}
		
	}
	
	private static void endMturn(Monster m) {
		//Checks if monster is defeated
		if(m.hpMon < 0) {
			System.out.println(m.nameMon + " was defeated!");
			m.hpMon = 0;
		}
	}
	
	private static void tbb(int i, Hero h, Hero h2, Hero h3, Monster m, Monster m2, Monster m3) {
		//Logic between the battle - scales from 1v1 to 3v3
		if (i == 1) {
			//1v1
			strike(h,m);
			if (m.hpMon > 0) {
				Monster.Mattack(h,m);
			}
			endMturn(m);
			endHturn(h);	
		}
		else if (i == 2) {
			//2v2 at most
			int Hcount = 0;
			int Mcount = 0;
			for (int k = 0; k < Hero.heroParty.size(); k++) {
				Hero fighter = Hero.heroParty.get(k);
				Monster mon = Monster.monLineup.get(k);
				if(fighter.hpHero > 0) {
					Hcount++;
				}
				if (mon.hpMon > 0) {
					Mcount++;
				}
			}
			if (Hcount == 1 && Mcount==2) {
				if (h.hpHero <= 0) {
					strike(h2,m2);
					endMturn(m2);
					Monster.Mattack(h2,m);
					if (m2.hpMon > 0) {
						Monster.Mattack(h2,m2);
					}
					endHturn(h2);
				}
				else if(h2.hpHero <= 0) {
					strike(h,m);
					endMturn(m);
					if (m.hpMon > 0) {
						Monster.Mattack(h,m);
					}
					Monster.Mattack(h,m2);
					endHturn(h);
				}
			}
			else if (Hcount == 2 && Mcount == 1) {
				if(m.hpMon <= 0) {
					strike(h,m2);
					endMturn(m2);
					if (m2.hpMon > 0) {
						strike(h2,m2);
						if (m2.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
					}
					endHturn(h);
					endHturn(h2);
				}
				else if(m2.hpMon <= 0) {
					strike(h,m);
					endMturn(m);
					if (m.hpMon > 0) {
						strike(h2,m);
						if (m.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
					}
					endHturn(h);
					endHturn(h2);
				}
			}
			else if (Hcount == 1 && Mcount == 1) {
				if (h.hpHero <= 0) {
					if (m.hpMon <= 0) {
						tbb(1,h2, null, null, m2, null, null);
					}
					else {
						tbb(1,h2, null, null, m, null, null);
					}
				}
				else if (h2.hpHero <= 0) {
					if (m.hpMon <= 0) {
						tbb(1,h, null, null, m2, null, null);
					}
					else {
						tbb(1,h, null, null, m, null, null);
					}
				}
			}
			else {
				strike(h,m);
				endMturn(m);
				strike(h2,m2);
				endMturn(m2);
				if (m.hpMon > 0) {
					Monster.Mattack(h,m);
				}
				if (m2.hpMon > 0) {
					Monster.Mattack(h2,m2);
				}
				endHturn(h);
				endHturn(h2);
			}
		}
		else if(i==3) {
			//3v3 at most
			int Hcount = 0;
			int Mcount = 0;
			for (int k = 0; k < Hero.heroParty.size(); k++) {
				Hero fighter = Hero.heroParty.get(k);
				Monster mon = Monster.monLineup.get(k);
				if(fighter.hpHero > 0) {
					Hcount++;
				}
				if (mon.hpMon >0) {
					Mcount++;
				}
			}
			
			if (Hcount == 3 && Mcount == 3) {
				strike(h,m);
				endMturn(m);
				strike(h2,m2);
				endMturn(m2);
				strike(h3,m3);
				endMturn(m3);
				if (m.hpMon > 0) {
					Monster.Mattack(h,m);
				}
				if (m2.hpMon > 0) {
					Monster.Mattack(h2,m2);
				}
				if (m.hpMon > 0) {
					Monster.Mattack(h3,m3);
				}
				endHturn(h);
				endHturn(h2);
				endHturn(h3);
			}
			else if (Hcount == 2 && Mcount == 3) {
				if (h.hpHero <= 0) {
					strike(h2,m2);
					endMturn(m2);
					strike(h3,m3);
					endMturn(m3);
					Monster.Mattack(h2,m);
					if (m2.hpMon > 0) {
						if (h2.hpHero >0) {
							Monster.Mattack(h2,m2);
						}
						else {
							Monster.Mattack(h3,m2);
						}
					}
					if (m3.hpMon > 0) {
						Monster.Mattack(h3,m3);
					}
					endHturn(h2);
					endHturn(h3);
				}
				else if (h2.hpHero <= 0) {
					strike(h,m);
					endMturn(m);
					strike(h3,m3);
					endMturn(m3);
					if (m.hpMon > 0) {
						Monster.Mattack(h,m);
					}
					if (m2.hpMon > 0) {
						if (h.hpHero > 0) {
							Monster.Mattack(h,m2);
						}
						else {
							Monster.Mattack(h3,m2);
						}
					}
					if (h3.hpHero > 0) {
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
					}
					endHturn(h);
					endHturn(h3);
				}
				else if (h3.hpHero <= 0) {
					strike(h,m);
					endMturn(m);
					strike(h2,m2);
					endMturn(m2);
					if (m.hpMon > 0) {
						Monster.Mattack(h,m);
					}
					if (m2.hpMon > 0) {
						Monster.Mattack(h2,m2);
					}
					if (m3.hpMon > 0) {
						if(h2.hpHero > 0) {
							Monster.Mattack(h2,m3);
						}
						else {
							Monster.Mattack(h2,m);
						}
					}
					endHturn(h);
					endHturn(h2);
				}
			}
			else if (Hcount == 1 && Mcount == 3) {
				if (h.hpHero <= 0) {
					if(h2.hpHero <= 0) {
						strike(h3,m3);
						endMturn(m3);
						Monster.Mattack(h3,m);
						Monster.Mattack(h3,m2);
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
						endHturn(h3);
					}
					else {
						strike(h2,m2);
						endMturn(m2);
						Monster.Mattack(h2,m);
						if (m2.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
						Monster.Mattack(h2,m3);
						endHturn(h2);
					}
				}
				else if (h2.hpHero <= 0) {
					if(h.hpHero <= 0) {
						strike(h3,m3);
						endMturn(m3);
						Monster.Mattack(h3,m);
						Monster.Mattack(h3,m2);
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
						endHturn(h3);

					}
					else {
						strike(h,m2);
						endMturn(m2);
						Monster.Mattack(h,m);
						if (m2.hpMon > 0) {
							Monster.Mattack(h,m2);
						}
						Monster.Mattack(h,m3);
						endHturn(h);
					}
				}
				else if (h3.hpHero <= 0) {
					if(h2.hpHero <= 0) {
						strike(h,m);
						endMturn(m);
						if (m.hpMon > 0) {
							Monster.Mattack(h,m);
						}
						Monster.Mattack(h,m2);
						Monster.Mattack(h,m3);
						endHturn(h);

					}
					else {
						strike(h2,m2);
						endMturn(m2);
						Monster.Mattack(h2,m);
						if (m2.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
						Monster.Mattack(h2,m3);
						endHturn(h2);
					}
				}
			}
			else if (Hcount == 3 && Mcount == 2) {
				if (m.hpMon <= 0) {
					strike(h,m2);
					endMturn(m2);
					if (m2.hpMon > 0) {
						strike(h2,m2);
						endMturn(m2);
						if (m2.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
					}
					else {
						strike(h2,m3);
						endMturn(m3);
					}
					if (m3.hpMon > 0) {
						strike(h3,m3);
						endMturn(m3);
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
					}
					endHturn(h);
					endHturn(h2);
					endHturn(h3);
					}
				}
				else if (m2.hpMon <= 0) {
					strike(h, m);
					endMturn(m);
					if (m.hpMon > 0) {
						strike(h2,m);
						endMturn(m);
						if (m.hpMon > 0) {
							Monster.Mattack(h2,m);
						}
					}
					else {
						strike(h2,m3);
						endMturn(m3);
					}
					if (m3.hpMon > 0) {
						strike(h3,m3);
						endMturn(m3);
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
					}

					endHturn(h);
					endHturn(h2);
					endHturn(h3);
				}
				else if (m3.hpMon <= 0) {
					strike(h, m);
					endMturn(m);
					strike(h2,m2);
					endMturn(m2);
					if (m.hpMon > 0) {
						strike(h3,m);
						endMturn(m);
						if (m.hpMon > 0) {
							Monster.Mattack(h,m);
						}
					}
					else {
						strike(h3,m2);
						endMturn(m2);
						if (m2.hpMon > 0) {
							Monster.Mattack(h2,m2);
						}
					}
					endHturn(h);
					endHturn(h2);
					endHturn(h3);
				}
			
			else if (Hcount == 3 && Mcount == 1) {
				if (m.hpMon <= 0) {
					if(m2.hpMon <= 0) {
						strike(h,m3);
						endMturn(m3);
						if (m3.hpMon > 0) {
							strike(h2,m3);
							endMturn(m3);
						}
						if (m3.hpMon > 0) {
							strike(h3,m3);
							endMturn(m3);
						}
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
					else {
						strike(h,m2);
						endMturn(m2);
						if (m2.hpMon > 0) {
							strike(h2,m2);
							endMturn(m2);
						}
						if (m2.hpMon > 0) {
							strike(h3,m2);
							endMturn(m2);
						}
						if (m2.hpMon > 0) {
							Monster.Mattack(h2, m2);
						}
						
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
				}
				else if (m2.hpMon <= 0) {
					if(m.hpMon <= 0) {
						strike(h,m3);
						endMturn(m3);
						if (m3.hpMon > 0) {
							strike(h2,m3);
							endMturn(m3);
						}
						if (m3.hpMon > 0) {
							strike(h3,m3);
							endMturn(m3);
						}
						if (m3.hpMon > 0) {
							Monster.Mattack(h3,m3);
						}
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
					else {
						strike(h,m);
						endMturn(m);
						if (m.hpMon > 0) {
							strike(h2,m);
							endMturn(m);
						}
						if (m.hpMon > 0) {
							strike(h3,m);
							endMturn(m);
						}
						if (m.hpMon > 0) {
							Monster.Mattack(h,m);
						}
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
				}
				else if (m3.hpMon <= 0) {
					if(m.hpMon <= 0) {
						strike(h,m2);
						endMturn(m2);
						if (m2.hpMon > 0) {
							strike(h2,m2);
							endMturn(m2);
						}
						if (m2.hpMon > 0) {
							strike(h3,m2);
							endMturn(m2);
						}
						if (m2.hpMon > 0) {
							Monster.Mattack(h2, m2);
						}
						
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
					else {
						strike(h,m);
						endMturn(m);
						if (m.hpMon > 0) {
							strike(h2,m);
							endMturn(m);
						}
						if (m.hpMon > 0) {
							strike(h3,m);
							endMturn(m);
						}
						if (m.hpMon > 0) {
							Monster.Mattack(h,m);
						}
						endHturn(h);
						endHturn(h2);
						endHturn(h3);
					}
				}
			}
			else if ((Hcount == 2 && Mcount == 2) || (Hcount == 1 && Mcount == 2) || (Hcount == 2 && Mcount == 1)) {
				if (h.hpHero <= 0) {
					if (m.hpMon <= 0) {
						tbb(2,h2,h3,null,m2,m3, null);
					}
					else if (m2.hpMon <= 0) {
						tbb(2,h2,h3,null,m,m3, null);
					}
					else {
						tbb(2,h2,h3,null,m,m2, null);
					}
				}
				else if (h2.hpHero <= 0) {
					if (m.hpMon <0) {
						tbb(2,h,h3,null,m2,m3, null);
					}
					else if (m2.hpMon <= 0) {
						tbb(2,h,h3,null,m,m3, null);
					}
					else {
						tbb(2,h,h3,null,m,m2, null);
					}
				}
				else if (h3.hpHero <= 0) {
					if (m.hpMon <= 0) {
						tbb(2,h,h2,null,m2,m3, null);
					}
					else if (m2.hpMon <= 0) {
						tbb(2,h,h2,null,m,m3, null);
					}
					else {
						tbb(2,h,h2,null,m,m2, null);
					}
				}

			}
			else if (Hcount == 1 && Mcount == 1) {
				for(int k = 0; k < Hero.heroParty.size(); k++) {
					Hero fighter = Hero.heroParty.get(k);
					if(fighter.hpHero > 0) {
						for(int y = 0; y < Monster.monLineup.size(); y++) {
							Monster mon = Monster.monLineup.get(y);
								if (mon.hpMon > 0) {
									tbb(1,fighter, null, null, mon, null, null);
								}
						}
					}
				}
				
			}
		}
	}
	
	public static int initiateBattle(List<Hero> heroParty) throws Exception {
		// Battle logic after player steps on common square and gives exp and money afterwards
		System.out.println("Oh no! You've encounted a Monster!!");
		Monster m;
		Monster m2;
		Monster m3;
		Hero H;
		Hero H2;
		Hero H3;
		boolean runner = true;
		while (runner) {
			if(heroParty.size() == 1) {
				m = Monster.pickRandom();
				Monster.monLineup.add(m);
			}
			else if (heroParty.size() == 2) {
				m = Monster.pickRandom();
				m2 = Monster.pickRandom();
				Monster.monLineup.add(m);
				Monster.monLineup.add(m2);
			}
			else {
				m = Monster.pickRandom();
				m2 = Monster.pickRandom();
				m3 = Monster.pickRandom();
				Monster.monLineup.add(m);
				Monster.monLineup.add(m2);
				Monster.monLineup.add(m3);
			}
			Monster Mmax = Collections.max(Monster.monLineup);
			Hero Hmax = Collections.max(heroParty);
			if(Mmax.levelMon <= Hmax.lvlHero) {
				break;
			}
			else {
				Monster.monLineup.clear();
			}
		}
		if(heroParty.size() == 1) {
			//for one player
			m = Collections.max(Monster.monLineup);
			H = Collections.max(heroParty);
			System.out.println("You have entered a fight with " + m.nameMon);
			System.out.println();
			artGen.printTextArt("FIGHT!!!", AsciiArt.ART_SIZE_SMALL);
			while(m.hpMon > 0 && H.hpHero > 0) {
				Hero.printHero();
				System.out.println();
				Monster.printMon();
				tbb(1,H, null, null, m, null, null);
			}
			if (H.hpHero <= 0) {
				artGen.printTextArt("You Lost...", AsciiArt.ART_SIZE_SMALL);
				H.moneyHero = (H.moneyHero/2);
				System.out.println(H.nameHero + " woke up and regained half health.");
				System.out.println(H.nameHero + " also lost half of their money");
				H.hpHero = (H.lvlHero * 100)/2;
				Potion.normalizeStat(H);
				Monster.repairMon(m);
				Monster.monLineup.clear();
				return 0;
			}
			else {
				System.out.println();
				artGen.printTextArt("Victory!", AsciiArt.ART_SIZE_SMALL);
				Potion.normalizeStat(H);
				Monster.repairMon(m);
				Monster.monLineup.clear();
				int nMoney = m.levelMon * 100;
				System.out.println(H.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
				H.moneyHero += nMoney;
				H.expHero += 2;
				if (H.expHero >= H.lvlHero*10) {
					H.levelUp();
					System.out.println(H.nameHero + " leveled up!");
					Hero.printHero();
				}
				return 0;
			}
		}
		else if (heroParty.size() == 2) {
			//for a party of two
			m = Monster.monLineup.get(0);
			m2 = Monster.monLineup.get(1);
			H = heroParty.get(0);
			H2 = heroParty.get(1);
			System.out.println("You have entered a fight with " + m.nameMon + " and " + m2.nameMon);
			System.out.println();
			artGen.printTextArt("FIGHT!!!", AsciiArt.ART_SIZE_SMALL);
			while((m.hpMon > 0 || m2.hpMon > 0) && (H.hpHero > 0 || H2.hpHero > 0)) {
				Hero.printHero();
				System.out.println();
				Monster.printMon();
				tbb(2,H, H2, null, m, m2, null);
			}
			if (H.hpHero <= 0 && H2.hpHero <= 0) {
				artGen.printTextArt("You Lost...", AsciiArt.ART_SIZE_SMALL);
				H.moneyHero = (H.moneyHero/2);
				H2.moneyHero = (H2.moneyHero/2);
				System.out.println(H.nameHero + " woke up and regained half health.");
				H.hpHero = (H.lvlHero * 100)/2;
				System.out.println(H2.nameHero + " woke up and regained half health.");
				H2.hpHero = (H2.lvlHero * 100)/2;
				System.out.println(H.nameHero + " also lost half of their money");
				System.out.println(H2.nameHero + " also lost half of their money");
				Potion.normalizeStat(H);
				Potion.normalizeStat(H2);
				Monster.repairMon(m);
				Monster.repairMon(m2);
				Monster.monLineup.clear();
				return 0;
			}
			else {
				System.out.println();
				artGen.printTextArt("Victory!", AsciiArt.ART_SIZE_SMALL);
				Potion.normalizeStat(H);
				Potion.normalizeStat(H2);
				Monster.repairMon(m);
				Monster.repairMon(m2);
				Monster.monLineup.clear();
				int nMoney = m.levelMon * 100;
				if (H.hpHero > 0) {
					System.out.println(H.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
					H.moneyHero += nMoney;
					H.expHero += 2;
					if (H.expHero >= H.lvlHero*10) {
						H.levelUp();
						System.out.println(H.nameHero + " leveled up!");
						Hero.printHero();
					}
				}
				if (H2.hpHero > 0) {
					System.out.println(H2.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
					H2.moneyHero += nMoney;
					H2.expHero += 2;
					if (H2.expHero >= H2.lvlHero*10) {
						H2.levelUp();
						System.out.println(H2.nameHero + " leveled up!");
						Hero.printHero();
					}
				}
				if (H.hpHero <= 0) {
					H.hpHero = (H.lvlHero * 100)/2;
					System.out.println(H.nameHero + " woke up and regained half health.");
				}
				else if (H2.hpHero <= 0) {
					H2.hpHero = (H2.lvlHero * 100)/2;
					System.out.println(H2.nameHero + " woke up and regained half health.");
				}
				return 0;
			}
		}
		else {
			//for a party of 3
			m = Monster.monLineup.get(0);
			m2 = Monster.monLineup.get(1);
			m3 = Monster.monLineup.get(2);
			H = heroParty.get(0);
			H2 = heroParty.get(1);
			H3 = heroParty.get(2);
			System.out.println("You have entered a fight with " + m.nameMon + " and " + m2.nameMon + " and " + m3.nameMon);
			System.out.println();
			artGen.printTextArt("FIGHT!!!", AsciiArt.ART_SIZE_SMALL);
			while((m.hpMon > 0 || m2.hpMon > 0 || m3.hpMon > 0) && (H.hpHero > 0 || H2.hpHero > 0 || H3.hpHero > 0)) {
				Hero.printHero();
				System.out.println();
				Monster.printMon();
				tbb(3,H, H2, H3, m, m2, m3);
			}
			if (H.hpHero <= 0 && H2.hpHero <= 0 && H3.hpHero <= 0) {
				artGen.printTextArt("You Lost...", AsciiArt.ART_SIZE_SMALL);
				H.moneyHero = (H.moneyHero/2);
				H2.moneyHero = (H2.moneyHero/2);
				H3.moneyHero = (H3.moneyHero/2);
				System.out.println(H.nameHero + " woke up and regained half health.");
				H.hpHero = (H.lvlHero * 100)/2;
				System.out.println(H2.nameHero + " woke up and regained half health.");
				H2.hpHero = (H2.lvlHero * 100)/2;
				System.out.println(H3.nameHero + " woke up and regained half health.");
				H3.hpHero = (H3.lvlHero * 100)/2;
				System.out.println(H.nameHero + " also lost half of their money");
				System.out.println(H2.nameHero + " also lost half of their money");
				System.out.println(H3.nameHero + " also lost half of their money");
				Potion.normalizeStat(H);
				Potion.normalizeStat(H2);
				Potion.normalizeStat(H3);
				Monster.repairMon(m);
				Monster.repairMon(m2);
				Monster.repairMon(m3);

				return 0;
			}
			else {
				System.out.println();
				artGen.printTextArt("Victory!", AsciiArt.ART_SIZE_SMALL);
				Potion.normalizeStat(H);
				Potion.normalizeStat(H2);
				Potion.normalizeStat(H3);
				Monster.repairMon(m);
				Monster.repairMon(m2);
				Monster.repairMon(m3);
				Monster.monLineup.clear();
				int nMoney = m.levelMon * 100;
				if (H.hpHero > 0) {
					System.out.println(H.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
					H.moneyHero += nMoney;
					H.expHero += 2;
					if (H.expHero >= H.lvlHero*10) {
						H.levelUp();
						System.out.println(H.nameHero + " leveled up!");
						Hero.printHero();
					}
				}
				if (H2.hpHero > 0) {
					System.out.println(H2.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
					H2.moneyHero += nMoney;
					H2.expHero += 2;
					if (H2.expHero >= H2.lvlHero*10) {
						H2.levelUp();
						System.out.println(H2.nameHero + " leveled up!");
						Hero.printHero();
					}
				}
				if (H3.hpHero > 0) {
					System.out.println(H3.nameHero + " recieved " + nMoney + " coins and 2 experince points!");
					H3.moneyHero += nMoney;
					H3.expHero += 2;
					if (H3.expHero >= H3.lvlHero*10) {
						H3.levelUp();
						System.out.println(H3.nameHero + " leveled up!");
						Hero.printHero();
					}
				}
				if (H.hpHero <= 0) {
					System.out.println(H.nameHero + " woke up and regained half health.");
					H.hpHero = (H.lvlHero * 100)/2;
				}
				else if (H2.hpHero <= 0) {
					System.out.println(H2.nameHero + " woke up and regained half health.");
					H2.hpHero = (H2.lvlHero * 100)/2;
				}
				else {
					System.out.println(H3.nameHero + " woke up and regained half health.");
					H3.hpHero = (H3.lvlHero * 100)/2;
				}
				return 0;
			}
		}
	}
	
}
