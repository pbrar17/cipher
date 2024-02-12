//The Code below, is based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Cipher;
import model.CipherList;
import model.exception.KeyIsNotEqualToBaseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads cipherList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CipherList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCipherList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private CipherList parseCipherList(JSONObject jsonObject) {
        CipherList wr = new CipherList();
        addCiphers(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses ciphers from JSON object and adds them to workroom
    private void addCiphers(CipherList wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ciphers");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCipher(wr, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCipher(CipherList wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String key = (jsonObject.getString("key"));
        Cipher c = null;
        try {
            c = new Cipher(key, name);
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        wr.insertCipher(c);
    }
}
