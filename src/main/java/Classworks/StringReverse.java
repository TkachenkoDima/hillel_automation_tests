package Classworks;

public class StringReverse {

    public String reversedString() {
        String toReverse = "Hello World";
        StringBuilder result = new StringBuilder();

        for (int i = toReverse.length() - 1; i >= 0; i--) {
            result.append(toReverse.charAt(i));
        }
        return result.toString();
    }
}
