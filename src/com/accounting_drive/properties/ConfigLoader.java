package com.accounting_drive.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Used to load the property file and obtain related properties
 * @author bharath
 *
 */
public class ConfigLoader {
	
	private static Properties properties = null;

	
	/**
	 * Returns the property value for the given property key 
	 * @param property
	 * @return
	 */
	public static String getValueForProperty(String propertyKey){
		if(properties==null){
			try (InputStream input = ConfigLoader.class.getResourceAsStream("config.properties")){
				properties = new Properties();
				properties.load(input);
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return properties.getProperty(propertyKey);
	}
	
	
	

	
}
