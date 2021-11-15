package Classworks;

public class NumberInPower {

    public int powered(int a, int b) {
        int result = 1;
        for (
                int i = 1;
                i <= a; i++) {
            result *= b;
        }
        return result;
    }
}
