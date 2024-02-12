//The Code below, is based on Code from the WorkRoom Application, link below.
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Cipher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCipher(String name, String key, Cipher c) {
        assertEquals(name, c.getName());
        assertEquals(key, c.getKey());
    }
}
