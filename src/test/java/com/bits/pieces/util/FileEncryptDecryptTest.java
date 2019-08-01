package com.bits.pieces.util;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/23/2019
 */
public class FileEncryptDecryptTest {

    private static final String fileName = "encryptedFile.enc";

    @Test
    public void whenEncryptingFile_thenDecrypting_originalContentIsReturned() throws
            NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        String originalContent = "Can't get any more original than that!";
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        FileEncryptDecrypt fileEncryptDecrypt = new FileEncryptDecrypt(secretKey, "AES/CBC/PKCS5Padding");

        // Create new encrypted file
        fileEncryptDecrypt.encrypt(originalContent, fileName);

        // Assert that decrypted file contains original contents
        assertThat(fileEncryptDecrypt.decrypt(fileName), is(originalContent));
    }

}