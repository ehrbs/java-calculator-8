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
        String[] tokens = input.split("[,:]", -1);
        int sum = 0;

        for (String token : tokens) {
            String t = token.trim();

            if (t.isEmpty()) {
                throw new IllegalArgumentException("구분자가 연속으로 들어가 있습니다.");
            }
            int value = parseNumberOrThrow(t);
            sum += value;
        }
        return sum;
    }

    private int parseNumberOrThrow(String t) {
        // 0 포함 정수만 허용 (정규표현식 사용)
        if (!t.matches("^[0-9]*$")) {
            if (t.startsWith("-")) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            throw new IllegalArgumentException("숫자가 아닌 문자는 입력할 수 없습니다.");
        }
        return Integer.parseInt(t);
    }

    private boolean isSingleNumber(String input) {
        // 0 포함한 양의 정수만 허용(음수/부호/소수점은 불가)
        return input.matches("^[0-9]*$");
    }

    private boolean isBlankOrNull(String input) {
        return input == null || input.isEmpty();
    }
}
