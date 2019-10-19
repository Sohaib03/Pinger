/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpinger;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author sohaib
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField textField;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Ping !!!");
        String webAddress=textField.getText();
        label.setText(Pinger(webAddress));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    String Pinger(String webaddress) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash" , "-c", "ping -c 3 " + webaddress);
        String allLines = "";
        try {
            Process process =  processBuilder.start();
            
            BufferedReader reader = new BufferedReader( new InputStreamReader(process.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                allLines += line + "\n";
            }
            //System.out.println(allLines);
            return allLines;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "Please Check Your internet Connection.";
    }
}

