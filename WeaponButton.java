
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class WeaponButton extends JPanel implements ActionListener {
	private ButtonGroup radio;
	private JRadioButton radio1;
	private JRadioButton radio2, radio3, radio4;

	private HaePanel panel;

	public WeaponButton(HaePanel p) {
		panel = p;

		this.radio = new ButtonGroup();

		this.radio1 = new JRadioButton("ハエ", true);

		this.radio.add(radio1);

		this.add(this.radio1);

		this.radio1.addActionListener(this);
		
		panel.changeImg(1);

		if (Menu.moden == 0) {

			this.radio2 = new JRadioButton("フワ", false);
			this.radio3 = new JRadioButton("ボム", false);
			this.radio4 = new JRadioButton("ゴリ", false);

			this.radio.add(radio2);
			this.radio.add(radio3);
			this.radio.add(radio4);

			this.add(this.radio2);
			this.add(this.radio3);
			this.add(this.radio4);

			this.radio2.addActionListener(this);
			this.radio3.addActionListener(this);
			this.radio4.addActionListener(this);
		}

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (e.getSource() == this.radio1) {
			panel.changeImg(1);
		} else if (e.getSource() == this.radio2) {
			panel.changeImg(2);
		} else if (e.getSource() == this.radio3) {
			panel.changeImg(3);
		} else if (e.getSource() == this.radio4) {
			panel.changeImg(4);
		}
	}
}

