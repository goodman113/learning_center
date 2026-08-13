package org.example.learningcenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Generator {
    private final Random random = new Random();
    private final PasswordEncoder passwordEncoder;

    public String generatePassword() {
        StringBuilder sb = new StringBuilder();
        String string = UUID.randomUUID().toString();
        int i = 0;
        while (sb.length() < 8) {
            char c = string.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c)) sb.append(c);
            i++;
        }
        return passwordEncoder.encode(sb);
    }

    public String generateSuperAdminName(String orgName) {
        StringBuilder sb = new StringBuilder(orgName);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = alphabet.length();
        for (int i = 0; i < 5; i++) {
            sb.append(alphabet.charAt(random.nextInt(0, length)));
        }
        return sb.toString();
    }
}
