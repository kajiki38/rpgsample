import java.util.ArrayList;

public class Party implements Interface{
	public static int number;
	public static ArrayList<Pc> partyMember = new ArrayList<>();
	public static ArrayList<Weapon> havingWeapon = new ArrayList<>();
	public static ArrayList<Armor> havingArmor = new ArrayList<>();
	public static ArrayList<Accessory> havingAccessory = new ArrayList<>();
	public static int money;
	public static int location;
	public static boolean defence = false;
	public static int aliveMember;
	
	
	public static void add(Pc newMember) {
		partyMember.add(newMember);
		number = partyMember.size();
	}
	
	public static void remover(Pc oldMember) {
		partyMember.remove(partyMember.indexOf(oldMember));
		number = partyMember.size();
	}
	
	public static void show() {
		int i = 1;
		Interface.wait(0.5);
		System.out.println();
		System.out.println("-----------------------------------------------");
		for (Pc member : partyMember) {
			System.out.println(i + "．" + member.name + "：" + member.level + "Lv " + member.nowHp + "/" + member.finalStatus[hps]);
			i++;
		}
		System.out.println("所持金：" + Party.money);
		System.out.println("現在地：" + Party.location);
		System.out.println("-----------------------------------------------");
		System.out.println("誰の情報を見ますか？");
		System.out.println("1 ～ " + number + "：キャラクターの情報を表示　0：終了");
		int r = sc.nextInt();
		if (r > 0 && r <= number) {
			partyMember.get(r-1).showStatus();
			System.exit(0);
		}
		if (Party.location == 0 || Party.location == 15 || Party.location == 35) {
			Party.arrive();
			System.exit(0);
		} else {
			Party.what();
			System.exit(0);
		}
	}
	
	public static void get(Equipment equipment) {
		if (equipment.getClass() == Weapon.class) {
			havingWeapon.add((Weapon)equipment);
		} else if (equipment.getClass() == Armor.class) {
			havingArmor.add((Armor)equipment);
		} else if (equipment.getClass() == Accessory.class){
			havingAccessory.add((Accessory)equipment);
		}
		equipment.isGeted = true;
	}
	
	public static void getEqFromShop(Equipment equipment) {
		if (equipment.getClass() == Weapon.class) {
			havingWeapon.add((Weapon)equipment);
		} else if (equipment.getClass() == Armor.class) {
			havingArmor.add((Armor)equipment);
		} else if (equipment.getClass() == Accessory.class){
			havingAccessory.add((Accessory)equipment);
		}
		equipment.isGeted = true;
		Interface.wait(0.5);
		System.out.println("すぐに装備しますか？");
		System.out.println("1：はい　その他：いいえ");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 1) {
			System.out.println("だれが装備しますか？");
			int i = 1;
			for (Pc member :Party.partyMember) {
				System.out.print(i +"．" + member.name);
				if (equipment.getClass() == Weapon.class) {
					System.out.print("　装備中：");
					if (member.equippingWeapon == null) {
						System.out.println("なし");
					} else {
						System.out.println(member.equippingWeapon.name);
					}
				} else if (equipment.getClass() == Armor.class) {
					System.out.print("　装備中：");
					if (member.equippingArmor == null) {
						System.out.println("なし");
					} else {
						System.out.println(member.equippingArmor.name);
					}
				} else {
					System.out.print("　装備中：");
					if (member.equippingAccessory == null) {
						System.out.println("なし");
					} else {
						System.out.println(member.equippingAccessory.name);
					}
				}
				i++;
			}
			int n = sc.nextInt();
			Interface.wait(0.5);
			if (n > 0 && n <= Party.partyMember.size()) {
				Party.partyMember.get(n-1).equip(equipment);
				
			} else {
				
			}
			
		}
		Party.goShop();
		System.exit(0);
		
	}
	
	public static void encount(Monster monster) {
		int dummy = 0;
		System.out.println(monster.name + "が飛び出してきた!");
		aliveMember = partyMember.size();
		for (Pc member : partyMember) {
			if (member.nowHp <= 0) {
				aliveMember--;
			}
		}
		while (monster.hp > 0 && aliveMember > 0) {
			for (Pc member : partyMember) {
				if (member.nowHp <= 0 && member.alive == true) {
					member.alive = false;
					aliveMember--;
				}
			}
			Interface.wait(0.5);
			fightView(monster);
			for (Pc member : partyMember) {
				if (member.nowHp > 0 && monster.hp > 0) {
					System.out.println(member.name + "はどうする？");
					if (!Party.defence) {
						System.out.println("1：攻撃 2：防御");
						int n = sc.nextInt();
						if (n == 1) {
							member.attack(monster);
						} else {
							dummy = member.finalStatus[defs];
							Interface.wait(0.5);
							System.out.println(member.name + "は防御態勢に入った!");
							Interface.wait(0.5);
							member.defend();
							System.out.println();
						}
					} else {
						System.out.println("1：攻撃");
						int n = sc.nextInt();
						if (n == 1) {
							member.attack(monster);
						} else {
						}
					}
					
				}
			}
			System.out.println();
			if (Party.defence) {
				for (Pc member : partyMember) {
					if (member.defence) {
						for (int i = 0; i < Party.partyMember.size(); i++) {
							monster.attack(member);
						}
						member.defence = false;
						member.finalStatus[defs] = dummy;
					}
				}
				Party.defence = false;
			} else {
				for (Pc member : partyMember) {
					monster.attack(member);
				}
			} 
		}
		if (aliveMember <= 0) {
			System.out.println("全滅してしまった");
			System.out.println("所持金を失った");
			Party.money = 0;
			for (Pc member : Party.partyMember) {
				member.nowHp = member.finalStatus[hps];
			}
			if (location < 15) {
				location = 0;
				Party.arrive();
			} else if (location < 35) {
				location = 15;
				Party.arrive();
			} else if (location < 70) {
				location = 35;
				Party.arrive();
			}
		} else {
			Interface.wait(0.5);
			System.out.println(monster.name + "を倒した!");
			Interface.wait(1.0);
			System.out.println(monster.money + "円手に入れた");
			Party.money += monster.money;
			Party.gainExp(monster.exp);
			Interface.wait(0.5);
			Party.what();
		}
	}
	
	public static void gainExp(int exp) {
		int n = exp / partyMember.size();
		System.out.println();
		for (Pc member :partyMember) {
			Interface.wait(0.5);
			member.gainExp(n);
		}
		System.out.println();
	}
	
	public static void what() {
		Interface.wait(0.5);
		System.out.println("1：進む　2：町へ戻る　その他：メニューを開く");
		int r = sc.nextInt();
		if (r == 1) {
			Party.forward();
		} else if (r == 2) {
			System.out.println("町へ戻った。");
			if (location < 15) {
				location = 0;
				Party.arrive();
			} else if (location < 35) {
				location = 15;
				Party.arrive();
			} else if (location < 70) {
				location = 35;
				Party.arrive();
			}
		} else {
			Party.show();
		}
	}
	
	
	public static void forward() {
		location++;
		Interface.wait(0.5);
		System.out.println("先へ進んだ。");
		if(location < 15) {
			if (Math.random() < 0.3) {
				Party.encount(new Monster(1));
			} else {
				Party.what();
			}
			System.exit(0);
		} else if (location == 15) {
			System.out.println("15の町へ到着した");
			Party.arrive();
			System.exit(0);
		} else if (location < 35) {
			if (Math.random() < 0.1) {
				Party.encount(new Monster(1));
				System.exit(0);
			} else if (Math.random() < 0.3) {
				Party.encount(new Monster(2));
				System.exit(0);
			} else {
				Party.what();
			}
			System.exit(0);
		} else if (location == 35) {
			System.out.println("35の町へ到着した");
			Party.arrive();
			System.exit(0);
		} else if (location < 70) {
			if (Math.random() < 0.1) {
				Party.encount(new Monster(2));
				System.exit(0);
			} else if (Math.random() < 0.4) {
				Party.encount(new Monster(3));
				System.exit(0);
			} else {
				Party.what();
			}
			System.exit(0);
		}
	}
	
	public static void arrive() {
		Interface.wait(0.5);
		System.out.println("1：先へ進む　2：宿屋へ行く　3：武具屋へ行く　4：メニューを開く　0：セーブする　その他：前の町へ戻る");
		int r = sc.nextInt();
		switch (r) {
			case 1:
				Party.forward();
				break;
			case 2:
				Interface.wait(0.5);
				System.out.println("宿屋へ入った。");
				Party.goInn();
				break;
			case 3:
				Interface.wait(0.5);
				System.out.println("武具屋へ入った。");
				Party.goShop();
				break;
			case 4:
				Party.show();
				break;
			case 0:
				Main.save();
				break;
			default :
				Interface.wait(0.5);
				System.out.println("前の町へ戻った。");
				if (Party.location <= 15) {
					Party.location = 0;
					Party.arrive();
				} else {
					Party.location = 15;
					Party.arrive();
				}
				break;
		}
	}
	
	
	public static void fightView(Monster monster) {
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(monster.name + " HP：" + monster.hp);
		System.out.println();
		for (Pc member : partyMember) {
			int n;
			if (member.nowHp < 0) {
				n = 0;
			} else {
				n = member.nowHp;
			}
			System.out.println(member.name + " HP：" + n + "/" + member.finalStatus[hps]);
		}
		System.out.println("-----------------------------------------------");
		System.out.println();
	}
	
	
	public static void goInn() {
		Interface.wait(0.5);
		System.out.println("料金：\\100　所持金：\\" + Party.money);
		Interface.wait(0.5);
		if (Party.money >= 100) {
			Party.money -= 100;
			System.out.println("宿屋で回復した。");
			for (Pc member : Party.partyMember) {
				member.nowHp = member.finalStatus[hps];
			}
		} else {
			System.out.println("お金が足りない。");
		}
		Party.arrive();
	}
	
	public static void goShop() {
		Interface.wait(0.5);
		switch (Party.location) {
			case 0:
				shops.get(0).show();
				break;
			case 15:
				shops.get(1).show();
				break;
			case 35:
				shops.get(2).show();
				break;
			default:
				shops.get(0).show();
		}
	}
}