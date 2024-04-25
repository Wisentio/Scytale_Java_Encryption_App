package Scytale_v3.bin;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Sound {
	public static void main(String[] args) {
		sound(500);
	}
	
	static void sound(int time){
        try {
            // define the sound wave, whose characteristics we'll modify later
            SourceDataLine line = AudioSystem.getSourceDataLine(new AudioFormat(44100, 16, 1, true, true));

            // open a "line"
            line.open();
            line.start();

            
            generate_sound(line, 1000, time);     // default sound, frequency, sound time
            
            // close the "line"
            line.drain();
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generate_sound(SourceDataLine line, int frequency, int time) {
        int sampleRate = 44100;												 // sampling frequency
        byte[] buffer = new byte[sampleRate * time / 1000];			     	 // value of the sound wave at each sampling moment

        for (int i = 0; i < buffer.length; i++) {
            double angle = 2.0 * Math.PI * frequency * i / sampleRate;       // angle at each sampling
            buffer[i] = (byte) (Math.sin(angle) * 127.0);					 // calculate the value of sine at each sampling moment scaled
            																	// to the limits of the data type size (-128 127)
        }

        line.write(buffer, 0, buffer.length);                                // write the calculated wave information in the buffer to the audio line
    }
}
