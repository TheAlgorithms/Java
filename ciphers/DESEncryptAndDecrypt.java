package ciphers;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

public class DESEncryptAndDecrypt {


    public static void main(String[] args) {

        String message = "this is my message";
        byte[] keyBytes = DatatypeConverter.parseBase64Binary("thisissecret");

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey key = factory.generateSecret(new DESKeySpec(keyBytes));
            Cipher encript = Cipher.getInstance("DES");
            encript.init(Cipher.ENCRYPT_MODE, key);
            String encriptedMessage = new String(BASE64EncoderStream.encode( encript.doFinal(message.getBytes("UTF8"))), "UTF8");
            System.out.println("this is my encrypted message --->" + encriptedMessage);

            Cipher decript = Cipher.getInstance("DES");
            decript.init(Cipher.DECRYPT_MODE,key);

            String decriptedMessage = new String(decript.doFinal(BASE64DecoderStream.decode(encriptedMessage.getBytes())), "UTF8");

            System.out.println("this is my decripted message --->" + decriptedMessage);

            if (decriptedMessage.equalsIgnoreCase(message)){
                System.out.println("it works");
            }else{
                System.out.println("Ops");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
