//Code based on the Encrypt Class

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

//Represents a class that extends JFrame and decrypts a user's message
public class Decrypt extends JFrame implements ActionListener {
    private JFrame frame;

    private JPanel encryptedMessagePanel;
    private JPanel buttonPanel;
    private JPanel decryptedMessagePanel;

    private JLabel encryptedMessage;
    private JLabel decryptedMessage;

    private JTextField warning;
    private JTextField encryptedMessageField;
    private JTextField decryptedMessageField;

    private JButton ok;
    private JButton close;

    private AudioClip sound;

    private Cipher chosenCipher;

    //EFFECTS: Creates a JFrame with some JElements
    Decrypt(Cipher c) {
        chosenCipher = c;
        setup();
        setupFrameAdder();

        ok.addActionListener(this);
        close.addActionListener(this);
    }

    //EFFECTS: Initializes all of the elements
    private void setup() {
        frame = new JFrame("Decrypt");

        encryptedMessagePanel = new JPanel();
        buttonPanel = new JPanel();
        decryptedMessagePanel = new JPanel();

        encryptedMessage = new JLabel("The Encrypted Message:");
        decryptedMessage = new JLabel("The Decrypted Message:");

        warning = new JTextField("Encrypted message cannot contain any spaces or any "
                + "characters not defined in the chosen key");
        warning.setEditable(false);
        encryptedMessageField = new JTextField("");
        decryptedMessageField = new JTextField("");

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
        encryptedMessagePanel.setLayout(new BoxLayout(encryptedMessagePanel, BoxLayout.PAGE_AXIS));
        decryptedMessagePanel.setLayout(new BoxLayout(decryptedMessagePanel, BoxLayout.PAGE_AXIS));

        encryptedMessage.add(warning);
        encryptedMessagePanel.add(encryptedMessage);
        encryptedMessagePanel.add(encryptedMessageField);

        buttonPanel.add(ok);
        buttonPanel.add(close);

        decryptedMessagePanel.add(decryptedMessage);
        decryptedMessagePanel.add(decryptedMessageField);

        frame.add(encryptedMessagePanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(decryptedMessagePanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: if ok is pressed, decrypts the users message, if cancel is pressed it disposes the frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            sound.play();
            try {
                decryptedMessageField.setText(chosenCipher.decrypt((encryptedMessageField.getText()).toLowerCase()));
            } catch (MessageIsEmptyException messageIsEmptyException) {
                warning.setEditable(true);
                warning.setText("Message is Empty, please put in a message to decrypt and then press ok");
                warning.setEditable(false);
            }
            decryptedMessageField.setEditable(false);
        } else if (e.getSource() == close) {
            sound.play();
            frame.dispose();
        }
    }
}
