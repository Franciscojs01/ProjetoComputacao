
import static Functions.BaseConverter.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(toBase10("A1", 2));
        System.out.println(fromBase10(26, 16));
        System.out.println(convertBase("5E90E.EF", 16, 19));
        System.out.println(calculate("5E90EEF", 19, "37742106", 8, 16, '*' ));


    }
}