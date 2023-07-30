
public class KingHae extends Hae{
	public int hp;
	public int sound = 0;

	public KingHae(double x, double y, boolean die, int h, int sound) {
		super(x, y, die);
		this.ox = x;
		this.oy = y;
		this.hp = h;

		this.vx = Math.random() - 0.5;
		this.vy = Math.random() - 0.5;
		this.v = Math.sqrt(vx * vx + vy * vy);
		this.vx = (int)(Math.random() * 50) * vx / v + 80;
		this.vy = (int)(Math.random() * 50) * vy / v + 80;
		
		this.sound = 0;
	}

	@Override
	public void update() {

		long diff = System.currentTimeMillis() - this.time;
		
		this.x = this.ox + this.vx * diff / 100.0;
		this.y = this.oy + this.vy * diff / 100.0;
		
		
		if (Math.random() < 0.01 && this.die == false) {
			this.vx *= -1;
			this.ox = (int)this.x;
			this.oy = (int)this.y;
			this.time = System.currentTimeMillis();
		}

		if (Math.random() < 0.01 && this.die == false) {
			this.vy *= -1;
			this.ox = (int)this.x;
			this.oy = (int)this.y;
			this.time = System.currentTimeMillis();
		}


		if (this.die == false) {
			if (this.x <= 150 && this.vx < 0.0) {
				this.vx *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			} else if (this.x >= 700 && this.vx > 0.0) {
				this.vx *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			}

			if (this.y <= 50 && this.vy < 0.0) {
				this.vy *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			} else if (this.y >= 650 && this.vy > 0.0) {
				this.vy *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			}
		} else {
			if (this.vx > 0.0) {
				this.vx = 0.0000000000000000001;
			} else {
				this.vx = -0.00000000000000000001;
			}
			this.vy = 0.2;

			this.ox = (int)this.x;
			this.oy = (int)this.y;

		}
	}
}
