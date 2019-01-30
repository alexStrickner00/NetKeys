package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigReader {

	private String filePath;

	HashMap<String, String> configHashMap;

	public ConfigReader(String filePath) {
		this.filePath = filePath;
		this.configHashMap = new HashMap<String, String>();
	}

	public ConfigReader() {
		this("default.conf");
	}

	public void readConfigFile() throws FileNotFoundException {

		if (filePath.equals(""))
			return;

		Scanner sc = new Scanner(new File(filePath));

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line != "" && !line.trim().startsWith("#")) {
				int splitIndex = line.indexOf("=");
				String propertyName = line.substring(0, splitIndex);
				String value = line.substring(splitIndex + 1);

				configHashMap.put(propertyName, value);
			}
		}
		sc.close();
	}

	public String getPropertyByName(String propertyName) {
		return configHashMap.get(propertyName);
	}
}
