package Scytale_v3.bin;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Morse_Cipher {
    // Morse codes and their respective characters are stored in variables
    // "morseCodes" and "characters" which will be used to populate the hashtable "cheatSheet"

    List<String> morseCodes = Arrays.asList(
                            ".-",     /*A*/      "-...",	 //B
                            "-.-.",	 /*C*/		"-..",   //D
                            ".",      /*E*/		"..-.",  //F
                            "--.",    /*G*/		"....",	 //H
                            "..",	 /*I*/ 		".---",	 //J
                            "-.-",    /*K*/		".-..",  //L
                            "--",	 /*M*/		"-.",	 //N
                            "---",	 /*O*/		".--.",	 //P
                            "--.-",	 /*Q*/		".-.",	 //R
                            "...",	 /*S*/		"-",	 //T
                            "..-",	 /*U*/		"...-",	 //V
                            ".--",	 /*W*/		"-..-",	 //X
                            "-.--",	 /*Y*/		"--..",	 //Z
                            ".----",	 /*1*/		"..---", //2
                            "...--",	 /*3*/		"....-", //4
                            ".....",	 /*5*/		"-....", //6
                            "--...",	 /*7*/		"---..", //8
                            "----.",	 /*9*/		"-----", //0
                            "\t" // space
                            );       
    List<Character> characters = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L',
                         'M','N','O','P','Q','R','S','T','U','V','W','X',
                         'Y','Z','1','2','3','4','5','6','7','8','9','0',' '); 
        

    String encryptMorse(String text){
        // The "encryptedText" variable will be used to store the result
        StringBuffer encryptedText = new StringBuffer();                 
    
        Hashtable<Character,String> cheatSheet = new Hashtable<>();
        
        for(int i = 0; i < 37; i++) {
            cheatSheet.put(characters.get(i), morseCodes.get(i));		// Add elements to the Hashtable
        }
        
        // Analyze the text character by character, encrypting each element
        // Morse code only analyzes uppercase Latin alphabet characters, so we convert each character to
        // uppercase before searching in the hashtable for the respective Morse code (otherwise we get null)

        for(int i = 0; i < text.length(); i++) {
            if(characters.contains(Character.toUpperCase(text.charAt(i)))) {
                encryptedText.append(cheatSheet.get(Character.toUpperCase(text.charAt(i))));
                encryptedText.append("   ");
            }
            else {
                encryptedText.append('?');
            }
        }
        
        return encryptedText.toString();
    }

    
    String decryptMorse(String code){
        StringBuffer decryptedText = new StringBuffer();

        Hashtable<String,Character> cheatSheet = new Hashtable<>();
        
        for(int i = 0; i < 37; i++) {
            cheatSheet.put(morseCodes.get(i), characters.get(i));		// Add elements to the Hashtable
        }

        String[] code_characters = code.split("   "); 					    // Split the input string
																		 // to use it as a word vector

        for(String ch : code_characters){
            if(morseCodes.contains(ch))
                decryptedText.append(cheatSheet.get(ch));
            else {
                decryptedText.append("?");
                System.out.println("You entered wrong text (should include - . \\t and three spaces)");
            }
                
        }

        return decryptedText.toString();
    }
}
