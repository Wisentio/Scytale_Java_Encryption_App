package Scytale_v3.bin;

public class Caesar_Cipher_Adapter implements CipherInterface {
	Caesar_Cipher caesar_Cipher;			// declare an object of the Caesar Cipher class
    int shift;								// the integer used to shift through the ASCII table

    Caesar_Cipher_Adapter(Caesar_Cipher cipher, int shift) {
        this.caesar_Cipher = cipher; 		// initialize the attributes of the class
        this.shift = shift;
    }

    // implement the adapter function (functions with the same signature as in the Morse adapter)
    public String encrypt(String text) {  
        return caesar_Cipher.encryptCaesar(text, shift);
    }

    public String decrypt(String cipher) {
        return caesar_Cipher.decryptCaesar(cipher, shift);
    }
}