package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import config.ConfigReader;
import config.SELInterpreter;
import network.NetworkConnection;

public class NetKeyListener implements KeyListener {

	private NetworkConnection con;
	private SELInterpreter interpreter;
	private ConfigReader config;

	public NetKeyListener() {
		config = new ConfigReader("socket.conf");
		try {
			config.readConfigFile();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		con = new NetworkConnection(config.getPropertyByName("ip"), Integer.parseInt(config.getPropertyByName("port")));
		try {
			con.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		interpreter = new SELInterpreter(con);
		interpreter.interpretFile("keys.sel");

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
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
