import static java.util.concurrent.TimeUnit.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Timers extends JPanel implements ActionListener{
	private HaePanel p;
	private ImageIcon clock1 = new ImageIcon("clock.png");
	private ImageIcon hae = new ImageIcon("hae.png");
	private ImageIcon high = new ImageIcon("high.png");
	private ImageIcon king = new ImageIcon("king_menu.png");
	private boolean ch = false, alch = false;
	private Clip music = createClip(new File("music.wav"));
	public static int a = 0;

	public static int check, but = 0;

	public Timers(HaePanel Panel) {
		Panel = p;
		check = -1;

		String start1 = "ゲーム開始まで : ";
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		JLabel label = new JLabel(clock1);
		this.add(label);

		TimerTask task1 = new TimerTask() {
			int start = 10;
			@Override
			public void run() {
				
				label.setText(start1 + start);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				start--;
				
				if (start < 4 && ch == false) {
					Clip clip = createClip(new File("count.wav"));
					clip.start();
					ch = true;
				}
				
				if (start < 1) {
					scheduler.shutdown();
					label.setText("");
					start();
				}

			}

		};
		scheduler.scheduleAtFixedRate(task1, 0, 1, SECONDS);
	}

	public void start() {
		
		String str = "制限時間 : ";
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		JLabel label = new JLabel(str);

		this.add(label);
		
		FloatControl control = (FloatControl)music.getControl(FloatControl.Type.MASTER_GAIN);
		controlByLinearScalar(control, 1.0);
		music.start();
		

		if (Menu.moden == 0) {
			
			String str1 = "残りハエ数 : ";
			JLabel label1 = new JLabel(str1);
			JLabel img = new JLabel(hae);
			String spa = "  ";
			JLabel spal = new JLabel(spa);
			this.add(spal);
			this.add(img);
			
			this.add(label1);
			
			TimerTask task = new TimerTask() {
				int limit = 60;
				@Override
				public void run() {
					check = 0;
					label.setText(str + limit);
					Clip clip1 = createClip(new File("alarm.wav"));
					
					if (HaePanel.number == 0) {
						music.stop();
						limit = 15;
						check = -3;
						a++;
						clip1.stop();
						clip1.close();
						label.setText("ゲームクリア！！");
						scheduler.shutdown();
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					limit--;
					
					if (limit < 11 && alch == false) {
						clip1.start();
						alch = true;
					}
					
					if (limit < 1) {
						music.stop();
						Clip clip = createClip(new File("lose.wav"));
						clip.start();
						check = -2;
						label.setText("ゲームオーバー！");
						scheduler.shutdown();
					}

				}

			};
			scheduler.scheduleAtFixedRate(task, 0, 1, SECONDS);
			
			Thread tas = new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
					// TODO 自動生成されたメソッド・スタブ
						
						label1.setText(str1 + HaePanel.number);
					}
				}

			});
			tas.start();

		} else if (Menu.moden == 1) {
			String str1 = "倒したハエ数 : ";
			JLabel label1 = new JLabel(str1);
			String str2 = "ハイスコア : ";
			JLabel label2 = new JLabel(str2);
			String s = "  ";
			JLabel ls = new JLabel(s);
			JLabel ls1 = new JLabel(s);
			JLabel i1 = new JLabel(hae);
			JLabel i2 = new JLabel(high);
			this.add(ls);
			this.add(i1);
			this.add(label1);
			this.add(ls1);
			this.add(i2);
			this.add(label2);
			
			TimerTask task = new TimerTask() {
				int limit = 60;
				@Override
				public void run() {
					check = 0;
					label.setText(str + limit);
					Clip clip1 = createClip(new File("alarm.wav"));
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					limit--;
					
					if (limit < 11 && alch == false) {
						clip1.start();
						alch = true;
					}
					
					if (limit < 1) {
						music.stop();
						Clip clip = createClip(new File("end.wav"));
						clip.start();
						check = -2;
						label.setText("終了！");
						scheduler.shutdown();
					}

				}

			};
			scheduler.scheduleAtFixedRate(task, 0, 1, SECONDS);
			
			Thread tas = new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
					// TODO 自動生成されたメソッド・スタブ
						label1.setText(str1 + HaePanel.res_h);
						label2.setText(str2 + Main.high_h);
						
						if (Main.high_h < HaePanel.res_h) {
							Main.high_h = HaePanel.res_h;
						}
					}
				}

			});
			tas.start();
		} else if (Menu.moden == 2) {
			String str1 = "倒したボスハエ数 : ";
			JLabel label1 = new JLabel(str1);
			String str2 = "ハイスコア : ";
			JLabel label2 = new JLabel(str2);
			String s = "  ";
			JLabel ls = new JLabel(s);
			JLabel ls1 = new JLabel(s);
			JLabel i1 = new JLabel(king);
			JLabel i2 = new JLabel(high);
			this.add(ls);
			this.add(i1);
			this.add(label1);
			this.add(ls1);
			this.add(i2);
			this.add(label2);
			
			TimerTask task = new TimerTask() {
				int limit = 60;
				@Override
				public void run() {
					check = 0;
					label.setText(str + limit);
					Clip clip1 = createClip(new File("alarm.wav"));
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					limit--;
					
					if (limit < 11 && alch == false) {
						clip1.start();
						alch = true;
					}
					
					if (limit < 1) {
						music.stop();
						Clip clip = createClip(new File("end.wav"));
						clip.start();
						check = -2;
						label.setText("終了！");
						scheduler.shutdown();
					}

				}

			};
			scheduler.scheduleAtFixedRate(task, 0, 1, SECONDS);
			
			Thread tas = new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
					// TODO 自動生成されたメソッド・スタブ
						label1.setText(str1 + HaePanel.res_k);
						label2.setText(str2 + Main.high_k);
						
						if (Main.high_k < HaePanel.res_k) {
							Main.high_k = HaePanel.res_k;
						}
					}
				}

			});
			tas.start();
		}

		

		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		but = -1;

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
	
	private void controlByLinearScalar(FloatControl control, double linearScalar) {
		control.setValue((float)Math.log10(linearScalar) * 20);
	}
}

