import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame{
	public Game() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 900);
		this.setTitle("ハエたたきゲーム！");
		
		HaePanel panel = new HaePanel();
		WeaponButton button = new WeaponButton(panel);
		Timers time = new Timers(panel);

		this.getContentPane().add(BorderLayout.SOUTH, button);
		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.getContentPane().add(BorderLayout.NORTH, time);

		this.setVisible(true);
	}
}
