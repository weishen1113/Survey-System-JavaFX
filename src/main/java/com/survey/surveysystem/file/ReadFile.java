/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.survey.surveysystem.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a
 */
public class ReadFile {
    private String fileName;
    
    public ReadFile(String fileName){
        this.fileName = fileName;
    }

    public List<String> ReadFileByLines() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("files/"+fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            // Condition holds true till
            // there is character in a string
            while ((st = br.readLine()) != null) // Print the string
            {
                lines.add(st);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

}
