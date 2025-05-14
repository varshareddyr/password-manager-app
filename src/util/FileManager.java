package util;

import model.Credential;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "passwords.txt";

    public static void saveCredential(Credential c) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String encryptedPass = Encryptor.encrypt(c.getPassword());
            writer.write(c.getWebsite() + "," + c.getUsername() + "," + encryptedPass);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Credential> loadCredentials() {
        List<Credential> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    String decryptedPass = Encryptor.decrypt(parts[2]);
                    list.add(new Credential(parts[0], parts[1], decryptedPass));
                }
            }
        } catch (IOException e) {
            // File might not exist yet – ignore
        }
        return list;
    }
}
