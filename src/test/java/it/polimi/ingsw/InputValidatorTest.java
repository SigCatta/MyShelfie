package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {

    @Test
    void isValidIpAddress() {
        assertTrue(InputValidator.isValidIpAddress("192.168.0.1"));
        assertTrue(InputValidator.isValidIpAddress("10.0.0.1"));
        assertTrue(InputValidator.isValidIpAddress("172.16.0.1"));
        assertFalse(InputValidator.isValidIpAddress("256.256.256.256"));
        assertFalse(InputValidator.isValidIpAddress("192.168.0"));
        assertFalse(InputValidator.isValidIpAddress("192.168.0.1.1"));
        assertTrue(InputValidator.isValidIpAddress("192.168.0.01"));
    }

    @Test
    void isValidPort() {
        assertTrue(InputValidator.isValidPort("1024"));
        assertTrue(InputValidator.isValidPort("65535"));
        assertFalse(InputValidator.isValidPort("1023"));
        assertFalse(InputValidator.isValidPort("65536"));
        assertFalse(InputValidator.isValidPort("abc"));
    }
}
