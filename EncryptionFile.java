package test2;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author ARNAB
 */
public class EncryptionFile {
    String InputFile, OutputFile, password;
    
    EncryptionFile(String InFile, String OutFile, String pass) {
        this.InputFile = InFile;
        this.OutputFile = OutFile;
        this.password = pass;
        
    }
    
    public int en() {
        String st;
	try {
            FileInputStream inFile = new FileInputStream(InputFile);
            FileOutputStream outFile = new FileOutputStream(OutputFile);

            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
            outFile.write(salt);

            byte[] input = new byte[64];
            int bytesRead;
            while ((bytesRead = inFile.read(input)) != -1) {
		byte[] output = cipher.update(input, 0, bytesRead);
		if (output != null)
                    outFile.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
		outFile.write(output);

            inFile.close();
            outFile.flush();
            outFile.close();
            return 1 ;
        }
        
        catch (Exception fe) {
            JOptionPane.showMessageDialog(null, "Error!!");
            return 0;
        }
    }
}
