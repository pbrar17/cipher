//The Code below, is based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

        package persistence;

import model.Cipher;
import model.CipherList;
import model.exception.KeyIsNotEqualToBaseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            CipherList wr = new CipherList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCipherList() {
        try {
            CipherList cl = new CipherList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCipherList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCipherList.json");
            cl = reader.read();
            assertEquals(0, cl.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCipherList() {
        try {
            CipherList cl = new CipherList();
            try {
                cl.insertCipher(new Cipher("bcdefghijklmnopqrstuvwxyza", "caesar"));
            } catch (KeyIsNotEqualToBaseException e) {
                e.printStackTrace();
            }
            try {
                cl.insertCipher(new Cipher("nopqrstuvwxyzabcdefghijklm", "rot"));
            } catch (KeyIsNotEqualToBaseException e) {
                e.printStackTrace();
            }

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCipherList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCipherList.json");
            cl = reader.read();
            List<Cipher> lc = cl.getCiphers();
            assertEquals(2, lc.size());
            checkCipher("caesar", "bcdefghijklmnopqrstuvwxyza", lc.get(0));
            checkCipher("rot", "nopqrstuvwxyzabcdefghijklm", lc.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
