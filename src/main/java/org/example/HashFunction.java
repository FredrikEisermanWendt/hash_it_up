package org.example;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class HashFunction {
    // the hash function should not return null
    // the hash function should not return the same input
    // the hash function should produce the same hash for the same input
    // the hash should be 32 characters long
    // the hash function should not produce the same hash for different inputs
    // the hash function should not produce the same hash for different inputs
    private final int MAX_CHAR_LENGTH = 24;
    private final int RESULT_CHAR_LENGTH = 32;
    private final Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
    private final List<Character> substitution_list = List.of('!', '#', '$', '%', '&', '(', ')', '*', '-', '.', ':', ';', '<', '=', '>', '?', '@');
    
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
        
        byte[] input_byte_array = input.getBytes();
        String first_encoding_result = encoder.encodeToString(input_byte_array);
        
        if (first_encoding_result.length() < RESULT_CHAR_LENGTH) {
            int padding_to_add = RESULT_CHAR_LENGTH - first_encoding_result.length();
            for (int i = 0; i < padding_to_add; i++) {
                first_encoding_result = first_encoding_result.concat("=");
            }
        }
        
        int shift_with = input.length() % 3;
        StringBuilder sb = new StringBuilder(32);
        int sub_count = 0;
        for (char c : first_encoding_result.toCharArray()) {
            if (sub_count >= substitution_list.size() - 1){
                sub_count = 0;
            }
            
            char to_insert = c;
            if (c == '=') {
                to_insert = substitution_list.get(sub_count);
            }
            
            char new_char_code = (char) ((int) to_insert + shift_with);
            
            sb.append(new_char_code);
            
            sub_count += 1;
        }
        
        return sb.toString();
    }
    
    
}
