package model;

import model.exception.KeyIsNotEqualToBaseException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CipherListTest {
    private CipherList clt;
    private Cipher ct;

    @BeforeEach
    void runBefore() {
        clt = new CipherList();
    }

    @Test
    void testInsertNotThere() {
        assertEquals(0, clt.size());
        try {
            ct = new Cipher("bacdefghijklmonpqrstuvwxyz","testInsertNotThereCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        clt.insertCipher(ct);
        assertEquals(1, clt.size());

    }

    @Test
    void testInsertAlreadyThere() {
        assertEquals(0, clt.size());
        try {
            ct = new Cipher("bacdefghijklmonpqrstuvwxyz", "testInsertAlreadyThereCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        clt.insertCipher(ct);
        assertEquals(1, clt.size());
        clt.insertCipher(ct);
        assertEquals(1, clt.size());
    }

    @Test
    void testRemoveNotThere() {
        assertEquals(0, clt.size());
        try {
            ct = new Cipher("bacdefghijklmonpqrstuvwxyz", "testRemoveNotThereCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        Cipher ct2 = null;
        try {
            ct2 = new Cipher("abdcefghijklmonpqrstuvwxyz", "testRemoveNotThereCipherTwo");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        clt.insertCipher(ct);
        assertEquals(1, clt.size());
        clt.removeCipher(ct2);
        assertEquals(1, clt.size());

    }

    @Test
    void testRemoveThere() {
        assertEquals(0, clt.size());
        try {
            ct = new Cipher("bacdefghijklmonpqrstuvwxyz","testRemoveThereTwoCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        clt.insertCipher(ct);
        assertEquals(1, clt.size());
        clt.removeCipher(ct);
        assertEquals(0, clt.size());
    }

    @Test
    void testRemoveTwoElements() {
        assertEquals(0, clt.size());
        try {
            ct = new Cipher("bacdefghijklmonpqrstuvwxyz", "testRemoveTwoElementsCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        Cipher ct2 = null;
        try {
            ct2 = new Cipher("abdcefghijklmonpqrstuvwxyz", "testRemoveTwoElementsCipherTwo");
        } catch (KeyIsNotEqualToBaseException e) {
            e.printStackTrace();
        }
        clt.insertCipher(ct);
        assertEquals(1, clt.size());
        clt.insertCipher(ct2);
        assertEquals(2, clt.size());
        assertEquals(ct,clt.get(0));
        clt.removeCipher(ct);
        assertEquals(1, clt.size());
    }
}
