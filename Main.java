
import javax.swing.SwingUtilities;
public class Main {
	public static int high_h = 0, high_k = 0;
	
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				/*
				JFrame frame = new JFrame();

				// フレームの設定関連
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(900, 900);

				
				HaePanel panel = new HaePanel();
				WeaponButton button = new WeaponButton(panel);
				Timers time = new Timers(panel);

				frame.getContentPane().add(BorderLayout.SOUTH, button);
				frame.getContentPane().add(BorderLayout.CENTER, panel);
				frame.getContentPane().add(BorderLayout.NORTH, time);

				frame.setVisible(true);
				*/
				
				Menu menu = new Menu();
				
				menu.setVisible(true);
			}
		});
	}
}

