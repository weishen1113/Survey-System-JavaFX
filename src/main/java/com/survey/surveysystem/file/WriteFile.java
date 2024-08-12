/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a
 */
public class WriteFile {

    private String fileName;

    public WriteFile(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + fileName, true))) {
            // Append the new line
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public void updateFile(String key, String newLine, int keyIndex) {
        try {
            // Read the file
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("files/" + fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Update the line
            boolean updated = false;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(";")) {
                    String[] parts = lines.get(i).split(";");
                    if (parts.length > 0 && parts[keyIndex].equals(key)) {
                        lines.set(i, newLine);
                        updated = true;
                        break;
                    }
                }
            }

            // Write back to the file
            if (updated) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + fileName));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();

                System.out.println("File updated successfully.");
            } else {
                System.out.println("ID not found for update.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public void updateFileWithTwoKeys(String key1, String key2, String newLine, int key1Index, int key2Index) {
        try {
            // Read the file
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("files/" + fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Update the line
            boolean updated = false;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(";")) {
                    String[] parts = lines.get(i).split(";");
                    if (parts.length > 0 && parts[key1Index].equals(key1)
                            && parts[key2Index].equals(key2)) {
                        lines.set(i, newLine);
                        updated = true;
                        break;
                    }
                }
            }

            // Write back to the file
            if (updated) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + fileName));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();

                System.out.println("File updated successfully.");
            } else {
                System.out.println("ID not found for update.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
