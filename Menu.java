import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class Menu extends JFrame implements ActionListener, ItemListener{
	private JButton button1, button2;
	private JPanel header, footer, upper;
	private JLabel imageLabel;
	public static int moden = 0, diff = 0;
	public JComboBox<String> combo;
	public static double ran;
	
	public Menu() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 800);
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.setTitle("メニュー");
		this.setVisible(true);
		
		// 上部分のパネルを作成しコンテナに追加
		this.header = new JPanel();
		this.getContentPane().add(this.header);
		
		// 背景色を青にし,画像のJLabelをheaderに登録
		this.header.setBackground(Color.cyan);
		this.imageLabel = new JLabel(new ImageIcon("game.png"));
		this.header.add(imageLabel);
		
		// 下部分のパネルを作成
		this.footer = new JPanel();
		this.footer.setLayout(new GridLayout(2, 1));
		this.getContentPane().add(this.footer);
		
		// 下上部分のパネルを作成し背景色を緑にしてfooterに登録
		this.upper = new JPanel();
		this.upper.setLayout(null);
		this.upper.setBackground(Color.GREEN);
		this.footer.add(this.upper);
		
		this.button2 = new JButton("操作方法");
		this.button2.addActionListener(this);
		this.upper.add(button2);
		this.button2.setBounds(180, 30, 100, 25);
		
		JLabel dif = new JLabel("　　難易度 : ");
		this.upper.add(dif);
		//dif.setBounds(100, 10, 100, 100);
		dif.setBounds(100, 90, 100, 100);
		
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("かんたん");
		combo.addItem("ふつう");
		combo.addItem("むずかすぃ～");
		combo.addItem("とてもむずかすぃ～");
		combo.addItem("超むずかすぃ～");
		combo.addItem("激むず");
		combo.addItem("無理ゲー");
		combo.addItem("（・∀・）");
		this.upper.add(combo);
		//combo.setBounds(200, 50, 180, 20);
		combo.setBounds(200, 130, 180, 20);
		combo.addItemListener(this);
		
		JLabel mode = new JLabel("モード選択 : ");
		this.upper.add(mode);
		mode.setBounds(100, 40, 100, 100);
		//mode.setBounds(100, 80, 100, 100);
		
		JComboBox<String> combo1 = new JComboBox<String>();
		combo1.addItem("ノーマル");
		combo1.addItem("スコアアタック(ノーマル)");
		combo1.addItem("スコアアタック(ボス)");
		this.upper.add(combo1);
		combo1.setBounds(200, 80, 180, 20);
		//combo1.setBounds(200, 120, 180, 20);
		combo1.addItemListener(this);
		
		this.button1 = new JButton("すた～と");
		this.button1.addActionListener(this);
		this.footer.add(button1);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String s = (String)e.getItem();
			
			if (s == "かんたん") {
				diff = 0;
			} else if (s == "ふつう") {
				diff = 1;
			} else if (s == "むずかすぃ～") {
				diff = 2;
			} else if (s == "とてもむずかすぃ～") {
				diff = 3;
			} else if (s == "超むずかすぃ～") {
				diff = 4;
			} else if (s == "激むず") {
				diff = 5;
			} else if (s == "無理ゲー") {
				diff = 6;
			} else if (s == "（・∀・）") {
				diff = 7;
			} else if (s == "ノーマル") {
				moden = 0;
			} else if (s == "スコアアタック(ノーマル)") {
				moden = 1;
			} else if (s == "スコアアタック(ボス)") {
				moden = 2;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (e.getSource() == this.button1) {
			
			ran = Math.random();
			
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					Game g = new Game();
				}
				
			});
			
		} else if (e.getSource() == this.button2) {
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					Operate o = new Operate();
				}
				
			});
		}
	}
}

