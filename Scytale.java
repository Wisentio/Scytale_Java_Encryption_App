package Scytale_v3.bin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Scytale extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;                     // Main panel
    boolean executionIndicator = true;
    CipherInterface cipher = null;                   // Object to access both classes

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Scytale frame = new Scytale();   // Declare a new object of the Scytale class
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Scytale() {
        setTitle("Scytale");                                   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		  
        setBounds(100, 100, 613, 410);                         // Define position and dimension of the frame
        
        contentPane = new JPanel();                            // Initialize main panel
        JPanel panel_Morse = new JPanel();					   // Panel for Caesar code
        JPanel panel_Caesar = new JPanel();					   // Panel for Morse code
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);						   // Set main panel as initial one 	
        
        // Allow changing the positions of objects in the panel
        panel_Caesar.setLayout(null);
        panel_Morse.setLayout(null);
        contentPane.setLayout(null);
        
//  Panel for Caesar encryption
        
        // Define a text field to indicate the value for shifting, size, number of columns, and ActionListener
        JTextField textField_Caesar = new JTextField("10");				
        textField_Caesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cipher = new Caesar_Cipher_Adapter(new Caesar_Cipher(),    
                            Integer.valueOf(textField_Caesar.getText()));   
                }
                catch(NumberFormatException ex) {
                    System.out.println("Enter an integer...");
                }
                
            }
        });
        textField_Caesar.setBounds(406, 275, 96, 19);
        panel_Caesar.add(textField_Caesar);								
        textField_Caesar.setColumns(10);
        
        // Label associated with the shift text field, dimensions, and alignment
        JLabel lblShift = new JLabel("Shift:");
        lblShift.setHorizontalAlignment(SwingConstants.LEFT);
        lblShift.setBounds(406, 246, 75, 19);
        panel_Caesar.add(lblShift);
        
        // Text area for input text, dimensions, position, and initial text (Example input)
        JTextArea ta_Caesar_input = new JTextArea();
        ta_Caesar_input.setText("Example input");
        ta_Caesar_input.setBounds(141, 46, 293, 65);
        ta_Caesar_input.setLineWrap(true); 								 
        ta_Caesar_input.setWrapStyleWord(true);							
        panel_Caesar.add(ta_Caesar_input);								 
        
        // Text area for encrypted text (output), dimensions, position, and initial text (Example input)
        JTextArea ta_Caesar_output = new JTextArea();
        ta_Caesar_output.setBounds(140, 132, 294, 65);
        ta_Caesar_output.setLineWrap(true); 						     
        ta_Caesar_output.setWrapStyleWord(true);					     
        panel_Caesar.add(ta_Caesar_output);								 
		
        // Button to go back to the previous panel (to the main panel), dimensions, and alignment with ActionListener
        JButton b_Caesar_back = new JButton("<- Back");
        b_Caesar_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(contentPane);
                revalidate();											 
            }
        });
        b_Caesar_back.setHorizontalAlignment(SwingConstants.LEFT);		 
        b_Caesar_back.setBounds(0, 0, 85, 13);
        panel_Caesar.add(b_Caesar_back);								 

        // Button for encryption, dimensions, alignment, and ActionListener
        JButton b_Caesar_encrypt = new JButton("Encrypt");			  
        b_Caesar_encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta_Caesar_output.setText(							     
                        cipher.encrypt(ta_Caesar_input.getText()));      
            }
        });
        b_Caesar_encrypt.setBounds(94, 249, 96, 21);
        panel_Caesar.add(b_Caesar_encrypt);							 
		
        // Button for decryption, dimensions, alignment, and ActionListener
        JButton b_Caesar_decrypt = new JButton("Decrypt");
        b_Caesar_decrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta_Caesar_output.setText(cipher.decrypt(ta_Caesar_input.getText()));
            }
        });
        b_Caesar_decrypt.setBounds(94, 300, 96, 21);
        panel_Caesar.add(b_Caesar_decrypt);					 	

        
//  Panel for Morse encryption
        
        
        panel_Morse.setLayout(null);
        
        // Text area for input text
        JTextArea ta_Morse_input = new JTextArea();
        ta_Morse_input.setText("Example input");						  
        ta_Morse_input.setBounds(124, 45, 341, 73);						  
        ta_Morse_input.setLineWrap(true);								  
        ta_Morse_input.setWrapStyleWord(true);							  
        panel_Morse.add(ta_Morse_input);
        
        // Button to return to the initial panel (similar to the panel in Caesar encryption)
        JButton b_Morse_back = new JButton("<- Back");
        b_Morse_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(contentPane);
                revalidate();
            }
        });
        b_Morse_back.setBounds(0, 0, 85, 13);
        panel_Morse.add(b_Morse_back);
        
        // Text area for output
        JTextArea ta_Morse_output = new JTextArea();
        ta_Morse_output.setBounds(124, 145, 341, 73);
        ta_Morse_output.setLineWrap(true);								  
        ta_Morse_output.setWrapStyleWord(true);							  
        panel_Morse.add(ta_Morse_output);								  
        
        JRadioButton rb_Morse = new JRadioButton("Sound");				  
        rb_Morse.setBounds(250, 268, 103, 21);
        panel_Morse.add(rb_Morse);
        
        // Button for encryption
        JButton b_Morse_encrypt = new JButton("Encrypt");
        
        b_Morse_encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = cipher.encrypt(ta_Morse_input.getText());	  
                if(rb_Morse.isSelected()) {								  
                    ta_Morse_output.setText("");
                    for(char ch : text.toCharArray()) {						  
                        ta_Morse_output.append(String.valueOf(ch));			  
                        switch(ch) {
                        case '.' : {
                            Sound.sound(300);
                            break;
                        }
                        case '-': {
                            Sound.sound(500);
                            break;
                        }
                        case ' ':{
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e1) {
                            }
                            break;
                        }
                        case '\t':
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e1) {
                                
                            }
                        }
                    }
                }
                else {
                    ta_Morse_output.setText(text);
                }
            }
        });
        b_Morse_encrypt.setBounds(124, 268, 96, 21);
        panel_Morse.add(b_Morse_encrypt);
        
        // Button for decryption
        JButton b_Morse_decrypt = new JButton("Decrypt");
        b_Morse_decrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ta_Morse_output.setText(cipher.decrypt(ta_Morse_input.getText()));
            }
        });
        b_Morse_decrypt.setBounds(126, 318, 96, 21);
        panel_Morse.add(b_Morse_decrypt);
    
        
        
// Initial panel 


        // Button to use Morse cipher (change panel and initialize the cipher variable) 
        JButton b_Morse = new JButton("Morse Cipher");
        b_Morse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cipher = new Morse_Cipher_Adapter(new Morse_Cipher());
                setContentPane(panel_Morse);
                revalidate();
            }
        });
        
        b_Morse.setBounds(101, 262, 129, 37);
        contentPane.add(b_Morse);
        
        

        // Button to use Caesar cipher (change panel and initialize the cipher variable)
        JButton b_Caesar = new JButton("Caesar Cipher");
        b_Caesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cipher = new Caesar_Cipher_Adapter(new Caesar_Cipher(),10);
                setContentPane(panel_Caesar);
                revalidate();
            }
        });
        b_Caesar.setBounds(348, 262, 123, 37);
        contentPane.add(b_Caesar);
        
        JLabel lblNewLabel = new JLabel("Choose Cipher ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(101, 50, 369, 93);
        contentPane.add(lblNewLabel);
    }
}
