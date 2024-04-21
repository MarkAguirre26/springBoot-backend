package net.javaguides.springboot.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil
{
    public static void logError(Class<?> clazz, String message, Throwable throwable) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.log(Level.SEVERE, message, throwable);
    }

    public static void logWarning(Class<?> clazz, String message) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.log(Level.WARNING, message);
    }

    public static void logInfo(Class<?> clazz, String message) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.log(Level.INFO, message);
    }

}
