/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpinger;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
    TextField timeIntervalBox;
    
    private Service <Void> backgroundThread;
    
    public static String labelText = "Ping to continue";
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Ping !!!");
        RadioButton selectedRadio = (RadioButton) tg.getSelectedToggle();
        String ip = selectedRadio.getText();
        
        String flags = "-v";
        
        
        int PingCount;
        try {
            String PingCountString = PingCountText.getText();
            PingCount = Integer.parseInt(PingCountString);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return;
        }
        
        if ("IPv6".equals(ip)) flags += "6";
        else flags += "4";
        
        String webAddress=textField.getText();
        float timeInterval ;
        try {
            timeInterval = Float.parseFloat(timeIntervalBox.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        Ping ping = new Ping.Builder()
            .webAddress(webAddress)
            .timeInterval(timeInterval)
            .pingCount(PingCount)
            .flags(flags)
            .build();
        
        
        Thread t1 = new Thread(ping);
        t1.start();
        label.textProperty().bind(ping.messageProperty());
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    @FXML
    private void closeButtonClicked() {
        Platform.exit();
        System.exit(0);
    }
    
}

