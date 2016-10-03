package com.accounting_drive.main;

import java.util.logging.Level;

import com.accounting_drive.log.FileLog;
import com.accounting_drive.log.Log;

public class Main {

	public static void main(String[] args) {
		Log fileLog = FileLog.getInstance();
		fileLog.log(Level.INFO, "Main", "main", "This is a Test Log");
		fileLog.log(Level.INFO, "Main", "main", "This is a Test Log 2");
		fileLog.log(Level.INFO, "Main", "main", "This is a Test Log 3");
		fileLog.log(Level.INFO, "Main", "main", "This is a Test Log 4");
	}

}
