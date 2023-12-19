package local.compression;

public class Compressor {

    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        if (input.contains("±")) {
            throw new IllegalArgumentException("Invalid input, contains ±");
        }

        if (input.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Invalid input, contains digit");
        }

        StringBuilder result = new StringBuilder();
        int count = 1;
        char prev = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == prev) {
                count++;
            } else {
                appendCompressed(result, prev, count);
                count = 1;
                prev = current;
            }
        }

        appendCompressed(result, prev, count);
        return result.toString();
    }

    private static void appendCompressed(StringBuilder result, char character, int count) {
        result.append(character);
        if (count > 2) {
            result.append("±").append(count);
        } else if (count == 2) {
            result.append(character);
        }

    }
}
