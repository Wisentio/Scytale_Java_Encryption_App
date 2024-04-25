package Scytale_v3.bin;

public class Morse_Cipher_Adapter implements CipherInterface {
    Morse_Cipher morseCode;             // Declare an object of the Morse cipher class

    Morse_Cipher_Adapter(Morse_Cipher mCipher) {
        this.morseCode = mCipher;       // Initialize the class attribute
    }

    // Implement the adapter function (functions with the same header as in the Caesar adapter)
    public String encrypt(String text) {
        return morseCode.encryptMorse(text);
    }

    public String decrypt(String cipher) {
        return morseCode.decryptMorse(cipher);
    }
}
