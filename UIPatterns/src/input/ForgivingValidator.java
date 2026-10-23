
package input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class ForgivingValidator {

    public String normalizePhone(String phone) {
        if (phone == null) {
            return "";
        }

        String normalized = phone.replaceAll("[^0-9]", "");

        if (normalized.startsWith("57")
                && normalized.length() == 12) {
            normalized = normalized.substring(2);
        }

        return normalized;
    }

    public String normalizeDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return "";
        }

        String value = date.trim();

        String[] formats = {
            "d/M/uuuu",
            "d-M-uuuu",
            "d.M.uuuu",
            "uuuu-M-d",
            "uuuu/M/d",
            "uuuu.M.d"
        };

        for (String format : formats) {
            try {
                DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(format, Locale.ROOT)
                        .withResolverStyle(ResolverStyle.STRICT);

                LocalDate parsedDate =
                    LocalDate.parse(value, formatter);

                return parsedDate.toString();

            } catch (DateTimeParseException exception) {
                // Try the next supported format.
            }
        }

        return value;
    }
}
