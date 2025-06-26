package util;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
