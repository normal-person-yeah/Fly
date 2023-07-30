
import java.awt.geom.Ellipse2D;
public class Hanabi extends Ellipse2D.Double implements Runnable{
	public double vx;
	public double vy;
	public double v;
	public long start;
	public Thread thread;
	public double ox, oy;
	public double alpha = 255;
	public double r, g, b;
	
	public Hanabi(double x, double y, int w, int h, double r, double g, double b) {
		super(x, y, w, h);
		this.ox = x; this.oy = y;
		
		this.vx = Math.random() - 0.5;
		this.vy = Math.random() - 0.5;
		this.v = Math.sqrt(vx * vx + vy * vy);
		this.vx = 100 * vx / v;
		this.vy = 100 * vy / v;
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		start = System.currentTimeMillis();
	}
	
	private void update() {
		long diff = System.currentTimeMillis() - start; // 開始からの時間ms
		this.x = this.ox + this.vx * diff / 1000;
		this.y = this.oy + this.vy * diff / 1000;
		
		this.alpha = 255 - diff / 10;
		if (this.alpha < 0) this.alpha = 0;
	}
	
	@Override
	public void run() {
		for (int i=0; i<10000;i++) {
			
			update();
			try {
				Thread.sleep(10); // 5ms間隔で座標更新
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		this.thread = new Thread(this);
		this.thread.start();
	}
	
}

