import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    ParameterizedLogging.class);

    public static void main(String[] args) {

        String username = "John";

        int age = 25;

        double salary = 50000;

        logger.info(
                "User {} is {} years old",
                username,
                age);

        logger.debug(
                "User {} has salary {}",
                username,
                salary);

        logger.warn(
                "Warning for user {}",
                username);

        logger.error(
                "Error occurred for user {}",
                username);
    }
}
