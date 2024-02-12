package ui.gui;

import model.Cipher;
import model.exception.MessageIsEmptyException;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import static ui.gui.CipherAppGUI.waveFile;

//Represents a class that extends JFrame and encrypts a user's message
public class Encrypt extends JFrame implements ActionListener {
    private JFrame frame;

    private JPanel unencryptedMessagePanel;
    private JPanel buttonPanel;
    private JPanel encryptedMessagePanel;

    private JLabel unencryptedMessage;
    private JLabel encryptedMessage;

    private JTextField unencryptedMessageField;
    private JTextField encryptedMessageField;
    private JTextField warning;

    private JButton ok;
    private JButton close;

    private AudioClip sound;

    private Cipher chosenCipher;

    //EFFECTS: Creates a JFrame with some JElements
    Encrypt(Cipher c) {
        chosenCipher = c;
        setup();
        setupFrameAdder();

        ok.addActionListener(this);
        close.addActionListener(this);
    }

    //EFFECTS: Initializes all of the elements
    private void setup() {
        frame = new JFrame("Encrypt");

        unencryptedMessagePanel = new JPanel();
        buttonPanel = new JPanel();
        encryptedMessagePanel = new JPanel();

        unencryptedMessage = new JLabel("Your Message:");
        encryptedMessage = new JLabel("Your Encrypted Message:");

        warning = new JTextField("Message cannot contain any spaces or any characters not in the range of a-z");
        warning.setEditable(false);
        unencryptedMessageField = new JTextField("");
        encryptedMessageField = new JTextField("");

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
        unencryptedMessagePanel.setLayout(new BoxLayout(unencryptedMessagePanel, BoxLayout.PAGE_AXIS));
        encryptedMessagePanel.setLayout(new BoxLayout(encryptedMessagePanel, BoxLayout.PAGE_AXIS));

        unencryptedMessagePanel.add(warning);
        unencryptedMessagePanel.add(unencryptedMessage);
        unencryptedMessagePanel.add(unencryptedMessageField);

        buttonPanel.add(ok);
        buttonPanel.add(close);

        encryptedMessagePanel.add(encryptedMessage);
        encryptedMessagePanel.add(encryptedMessageField);

        frame.add(unencryptedMessagePanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(encryptedMessagePanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: if ok is pressed, encrypts the users message, if cancel is pressed it disposes the frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            sound.play();
            try {
                encryptedMessageField.setText(chosenCipher.encrypt((unencryptedMessageField.getText()).toLowerCase()));
            } catch (MessageIsEmptyException messageIsEmptyException) {
                warning.setEditable(true);
                warning.setText("Message to Encrypt is Empty, please input a message to encrypt and then press ok");
                warning.setEditable(false);
            }
            encryptedMessageField.setEditable(false);
        } else if (e.getSource() == close) {
            sound.play();
            frame.dispose();
        }
    }
}
