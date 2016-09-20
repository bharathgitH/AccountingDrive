package com.accounting_drive.log;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.accounting_drive.properties.ConfigLoader;

public class FileLog implements Log {
	
	private String parentDirectory;
	private String subDirectory;
	private String basePath;
	
	{
		basePath = ConfigLoader.getValueForProperty("LOG_DIR_PATH");
	}

	@Override
	public void log(Level logType, String className, String methodName, String logInfo) {
		
		String parentDir = this.getParentDirectory();
		String subDir = this.getSubDirectory();
		
		StringBuffer filePath = new StringBuffer(basePath).append(parentDir).append("/").append(subDir);
		
		if(filePath!=null && this.checkIfDirectoryExistsOrCreate(filePath.toString())){
			filePath.append("/").append(this.getLogFileName());
			Logger logger = Logger.getLogger("AccountingDriveLog");
			FileHandler handler = null;;
			try {
				handler = new FileHandler(filePath.toString(), true);
			
				logger.addHandler(handler);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        handler.setFormatter(formatter);
		        String logMsg = this.getMsgToWriteToLogFile(className, methodName, logInfo);
		        		
		        logger.log(logType, logMsg);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Gets the parent directory for the log location based on current year and month
	 * @return
	 */
	private String getParentDirectory(){
		
		Date currDate = new Date();
		String currentYear = String.valueOf(currDate.getYear());
		String currentMonth = String.valueOf(currDate.getMonth());
		
		String tempParentDir = currentYear.concat(currentMonth);
		if(!this.parentDirectory.equals(tempParentDir)){
			this.parentDirectory = tempParentDir;
		}
			
		return this.parentDirectory;
	}
	
	/**
	 * Gets the sub directory for the log location based on current date
	 * @return
	 */
	private String getSubDirectory(){
		
		String currentDate = String.valueOf(new Date().getDate());
		
		if(!this.subDirectory.equals(currentDate)){
			this.subDirectory = currentDate;
		}
			
		return this.subDirectory;
	}
	
	/**
	 * Gets the log file name to which the log message has to be written
	 * @return
	 */
	private String getLogFileName(){
		String logFileName = null;
		try{
			Date currentDate = new Date();
			String baseFileName = ConfigLoader.getValueForProperty("LOG_BASE_FILE_NAME");
			logFileName = baseFileName.concat(String.valueOf(currentDate.getHours()));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return logFileName;
	}
	
	/**
	 * Check if the directory exists or creates the directory
	 * @param path
	 * @return
	 */
	private boolean checkIfDirectoryExistsOrCreate(String path){
		boolean exists = false;
		try{
			File file = new File(path);
			if(file.exists()){
				exists = true;
			}
			else{
				exists = file.mkdirs();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Returns the log msg that will be written to the file
	 * @param className
	 * @param methodName
	 * @param msg
	 * @return
	 */
	private String getMsgToWriteToLogFile(String className,String methodName,String msg){
		StringBuilder logMsg = new StringBuilder(className);
		logMsg.append("::").append(methodName);
		logMsg.append("::").append(msg);
		
		return logMsg.toString();
	}

}
