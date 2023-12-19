package local.compression;

public class Decompressor {

    public static String decompress(String compressed) {
        if (compressed == null || compressed.isEmpty()) {
            return compressed;
        }

        StringBuilder result = new StringBuilder();
        if (compressed.charAt(0) == '±' || Character.isDigit(compressed.charAt(0))) {
            throw new IllegalArgumentException("Invalid input, expected character");
        }
        for (int i = 1; i < compressed.length(); i++) {
            char c = compressed.charAt(i);
            if (c == '±') {
                char prev = compressed.charAt(i - 1);
                i++;
                if (!Character.isDigit(compressed.charAt(i))) {
                    throw new IllegalArgumentException("Invalid input, expected digit");
                }
                int count = Character.getNumericValue(compressed.charAt(i));
                for (int j = 0; j < count; j++) {
                    result.append(prev);
                }
            } else if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Invalid input, expected character or ±, found digit");
            } else {
                if (i + 1 < compressed.length() && compressed.charAt(i + 1) == '±') {
                    continue;
                }
                if (result.length() > 2 && result.charAt(result.length() - 1) == result.charAt(result.length() - 2) && result.charAt(result.length() - 1) == c) {
                    throw new IllegalArgumentException("Invalid input, at least 3 same characters in a row");
                }
                result.append(c);
            }
        }
        return result.toString();
    }
}
