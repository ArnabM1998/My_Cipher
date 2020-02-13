/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author ARNAB
 */
public class Test2 extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    JFrame f;
    JPanel panel1, panel2, panel3, panel4;
    JButton button1, button2, button3, button4;
    JLabel l1, l2;
    
    public Test2 () {
        f = new JFrame("My CIPHER");
        f.setLayout(new GridLayout(4, 1));
        
        panel1 = new JPanel(new GridLayout(1, 1));
        f.getContentPane().add(panel1);
        
        l1 = new JLabel("  TO ENCRYPT & DECRYPT A MESSAGE :  ");
        l1.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 16));
        l1.setForeground(new Color(100, 50, 25));
    
        panel1.add(l1);
        
        panel2 = new JPanel(new FlowLayout());
        f.getContentPane().add(panel2);
        
	button1 = new JButton(" ENCRYPT MESSAGE > ");
	button1.addActionListener(this);
        button1.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
	button1.setPreferredSize(new Dimension(200, 25));
		
	button2 = new JButton(" DECRYPT MESSAGE > ");
	button2.addActionListener(this);
        button2.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        button2.setPreferredSize(new Dimension(200, 25));        
                
        panel2.add(button1);
        panel2.add(button2);
	
        panel3 = new JPanel(new GridLayout(1, 1));
        f.getContentPane().add(panel3);
        
        l2 = new JLabel("  TO ENCRYPT & DECRYPT A FILE :  ");
        l2.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 16));
        l2.setForeground(new Color(100, 50, 25));
        
        panel3.add(l2);
        
        panel4 = new JPanel(new FlowLayout());
        f.getContentPane().add(panel4);
               
	button3 = new JButton(" ENCRYPT FILE > ");
	button3.addActionListener(this);
        button3.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
	button3.setPreferredSize(new Dimension(200, 25));
		
	button4 = new JButton(" DECRYPT FILE > ");
	button4.addActionListener(this);
        button4.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        button4.setPreferredSize(new Dimension(200, 25));        
         
        panel4.add(button3);
        panel4.add(button4);
        
	f.setSize(480,360);
	f.setLocationRelativeTo(null);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    	f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            EncryptMsg e1 = new EncryptMsg();
        }
        else if(e.getSource() == button2) {
            DecryptMsg d1 = new DecryptMsg();
        }
        else if(e.getSource() == button3){
            EncryptFile e2 = new EncryptFile();
        }
        else if(e.getSource() == button4){
            DecryptFile d2 = new DecryptFile();
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Test2 t = new Test2();
    }
    
}
