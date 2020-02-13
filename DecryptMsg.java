/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.io.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author ARNAB
 */
public class DecryptMsg extends JFrame implements ActionListener  {
    JFrame f2;
    JPanel panel1, panel2, panel3, panel4, panel5;
    JFileChooser fopen, fsave;
    JTextArea txt;
    JScrollPane Scrollpane;
    JTextField InFile, OutFile;
    JButton button1, button2, button3, button4, button5, button6;
    JLabel l1, l2, l3;
    JPasswordField t1;
    JCheckBox c1;
    
    String InFilename, OutFilename; 
    
    public DecryptMsg()
    {
        f2 = new JFrame("Message Decryption");
	fopen = new JFileChooser();
        fsave = new JFileChooser();

        f2.setLayout(new GridLayout(5,1));
        
        panel1 = new JPanel(new FlowLayout());
        f2.getContentPane().add(panel1);
        
        l1 = new JLabel("Input Encrypt Message from the File : ");
	
	InFile = new JTextField(25);
        
	button1 = new JButton("OPEN");
	button1.addActionListener(this);
	button1.setPreferredSize(new Dimension(160, 25));
		
	button2 = new JButton("BROWSE");
	button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(160, 25));
        
        panel1.add(l1);
	panel1.add(InFile);        
        panel1.add(button1);
        panel1.add(button2);
        
        panel2 = new JPanel(new GridLayout(1, 1));
        f2.getContentPane().add(panel2);  
  
        txt = new JTextArea(10, 40);  
        Scrollpane = new JScrollPane(txt);  
  
        Scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        Scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
  
        panel2.add(Scrollpane);
        
        panel3 = new JPanel(new FlowLayout());
        f2.getContentPane().add(panel3);
        
        l2 = new JLabel("Enter Password : ");
        
        t1 = new JPasswordField(25);
        
        c1 = new JCheckBox("Show Password");
        c1.addActionListener(this);
        
        panel3.add(l2);
        panel3.add(t1);
        panel3.add(c1);
        
        panel4 = new JPanel(new FlowLayout());
        f2.getContentPane().add(panel4);
        
        button3 = new JButton("DECODE");
	button3.addActionListener(this);
	button3.setPreferredSize(new Dimension(160, 25));
		
	button4 = new JButton("CANCEL");
	button4.addActionListener(this);
        button4.setPreferredSize(new Dimension(160, 25));
        
        panel4.add(button3);
        panel4.add(button4);
	
        panel5 = new JPanel(new FlowLayout());
        f2.getContentPane().add(panel5); 
        
        l3 = new JLabel("If you want to save Decrypted Message in a file : ");
        
	OutFile = new JTextField(25);

	button5 = new JButton("SAVE");
	button5.addActionListener(this);
	button5.setPreferredSize(new Dimension(160, 25));
  
	button6 = new JButton("BROWSE");
	button6.addActionListener(this);
	button6.setPreferredSize(new Dimension(160, 25));
        
        panel5.add(l3);
        panel5.add(OutFile);
        panel5.add(button5);
        panel5.add(button6);
        
	f2.setSize(580,460);
	f2.setLocationRelativeTo(null);
	f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
    	f2.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            try {
                this.InFilename = InFile.getText();
                readfile(InFilename);
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "File Error!!");
            }
	}
        
        else if(e.getSource() == button2) {
            try {
		fopen.showOpenDialog(f2);	
        	File Filename = fopen.getSelectedFile();
		InFile.setText(Filename.getAbsolutePath());
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "No File Select!!");
            }
	}
        
        else if(e.getSource() == c1) {
            if(c1.isSelected())
                t1.setEchoChar((char)0);
            else
                t1.setEchoChar('*');
        }
        
        else if(e.getSource() == button3) {
            char getpass[] = t1.getPassword();
            String password = String.valueOf(getpass);
            DecryptionMsg e1 = new DecryptionMsg();
            String s = e1.decrypt(txt.getText(), password);
            txt.setText("");
            txt.setText(s);
	}
        
        else if(e.getSource() == button4) {
            f2.dispose();
	}
        
        else if(e.getSource() == button5) {
            try {
                this.OutFilename = OutFile.getText();
                writefile(OutFilename);
                JOptionPane.showMessageDialog(null, "DONE!!");
                f2.dispose();
            }
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "No File Name Entered!!");
            }
	}
        
        else if(e.getSource() == button6) {
            try {	
        	if (fsave.showSaveDialog(f2) == JFileChooser.APPROVE_OPTION) {
                    File Filename = fsave.getSelectedFile();
                    OutFile.setText(Filename.getAbsolutePath());
                }
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "No File Select!!");
            }
	}
    }
    
    public void readfile(String Filename) throws Exception {
        txt.setText("");
        String st = "";
        FileReader fr = new FileReader(Filename); 
        int i; 
        while ((i=fr.read()) != -1) 
            st = st + ((char) i);
        txt.setText(st);
    }
    
    public void writefile(String Filename) throws Exception {
        String st = txt.getText();
        FileWriter fw=new FileWriter(Filename);    
        fw.write(st);    
        fw.close(); 
        txt.setText("");
    }
}
