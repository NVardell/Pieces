package com.bits.pieces.app.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypts and Decrypts files
 *
 * @author Nate Vardell
 * @since 7/23/2019
 */
public class FileEncryptDecrypt {

    private Cipher cipher;
    private SecretKey secretKey;
    private String transformation;

    FileEncryptDecrypt(SecretKey key, String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.secretKey = key;
        this.cipher = Cipher.getInstance(transformation);
    }

    public void encrypt(String originalContent, String fileName) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try(FileOutputStream fileOut = new FileOutputStream(fileName);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)){
            fileOut.write(iv);
            cipherOut.write(originalContent.getBytes());
        }
    }

    public String decrypt(String fileName) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        String fileContents;

        try(FileInputStream fileIn = new FileInputStream(fileName)){
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try(
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
                    ){
                StringBuilder sb = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null)
                    sb.append(line);

                fileContents = sb.toString();
            }
        }

        return fileContents;
    }
}
