package calculator;

public class StringCalculator {

    public int calculate(String input) {
        if (isBlankOrNull(input)) {
            return 0;
        }

        if(isSingleNumber(input)){
            return Integer.parseInt(input);
        }
        
        // 커스텀 구분자 처리
        if (input.startsWith("//")) {
            return sumByCustomDelimiter(input);
        }
        
        return sumByDefaultDelimiters(input);
    }


    // 커스텀 구분자 처리: "//구분자\n숫자..." 형식
    private int sumByCustomDelimiter(String input) {
        int delimiterEndIndex = input.indexOf("\\n");
        
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }
        
        String customDelimiter = input.substring(2, delimiterEndIndex);
        String numbersString = input.substring(delimiterEndIndex + 2);
        
        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
        }
        
        return sumByDelimiter(numbersString, customDelimiter);
    }
    
    // 주어진 구분자로 문자열을 분리하여 합계 계산 (기본 구분자도 함께 사용)
    private int sumByDelimiter(String input, String delimiter) {
        // 정규표현식 특수문자 이스케이프
        String escapedDelimiter = java.util.regex.Pattern.quote(delimiter);
        // 기본 구분자(쉼표, 콜론)와 커스텀 구분자를 모두 사용
        String combinedDelimiter = "[,:]|" + escapedDelimiter;
        String[] tokens = input.split(combinedDelimiter, -1);
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
