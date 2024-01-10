package utility;

/**
 * A log utility wrapper for the app.
 */
public class LogUtility {

    private LogUtility() {
    }

    public static void log(String message) {
        System.out.println("LOG: " + message);
    }
}
