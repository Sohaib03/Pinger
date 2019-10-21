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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author sohaib
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField textField;
    @FXML 
    private Label label;
    @FXML 
    private RadioButton ipv4;
    @FXML
    private RadioButton ipv6;
    @FXML
    private ToggleGroup tg;
    @FXML
    private CheckBox SoundCheckBox;
    @FXML 
    TextField PingCountText; 
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Ping !!!");
        RadioButton selectedRadio = (RadioButton) tg.getSelectedToggle();
        String ip = selectedRadio.getText();
        
        String flags = "-v";
        
        //if (SoundCheckBox.isSelected()) flags.concat("a");
        String PingCountString = PingCountText.getText();
        int PingCount = Integer.parseInt(PingCountString);
        if ("IPv6".equals(ip)) flags += "6";
        else flags += "4";
        String webAddress=textField.getText();
        label.setText(Pinger(webAddress,flags, PingCount));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
    
    String Pinger(String webaddress, String flags, int PingCount) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String command = "ping -c "+PingCount+" "+flags+ " " + webaddress;
        System.out.println(command);
        processBuilder.command("bash" , "-c", command);
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

