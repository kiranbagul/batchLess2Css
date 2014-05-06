package org.batchless2css.utils.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerFactory {
	public static Logger getLogger(String name) {
		Logger logger = Logger.getLogger(name);
		Handler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		return logger;
	}
}
