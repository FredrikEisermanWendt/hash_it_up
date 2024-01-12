package org.example;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Requirements
 * the hash function should not return null
 * the hash function should not return the same input
 * the hash function should produce the same hash for the same input
 * the hash should be 32 characters long
 * the hash function should not produce the same hash for different inputs
 * the hash function should not produce the same hash for different inputs
 */
public class HashFunction {
    
    public static final int MODULO_AMOUNT = 3;
    private final int MAX_CHAR_LENGTH = 24;
    private final int RESULT_CHAR_LENGTH = 32;
    private final Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
    private final List<Character> substitution_list = List.of('!', '#', '$', '%', '&', '(', ')', '*', '-', '.', ':', ';', '<', '=', '>', '?', '@');
    
    public String hash(String input) {
        validate_input(input);
        
        String base_encoded = encode_string_base64(input);
        base_encoded = padd_to_length(base_encoded, RESULT_CHAR_LENGTH);
        
        int input_chars_sum = input.chars().sum();
        int shift_with = (input_chars_sum + input.length()) % MODULO_AMOUNT;
        
        return shifted_string(shift_with, base_encoded);
    }
    
    private String shifted_string(int shift_amount, String base_encoded) {
        StringBuilder sb = new StringBuilder(RESULT_CHAR_LENGTH);
        
        List<Character> sub_list = (shift_amount % 2 == 1)
                ? substitution_list.reversed()
                : substitution_list;
        
        int sub_count = shift_amount;
        for (char c : base_encoded.toCharArray()) {
            if (sub_count >= sub_list.size() - 1) {
                sub_count = 0;
            }
            
            char to_insert = c;
            if (c == '=') {
                to_insert = sub_list.get(sub_count);
            }
            
            char new_char_code = (char) ((int) to_insert + shift_amount);
            
            sb.append(new_char_code);
            
            sub_count += 1;
        }
        
        return sb.toString();
    }
    
    private String padd_to_length(String base_encoded, int length) {
        if (base_encoded.length() < RESULT_CHAR_LENGTH) {
            int padding_to_add = length - base_encoded.length();
            for (int i = 0; i < padding_to_add; i++) {
                base_encoded = base_encoded.concat("=");
            }
        }
        
        return base_encoded;
    }
    
    private String encode_string_base64(String input) {
        byte[] input_byte_array = input.getBytes();
        return encoder.encodeToString(input_byte_array);
    }
    
    private void validate_input(String input) {
        if (input == null) {
            throw new RuntimeException("input may not be null");
        }
        
        if (input.isBlank()) {
            throw new RuntimeException("input may not be an empty string");
        }
        
        if (input.length() > MAX_CHAR_LENGTH) {
            throw new RuntimeException(String.format("input may not be longer than %s characters", MAX_CHAR_LENGTH));
        }
    }
    
    
}
