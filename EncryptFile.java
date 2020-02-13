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
public class EncryptFile extends JFrame implements ActionListener  {
    JFrame f3;
    JPanel panel1, panel2, panel3, panel4;
    JFileChooser fopen, fsave;
    JTextField InFile, OutFile;
    JButton button1, button2, button3, button4, button5;
    JLabel l1, l2, l3;
    JPasswordField t1;
    JCheckBox c1;
    
    String InFilename; 
    
    public EncryptFile()
    {
        f3 = new JFrame("File Encryption");
	fopen = new JFileChooser();
        fsave = new JFileChooser();

        f3.setLayout(new GridLayout(4,1));
        
        panel1 = new JPanel(new FlowLayout());
        f3.getContentPane().add(panel1);
        
        l1 = new JLabel("Choose A File To Encrypt : ");
	
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
        
        panel2 = new JPanel(new FlowLayout());
        f3.getContentPane().add(panel2); 
        
        l3 = new JLabel("Select File Destination For Save Enc. File : ");
        
	OutFile = new JTextField(25);

	button3 = new JButton("CHOOSE DIRECTORY");
	button3.addActionListener(this);
	button3.setPreferredSize(new Dimension(160, 25));
        
        panel2.add(l3);
        panel2.add(OutFile);
        panel2.add(button3);
        
        panel3 = new JPanel(new FlowLayout());
        f3.getContentPane().add(panel3);
        
        l2 = new JLabel("Enter Password : ");
        
        t1 = new JPasswordField(25);
        
        c1 = new JCheckBox("Show Password");
        c1.addActionListener(this);
        
        panel3.add(l2);
        panel3.add(t1);
        panel3.add(c1);
        
        panel4 = new JPanel(new FlowLayout());
        f3.getContentPane().add(panel4);
        
        button4 = new JButton("ENCODE");
	button4.addActionListener(this);
	button4.setPreferredSize(new Dimension(160, 25));
		
	button5 = new JButton("CANCEL");
	button5.addActionListener(this);
        button5.setPreferredSize(new Dimension(160, 25));
        
        panel4.add(button4);
        panel4.add(button5);
        
	f3.setSize(530,410);
	f3.setLocationRelativeTo(null);
	f3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
    	f3.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            try {
                this.InFilename = InFile.getText();
            }	 
            catch (Exception fe) {
		JOptionPane.showMessageDialog(null, "File Error!!");
            }
	}
        
        else if(e.getSource() == button2) {
            try {
		fopen.showOpenDialog(f3);	
        	File Filename = fopen.getSelectedFile();
		InFile.setText(Filename.getAbsolutePath());
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
                int response = fsave.showOpenDialog(f3);
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
            EncryptionFile e1 = new EncryptionFile(InFilename, fo, password);
            int i = e1.en();
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "DONE!!");
                f3.dispose();
            }
	}
        
        else if(e.getSource() == button5) {
            f3.dispose();
	}
    }
    
    public String filen(String s1, String s2) throws Exception {
        String st = s1 + "\\" + "Enc";
        File f = new File(s2);
        st = st + f.getName() + ".dse"; 
        return st;
    }
}