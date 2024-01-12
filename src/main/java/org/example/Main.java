package org.example;

public class Main {
    public static void main(String[] args) {
        
        HashFunction hashFunction = new HashFunction();
        
        String input1 = "TestPassword1234";
        String input2 = "TestPassword124";
        
        System.out.println(input1);
        System.out.println(hashFunction.hash(input1));
        
        System.out.println(input2);
        System.out.println(hashFunction.hash(input2));
        // Tove was here
        
    }
}