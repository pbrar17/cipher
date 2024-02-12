package model;

import model.exception.KeyIsNotEqualToBaseException;
import model.exception.MessageIsEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {
    private Cipher ct;
    @BeforeEach
    void runBefore(){
        try {
            ct = new Cipher("zyxwuvtsrqponmlkjihgfedcba", "testRunBeforeCipher");
        } catch (KeyIsNotEqualToBaseException e) {
            fail("Test Cipher Cannot be constructed");
        }
    }
    @Test
    void testEncryptNoExceptionExpected(){
        try {
            assertEquals("yzw", ct.encrypt("bad"));
        } catch (MessageIsEmptyException e) {
            fail("No Exception Expected");
        }
    }

    @Test
    void testEncryptExceptionExpected(){
        try {
            ct.encrypt("");
            fail("MessageIsEmptyException Expected");
        } catch (MessageIsEmptyException e) {
        }
    }
    @Test
    void testDecryptNoExceptionExpected(){
       try {
           assertEquals("bad", ct.decrypt("yzw"));
       } catch (MessageIsEmptyException e) {
           fail("No Exception Expected");
       }
    }
    @Test
    void testDecryptExceptionExpected(){
        try {
            ct.decrypt("");
            fail("MessageIsEmptyException Expected");
        } catch (MessageIsEmptyException e) {
        }
    }
    @Test
    void testPrintCipher(){
        assertEquals("zyxwuvtsrqponmlkjihgfedcba", ct.exportCipher());
    }
    @Test
    void testEditCipherNoExceptionExpected(){
        Cipher ct2 = null;
        try {
            ct2 = new Cipher("abdcefghijklmonpqrstuvwxyz", "testEditCipher");
            ct.editCipher(ct2);
            assertEquals(ct2.exportCipher(), ct.exportCipher() );
        } catch (KeyIsNotEqualToBaseException e) {
            fail("No Exception Expected");
        }
    }

    @Test
    void testEditCipherExceptionExpected(){
        try {
            Cipher ct2 = new Cipher("abdcefghijklmonpqrstuvwxy", "testEditCipher");
            fail("Exception Expected");
            ct.editCipher(ct2);
            assertEquals(ct2.exportCipher(), ct.exportCipher() );
        } catch (KeyIsNotEqualToBaseException e) {
        }
    }
    @Test
    void testGetKey(){
        assertEquals("zyxwuvtsrqponmlkjihgfedcba", ct.getKey());
    }
    @Test
    void testGetName(){
        assertEquals("testRunBeforeCipher", ct.getName());
    }
}