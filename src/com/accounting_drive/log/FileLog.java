package com.accounting_drive.log;

import java.io.File;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
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
	private static FileLog instance;
	
	{
		basePath = ConfigLoader.getValueForProperty("LOG_DIR_PATH");
	}
	
	private FileLog(){
		
	}
	
	/**
	 * Logs the log msges into the specified file
	 */
	@Override
	public void log(Level logType, String className, String methodName, String logInfo) {
		
		String parentDir = this.getParentDirectory();
		String subDir = this.getSubDirectory();
		
		StringBuffer filePath = new StringBuffer(basePath).append(parentDir).append("\\").append(subDir);
		
		if(filePath!=null && this.checkIfDirectoryExistsOrCreate(filePath.toString())){
			filePath.append("\\").append(this.getLogFileName());
			Logger logger = Logger.getLogger("AccountingDriveLog");
			FileHandler handler = null;
			try {
				handler = new FileHandler(filePath.toString(), true);
			
				logger.addHandler(handler);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        handler.setFormatter(formatter);
		        String logMsg = this.getMsgToWriteToLogFile(className, methodName, logInfo);
		        		
		        logger.log(logType, logMsg);
		        
		        handler.flush();
		        handler.close();
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
		
		String currentYear = String.valueOf(Year.now().getValue());
		String currentMonth = String.valueOf(MonthDay.now().getMonthValue());
		
		String tempParentDir = currentYear.concat(currentMonth);
		if(this.parentDirectory==null || !this.parentDirectory.equals(tempParentDir)){
			this.parentDirectory = tempParentDir;
		}
			
		return this.parentDirectory;
	}
	
	/**
	 * Gets the sub directory for the log location based on current date
	 * @return
	 */
	private String getSubDirectory(){
		
		StringBuilder currentMonth = new StringBuilder(String.valueOf(MonthDay.now().getDayOfMonth()));
		currentMonth = currentMonth.length() == 1 ? currentMonth.insert(0, "0") : currentMonth;
		
		if(this.subDirectory==null || !this.subDirectory.equals(currentMonth.toString())){
			this.subDirectory = currentMonth.toString();
		}
			
		return this.subDirectory;
	}
	
	/**
	 * Gets the log file name to which the log message has to be written
	 * @return
	 */
	private String getLogFileName(){
		Date currentDate = new Date();
		
		StringBuilder baseFileName = new StringBuilder(ConfigLoader.getValueForProperty("LOG_BASE_FILE_NAME"));
		baseFileName.append(currentDate.getHours()).append(".out");
		
		return baseFileName.toString();
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
	
	/**
	 * Returns a instance of the FileLog class if it does not already exists
	 * @return
	 */
	public static FileLog getInstance(){
		if(instance==null){
			instance = new FileLog();
		}
		return instance;
	}

}
