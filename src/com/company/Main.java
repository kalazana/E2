package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String csvFile = "/C:/Users/Moritz/OneDrive - hochschule-stralsund.de/LP Software/2. Aufgabenblatt/sfd/DWS-TOP-DIVIDENDE.csv";
        String line = " ";
        String cvsSplitBy = ";";
        int cnt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while(br.readLine() != null && cnt<3){
                br.readLine();
                cnt++;
            }
            while ((line = br.readLine()) != null) {


                    String[] country = line.split(cvsSplitBy);

                        if (br.readLine() == "(?i) (30.12.2011).*") {
                    System.out.println("Aktienwert [Wert= " + country[0] + " Wert " + country[1] + "]");


                     } else {
                    }
                System.out.println("Test");
            }
            } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    }


