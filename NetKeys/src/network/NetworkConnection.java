package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkConnection extends Thread{

	private Socket socket;
	private String ip;
	private int port;

	private BufferedReader reader;
	private PrintWriter writer;
	
	public NetworkConnection(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public void run() {
		while(socket.isConnected()) {
			String val = null;
			try {
				if((val = reader.readLine()) != null) {
					System.out.println(val);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void connect() throws IOException {
		socket = new Socket(this.ip, this.port);
		if (socket.isConnected()) {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		this.start();
	}

	public String waitForString() {
		String input = null;
		try {
			while ((input = reader.readLine()) == null)
				;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;

	}

	public PrintWriter getWriter() {
		return writer;
	}

}
