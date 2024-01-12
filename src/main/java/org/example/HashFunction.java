package org.example;

public class HashFunction {
    // the hash function should not return null
    // the hash function should not return the same input
    // the hash function should produce the same hash for the same input
    // the hash should be 32 characters long
    // the hash function should not produce the same hash for different inputs
    // the hash function should not produce the same hash for different inputs
    private final int MAX_CHAR_LENGTH = 32;
    
    
    public String hash(String input) {
        if (input == null) {
            throw new RuntimeException("input may not be null");
        }
        
        if (input.isBlank()) {
            throw new RuntimeException("input may not be an empty string");
        }
        
        
        if (input.length() > MAX_CHAR_LENGTH) {
            throw new RuntimeException(String.format("input may not be longer than %s characters", MAX_CHAR_LENGTH));
        }
        
        return null;
    }
    
    
}
