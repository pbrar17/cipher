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
import model.CipherList;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import static ui.gui.CipherAppGUI.listOfCiphers;
import static ui.gui.CipherAppGUI.waveFile;

//Represents a class that extends JFrame and removes the Cipher the user wants to remove
public class RemoveFunction extends JFrame implements ActionListener {
    private JScrollPane scrollPane;

    private JFrame frame;

    private JPanel listPanel;
    private JPanel buttonPanel;

    private JButton remove;
    private JButton close;

    private JList<String> loc;

    private AudioClip sound;

    private DefaultListModel<String> listModel;

    private CipherList cipherList = listOfCiphers;

    //MODIFIES: CipherAppGUI.listOfCiphers
    //EFFECTS: Creates a JFrame with some JElements, and removes the chosen Cipher from the list
    RemoveFunction() {
        defaultListModelMaker();

        setup();
        setupFrameAdder();

        listOfCiphers = cipherList;

        remove.addActionListener(this);
        close.addActionListener(this);
    }

    //EFFECTS: Initializes all of the elements
    private void setup() {
        frame = new JFrame("Remove");

        listPanel = new JPanel();
        buttonPanel = new JPanel();

        remove = new JButton("Remove");
        close = new JButton("Close");

        loc = new JList<>(listModel);
        loc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(loc);

        try {
            sound = Applet.newAudioClip(waveFile.toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    //EFFECTS: Adds all of the Elements to the JFrame, and sets default properties for the JFrame
    private void setupFrameAdder() {
        listPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new FlowLayout());

        listPanel.add(scrollPane);
        buttonPanel.add(remove);
        buttonPanel.add(close);

        frame.add(listPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        if (e.getSource() == remove) {
            sound.play();
            for (int x = 0; x < cipherList.size(); x++) {
                Cipher c = cipherList.get(x);
                if (c.getName() == loc.getSelectedValue()) {
                    cipherList.removeCipher(c);
                    listOfCiphers = cipherList;
                }
            }
            frame.dispose();
        } else if (e.getSource() == close) {
            sound.play();
            frame.dispose();
        }
    }
}
