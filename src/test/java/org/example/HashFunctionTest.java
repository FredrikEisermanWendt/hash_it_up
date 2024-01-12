package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashFunctionTest {

    private final HashFunction hashFunction = new HashFunction();

    @Test
    public void testHashNotNull() {
        // the hash function should not return null
        assertNotNull(hashFunction.hash("TestPassword123"));
    }

    @Test
    public void testHashNotUnchanged() {
        // the hash function should not return the same input
        assertNotEquals("TestPassword123", hashFunction.hash("TestPassword123"));
    }

    @Test
    public void testHashConsistency() {
        // the hash function should produce the same hash for the same input
        String input = "TestPassword123";
        assertEquals(hashFunction.hash(input), hashFunction.hash(input));
    }

    @Test
    public void testHashLength() {
        // the hash should be 32 characters long
        String input = "Test";
        assertEquals(32, hashFunction.hash(input).length());
    }

    @Test
    public void testHashCollision() {
        // the hash function should not produce the same hash for different inputs
        String input1 = "TestPassword123";
        String input2 = "TestPassword124";
        assertNotEquals(hashFunction.hash(input1), hashFunction.hash(input2));
    }

    @Test
    public void testHashCollisionComplex() {
        // the hash function should not produce the same hash for different inputs
        String input1 = "TestPassword1234";
        String input2 = "TestPassword124";

        String hash1 = hashFunction.hash(input1);
        String hash2 = hashFunction.hash(input2);

        assertEquals(hash1.toCharArray().length, hash2.toCharArray().length);

        int matchingCount = 0;
        int totalCharacters = input1.length(); // Assuming both strings are of the same length

        for (int i = 0; i < totalCharacters; i++) {
            char char1 = hash1.charAt(i);
            char char2 = hash2.charAt(i);

            if (char1 == char2) {
                matchingCount++;
            }
        }

        double matchingPercentage = ((double) matchingCount / totalCharacters) * 100;

        assertFalse(matchingPercentage >= 10,
                "Hash collision test failed. Matching percentage: " + matchingPercentage + "%");
    }

    @Test
    public void testHashPerformance() {
        // measure the performance of the hash function here
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            hashFunction.hash("TestPassword123" + i);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue(duration < 1000, "Hashing performance test took too long: " + duration + " ms");
    }

}
