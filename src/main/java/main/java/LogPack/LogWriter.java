package main.java.LogPack;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogWriter {
	private static Logger logger;
	private String className;
	private FileHandler fileHandler = null;
	
	public LogWriter(String className) {
		this.className = className;
		try {
			logger = Logger.getLogger(className);
			logger.setLevel(Level.ALL);
			fileHandler = new FileHandler();
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Explanation: " + e);
		}
	}
	
	private void logging() {
		
	}
}
