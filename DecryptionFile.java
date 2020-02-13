package test2;

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
public class DecryptionFile {
    String InputFile, OutputFile, password;
    
    DecryptionFile(String InFile, String OutFile, String pass) {
        this.InputFile = InFile;
        this.OutputFile = OutFile;
        this.password = pass;
        
    }
    
    public int en() {
        String st;
	try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            FileInputStream fis = new FileInputStream(InputFile);
            byte[] salt = new byte[8];
            fis.read(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            FileOutputStream fos = new FileOutputStream(OutputFile);
            byte[] in = new byte[64];
            int read;
            while ((read = fis.read(in)) != -1) {
		byte[] output = cipher.update(in, 0, read);
		if (output != null)
                    fos.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
		fos.write(output);

            fis.close();
            fos.flush();
            fos.close();
            return 1;
        }
        
        catch (Exception fe) {
            JOptionPane.showMessageDialog(null, "Error!!");
            return 0;
        }
    }
}
