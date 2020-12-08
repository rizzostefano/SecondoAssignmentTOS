////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import it.unipd.tos.model.User;
import org.junit.*;


public class UserTest {

    private static final User 
    U1 = new User("Paolo", "Lampredotto", LocalDate.of(1973, 5, 17)),
    U2 = new User("Joaquin", "Merdovich", LocalDate.of(2007, 9, 5));

    @Test
    public void testGetUserName() {
        assertEquals("Paolo Lampredotto", U1.getUserName());
        assertEquals("Joaquin Merdovich", U2.getUserName());
    }

    @Test
    public void testGetBirthDate() {
        assertEquals("1973-05-17", U1.getBirthDate().toString());
        assertEquals("2007-09-05", U2.getBirthDate().toString());
    }

    @Test
    public void testIsUnderage() {
        assertEquals(false, U1.isUnderage());
        assertEquals(true, U2.isUnderage());
    }
    
}
