package com.phamvanviet.losoxa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Encode {
    public static String passwordEncoder(String pass) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(pass);
        return encodedPassword;
    }
    public static void main(String[] args) {
//        System.out.println(passwordEncoder("123456"));
//        byte[] decodedBytes = Base64.getDecoder().decode("NDUwMDA=");
//        String decodedString = new String(decodedBytes);
//        System.out.println(decodedString);

//        String originalInput = "totalPrice45000";
//        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//        System.out.println(Base64.getEncoder().encodeToString(encodedString.getBytes()));


    }
}
