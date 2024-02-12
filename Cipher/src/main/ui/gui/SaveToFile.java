package ui.gui;

import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;

//Represents a class that extends JFrame and saves the CipherList to File
public class SaveToFile extends JFrame {
    private JFrame saveFrame;

    private JPanel savePanel;

    private JTextField status;

    private JsonWriter jsonWriter;


    //MODIFIES: JSON_STORE_LOCATION
    //EFFECTS: Creates a JFrame with some JElements, and saves the listOfCiphers to the chosen file
    SaveToFile() {
        saveFrame = new JFrame("Save To File");

        savePanel = new JPanel();

        status = new JTextField("");

        jsonWriter = new JsonWriter(CipherAppGUI.JSON_STORE_LOCATION);

        try {
            jsonWriter.open();
            jsonWriter.write(CipherAppGUI.listOfCiphers);
            jsonWriter.close();
            status.setText("Saved " + "your Cipher List" + " to " + CipherAppGUI.JSON_STORE_LOCATION);
        } catch (FileNotFoundException e) {
            status.setText("Unable to write to file: " + CipherAppGUI.JSON_STORE_LOCATION);
        }

        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.PAGE_AXIS));

        savePanel.add(status);
        saveFrame.add(savePanel);

        saveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveFrame.pack();
        saveFrame.setVisible(true);
    }

}
