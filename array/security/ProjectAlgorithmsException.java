package array.security;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Grzegorz Kawalec
 */
public class ProjectAlgorithmsException extends Exception {

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    ProjectAlgorithmsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return sdf.format(new Date()) + " | " + super.getMessage();
    }

}
