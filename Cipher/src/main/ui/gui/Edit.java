package ui.gui;

import model.Cipher;
import model.exception.KeyIsNotEqualToBaseException;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import static ui.gui.CipherAppGUI.waveFile;

//Represents a class that extends JFrame and edits the Cipher the user wants to edit
public class Edit extends JFrame {
    private JFrame frame;

    private JPanel buttonPanel;
    private JPanel fieldsPanel;

    private JLabel name;
    private JLabel key;

    private JTextField nameField;
    private JTextField keyField;
    private JTextField warning;

    private JButton ok;
    private JButton close;

    private AudioClip sound;

    //EFFECTS: Creates a JFrame with some JElements
    Edit(Cipher c) {
        setup();
        setupFrameAdder();

        ok.addActionListener(new ActionListener() {
            //MODIFIES: CipherAppGUi.listOfCiphers
            //EFFECTS: when ok is pressed, plays a sound, and then
            // the text on the two fields in used to create a new cipher
            // to replace the chosen cipher
            public void actionPerformed(ActionEvent e) {
                sound.play();
                String name = nameField.getText();
                String key = keyField.getText();
                if (key.length() != Cipher.BASE.length()) {
                    key = key + Cipher.BASE.substring(key.length());
                }
                try {
                    c.editCipher(new Cipher(key, name));
                } catch (KeyIsNotEqualToBaseException keyIsNotEqualToBaseException) {
                    keyWarning();
                }
            }
        });

        //EFFECTS: when cancel is pressed, plays a sound and then disposes the frame.
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sound.play();
                frame.dispose();
            }
        });
    }

    //MODIFIES: warning
    //EFFECTS: changes warning to display different "Key is not equal to 26"
    private void keyWarning() {
        warning.setEditable(true);
        warning.setText("Key is not equal to 26");
        warning.setEditable(false);
    }

    //EFFECTS: Initializes all of the elements
    private void setup() {
        frame = new JFrame("Edit");

        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();

        name = new JLabel("Name");
        key = new JLabel("Key");

        nameField = new JTextField("");
        keyField = new JTextField("");
        warning = new JTextField("Key must be less than or equal to 26 characters");
        warning.setEditable(false);

        ok = new JButton("OK");
        close = new JButton("Close");

        try {
            sound = Applet.newAudioClip(waveFile.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: Adds all of the Elements to the JFrame, and sets default properties for the JFrame
    private void setupFrameAdder() {
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        fieldsPanel.add(name);
        fieldsPanel.add(nameField);
        fieldsPanel.add(key);
        fieldsPanel.add(keyField);
        fieldsPanel.add(warning);
        buttonPanel.add(ok);
        buttonPanel.add(close);
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
