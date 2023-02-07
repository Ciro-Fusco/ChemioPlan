package com.example.OttimizzazioneAcquisto.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class OttimizzazioneService implements IOttimizzazioneService{

    @Override
    public String Ottimizza(String codice) {

        String output="";

        try {

//            String path = System.getProperty("user.dir");
//            System.out.println("Current path: " + path);

            String command ="python Modello_Deploy.py "+codice;
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);

//            File directory = new File("/src/main/AcquistoDeploy");
//            ProcessBuilder processBuilder = new ProcessBuilder("python", "Modello_Deploy.py",codice);
//            processBuilder.directory(directory);
//            System.out.println("Current directory: " + processBuilder.directory().getAbsolutePath());
//            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output=output+line;
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
