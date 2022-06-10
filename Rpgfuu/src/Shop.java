import java.util.ArrayList;

public class Shop implements Interface {
	public ArrayList<Equipment> shopEq = new ArrayList<>();
	
	Shop (int id) {
		switch (id) {
			case 1:
				this.shopEq.add(weapons.get(COPPER_SWORD));
				this.shopEq.add(armors.get(KAWA_FUKU));
				this.shopEq.add(accessorys.get(SILVER_RING));
				break;
			case 2:
				this.shopEq.add(weapons.get(COPPER_SWORD));
				this.shopEq.add(armors.get(KAWA_FUKU));
				this.shopEq.add(accessorys.get(SILVER_RING));
				this.shopEq.add(weapons.get(IRON_SWORD));
				this.shopEq.add(armors.get(IRON_ARMOR));
				this.shopEq.add(accessorys.get(GOLD_RING));
				break;
			case 3:
				this.shopEq.add(weapons.get(COPPER_SWORD));
				this.shopEq.add(armors.get(KAWA_FUKU));
				this.shopEq.add(accessorys.get(SILVER_RING));
				this.shopEq.add(weapons.get(IRON_SWORD));
				this.shopEq.add(armors.get(IRON_ARMOR));
				this.shopEq.add(accessorys.get(GOLD_RING));
				this.shopEq.add(weapons.get(SILVER_SWORD));
				this.shopEq.add(armors.get(KUSARI_KATABIRA));
				this.shopEq.add(accessorys.get(ATTACK_PIERCE));
				break;
			default:
		}
	}
	
	public static void createData() {
		for (int i = 1; i < 4; i++) {
			Shop.shops.add(new Shop(i));
		}

	}
	
	public void show() {
		System.out.println();
		System.out.println("何を見ますか？　所持金：\\" + Party.money);
		System.out.println("1：武器　2：防具　3：アクセ　4：装備　その他：戻る");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 1) {
			int i = 1;
			ArrayList<Weapon> shopWeapon = new ArrayList<>();
			for (Equipment equipment : this.shopEq) {
				if (equipment.getClass() == Weapon.class && !equipment.isGeted) {
					shopWeapon.add((Weapon)equipment);
					System.out.println(i + "．" + equipment.name + " \\" + equipment.value);
					i++;
				}
			}
			if (i == 1) {
				System.out.println("めぼしい武器は売っていない");
				Party.goShop();
				System.exit(0);
			} else {
				int s = sc.nextInt();
				if (s > 0 && s <= shopWeapon.size()) {
					shopWeapon.get(s-1).showFromShop();
					System.exit(0);
				} else {
					Party.goShop();
					System.exit(0);
				}
			}
			
		} else if (r == 2) {
			int i = 1;
			ArrayList<Armor> shopArmor = new ArrayList<>();
			for (Equipment equipment : this.shopEq) {
				if (equipment.getClass() == Armor.class && !equipment.isGeted) {
					shopArmor.add((Armor)equipment);
					System.out.println(i + "．" + equipment.name + " \\" + equipment.value);
					i++;
				}
			}
			if (i == 1) {
				System.out.println("めぼしい防具は売っていない");
				Party.goShop();
				System.exit(0);
			} else {
				int s = sc.nextInt();
				if (s > 0 && s <= shopArmor.size()) {
					shopArmor.get(s-1).showFromShop();
					System.exit(0);
				} else {
					Party.goShop();
					System.exit(0);
				}
			}
			
		} else if (r == 3) {
			int i = 1;
			ArrayList<Accessory> shopAcc = new ArrayList<>();
			for (Equipment equipment : this.shopEq) {
				if (equipment.getClass() == Accessory.class && !equipment.isGeted) {
					shopAcc.add((Accessory)equipment);
					System.out.println(i + "．" + equipment.name + " \\" + equipment.value);
					i++;
				}
			}
			if (i == 1) {
				System.out.println("めぼしいアクセは売っていない");
				Party.goShop();
				System.exit(0);
			} else {
				int s = sc.nextInt();
				if (s > 0 && s <= shopAcc.size()) {
					shopAcc.get(s-1).showFromShop();
					System.exit(0);
				} else {
					Party.goShop();
					System.exit(0);
				}
			}
			
		} else if (r ==4) {
			int i = 1;
			ArrayList<Equipment> shopEquipment = new ArrayList<>();
			for (Equipment equipment : shopEq) {
				if (!equipment.isGeted) {
					shopEquipment.add(equipment);
					System.out.println(i + "．" + equipment.name + "\\" + equipment.value);
					i++;
				}
			}
			if (i == 1) {
				System.out.println("めぼしい装備は売っていない");
				Party.goShop();
				System.exit(0);
			} else {
				int s = sc.nextInt();
				if (s > 0 && s <= shopEquipment.size()) {
					shopEquipment.get(s-1).showFromShop();
					System.exit(0);
				} else {
					Party.goShop();
					System.exit(0);
				}
			}
		} else {
			Party.arrive();
			System.exit(0);
		}
	}
}
