package org.example.learningcenter.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Generator {


    public String generatePassword(){
        String string = UUID.randomUUID().toString();
        char[] charArray = string.substring(0, 7).toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : charArray) {
            if (Character.isDigit(c)||Character.isLetter(c)) characters.add(c);
        }
        return characters.toString();
    }
}
