package calculator;

public class StringCalculator {

    public int calculate(String input) {
        if (isBlankOrNull(input)) {
            return 0;
        }
        return 1;
    }

    public boolean isBlankOrNull(String input) {
        return input == null || input.isEmpty();
    }
}
