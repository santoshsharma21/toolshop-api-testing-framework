/**
 * 
 */
package com.toolshop.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 */
public class ConfigUtils {

	private static Properties pro;
	private static FileReader file;
	
	public static Properties loadConfig() throws IOException{
		file = new FileReader("./src/test/resources/configurations/config.properties");
		pro = new Properties();
		pro.load(file);
		return pro;
	}
	
	public static String getUrl(){
		String url = null;
		try {
			url = loadConfig().getProperty("base_url");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public static String buildEndpoint(String route){
		return getUrl() + route;
	}
}