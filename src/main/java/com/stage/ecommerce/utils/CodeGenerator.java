package com.stage.ecommerce.utils;

import java.util.Random;

public class CodeGenerator {

    public static String generateCode(String nomEntite){
        if(nomEntite==null || nomEntite.isEmpty()){
            return null;
        }
        String prefix = nomEntite.substring(0,3).toUpperCase();
        String randomDigits = generateRandomDigits(3);
        return prefix+randomDigits;
    }

    public static String generateRandomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
