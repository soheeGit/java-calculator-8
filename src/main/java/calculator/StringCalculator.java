package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static int calculate(String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = "[,:]";
        String numbers = input;

        if(input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int newlineIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

            if (newlineIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }

            String customDelimiter = input.substring(2, newlineIndex);

            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자는 비어있을 수 없습니다.");
            }

            numbers = input.substring(newlineIndex + CUSTOM_DELIMITER_SUFFIX.length());
            
            delimiter = delimiter.substring(0, delimiter.length() - 1) + Pattern.quote(customDelimiter) + "]";
        }

        return sum(numbers, delimiter);
    }

    private static int sum(String numbers, String delimiter) {
        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        for (String token : tokens) {
            int number = parseAndValidate(token);
            sum += number;
        }

        return sum;
    }

    private static int parseAndValidate(String token) {
        try {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}
