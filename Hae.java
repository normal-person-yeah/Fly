import java.awt.geom.Ellipse2D;
public class Hae extends Ellipse2D.Double implements Runnable{
	double vx, vy, v, vvx, vvy;
	double ox, oy;
	double x, y;
	long time;
	Thread thread;
	boolean die = false;
	double alpha = 255;

	public Hae(double x, double y, boolean die) {
		this.x = x;
		this.y = y;
		this.ox = x;
		this.oy = y;
		this.die = die;
		
		this.time = System.currentTimeMillis();
		
		this.vx = Math.random() - 0.5;
		this.vy = Math.random() - 0.5;
		this.v = Math.sqrt(vx * vx + vy * vy);
		this.vx = (int)(Math.random() * 30 + 30) * vx / v;
		this.vy = (int)(Math.random() * 30 + 30) * vy / v;
		
		this.vvx = this.vx;
		this.vvy = this.vy;
	}
	
	public void update() {
		long diff = System.currentTimeMillis() - time;

		
		if (HaePanel.nf == 2 && this.die == false) {
			this.x = this.ox + vx * diff / 100.0;
			this.y = this.oy + vy * diff / 100.0;
		} else if (HaePanel.nf == 1 || HaePanel.nf == 3 || HaePanel.nf == 4 || this.die == false) {
			this.x = this.ox + vx * diff / 100.0;
			this.y = this.oy + vy * diff / 100.0;
		} 
		
		if (Math.random() < 0.005 && this.die == false) {
			this.vx = this.vvx * -1 + Math.random() * 15;
			this.ox = (int)this.x;
			this.oy = (int)this.y;
			this.time = System.currentTimeMillis();
		}

		if (Math.random() < 0.005 && this.die == false) {
			this.vy = this.vvy * -1 + Math.random() * 15;
			this.ox = (int)this.x;
			this.oy = (int)this.y;
			this.time = System.currentTimeMillis();
		}

		if (this.die == false) {
			if (this.x <= 0 && this.vx < 0.0 && this.die == false) {
				this.vx *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			} else if (this.x >= 880 && this.vx > 0.0 && this.die == false) {
				this.vx *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			}
			if (this.y <= 0 && this.vy < 0.0 && this.die == false) {
				this.vy *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			} else if (this.y >= 750 && this.die == false && this.vy > 0.0) {
				this.vy *= -1;
				this.ox = (int)this.x;
				this.oy = (int)this.y;
				this.time = System.currentTimeMillis();
			}
		}

		if (HaePanel.nf == 1 || HaePanel.nf == 4) {
			if (this.die == true) {
				if (this.vx > 0.0) {
					this.vx = 0.0000000000000000001;
				} else {
					this.vx = -0.00000000000000000001;
				}
				this.vy = 0.7;

				this.ox = (int)this.x;
				this.oy = (int)this.y;

				this.x = this.ox;
				this.y = this.oy;
			}
		} else if (HaePanel.nf == 2) {
			if (this.die == true || Timers.check == -2) {
				/*
				if (this.vx > 0.0) {
					this.vx = 0.0000000000000000001;
				} else {
					this.vx = -0.00000000000000000001;
				}
				*/

				this.ox = 5000;
				this.oy = 5000;
				
				//そのままの座標で死んだことにしたいならoxとoyだけ残して下は消す
				this.x = 5000;
				this.y = 5000;

				if (this.alpha > 0) {
					this.alpha = 255 - diff / 10.0;
				} else {
					this.alpha = 0.0;
				}
			}
		} else if (HaePanel.nf == 3) {
			if (this.die == true) {
				this.vx = (this.x - HaePanel.xx) / 100.0;
				this.vy = (this.y - HaePanel.yy) / 100.0;
				
				if (this.vx > 0.0) {
					this.vx += 1;
				} else {
					this.vx -= 1;
				}
				
				if (this.vy > 0.0) {
					this.vy += 1;
				} else {
					this.vy -= 1;
				}
				
				this.ox = (int)this.x;
				this.oy = (int)this.y;

				//this.x = this.ox;
				//this.y = this.oy;
			}
		}
	}

	public void start() {
		this.thread = new Thread(this);
		this.thread.start();
	}
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		for (int i=0; i<20000;i++) {
			update();
			try { Thread.sleep(5); // 5ms間隔で座標更新

			} catch (InterruptedException e) { e.printStackTrace();

			}
		}
	}

	public double sgetX(){
		return this.x;
	}
	
	public double sgetY(){
		return this.y;
	}
	
	public double sgetVx() {
		return this.vx;
	}
	
	public double sgetox(){
		return this.ox;
	}
	
	public double sgetoy(){
		return this.oy;
	}

}
