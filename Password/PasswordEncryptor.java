package Password;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


public class PasswordEncryptor{

    public void encrypt(String password){

            //Data is encrypted using the DES algorithm three separate times.
        // It is first encrypted using the first subkey, then decrypted with the second subkey,
        // and encrypted with the third subkey
        String algorithm = "DESede";
        try{
            Key symKey = KeyGenerator.getInstance(algorithm).generateKey();
            Cipher c = Cipher.getInstance(algorithm);

            byte[] encryptionB = encryptF(password, symKey, c);

            //System.out.println("Encrypted: " + encryptionB.toString());
            System.out.println("Decrypted: " + decryptF(encryptionB, symKey, c));

            //https://www.geeksforgeeks.org/symmetric-encryption-cryptography-in-java/?ref=rp

        }catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e){
            e.printStackTrace();
        }
    }

    private static byte[] encryptF(String input, Key pkey, Cipher c) throws InvalidKeyException, BadPaddingException,
    IllegalBlockSizeException {

        c.init(Cipher.ENCRYPT_MODE, pkey);

        byte[] inputBytes = input.getBytes();

        return c.doFinal(inputBytes);
    }

    private static String decryptF(byte[] encryptionBytes,Key pkey,Cipher c) throws InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {

        c.init(Cipher.DECRYPT_MODE, pkey);

        byte[] decrypt = c.doFinal(encryptionBytes);

        return new String(decrypt);
    }

}
