//The code of the Add Function was modeled after some code found on this link,
//https://stackoverflow.com/questions/
// 34186291/how-do-i-lay-out-input-panel-with-multiple-textfields-and-ok-cancel-buttons/34186885
// link is broken up, to meet the line limit.
//All code related to playing a wave file is based on https://coderanch.com/t/341739/java/play-wav-file-button-pressed
//The chime wave file is from http://soundbible.com/1598-Electronic-Chime.html

//No code was directly copied from any of these sites, but the codes was in some parts based on these sites, as in the
// method of implementation was similar, and the name choice was similar, to allow for easier comparison
// while writing the code.

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

//Represents an JFrame window that adds a new cipher to the cipherList, based on the user's choices.
public class AddFunction extends JFrame implements ActionListener {
    private JFrame addFrame;

    private JPanel buttonPanel;
    private JPanel fieldsPanel;

    private JLabel name;
    private JLabel key;

    private JTextField nameField;
    private JTextField keyField;
    private JTextField warning;

    private JButton ok;
    private JButton close;

    private AudioClip chime;

    //EFFECTS: Creates a JFrame window with two panels, one for the buttons and another for the text fields.
    AddFunction() {
        initializeAllElements();
        addAllElements();

        ok.addActionListener(this);
        close.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: Initializes all of the elements
    private void initializeAllElements() {
        addFrame = new JFrame("Add");

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
            chime = Applet.newAudioClip(waveFile.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds all of the Jbuttons and Jfields to their respective JPanels, and the Jpanels to the JFrame
    //         and sets the appearance of the JFrame.
    private void addAllElements() {
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        fieldsPanel.add(name);
        fieldsPanel.add(nameField);
        fieldsPanel.add(key);
        fieldsPanel.add(keyField);
        fieldsPanel.add(warning);
        buttonPanel.add(ok);
        buttonPanel.add(close);
        addFrame.add(fieldsPanel, BorderLayout.PAGE_START);
        addFrame.add(buttonPanel, BorderLayout.PAGE_END);

        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.pack();
        addFrame.setVisible(true);
    }

    //MODIFIES: CipherAppGUI.listOfCiphers
    //EFFECTS: if Ok is pressed the new cipher is added to the CipherAppGUI.listOfCiphers and the frame is disposed,
    // if cancel is pressed the JFrame window is disposed without adding anything to CipherAppGUI.listOfCiphers
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            chime.play();
            String name = nameField.getText();
            String key = keyField.getText();
            if (key.length() != Cipher.BASE.length()) {
                key = key + Cipher.BASE.substring(key.length());
            }
            try {
                CipherAppGUI.listOfCiphers.insertCipher(new Cipher(key, name));
            } catch (KeyIsNotEqualToBaseException keyIsNotEqualToBaseException) {
                warning.setEditable(true);
                warning.setText("Key is Not Equal to base, please ensure key is equal to base");
                warning.setEditable(false);
            }
            addFrame.dispose();
        } else if (e.getSource() == close) {
            chime.play();
            addFrame.dispose();
        }
    }
}