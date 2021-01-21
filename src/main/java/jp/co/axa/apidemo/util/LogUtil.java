package jp.co.axa.apidemo.util;

/**
 * This class serves as a starting point and wrapper for logging.
 * Currently no framework (log4j, ) is used, and output is piped to syso.
 * If more advanced logging is needed, we can start by only changing this class
 */
public class LogUtil {

    public static void log(StringBuilder logStatementBuilder){
        log(logStatementBuilder.toString());
    }

    public static void log(String logStatement){
        System.out.println(logStatement);
    }
}
