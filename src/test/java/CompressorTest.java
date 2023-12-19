import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import local.compression.Compressor;


public class CompressorTest {

    @Test
    public void testBasicCompression() {
        assertEquals("A±5B±3#±5", Compressor.compress("AAAAABBB#####"));
    }

    @Test
    public void testCompressionWithSingleCharacters() {
        assertEquals("A±5B±3#±5C", Compressor.compress("AAAAABBB#####C"));
    }

    @Test
    public void testCompressionWithDoubleCharacters() {
        assertEquals("A±5B±3#±5CC", Compressor.compress("AAAAABBB#####CC"));
    }

    @Test
    public void testDigitInString() {
        assertThrows(IllegalArgumentException.class, () -> Compressor.compress("AAAAABBB#####1"));
    }

    @Test
    public void testForbiddenCharInString() {
        assertThrows(IllegalArgumentException.class, () -> Compressor.compress("AAAAABBB#####±"));
    }
}
