
public class Assassin extends Pc{
	Assassin(String name) {
		super(name);
		this.job = "ÉAÉTÉVÉì";
		this.hp[0] = 80;
		for (int i = 1; i < 50; i ++) {
			this.hp[i] = (int)Math.round(this.hp[i - 1] * 1.1);
		}
		this.atk[0] = 60;
		for (int i = 1; i < 50; i ++) {
			this.atk[i] = (int)Math.round(this.atk[i - 1] * 1.1);
		}
		this.def[0] = 30;
		for (int i = 1; i < 50; i ++) {
			this.def[i] = (int)Math.round(this.def[i - 1] * 1.1);
		}
		this.status[hps] = this.hp[this.level - 1];
		this.status[atks] = this.atk[this.level - 1];
		this.status[defs] = this.def[this.level - 1];
		this.finalStatus[hps] = this.status[hps];
		this.finalStatus[atks] = this.status[atks];
		this.finalStatus[defs] = this.status[defs];
		this.nowHp = this.finalStatus[hps];
	}

}
