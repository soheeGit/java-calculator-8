package calculator;

public class StringCalculator {
    public static int calculate(String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = "[,:]";

        return sum(input, delimiter);
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
