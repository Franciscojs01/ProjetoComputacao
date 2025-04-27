import Functions.BaseConverter;

import static Functions.BaseConverter.toBase10;

public class Main {
    public static void main(String[] args) {
        System.out.println(toBase10("84A", 16));
        System.out.println(BaseConverter.convertBase("7A8C", 16, 18));

    }
}