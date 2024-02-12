//Parts of the Code below, were based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package model;

import model.exception.KeyIsNotEqualToBaseException;
import model.exception.MessageIsEmptyException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a cipher, having a key and a name.
public class Cipher implements Writable {
    public static final String BASE = "abcdefghijklmnopqrstuvwxyz";
    public String key;
    public String name;

    //EFFECTS: Takes the given string, and assigns it as the cipher.
    public Cipher(String key, String name) throws KeyIsNotEqualToBaseException {
        if (key.length() == BASE.length()) {
            this.key = key;
            this.name = name;
        } else {
            throw new KeyIsNotEqualToBaseException();
        }
    }

    //EFFECTS: gets the name of the given cipher.
    public String getName() {
        return name;
    }

    //EFFECTS: gets the name of the given cipher.
    public String getKey() {
        return key;
    }

    //MODIFIES: It changes String message, based on what the cipher is.
    //EFFECTS: Takes String message, and converts each of its characters, to a character based on the cipher.
    public String encrypt(String message) throws MessageIsEmptyException {
        if (message.length() > 0) {
            StringBuilder encryptedMessage = new StringBuilder();
            for (int x = 0; x < message.length(); x++) {
                char letter = message.charAt(x);
                int location = BASE.indexOf(letter);
                letter = key.charAt(location);
                encryptedMessage.append(letter);
            }
            return encryptedMessage.toString();
        } else {
            throw new MessageIsEmptyException();
        }
    }

    //MODIFIES: It reverts String encryptedMessage to English, based on what the cipher is.
    //EFFECTS: Takes String encryptedMessage, and converts each of its characters back to English based on the cipher.
    public String decrypt(String encryptedMessage) throws MessageIsEmptyException {
        if (encryptedMessage.length() > 0) {
            StringBuilder message = new StringBuilder();
            for (int x = 0; x < encryptedMessage.length(); x++) {
                char letter = encryptedMessage.charAt(x);
                int location = key.indexOf(letter);
                letter = BASE.charAt(location);
                message.append(letter);
            }
            return message.toString();
        } else {
            throw new MessageIsEmptyException();
        }
    }

    //EFFECTS: It returns the cipher's key
    public String exportCipher() {
        return key;
    }

    //MODIFIES: this
    //EFFECTS: It takes the existing Cipher and changes it into the new Cipher.
    public void editCipher(Cipher c2) {
        key = c2.exportCipher();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("key", key);
        json.put("name", name);
        return json;
    }
}
