package org.example.learningcenter.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class Generator {
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final Random random = new Random();
    public String generatePassword() {
        String string = UUID.randomUUID().toString();
        return string.substring(0, 8);
    }

    public String generateSuperAdminName(String orgName) {
        StringBuilder sb = new StringBuilder(orgName);
        int length = alphabet.length();
        for (int i = 0; i < 5; i++) {
            sb.append(alphabet.charAt(random.nextInt(0, length)));
        }
        return sb.toString();
    }
}
