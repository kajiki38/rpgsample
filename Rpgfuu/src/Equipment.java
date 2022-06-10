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
		System.out.println("体力：" + this.hp + " 攻撃力：" + this.atk + " 防御力：" + this.def);
		System.out.println(S.name + "が装備中");
		System.out.println("-----------------------------------------------");
		System.out.println("1：外す  2：装備を変える  0：戻る");
	}
	
	public void showFromEquipment() {
		
	}
	
	public void showFromShop() {
		Interface.wait(0.5);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(this.name);
		if (this.hp != 0) {
			System.out.print("体力 +" + this.hp + "  ");
		}
		if (this.atk !=0) {
			System.out.print("攻撃力 +" + this.atk + "  ");
		}
		if (this.def != 0) {
			System.out.print("防御力 +" + this.def + "  ");
		}
		System.out.println("\\" + this.value);
		System.out.println("-----------------------------------------------");
		System.out.println("1：買う(所持金 \\" + Party.money + ")  その他：戻る");
		int r = sc.nextInt();
		Interface.wait(0.5);
		if (r == 1) {
			if (Party.money < this.value) {
				System.out.println("お金が足りません");
			} else {
				Party.money -= this.value;
				System.out.println(this.name + "を購入した");
				Party.getEqFromShop(this);
			}
		}
		Party.goShop();
		System.exit(0);
	}
}