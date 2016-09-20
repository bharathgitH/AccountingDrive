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
	
	private static Properties properties = new Properties();
	
	{
		try (InputStream input = new FileInputStream("config.properties")){
			properties.load(input);
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns the property value for the given property key 
	 * @param property
	 * @return
	 */
	public static String getValueForProperty(String propertyKey){
		return properties.getProperty(propertyKey);
	}
	
	
	

	
}
