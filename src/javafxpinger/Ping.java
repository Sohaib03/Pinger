/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpinger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author sohaib
 */
public class Ping extends Task<Integer>{

    /*
    ** 
    **    
     */
    public final String webAddress;
    public String flags;
    public final int pingCount;
    public final float timeInterval;
    public final boolean isSuperUser;

    public static class Builder {

        private String webAddress = "www.google.com";
        private String flags = "-v";
        private int pingCount = 3;
        private float timeInterval = 1.0f;
        private boolean isSuperUser = false;

        public Builder webAddress(String val) {
            this.webAddress = val;
            return this;
        }

        public Builder flags(String val) {
            this.flags = val;
            return this;
        }

        public Builder pingCount(int val) {
            this.pingCount = val;
            return this;
        }

        public Builder timeInterval(float val) {
            this.timeInterval = val;
            return this;
        }

        public Builder isSuperUser(boolean val) {
            this.isSuperUser = val;
            return this;
        }

        public Builder() {
        }

        public Ping build() {
            return new Ping(this);
        }
    }

    private Ping(Builder builder) {
        this.webAddress = builder.webAddress;
        this.flags = builder.flags;
        this.pingCount = builder.pingCount;
        this.timeInterval = builder.timeInterval;
        this.isSuperUser = builder.isSuperUser;
    }

    public String send() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        //String command = "ping -c "+PingCount+" "+flags+ " " + " -i " +timeInterval+ " " + webaddress;
        String command = String.format("ping -c %d %s -i %f %s", this.pingCount, this.flags, this.timeInterval, this.webAddress);
        processBuilder.command("bash", "-c", command);
        String allLines = "";
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                allLines += line + "\n";
                FXMLDocumentController.labelText = allLines;
                System.out.println(line);
            }
            //System.out.println(allLines);
            return allLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Please Check Your internet Connection.";
    }

    
    @Override
    protected Integer call() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder();
        //String command = "ping -c "+PingCount+" "+flags+ " " + " -i " +timeInterval+ " " + webaddress;
        String command = String.format("ping -c %d %s -i %f %s", this.pingCount, this.flags, this.timeInterval, this.webAddress);
        processBuilder.command("bash", "-c", command);
        String allLines = "";
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                allLines += line + "\n";
                updateMessage(allLines);
                System.out.println(line);
            }
            //System.out.println(allLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    

}
