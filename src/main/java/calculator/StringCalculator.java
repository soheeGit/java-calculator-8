package calculator;

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
            String customDelimiter = input.substring(2, newlineIndex);
            numbers = input.substring(newlineIndex + CUSTOM_DELIMITER_SUFFIX.length());

            delimiter = delimiter.substring(0, delimiter.length() - 1) + customDelimiter + "]";
        }

        return sum(numbers, delimiter);
    }

    private static int sum(String numbers, String delimiter) {
        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        for (String token : tokens) {
            int number = Integer.parseInt(token);
            sum += number;
        }

        return sum;
    }
}
