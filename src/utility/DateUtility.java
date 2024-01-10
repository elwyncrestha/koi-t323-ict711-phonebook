package utility;

import static constant.AppConstant.DATE_FORMAT;
import static constant.AppConstant.DATE_FORMAT_2;
import static constant.AppConstant.DATE_FORMAT_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A date utility wrapper for the app.
 */
public class DateUtility {

    private DateUtility() {
    }

    /**
     * A method to parse the date string to LocalDate using defined formats.
     *
     * @param dateString A valid date string.
     * @return A parsed LocalDate instance.
     * @throws DateTimeParseException throws exception if unable to parse using allowed formats.
     */
    public static LocalDate parse(String dateString) throws DateTimeParseException {
        final String[] dateFormats = {DATE_FORMAT, DATE_FORMAT_2, DATE_FORMAT_3};
        for (String format : dateFormats) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Continue to the next format if the current one fails.
            }
        }
        // If none of the formats work, throw an exception.
        throw new DateTimeParseException("Unable to parse the date.", dateString, 0);
    }
}
