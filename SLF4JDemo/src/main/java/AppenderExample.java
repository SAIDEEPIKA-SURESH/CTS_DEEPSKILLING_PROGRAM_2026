import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderExample {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AppenderExample.class);

    public static void main(String[] args) {

        logger.trace(
                "Trace message");

        logger.debug(
                "Debug message");

        logger.info(
                "Info message");

        logger.warn(
                "Warning message");

        logger.error(
                "Error message");

        System.out.println(
                "Logs written to console and app.log");
    }
}
