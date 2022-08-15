package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	public static String projectPath = System.getProperty("user.dir");
	public static String url;
	static Properties prop = new Properties();

	public static void getProperties() {
		try {
			InputStream input = new FileInputStream(projectPath + "/src/test/java/config/config.properties");
			prop.load(input);
			url = prop.getProperty("baseurl");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		getProperties();

	}

}
