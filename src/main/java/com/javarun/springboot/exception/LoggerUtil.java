package com.javarun.springboot.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    public static void logError(Class<?> clazz, String message, Throwable throwable) {
        logger.log(Level.SEVERE, formatLogMessage(clazz, message), throwable);
    }

    public static void logWarning(Class<?> clazz, String message) {
        logger.log(Level.WARNING, formatLogMessage(clazz, message));
    }

    public static void logInfo(Class<?> clazz, String message) {
        logger.log(Level.INFO, formatLogMessage(clazz, message));
    }

    private static String formatLogMessage(Class<?> clazz, String message) {
        return String.format("[%s] %s: %s", clazz.getSimpleName(), Thread.currentThread().getName(), message);
    }
}
