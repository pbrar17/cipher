//All code related to playing a wave file is based on https://coderanch.com/t/341739/java/play-wav-file-button-pressed
//The chime wave file is from http://soundbible.com/1598-Electronic-Chime.html
//The code related to the action listener and associating it with a button, can be found on
//https://stackoverflow.com/questions/5936261/how-to-add-action-listener-that-listens-to-multiple-buttons
//Also, some parts of the Code below, were based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//No code was directly copied from any of these sites, but the codes was in some parts based on these sites, as in the
// method of implementation was similar, and the name choice was similar, to allow for easier comparison
// while writing the code.


package ui.gui;

import model.Cipher;
import model.CipherList;
import model.exception.KeyIsNotEqualToBaseException;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.fail;

// Represents a CipherApp GUI that extends JFrame
public class CipherAppGUI extends JFrame implements ActionListener {
    protected static final String JSON_STORE_LOCATION = "./data/cipherApp.json";
    protected static final File waveFile = new File("./data/chime.wav");
    protected static CipherList listOfCiphers;

    private JFrame encoder;

    private JPanel buttonPane;

    private JButton cipherListButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;

    private AudioClip sound;

    //EFFECTS:Creates a JFrame window with one panel,
    // and 5 buttons, and when a button is pressed does the required action.
    public CipherAppGUI() {
        encoder();
        init();
        setup();

        cipherListButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: when a button is pressed, a sound is played, and the corresponding class is called,
    // to do the required action.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cipherListButton) {
            sound.play();
            new CipherListFunction();
        } else if (e.getSource() == addButton) {
            sound.play();
            new AddFunction();
        } else if (e.getSource() == removeButton) {
            sound.play();
            new RemoveFunction();
        } else if (e.getSource() == saveButton) {
            sound.play();
            new SaveToFile();
        } else if (e.getSource() == loadButton) {
            sound.play();
            new LoadFromFile();
        }
    }

    //EFFECTS: initializes the JFrame
    private void encoder() {
        encoder = new JFrame("Encoder");
        encoder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        encoder.pack();
        encoder.setVisible(true);
        encoder.setSize(150, 200);
    }

    //EFFECTS: initializes all of the other elements
    private void setup() {
        cipherListButton = new JButton("Use A Cipher");
        addButton = new JButton("Add A Cipher");
        removeButton = new JButton("Remove A Cipher");
        saveButton = new JButton("Save To File");
        loadButton = new JButton("Load From File");

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());

        buttonPane.add(cipherListButton);
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);

        encoder.add(buttonPane);

        try {
            sound = Applet.newAudioClip(waveFile.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the default ciphers and the cipher list
    private void init() {
        listOfCiphers = new CipherList();
        Cipher atbash = null;
        try {
            atbash = new Cipher("zyxwvutsrqponmlkjihgfedcba", "atbash");
        } catch (KeyIsNotEqualToBaseException e) {
            fail("Exception thrown during initialization");
        }
        listOfCiphers.insertCipher(atbash);
        Cipher rot = null;
        try {
            rot = new Cipher("nopqrstuvwxyzabcdefghijklm", "rot");
        } catch (KeyIsNotEqualToBaseException e) {
            fail("Exception thrown during initialization");
        }
        listOfCiphers.insertCipher(rot);
        Cipher caesar = null;
        try {
            caesar = new Cipher("bcdefghijklmnopqrstuvwxyza", "caesar");
        } catch (KeyIsNotEqualToBaseException e) {
            fail("Exception thrown during initialization");
        }
        listOfCiphers.insertCipher(caesar);
    }
}
