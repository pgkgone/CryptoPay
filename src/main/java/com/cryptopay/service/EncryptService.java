package com.cryptopay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EncryptService {

    private final AES256TextEncryptor aes256TextEncryptor;

    public String encrypt(String value) {
        return this.aes256TextEncryptor.encrypt(value);
    }

    public String decrypt(String value) {
        return this.aes256TextEncryptor.decrypt(value);
    }


}
