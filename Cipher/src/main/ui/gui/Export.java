package ui.gui;

import model.Cipher;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import static ui.gui.CipherAppGUI.waveFile;

//Represents a class that extends JFrame and exports the Cipher the user wants to export
public class Export {
    private JFrame frame;

    private JPanel buttonPanel;
    private JPanel fieldsPanel;

    private JLabel key;

    private JTextField keyField;

    private JButton close;

    private AudioClip sound;

    //EFFECTS: Creates a JFrame with some JElements
    Export(Cipher c) {
        setup();
        setupFrameAdder();
        keyField.setText(c.exportCipher());
        keyField.setEditable(false);

        //EFFECTS: when cancel is pressed, plays a sound and then disposes the frame.
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sound.play();
                frame.dispose();
            }
        });
    }

    //EFFECTS: Initializes all of the elements
    private void setup() {
        frame = new JFrame("Export");

        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();

        key = new JLabel("The Cipher Key");

        keyField = new JTextField("");

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

        fieldsPanel.add(key);
        fieldsPanel.add(keyField);

        buttonPanel.add(close);
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(200, 150);
    }
}
