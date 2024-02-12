//The Code below, is based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Cipher;
import model.CipherList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CipherList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // test passes
        }
    }

    @Test
    void testReaderEmptyCipherList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCipherList.json");
        try {
            CipherList cl = reader.read();
            assertEquals(0, cl.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCipherList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCipherList.json");
        try {
            CipherList cl = reader.read();
            List<Cipher> lc = cl.getCiphers();
            assertEquals(2, lc.size());
            checkCipher("caesar", "bcdefghijklmnopqrstuvwxyza", lc.get(0));
            checkCipher("rot", "nopqrstuvwxyzabcdefghijklm", lc.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
