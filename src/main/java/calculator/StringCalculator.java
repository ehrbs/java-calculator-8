package calculator;

public class StringCalculator {

    public int calculate(String input) {
        if (isBlankOrNull(input)) {
            return 0;
        }

        if(isSingleNumber(input)){
            return Integer.parseInt(input);
        }
        return sumByDefaultDelimiters(input);
    }

    // 기본 구분자: 쉼표(,) / 콜론(:)
    private int sumByDefaultDelimiters(String input) {
        String[] tokens = input.split("[,:]");
        int sum = 0;

        for (String token : tokens) {
            String t = token.trim();

            int value = Integer.parseInt(t);
            sum += value;
        }
        return sum;
    }


    private boolean isSingleNumber(String input) {
        // 0 포함한 양의 정수만 허용(음수/부호/소수점은 불가)
        return input.matches("^[0-9]*$");
    }

    private boolean isBlankOrNull(String input) {
        return input == null || input.isEmpty();
    }
}
