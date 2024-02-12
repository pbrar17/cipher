package ui.gui;

import persistence.JsonReader;

import javax.swing.*;
import java.io.IOException;

//Represents a class that extends JFrame and loads the CipherList from File
public class LoadFromFile extends JFrame {
    private JFrame loadFrame;

    private JPanel loadPanel;

    private JTextField status;

    private JsonReader jsonReader;

    //MODIFIES: CipherAppGUI.listOfCiphers
    //EFFECTS: Creates a JFrame with some JElements, and replaces CipherAppGUI.listOfCiphers
    // with the loaded CipherAppGUI.listOfCiphers
    LoadFromFile() {
        loadFrame = new JFrame("Load From File");

        loadPanel = new JPanel();

        status = new JTextField("");

        jsonReader = new JsonReader(CipherAppGUI.JSON_STORE_LOCATION);

        try {
            CipherAppGUI.listOfCiphers = jsonReader.read();
            status.setText("Loaded " + "your Cipher List" + " from " + CipherAppGUI.JSON_STORE_LOCATION);
        } catch (IOException e) {
            status.setText("Unable to read from file: " + CipherAppGUI.JSON_STORE_LOCATION);
        }
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.PAGE_AXIS));

        loadPanel.add(status);
        loadFrame.add(loadPanel);

        loadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadFrame.pack();
        loadFrame.setVisible(true);
    }
}
