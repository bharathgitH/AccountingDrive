package com.accounting_drive.log;

import java.util.logging.Level;

public interface Log {
	
	public void log(Level logType,String className,String methodName,String logInfo);
}
