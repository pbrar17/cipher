//Parts of the Code below, were based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package ui;

import ui.gui.CipherAppGUI;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Chooses which version of Encoder to run, the console or the GUI
public class Chooser {
    String userCommand;
    private Scanner input;
    private Boolean keepRunning = true;

    //Runs either the Cipher App, or the the CipherAppGui based on User Input
    Chooser() {
        setup();
        while (keepRunning) {
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("c")) {
                try {
                    new CipherApp();
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to run application: file not found");
                }
            } else if (userCommand.equals("g")) {
                new CipherAppGUI();
            } else if (userCommand.equals("q")) {
                keepRunning = false;
            } else {
                System.out.println("The option you have selected is currently not supported by Encoder.");
                System.out.println("Please enter an option that is currently available");
            }
        }
    }

    //EFFECTS: prints statements that allow a user to choose the version of encoder they would like to use.
    private void setup() {
        input = new Scanner(System.in);
        System.out.println("Hello There, Encoder is a Leading Edge Cipher Application");
        System.out.println("Encoder is available in two formats, "
                + "A Console Based User Interface and A Graphical User Interface");
        System.out.println("Please choose which version your would like to launch, "
                + "and press the key that matches your selection");

        System.out.println("\tPlease press c to launch the Console version");
        System.out.println("\tPlease press g to launch the GUI version");
        System.out.println("\tPlease press q to quit the application");
    }
}
