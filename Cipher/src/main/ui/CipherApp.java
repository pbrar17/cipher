//Parts of the Code below, were based on Code from the Teller Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//Also, some parts of the Code below, were based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package ui;

import model.Cipher;
import model.CipherList;
import model.exception.KeyIsNotEqualToBaseException;
import model.exception.MessageIsEmptyException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

// Cipher Application
public class CipherApp {
    private static final String JSON_STORE_LOCATION = "./data/cipherApp.json";
    private CipherList listOfCiphers;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the cipher application
    public CipherApp() throws FileNotFoundException {
        listOfCiphers = new CipherList();
        jsonWriter = new JsonWriter(JSON_STORE_LOCATION);
        jsonReader = new JsonReader(JSON_STORE_LOCATION);
        runCipherApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user's input
    public void runCipherApp() {
        boolean keepRunning = true;
        String userCommand;

        init();
        System.out.println("\nWelcome to Encoder, the modern day Cipher Java Desktop Application");
        System.out.println("\nWhen entering anything, please use lower case.");
        System.out.println("\nThank you for your Cooperation in advance!");

        while (keepRunning) {
            startUpScreen();
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(userCommand);
            }
        }
        System.out.println("\nThank You For Using Encoder!");
        System.out.println("\nHave a Good Rest of Your Day!");
    }

    //EFFECTS: processes user command
    private void processCommand(String userCommand) {
        Cipher selected = null;
        try {
            selected = new Cipher("abcdefghijklmnopqrstuvwxyz", "test");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < listOfCiphers.size(); x++) {
            Cipher c = listOfCiphers.get(x);
            if (userCommand.equals(c.getName())) {
                selected.name = c.getName();
                selected.key = c.getKey();
                break;
            }
        }
        doOperation(userCommand, selected);
    }

    private void doOperation(String userCommand, Cipher selected) {
        if (userCommand.equals(selected.getName())) {
            operationsForSelectedCipher(selected);
        } else if (userCommand.equals("remove")) {
            removeCipher();
        } else if (userCommand.equals("add")) {
            addCipher();
        } else if (userCommand.equals("save")) {
            saveCipherList();
        } else if (userCommand.equals("load")) {
            loadCipherList();
        } else {
            System.out.println("Invalid Selection");
        }
    }

    // EFFECTS: saves the cipher list to file
    private void saveCipherList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfCiphers);
            jsonWriter.close();
            System.out.println("Saved " + "your Cipher List" + " to " + JSON_STORE_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads cipher list from file
    private void loadCipherList() {
        try {
            listOfCiphers = jsonReader.read();
            System.out.println("Loaded " + "your Cipher List" + " from " + JSON_STORE_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_LOCATION);
        }
    }

    //EFFECTS: it does the operation, the user wants, with the given cipher
    private void operationsForSelectedCipher(Cipher c) {
        System.out.println("Choose the function that you would like to do with this cipher");
        System.out.println("encrypt, decrypt, edit, or export");
        String userCommand;
        userCommand = (input.next()).toLowerCase();
        if (userCommand.equals("encrypt")) {
            doEncryption(c);

        } else if (userCommand.equals("decrypt")) {
            doDecryption(c);

        } else if (userCommand.equals("edit")) {
            editCipher(c);

        } else if (userCommand.equals("export")) {
            exportCipher(c);
        }
    }

    //MODIFIES: list of ciphers
    //EFFECTS: it adds a new cipher to the list of ciphers
    private void addCipher() {
        System.out.println("Enter the cipher's name");
        String name = input.next();
        System.out.println("Enter the cipher's key");
        System.out.println("\tKey must be less than or equal to 26 characters");
        String key = input.next();
        if (key.length() != Cipher.BASE.length()) {
            key = key + Cipher.BASE.substring(key.length());
        }
        Cipher c = null;
        try {
            c = new Cipher(key, name);
        } catch (KeyIsNotEqualToBaseException e) {
            System.out.println("The Key was not equal to: " + Cipher.BASE.length());
            System.out.println("Please try again");
            addCipher();
        }
        listOfCiphers.insertCipher(c);
    }

    //MODIFIES: the list of ciphers
    //EFFECTS: it removes the cipher from the list
    private void removeCipher() {
        for (int counter = 0; counter < listOfCiphers.size(); counter++) {
            Cipher c = listOfCiphers.get(counter);
            System.out.println(c.getName());
        }
        String userCommand;
        System.out.println("\tPlease type the name of the Cipher you want to remove as listed above");
        userCommand = (input.next()).toLowerCase();
        for (int counter = 0; counter < listOfCiphers.size(); counter++) {
            if (userCommand.equals((listOfCiphers.get(counter)).getName())) {
                listOfCiphers.removeCipher((listOfCiphers.get(counter)));
            }
        }
    }

    //MODIFIES: the message the user enters
    //EFFECTS: it takes the message, and encrypts it with the given cipher
    private void doEncryption(Cipher c) {
        System.out.println("Please enter a message to encrypt");
        System.out.println("Message cannot contain any spaces or any characters not in the range of a-z");
        String userInput = (input.next()).toLowerCase();
        System.out.println("here is your encrypted message");
        try {
            System.out.println(c.encrypt(userInput));
        } catch (MessageIsEmptyException e) {
            System.out.println("Message to encrypt is empty.");
            doEncryption(c);
        }
    }

    //MODIFIES: the encrypted message the user enters
    //EFFECTS: it takes the encrypted message and decrypts it with the given cipher
    private void doDecryption(Cipher c) {
        System.out.println("Please enter a message to decrypt");
        String userInput = (input.next()).toLowerCase();
        System.out.println("here is your decrypted message");
        try {
            System.out.println(c.decrypt(userInput));
        } catch (MessageIsEmptyException e) {
            System.out.println("Message to decrypt is empty.");
            doDecryption(c);
        }
    }

    //MODIFIES: this
    //EFFECTS: it replaces the current cipher based on user's requests
    private void editCipher(Cipher c) {
        System.out.println("Please give a new cipher key, you want to replace the old key with");
        System.out.println("\tKey must be less than or equal to 26 characters");
        String userInputKey = input.next();
        if (userInputKey.length() != Cipher.BASE.length()) {
            userInputKey = userInputKey + Cipher.BASE.substring(userInputKey.length());
        }
        System.out.println("Please give a new cipher name, you want to replace the old name with");
        String userInputName = input.next();
        try {
            c.editCipher(new Cipher(userInputKey, userInputName));
        } catch (KeyIsNotEqualToBaseException e) {
            System.out.println("The Key was not equal to: " + Cipher.BASE.length());
            System.out.println("Please try again");
            editCipher(c);
        }
        System.out.println("Cipher has been updated");
    }

    //EFFECTS: prints out the key of the cipher
    private void exportCipher(Cipher c) {
        c.exportCipher();
        System.out.println("Here is the Cipher");
        System.out.println(c.exportCipher());
    }


    //MODIFIES: this
    //EFFECTS: initializes the default ciphers and the cipher list, and the Scanner
    public void init() {
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
        input = new Scanner(System.in);
    }

    //EFFECTS: Shows the available menu options to the user
    public void startUpScreen() {
        System.out.println("\nPlease type the name of the Cipher you want to use as listed below or press q to quit the"
                + " application");
        System.out.println();
        System.out.println("\nBelow are the Ciphers available:");
        for (int counter = 0; counter < listOfCiphers.size(); counter++) {
            System.out.println(listOfCiphers.get(counter).getName());
        }
        System.out.println();
        System.out.println("\nIf you would like to remove a cipher, please type remove, "
                + "and if you would like to add a "
                + "cipher, please type add");
        System.out.println("\nYou can also:");
        System.out.println("\nType save to save your cipher list to file");
        System.out.println("\nType load to load your cipher list from file");
    }
}
