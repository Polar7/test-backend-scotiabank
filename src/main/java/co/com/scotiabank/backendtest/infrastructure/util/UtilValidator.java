package co.com.scotiabank.backendtest.infrastructure.util;

import co.com.scotiabank.backendtest.infrastructure.exception.EmailFormatInvalidException;
import co.com.scotiabank.backendtest.infrastructure.exception.InvalidPhoneNumberException;

import java.util.regex.Pattern;

/**
 * Class with different util methods
 */
public class UtilValidator {

    public static String isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if(Pattern.matches(emailRegex, email)) {
            return email;
        }
        throw new EmailFormatInvalidException();
    }

    public static Double validatePhoneNumber(double phoneNumber) {
        if (phoneNumber % 1 != 0) {
            throw new InvalidPhoneNumberException();
        }
        return phoneNumber;
    }
}
