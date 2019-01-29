package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import network.NetworkConnection;

public class SELInterpreter {

	private HashMap<String, String> variableMap;
	private HashMap<String, String> keyMap;
	private NetworkConnection con;

	public SELInterpreter(NetworkConnection con) {
		variableMap = new HashMap<>();
		keyMap = new HashMap<>();
		this.con = con;
	}

	public void interpretFile(String filePath) {
		try {
			Scanner sc = new Scanner(new File(filePath));

			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				// Check if line is not a comment
				if (line.startsWith("#"))
					continue;

				if (line.contains("=")) {
					String[] args = line.split("=");
					keyMap.put(args[0], args[1]);
				} else {
					evaluateExpression(line);
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getVariableMap() {
		return variableMap;
	}

	public HashMap<String, String> getKeyMap() {
		return keyMap;
	}

	public void evaluateExpression(String ex) {
		int index = 0;

		while ((index = ex.indexOf("$", index + 1)) != -1) {
			int indexClosing = ex.indexOf(")", index);
			String varName = ex.substring(ex.indexOf("(", index) + 1, indexClosing).trim();
			String val = this.getVariableMap().get(varName);
			if (val != null) {
				ex = ex.replace(ex.substring(index, indexClosing + 1), val);
			}
		}

		if (ex.startsWith("send")) {
			con.getWriter().println(ex.substring(ex.indexOf("(", 4) + 1, ex.lastIndexOf(")")));
			con.getWriter().flush();
		} else if (ex.startsWith("var")) {
			String key = ex.substring(ex.indexOf("(") + 1, ex.indexOf(",")).trim();
			String value = ex.substring(ex.indexOf(",") + 1, ex.indexOf(")")).trim();
			// Check if Variable exists
			if (this.getVariableMap().containsKey(key)) {
				this.getVariableMap().replace(key, value);
				// If no variable with keyname, create one
			} else {
				this.getVariableMap().put(key, value);
			}
		}
	}

}
