import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class HaePanel extends JPanel implements MouseListener, MouseMotionListener{
	//protected JFrame frame = new JFrame();

	private Image img = (new ImageIcon("haetataki.png")).getImage();
	private ImageIcon icon = new ImageIcon("haetataki.png");
	private Image img1 = (new ImageIcon("keshigomu.png")).getImage();
	private ImageIcon icon1 = new ImageIcon("keshigomu.png");
	private Image img2 = (new ImageIcon("tataki.png")).getImage();
	private ImageIcon icon2 = new ImageIcon("tataki.png");
	private Image img3 = (new ImageIcon("kesikasu.png")).getImage();
	private ImageIcon icon3 = new ImageIcon("kesikasu.png");

	private Image haeimg = (new ImageIcon("hae.png")).getImage();
	private ImageIcon haeicon = new ImageIcon("hae.png");

	private Image haeimg_rev = (new ImageIcon("hae_rev.png")).getImage();
	//private ImageIcon haeicon_rev = new ImageIcon("hae_rev.png");

	private Image dieimg = (new ImageIcon("hae_die.png")).getImage();
	private Image dieimg_rev = (new ImageIcon("hae_die_rev.png")).getImage();

	private Image kingimg = (new ImageIcon("king.png")).getImage();
	private ImageIcon kingicon = new ImageIcon("king.png");
	private Image king_rev = (new ImageIcon("king_rev.png")).getImage();
	private Image king_die = (new ImageIcon("king_die.png")).getImage();
	private Image king_die_rev = (new ImageIcon("king_die_rev.png")).getImage();

	private Image sea = (new ImageIcon("sea.png")).getImage();
	private Image river = (new ImageIcon("river.png")).getImage();
	private Image toilet = (new ImageIcon("toilet.png")).getImage();
	private Image shop = (new ImageIcon("shop.png")).getImage();
	private Image mountain = (new ImageIcon("mountain.png")).getImage();
	private Image heaven = (new ImageIcon("heaven.png")).getImage();
	private Image hell = (new ImageIcon("hell.png")).getImage();
	private Image marriage = (new ImageIcon("marriage.png")).getImage();
	private Image station = (new ImageIcon("station.png")).getImage();
	private Image school = (new ImageIcon("school.png")).getImage();

	private Image bom = (new ImageIcon("bomb.png")).getImage();
	private ImageIcon bomicon = new ImageIcon("bomb.png");
	private Image exp = (new ImageIcon("explotion.png")).getImage();
	private ImageIcon expicon = new ImageIcon("explotion.png");
	
	private Image don = (new ImageIcon("donkey.png")).getImage();
	private ImageIcon donicon = new ImageIcon("donkey.png");
	
	private Image ban = (new ImageIcon("banana.png")).getImage();
	private ImageIcon banicon = new ImageIcon("banana.png");

	private int lastX = -100, lastY = -100;
	private Thread repaintThread;
	private int ch = 1, cc = -1, sound = 0;
	public static int number = 0, k_number = 0, h_number = 0, res_h = 0, res_k = 0, res_h_num = 0, res_k_num = 0;
	public static int nf = 1;
	public float fe = 1;
	public int alpha = 10, check = 0;
	public static int xx, yy;
	public int hae_num, king_num;
	public boolean boss = false;

	private ArrayList<Hanabi> hana = new ArrayList<>();
	private ArrayList<Hae> Hae = new ArrayList<>();
	private ArrayList<KingHae> king = new ArrayList<>();

	public HaePanel() {

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		if (Menu.moden == 0) {

			this.hae_num = (Menu.diff + 1) * 15;
			this.king_num = Menu.diff + 1;

			for (int i = 0; i < hae_num; i++) {
				this.Hae.add(new Hae(Math.random() * 980 + 20, Math.random() * 830 + 20, false));
			}
			for (Hae h : HaePanel.this.Hae) h.start();

			this.repaintThread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						HaePanel.this.repaint();
						if (boss == false && exist_hae() == true) {
							for (int i = 0; i < king_num; i++) {
								king.add(new KingHae(Math.random() * 450 + 50, Math.random() * 550 + 50, false, 50, 0));
							}
							for (KingHae h : HaePanel.this.king) h.start();
							boss = true;
						}
						try { Thread.sleep(10); } //100FPS
						catch (InterruptedException e) {}
					}
				}});
			this.repaintThread.start();

		} else if (Menu.moden == 1) {

			this.hae_num = 15;

			for (int i = 0; i < hae_num; i++) {
				this.Hae.add(new Hae(Math.random() * 980 + 20, Math.random() * 830 + 20, false));
			}
			for (Hae h : HaePanel.this.Hae) h.start();

			this.repaintThread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						HaePanel.this.repaint();
						if (exist_hae() == true && Timers.check == 0) {
							for (int i = 0; i < hae_num; i++) {
								Hae.add(new Hae(Math.random() * 980 + 20, Math.random() * 830 + 20, false));
							}
							for (Hae h : HaePanel.this.Hae) h.start();
						}
						try { Thread.sleep(10); } //100FPS
						catch (InterruptedException e) {}
					}
				}});
			this.repaintThread.start();

		} else if (Menu.moden == 2) {

			this.king_num = 1;

			for (int i = 0; i < king_num; i++) {
				king.add(new KingHae(Math.random() * 450 + 50, Math.random() * 550 + 50, false, 50, 0));
			}
			for (KingHae h : HaePanel.this.king) h.start();

			this.repaintThread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						HaePanel.this.repaint();
						if (exist_king() == true && Timers.check == 0) {
							for (int i = 0; i < king_num; i++) {
								king.add(new KingHae(Math.random() * 450 + 50, Math.random() * 550 + 50, false, 50, 0));
							}
							for (KingHae h : HaePanel.this.king) h.start();
						}
						try { Thread.sleep(10); } //100FPS
						catch (InterruptedException e) {}
					}
				}});
			this.repaintThread.start();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = this.getSize();
		Graphics2D gg = (Graphics2D)g;

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int)d.getWidth(), (int)d.getHeight());

		if (Menu.ran < 0.1) {
			g.drawImage(sea, 0, 0, this);
		} else if (Menu.ran < 0.2) {
			g.drawImage(river, -50, -50, this);
		} else if (Menu.ran < 0.3) {
			g.drawImage(toilet, 0, 0, this);
		} else if (Menu.ran < 0.4) {
			g.drawImage(shop, 0, 0, this);
		} else if (Menu.ran < 0.5) {
			g.drawImage(mountain, 0, 0, this);
		} else if (Menu.ran < 0.6) {
			g.drawImage(heaven, 0, 0, this);
		} else if (Menu.ran < 0.7) {
			g.drawImage(hell, 0, 0, this);
		} else if (Menu.ran < 0.8) {
			g.drawImage(school, 0, 0, this);
		} else if (Menu.ran < 0.9) {
			g.drawImage(marriage, 0, 0, this);
		} else {
			g.drawImage(station, 0, 0, this);
		}

		if (this.exist() == true && cc == -1 && boss == true && Timers.check != -2) {
			for (int j = 0; j < 30; j++) {
				double x, y, ra, rg, rb, cr, cg, cb;
				x = Math.random() * 900.0;
				y = Math.random() * 300.0;

				ra = Math.random();
				rg = Math.random();
				rb = Math.random();

				if (ra < 0.33) {
					cr = 255;
				} else if (0.33 <= ra && ra < 0.67) {
					cr = 128;
				} else {
					cr = 0;
				}

				if (cr == 255) {
					if (rg < 0.33) {
						cg = 255;
						cb = 0;
					} else if (0.33 <= rg && rg < 0.67) {
						cg = 128;
						cb = 0;
					} else {
						cg = 0;
						if (rb < 0.33) {
							cb = 255;
						} else if (0.33 <= rb && rb < 0.67) {
							cb = 128;
						} else {
							cb = 0;
						}
					}
				} else if (cr == 128) {

					if (rg < 0.5) {
						cg = 255;
						cb = 0;
					} else {
						cg = 0;
						cb = 255;
					}

				} else {

					if (rg < 0.33) {
						cg = 255;
						if (rb < 0.33) {
							cb = 255;
						} else if (0.33 <= rb && rb < 0.67) {
							cb = 128;
						} else {
							cb = 0;
						}
					} else if (0.33 <= rg && rg < 0.67) {
						cg = 128;
						cb = 255;
					} else {
						cg = 0;
						cb = 255;
					}

				}

				for (int i = 0; i < 100; i++) {
					int r = 20;
					Hanabi h = new Hanabi(x, y, r, r, cr, cg, cb);
					this.hana.add(h);
					h.start();
				}
				cc = 0;
			}
		}

		if (cc == 0) {
			if (sound == 0) {
				Clip clip = createClip(new File("victory.wav"));
				clip.start();
			}
			sound = 1;
			for (Hanabi b : this.hana) {
				if ((int)b.alpha > 0.0) {
					gg.setColor(new Color((int)b.r, (int)b.g, (int)b.b, (int)b.alpha));
					gg.fill(b);
				}
			}
		}

		int w = icon.getIconWidth();
		int h = icon.getIconHeight();

		int w1 = icon1.getIconWidth();
		int h1 = icon1.getIconHeight();

		int w2 = icon2.getIconWidth();
		int h2 = icon2.getIconHeight();

		int w3 = icon3.getIconWidth();
		int h3 = icon3.getIconHeight();

		int haew = haeicon.getIconWidth();
		int haeh = haeicon.getIconHeight();

		int bomw = bomicon.getIconWidth();
		int bomh = bomicon.getIconHeight();

		int expw = expicon.getIconWidth();
		int exph = expicon.getIconHeight();

		int kw = kingicon.getIconWidth();
		int kh = kingicon.getIconHeight();
		
		int dw = donicon.getIconWidth();
		int dh = donicon.getIconHeight();
		
		int bw = banicon.getIconWidth();
		int bh = banicon.getIconHeight();

		if (Menu.moden == 0) { 

			number = king_num + hae_num;

			for (Hae hae : HaePanel.this.Hae) {
				if (hae.die == true) number--;

				if (nf == 1 || nf == 3 || nf == 4) {
					if (hae.sgetVx() < 0) {
						if (hae.die) {
							g.drawImage(this.dieimg, (int)(hae.sgetox() - haew / 2), (int)(hae.sgetoy() - haeh / 2), this);
						} else {
							g.drawImage(this.haeimg, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						}
					} else {
						if (hae.die) {
							g.drawImage(this.dieimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						} else {
							g.drawImage(this.haeimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						}
					}
				} else if (nf == 2) {
					if (hae.sgetVx() < 0) {
						if (hae.die) {
							g.drawImage(this.dieimg, (int)(hae.sgetox() - haew / 2), (int)(hae.sgetoy() - haeh / 2), this);
						} else {
							g.drawImage(this.haeimg, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						}
					} else {
						if (hae.die) {
							g.drawImage(this.dieimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						} else {
							g.drawImage(this.haeimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
						}
					}
				}
			}

			for (KingHae k : HaePanel.this.king) {
				if (k.die == true) number--;

				if (k.sgetVx() < 0) {
					if (k.die) {
						g.drawImage(this.king_die, (int)(k.sgetox() - kw / 2), (int)(k.sgetoy() - kh / 2), this);
					} else {
						g.drawImage(this.kingimg, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
					}
				} else {
					if (k.die) {
						g.drawImage(this.king_die_rev, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
					} else {
						g.drawImage(this.king_rev, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
					}
				}

				if (k.die == true && k.sound == 0) {
					Clip clip = createClip(new File("victory.wav"));
					FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
					controlByLinearScalar(control, (double)(hae_num) / ((double)((Menu.diff + 1) * 100.0)));
					clip.start();
					k.sound = -1;
				}
			}
			
			if (Timers.check == -3) {
				HaePanel.number = 0;
				Hae.clear();
				if (Timers.a >= 5) {
					king.clear();
				}
			}

		} else if (Menu.moden == 1) {
			h_number = 0;

			if (this.exist() == false) {
				for (Hae hae : HaePanel.this.Hae) {
					if (hae.die == true) h_number++;

					if (Timers.check == 0) res_h = h_number;

					if (nf == 1 || nf == 3 || nf == 4) {
						if (hae.sgetVx() < 0) {
							if (hae.die) {
								g.drawImage(this.dieimg, (int)(hae.sgetox() - haew / 2), (int)(hae.sgetoy() - haeh / 2), this);
							} else {
								g.drawImage(this.haeimg, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							}
						} else {
							if (hae.die) {
								g.drawImage(this.dieimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							} else {
								g.drawImage(this.haeimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							}
						}
					} else if (nf == 2) {
						if (hae.sgetVx() < 0) {
							if (hae.die) {
								g.drawImage(this.dieimg, (int)(hae.sgetox() - haew / 2), (int)(hae.sgetoy() - haeh / 2), this);
							} else {
								g.drawImage(this.haeimg, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							}
						} else {
							if (hae.die) {
								g.drawImage(this.dieimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							} else {
								g.drawImage(this.haeimg_rev, (int)(hae.sgetX() - haew / 2), (int)(hae.sgetY() - haeh / 2), this);
							}
						}
					}
				}
			}

			if (Timers.check == -2) Hae.clear();

		} else if (Menu.moden == 2) {
			k_number = 0;

			if (this.exist_king() == false) {
				for (KingHae k : HaePanel.this.king) {
					if (k.die == true) k_number++;

					if (Timers.check == 0) res_k = k_number;

					if (k.sgetVx() < 0) {
						if (k.die) {
							g.drawImage(this.king_die, (int)(k.sgetox() - kw / 2), (int)(k.sgetoy() - kh / 2), this);
						} else {
							g.drawImage(this.kingimg, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
						}
					} else {
						if (k.die) {
							g.drawImage(this.king_die_rev, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
						} else {
							g.drawImage(this.king_rev, (int)(k.sgetX() - kw / 2), (int)(k.sgetY() - kh / 2), this);
						}
					}

					if (k.die == true && k.sound == 0) {
						Clip clip = createClip(new File("victory.wav"));
						clip.start();
						k.sound = -1;
					}
				}

				if (Timers.check == -2) king.clear();
			}


		}

		if (nf == 1) {
			if (this.ch == 1) {
				g.drawImage(this.img, lastX - w / 3, lastY - h / 4, this);
			} else if (this.ch == 2) {
				g.drawImage(this.img2, lastX - w2 / 3, lastY - h2 / 4, this);
			}
		} else if (nf == 2) {
			if (this.ch == 1) {
				g.drawImage(this.img1, lastX - w1 / 2, lastY - h1 / 2, this);
			} else if (this.ch == 2) {
				g.drawImage(this.img3, lastX - w3 / 2, lastY - h3 / 2, this);
			}
		} else if (nf == 3) {
			if (this.ch == 1) {
				g.drawImage(this.bom, lastX - bomw / 2, lastY - bomh / 2, this);
			} else if (this.ch == 2) {
				g.drawImage(this.exp, xx - expw / 2, yy - exph / 2, this);
			}
		} else if (nf == 4) {
			if (this.ch == 1) {
				g.drawImage(this.ban, lastX - bw / 2, lastY - bh / 2, this);
			} else if (this.ch == 2) {
				g.drawImage(this.don, lastX - dw / 2, lastY - dh / 2, this);
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		this.lastX = e.getX(); this.lastY = e.getY();
		this.repaint();

		if (Timers.check == 0 && nf == 2) {
			for (Hae hae : HaePanel.this.Hae) {
				if ((Math.abs(hae.sgetX() - e.getX()) < 30) && (Math.abs(hae.sgetY() - e.getY())) < 30) {
					if (hae.die == false) {
						Clip clip = createClip(new File("eraser.wav"));
						clip.start();
					}

					hae.die = true;
				}
			}
		}

		if (Timers.check == 0 && nf == 2) {
			for (KingHae k : HaePanel.this.king) {
				if ((Math.abs(k.sgetX() - e.getX()) < 150) && (Math.abs(k.sgetY() - e.getY())) < 150) {
					if (k.die == false && k.hp > 0) {
						k.hp--;
						Clip clip = createClip(new File("eraser.wav"));
						clip.start();
					} 

					if (k.hp <= 0) {
						k.die = true;
					}
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

		this.lastX = e.getX(); this.lastY = e.getY();
		this.repaint();


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mousePressed(MouseEvent e){
		// TODO 自動生成されたメソッド・スタブ
		xx = e.getX(); yy = e.getY();
		this.ch = 2;
		
		if (Timers.check != -1 && nf == 4) {
			Clip clip = createClip(new File("donkey.wav"));
			clip.start();
		}

		if (Timers.check == 0) {
			if (nf == 1) {

				Clip clip = createClip(new File("oh.wav"));
				clip.start();
				
			} else if (nf == 2){

				Clip clip = createClip(new File("eraser.wav"));
				clip.start();

			} else if (nf == 3) {

				Clip clip = createClip(new File("exp.wav"));
				clip.start();

			}

			if (nf == 1 || nf == 2) {
				for (Hae hae : HaePanel.this.Hae) {
					if ((Math.abs(hae.sgetX() - e.getX()) < 30) && (Math.abs(hae.sgetY() - e.getY())) < 30) {
						if (hae.die == false) {
							Clip clip = createClip(new File("punch.wav"));
							clip.start();
						}
						hae.die = true;
					}
				} 
			} else if (nf == 3) {
				for (Hae hae : HaePanel.this.Hae) {
					if ((Math.abs(hae.sgetX() - e.getX()) < 100) && (Math.abs(hae.sgetY() - e.getY())) < 100) {
						hae.die = true;
					}
				} 
			} else if (nf == 4) {
				for (Hae hae : HaePanel.this.Hae) {
					if ((Math.abs(hae.sgetX() - e.getX()) < 900) && (Math.abs(hae.sgetY() - e.getY())) < 900) {
						if (hae.die == false) {
							Clip clip = createClip(new File("punch.wav"));
							FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
							controlByLinearScalar(control, (double)(hae_num) / ((double)((Menu.diff + 1) * 100.0)));
							clip.start();
						}
						hae.die = true;
					}
				} 
			}

			if (nf == 1 || nf == 2) {
				for (KingHae k : HaePanel.this.king) {
					if ((Math.abs(k.sgetX() - e.getX()) < 150) && (Math.abs(k.sgetY() - e.getY())) < 150) {
						if (k.hp > 0 && k.die == false) {
							Clip clip = createClip(new File("punch.wav"));
							clip.start();
							k.hp--;
						} 

						if (k.hp <= 0) {
							k.die = true;
						}

					}
				} 
			} else if (nf == 3) {
				for (KingHae k : HaePanel.this.king) {
					if ((Math.abs(k.sgetX() - e.getX()) < 300) && (Math.abs(k.sgetY() - e.getY())) < 300) {
						if (k.hp > 0) {
							k.hp -= 5;
							Clip clip = createClip(new File("punch.wav"));
							clip.start();
						}

						if (k.hp <= 0) {
							k.die = true;
						}
					}
				} 
			} else if (nf == 4) {
				for (KingHae k : HaePanel.this.king) {
					if ((Math.abs(k.sgetX() - e.getX()) < 900) && (Math.abs(k.sgetY() - e.getY())) < 900) {
						if (k.hp > 0) {
							k.hp -= 0.5;
							Clip clip = createClip(new File("punch.wav"));
							FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
							controlByLinearScalar(control, (double)(king_num) / ((Menu.diff + 1) * (Menu.diff + 1)));
							clip.start();
						}

						if (k.hp <= 0) {
							k.die = true;
						}
					}
				} 
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		this.ch = 1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void changeImg(int n) {
		nf = n;
	}

	public static Clip createClip(File path) {
		//指定されたURLのオーディオ入力ストリームを取得
		try (AudioInputStream ais = AudioSystem.getAudioInputStream(path)){

			//ファイルの形式取得
			AudioFormat af = ais.getFormat();

			//単一のオーディオ形式を含む指定した情報からデータラインの情報オブジェクトを構築
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);

			//指定された Line.Info オブジェクトの記述に一致するラインを取得
			Clip c = (Clip)AudioSystem.getLine(dataLine);

			//再生準備完了
			c.open(ais);

			return c;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean exist() {
		for (Hae hae : HaePanel.this.Hae) {
			if (hae.die == false) return false;
		}

		for (KingHae k : HaePanel.this.king) {
			if (k.die == false) return false;
		}

		return true;
	}

	public boolean exist_hae() {
		for (Hae hae : HaePanel.this.Hae) {
			if (hae.die == false) return false;
		}

		return true;
	}

	public boolean exist_king() {
		for (KingHae k : HaePanel.this.king) {
			if (k.die == false) return false;
		}


		return true;
	}

	private void controlByLinearScalar(FloatControl control, double linearScalar) {
		control.setValue((float)Math.log10(linearScalar) * 20);
	}

}
