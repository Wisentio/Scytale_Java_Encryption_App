package Scytale_v3.bin;

public class Caesar_Cipher {
	String encryptCaesar(String text, int shift) {
        StringBuffer result = new StringBuffer();   // We use StringBuffer to be able to change its value 
                                                     // during program execution
 
       // Caesar encryption algorithm
        for (int i = 0; i < text.length(); i++) {
        	char ch = (char)((int)text.charAt(i) + shift);
            result.append(ch);
        }
        return result.toString();
    }

    
    // Algorithm for Caesar decryption (the only difference is the sign of the expression
    // for calculating the value stored in the variable ch
    String decryptCaesar(String cipher, int shift) {
        StringBuffer result = new StringBuffer();
 
        for (int i = 0; i < cipher.length(); i++) {
        	char ch = (char)((int)cipher.charAt(i) - shift);
            result.append(ch);
        }
        return result.toString();
    }
}
