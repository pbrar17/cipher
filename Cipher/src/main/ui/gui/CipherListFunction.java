//The code of the CipherListFunction was modeled after the Code from some code found on this link,
//https://stackoverflow.com/questions/
// 34186291/how-do-i-lay-out-input-panel-with-multiple-textfields-and-ok-cancel-buttons/34186885
// link is broken up, to meet the line limit.
//All code related to playing a wave file is based on https://coderanch.com/t/341739/java/play-wav-file-button-pressed
//The chime wave file is from http://soundbible.com/1598-Electronic-Chime.html
// The code for the JList is in part based on code from the following sites:
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//https://www.codejava.net/java-se/swing/jlist-basic-tutorial-and-examples
//https://stackoverflow.com/questions/29798512/displaying-and-manipulating-arraylist-in-java-swing-gui
//https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
//https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
//http://www.java2s.com/Code/Java/Swing-JFC/
// JListisacomponentthatdisplaysalistofobjectsItallowstheusertoselectoneormoreitems.htm
//No code was directly copied from any of these sites, but the codes was in some parts based on these sites, as in the
// method of implementation was similar, and the name choice was similar, to allow for easier comparison
// while writing the code.

package ui.gui;

import model.Cipher;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import static ui.gui.CipherAppGUI.listOfCiphers;
import static ui.gui.CipherAppGUI.waveFile;

//Represents a class that can perform actions on the selected Cipher in the listOfCiphers.
public class CipherListFunction extends JFrame implements ActionListener {
    private final model.CipherList cipherList = listOfCiphers;
    private JFrame frame;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton encrypt;
    private JButton decrypt;
    private JButton edit;
    private JButton export;
    private JList<String> loc;
    private AudioClip sound;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;

    //EFFECTS:Creates a JList to represent all of the ciphers, a JFrame Window with 2 panels, and a JScrollPane.
    CipherListFunction() {
        defaultListModelMaker();

        setup();
        setupFrameAdder();

        encrypt.addActionListener(this);
        decrypt.addActionListener(this);
        edit.addActionListener(this);
        export.addActionListener(this);
    }

    //EFFECTS: Initializes all of the elements.
    private void setup() {
        frame = new JFrame("Use a Cipher");

        listPanel = new JPanel();
        buttonPanel = new JPanel();

        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        edit = new JButton("Edit");
        export = new JButton("Export");

        loc = new JList<>(listModel);
        loc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(loc);

        try {
            sound = Applet.newAudioClip(waveFile.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: adds all of the elements to the JFrame
    private void setupFrameAdder() {
        listPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new FlowLayout());

        listPanel.add(scrollPane);

        buttonPanel.add(encrypt);
        buttonPanel.add(decrypt);
        buttonPanel.add(edit);
        buttonPanel.add(export);

        frame.add(listPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //MODIFIES: listModel
    //EFFECTS: creates a DefaultListModel that contains all of the names of the cipher,
    // in the same order as the listOfCiphers.
    private void defaultListModelMaker() {
        listModel = new DefaultListModel<>();
        for (int x = 0; x < cipherList.size(); x++) {
            Cipher c = cipherList.get(x);
            listModel.addElement(c.getName());
        }
    }

    //MODIFIES: CipherAppGui.listOfCiphers
    //EFFECTS: plays a sound when a button is clicked, and performs the subsequent action.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encrypt) {
            sound.play();
            doAppropriateAction("encrypt");
            frame.dispose();
        } else if (e.getSource() == decrypt) {
            sound.play();
            doAppropriateAction("decrypt");
            frame.dispose();
        } else if (e.getSource() == edit) {
            sound.play();
            doAppropriateAction("edit");
            frame.dispose();
        } else if (e.getSource() == export) {
            sound.play();
            for (int x = 0; x < cipherList.size(); x++) {
                Cipher c = cipherList.get(x);
                if (c.getName() == loc.getSelectedValue()) {
                    new Export(c);
                }
            }
            frame.dispose();
        }
    }

    //MODIFIES: CipherAppGui.listOfCiphers
    //EFFECTS: depending on what action is, it performs an action.
    private void doAppropriateAction(String action) {
        if (action == "encrypt") {
            for (int x = 0; x < cipherList.size(); x++) {
                Cipher c = cipherList.get(x);
                if (c.getName() == loc.getSelectedValue()) {
                    new Encrypt(c);
                }
            }
        } else if (action == "decrypt") {
            for (int x = 0; x < cipherList.size(); x++) {
                Cipher c = cipherList.get(x);
                if (c.getName() == loc.getSelectedValue()) {
                    new Decrypt(c);
                }
            }
        } else if (action == "edit") {
            for (int x = 0; x < cipherList.size(); x++) {
                Cipher c = cipherList.get(x);
                if (c.getName() == loc.getSelectedValue()) {
                    new Edit(c);
                }
            }
        }
    }
}
