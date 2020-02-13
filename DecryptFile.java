/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.io.*;
import java.lang.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author ARNAB
 */
public class DecryptFile extends JFrame implements ActionListener  {
    JFrame f4;
    JPanel panel1, panel2, panel3, panel4;
    JFileChooser fopen, fsave;
    JTextField InFile, OutFile;
    JButton button2, button3, button4, button5;
    JLabel l1, l2, l3;
    JPasswordField t1;
    JCheckBox c1;
    
    String InFilename; 
    
    public DecryptFile()
    {
        f4 = new JFrame("File Decryption");
	fopen = new JFileChooser();
        fsave = new JFileChooser();

        f4.setLayout(new GridLayout(4,1));
        
        panel1 = new JPanel(new FlowLayout());
        f4.getContentPane().add(panel1);
        
        l1 = new JLabel("Choose Encrypted File : ");
	
	InFile = new JTextField(25);
		
	button2 = new JButton("OPEN FILE");
	button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(160, 25));
        
        panel1.add(l1);
	panel1.add(InFile);        
        panel1.add(button2);
        
        panel2 = new JPanel(new FlowLayout());
        f4.getContentPane().add(panel2); 
        
        l3 = new JLabel("Select File Destination For Save Dec. File : ");
        
	OutFile = new JTextField(25);

	button3 = new JButton("CHOOSE DIRECTORY");
	button3.addActionListener(this);
	button3.setPreferredSize(new Dimension(160, 25));
        
        panel2.add(l3);
        panel2.add(OutFile);
        panel2.add(button3);
        
        panel3 = new JPanel(new FlowLayout());
        f4.getContentPane().add(panel3);
        
        l2 = new JLabel("Enter Password : ");
        
        t1 = new JPasswordField(25);
        
        c1 = new JCheckBox("Show Password");
        c1.addActionListener(this);
        
        panel3.add(l2);
        panel3.add(t1);
        panel3.add(c1);
        
        panel4 = new JPanel(new FlowLayout());
        f4.getContentPane().add(panel4);
        
        button4 = new JButton("DECODE");
	button4.addActionListener(this);
	button4.setPreferredSize(new Dimension(160, 25));
		
	button5 = new JButton("CANCEL");
	button5.addActionListener(this);
        button5.setPreferredSize(new Dimension(160, 25));
        
        panel4.add(button4);
        panel4.add(button5);
        
	f4.setSize(530,410);
	f4.setLocationRelativeTo(null);
	f4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
    	f4.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {        
        if(e.getSource() == button2) {
            try {
                FileFilter filter = new FileNameExtensionFilter("DSE File", "dse");
                fopen.setFileFilter(filter);
		int response = fopen.showOpenDialog(f4);	
        	if (response == JFileChooser.APPROVE_OPTION)
                    InFile.setText(fopen.getSelectedFile().toString());
                this.InFilename = InFile.getText();
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "No File Select!!");
            }
	}
        
        else if(e.getSource() == button3) {
            String FileName = "";
            try {
                fsave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int response = fsave.showOpenDialog(f4);
                if (response == JFileChooser.APPROVE_OPTION) 
                    FileName = fsave.getSelectedFile().toString();
                OutFile.setText(filen(FileName, InFilename));
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "Error!!");
            }
	}
        
        else if(e.getSource() == c1) {
            if(c1.isSelected())
                t1.setEchoChar((char)0);
            else
                t1.setEchoChar('*');
        }
        
        else if(e.getSource() == button4) {
            char getpass[] = t1.getPassword();
            String password = String.valueOf(getpass), fo = OutFile.getText();
            DecryptionFile e1 = new DecryptionFile(InFilename, fo, password);
            int i = e1.en();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "DONE!!");
                f4.dispose();
            }
	}
        
        else if(e.getSource() == button5) {
            f4.dispose();
	}
    }
    
    public String filen(String s1, String s2) throws Exception {
        String st = s1 + "\\";
        File f = new File(s2);
        StringBuffer st1 = new StringBuffer(f.getName());
        st1 = st1.delete(0, 3);
        st1 = st1.delete(st1.length()-4, st1.length());
        st = st + st1;
        return st;
    }
}