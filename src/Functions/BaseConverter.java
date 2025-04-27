package Functions;

public class BaseConverter {

    // Converte de qualquer base para base 10
    public static int toBase10(String n, int b) {
        int result = 0;
        int length = n.length();

        n = n.toUpperCase(); // Para trabalhar com letras maiúsculas

        for (int i = 0; i < length; i++) {
            char a = n.charAt(i);
            int num;

            // Se for uma letra (A-Z), transforma em número (A = 10, B = 11, etc)
            if (a >= 'A' && a <= 'Z') {
                num = a - 55; // Porque 'A' (65) - 55 = 10
            } else {
                num = Character.getNumericValue(a);
            }

            // Verifica se o número é válido para a base
            if (num >= b) {
                return -1; // Retorna -1 para indicar erro (base inválida)
            }

            int e = length - 1 - i;
            result += num * Math.pow(b, e);
        }

        return result;
    }

    // Converte de base 10 para qualquer base
    public static String fromBase10(int n, int b) {
        if (n == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (n > 0) {
            result.append(digits.charAt(n % b));
            n /= b;
        }

        return result.reverse().toString(); // Inverte porque construiu de trás pra frente
    }

    // Converte de base b1 para base b2
    public static String convertBase(String n, int b1, int b2) {
        int x = 0;

        n = n.toUpperCase();

        for (int i = 0; i < n.length(); i++) {
            char a = n.charAt(i);
            int num;

            if (a >= 'A' && a <= 'Z') {
                num = a - 55;
            } else {
                num = Character.getNumericValue(a);
            }

            if (num >= b1) {
                return null;
            }

            x = num + x * b1; // Método de Horner
        }

        if (x == 0) return "0";

        StringBuilder result = new StringBuilder();
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        while (x > 0) {
            result.insert(0, digits.charAt(x % b2)); // Constrói no começo (pra não precisar inverter depois)
            x /= b2;
        }

        return result.toString();
    }

    // Realiza operação entre dois números em bases diferentes
    public static String calculate(String n, int b1, String m, int b2, Integer d, char o) {
        int nBase10 = toBase10(n, b1);
        int mBase10 = toBase10(m, b2);

        if (nBase10 == -1 || mBase10 == -1) {
            return null; // Indica erro de base inválida
        }

        int result;
        if (o == '*') {
            result = nBase10 * mBase10;
        } else {
            result = nBase10 + mBase10;
        }

        return fromBase10(result, d != null ? d : b1);
    }
}
