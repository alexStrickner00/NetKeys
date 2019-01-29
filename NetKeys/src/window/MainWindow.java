package window;

import javax.swing.JFrame;

import listener.NetKeyListener;

public class MainWindow {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.addKeyListener(new NetKeyListener());
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
