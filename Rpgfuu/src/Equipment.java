public class Equipment extends Item {
	public String name;
	public int hp;
	public int atk;
	public int def;
	public int id;
	public int value;
	public boolean isGeted = false;
	public Pc beEquipped = null;
	
	Equipment(int id, String name, int hp, int atk, int def, int value) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.value = value;
	}
	
	public void showFromStatus() {
		Interface.wait(0.5);
		System.out.println();
		System.out.println("-----------------------------------------------");
		Pc S = this.beEquipped;
		System.out.println(this.name);
		System.out.println("�̗́F" + this.hp + " �U���́F" + this.atk + " �h��́F" + this.def);
		System.out.println(S.name + "��������");
		System.out.println("-----------------------------------------------");
		System.out.println("1�F�O��  2�F������ς���  0�F�߂�");
	}
	
	public void showFromEquipment() {
		
	}
	
	public void showFromShop() {
		Interface.wait(0.5);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(this.name);
		if (this.hp != 0) {
			System.out.print("�̗� +" + this.hp + "  ");
		}
		if (this.atk !=0) {
			System.out.print("�U���� +" + this.atk + "  ");
		}
		if (this.def != 0) {
			System.out.print("�h��� +" + this.def + "  ");
		}
		System.out.println("\\" + this.value);
		System.out.println("-----------------------------------------------");
		System.out.println("1�F����(������ \\" + Party.money + ")  ���̑��F�߂�");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 1) {
			if (Party.money < this.value) {
				System.out.println("����������܂���");
			} else {
				Party.money -= this.value;
				System.out.println(this.name + "���w������");
				Party.getEqFromShop(this);
			}
		}
		Party.goShop();
		System.exit(0);
	}
}