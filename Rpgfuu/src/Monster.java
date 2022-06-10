public class Monster implements Interface{
	public String name;
	public int hp;
	public int atk;
	public int def;
	public int exp;
	public int money;
	
	Monster(int id) {
		switch(id) {
			case 1:
				this.setData("スライム", 50,60,40,500,200);
				break;
			case 2:
				this.setData("ゴブリン", 100, 85, 70, 1000, 400);
				break;
			case 3:
				this.setData("オーガ", 150, 130, 95, 2000, 800);
				break;
			default:
				this.setData("けつばん", 1000, 1000, 1000, 5, 5);
		}
	}
	
	public void setData(String name, int hp, int atk, int def, int exp, int money) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.exp = exp;
		this.money = money;
	}
	
	public void attack(Pc character) {
		if (this.hp > 0 && character.nowHp > 0) {
			System.out.println(this.name + "のこうげき!");
			Interface.wait(0.5);
			int dmg = this.atk - character.finalStatus[defs];
			if (dmg <= 0) {
				System.out.println(character.name + "はダメージを受けなかった。");
			} else {
				character.nowHp -= dmg;
				System.out.println(character.name + "に" + dmg + "のダメージ!");
			}
			if (character.nowHp <= 0) {
				System.out.println(character.name + "は力尽きた。");
				Party.aliveMember--;
			}
			System.out.println();
			Interface.wait(1.0);
		}
	}
	
	public void showStatus() {
		System.out.println(this.name);
		System.out.println("体力　：" + this.hp);
		System.out.println("攻撃力：" + this.atk);
		System.out.println("防御力：" + this.def);
		System.out.println("----------");
	}
}