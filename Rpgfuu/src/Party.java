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
			System.out.println(i + "�D" + member.name + "�F" + member.level + "Lv " + member.nowHp + "/" + member.finalStatus[hps]);
			i++;
		}
		System.out.println("�������F" + Party.money);
		System.out.println("���ݒn�F" + Party.location);
		System.out.println("-----------------------------------------------");
		System.out.println("�N�̏������܂����H");
		System.out.println("1 �` " + number + "�F�L�����N�^�[�̏���\���@0�F�I��");
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
		System.out.println("�����ɑ������܂����H");
		System.out.println("1�F�͂��@���̑��F������");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 1) {
			System.out.println("���ꂪ�������܂����H");
			int i = 1;
			for (Pc member :Party.partyMember) {
				System.out.print(i +"�D" + member.name);
				if (equipment.getClass() == Weapon.class) {
					System.out.print("�@�������F");
					if (member.equippingWeapon == null) {
						System.out.println("�Ȃ�");
					} else {
						System.out.println(member.equippingWeapon.name);
					}
				} else if (equipment.getClass() == Armor.class) {
					System.out.print("�@�������F");
					if (member.equippingArmor == null) {
						System.out.println("�Ȃ�");
					} else {
						System.out.println(member.equippingArmor.name);
					}
				} else {
					System.out.print("�@�������F");
					if (member.equippingAccessory == null) {
						System.out.println("�Ȃ�");
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
		System.out.println(monster.name + "����яo���Ă���!");
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
					System.out.println(member.name + "�͂ǂ�����H");
					if (!Party.defence) {
						System.out.println("1�F�U�� 2�F�h��");
						int n = sc.nextInt();
						if (n == 1) {
							member.attack(monster);
						} else {
							dummy = member.finalStatus[defs];
							Interface.wait(0.5);
							System.out.println(member.name + "�͖h��Ԑ��ɓ�����!");
							Interface.wait(0.5);
							member.defend();
							System.out.println();
						}
					} else {
						System.out.println("1�F�U��");
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
			System.out.println("�S�ł��Ă��܂���");
			System.out.println("��������������");
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
			System.out.println(monster.name + "��|����!");
			Interface.wait(1.0);
			System.out.println(monster.money + "�~��ɓ��ꂽ");
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
		System.out.println("1�F�i�ށ@2�F���֖߂�@���̑��F���j���[���J��");
		int r = sc.nextInt();
		if (r == 1) {
			Party.forward();
		} else if (r == 2) {
			System.out.println("���֖߂����B");
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
		System.out.println("��֐i�񂾁B");
		if(location < 15) {
			if (Math.random() < 0.3) {
				Party.encount(new Monster(1));
			} else {
				Party.what();
			}
			System.exit(0);
		} else if (location == 15) {
			System.out.println("15�̒��֓�������");
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
			System.out.println("35�̒��֓�������");
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
		System.out.println("1�F��֐i�ށ@2�F�h���֍s���@3�F����֍s���@4�F���j���[���J���@0�F�Z�[�u����@���̑��F�O�̒��֖߂�");
		int r = sc.nextInt();
		switch (r) {
			case 1:
				Party.forward();
				break;
			case 2:
				Interface.wait(0.5);
				System.out.println("�h���֓������B");
				Party.goInn();
				break;
			case 3:
				Interface.wait(0.5);
				System.out.println("����֓������B");
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
				System.out.println("�O�̒��֖߂����B");
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
		System.out.println(monster.name + " HP�F" + monster.hp);
		System.out.println();
		for (Pc member : partyMember) {
			int n;
			if (member.nowHp < 0) {
				n = 0;
			} else {
				n = member.nowHp;
			}
			System.out.println(member.name + " HP�F" + n + "/" + member.finalStatus[hps]);
		}
		System.out.println("-----------------------------------------------");
		System.out.println();
	}
	
	
	public static void goInn() {
		Interface.wait(0.5);
		System.out.println("�����F\\100�@�������F\\" + Party.money);
		Interface.wait(0.5);
		if (Party.money >= 100) {
			Party.money -= 100;
			System.out.println("�h���ŉ񕜂����B");
			for (Pc member : Party.partyMember) {
				member.nowHp = member.finalStatus[hps];
			}
		} else {
			System.out.println("����������Ȃ��B");
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