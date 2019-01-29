package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import config.SELInterpreter;
import network.NetworkConnection;

public class NetKeyListener implements KeyListener {

	private NetworkConnection con;
	private SELInterpreter interpreter;

	public NetKeyListener() {
		con = new NetworkConnection("127.0.0.1", 8099);
		try {
			con.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		interpreter = new SELInterpreter(con);
		interpreter.interpretFile("keys.conf");
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String ex = interpreter.getKeyMap().get(e.getKeyChar() + "");
		if (ex == null)
			return;

		interpreter.evaluateExpression(ex);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
