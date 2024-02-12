//Parts of the Code below, were based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of Ciphers
public class CipherList implements Writable {
    public ArrayList<Cipher> cl;

    //EFFECTS: Creates an empty arraylist of type Cipher
    public CipherList() {
        cl = new ArrayList<Cipher>();
    }

    //MODIFIES: this
    //EFFECTS: inserts cipher into the CipherList unless it's already there, in which case do nothing
    public void insertCipher(Cipher c) {
        if (!cl.contains(c)) {
            cl.add(c);
        }
    }

    //EFFECTS: Gets the cipher located at that position in the list.
    public Cipher get(int x) {
        return cl.get(x);
    }

    //MODIFIES: this
    //EFFECTS: if the cipher is in the list, them remove it from the list.
    //         Otherwise, do nothing
    public void removeCipher(Cipher c) {
        if (cl.contains(c)) {
            cl.remove(c);
        }
    }

    //EFFECTS: returns the size of the set
    public int size() {
        return cl.size();
    }

    // EFFECTS: returns an unmodifiable list of ciphers in this CipherList
    public List<Cipher> getCiphers() {
        return Collections.unmodifiableList(cl);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ciphers", ciphersToJson());
        return json;
    }

    // EFFECTS: returns ciphers in this CipherList as a JSON array
    private JSONArray ciphersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Cipher c : cl) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
