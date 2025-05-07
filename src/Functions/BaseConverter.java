package Functions;

public class BaseConverter {

    public static double toBase10(String n, int b) {
        n = n.toUpperCase();
        String[] parts = n.split("\\.");
        String intPart = parts[0];
        String fracPart = parts.length > 1 ? parts[1] : "";

        double result = 0.0;

        for (int i = 0; i < intPart.length(); i++) {
            char c = intPart.charAt(i);
            int digit = getDigitValue(c);
            if (digit >= b) return -1;
            result = result * b + digit;
        }

        double fraction = 0.0;
        double divisor = b;
        for (int i = 0; i < fracPart.length(); i++) {
            char c = fracPart.charAt(i);
            int digit = getDigitValue(c);
            if (digit >= b) return -1;
            fraction += digit / divisor;
            divisor *= b;
        }

        return result + fraction;
    }

    public static String fromBase10(double num, int b) {
        if (num == 0) return "0";

        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder intPart = new StringBuilder();
        StringBuilder fracPart = new StringBuilder();

        long integer = (long) num;
        double fraction = num - integer;


        while (integer > 0) {
            intPart.insert(0, digits.charAt((int) (integer % b)));
            integer /= b;
        }


        int limit = 10;
        while (fraction > 0 && limit-- > 0) {
            fraction *= b;
            int digit = (int) fraction;
            fracPart.append(digits.charAt(digit));
            fraction -= digit;
        }

        return fracPart.length() > 0 ? intPart + "." + fracPart : intPart.toString();
    }

    public static String convertBase(String n, int b1, int b2) {
        double base10 = toBase10(n, b1);
        if (base10 == -1) return null;
        return fromBase10(base10, b2);
    }

    public static String calculate(String n, int b1, String m, int b2, Integer d, char o) {
        double nBase10 = toBase10(n, b1);
        double mBase10 = toBase10(m, b2);

        if (nBase10 == -1 || mBase10 == -1) {
            return null;
        }

        double result;
        switch (o) {
            case '*':
                result = nBase10 * mBase10;
                break;
            case '-':
                result = nBase10 - mBase10;
                break;
            case '/':
                result = mBase10 == 0 ? Double.NaN : nBase10 / mBase10;
                break;
            default: // '+'
                result = nBase10 + mBase10;
                break;
        }

        return fromBase10(result, d != null ? d : b1);
    }

    private static int getDigitValue(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        return -1;
    }
}
