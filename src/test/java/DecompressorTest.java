import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import local.compression.Decompressor;


public class DecompressorTest {

    @Test
    public void testBasicDecompression() {
        assertEquals("AAAAABBB#####", Decompressor.decompress("A±5B±3#±5"));
    }
    
    @Test
    public void testDecompressionWithSingleCharacters() {
        assertEquals("AAAAABBB#####C", Decompressor.decompress("A±5B±3#±5C"));
    }

    @Test
    public void testDecompressionWithDoubleCharacters() {
        assertEquals("AAAAABBB#####CC", Decompressor.decompress("A±5B±3#±5CC"));
    }

    @Test
    public void testNonCharAtBeginning() {
        assertThrows(IllegalArgumentException.class, () -> Decompressor.decompress("±5B±3#±5CC±5"));
    }

    @Test
    public void testMoreThanTwoCharacters() {
        assertThrows(IllegalArgumentException.class, () -> Decompressor.decompress("A±5B±3#±5CCC"));
    }
}
