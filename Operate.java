import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Operate extends JFrame {
	
	public Operate() {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("操作方法");
		
		JLabel l1 = new JLabel("1.マウスのカーソルでハエに照準を合わせる！");
		JLabel l2 = new JLabel("2.クリックでハエを倒す！！");
		
		JLabel i1 = new JLabel(new ImageIcon("o1.png"));
		LineBorder border = new LineBorder(Color.RED, 2, true);
		i1.setBorder(border);
		JLabel i2 = new JLabel(new ImageIcon("o2.png"));
		LineBorder border1 = new LineBorder(Color.RED, 2, true);
		i2.setBorder(border1);
		
		this.setLayout(null);
		this.add(l1);
		this.add(l2);
		this.add(i1);
		this.add(i2);
		
		l1.setBounds(50, 120, 500, 25);
		l2.setBounds(50, 300, 500, 25);
		
		i1.setBounds(320, 60, 145, 139);
		i2.setBounds(320, 240, 145, 139);
		
		this.setVisible(true);
	}
}
