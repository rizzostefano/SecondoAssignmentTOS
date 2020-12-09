////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import it.unipd.tos.model.User;
import org.junit.*;


public class UserTest {

    private static User U1, U2;

    @Before
    public void setUsers() {
        U1 = new User("Paolo", "Rossi", LocalDate.of(1973, 5, 17));
        U2 = new User("Giovanni", "Verdi", LocalDate.of(2007, 9, 5));
    }

    @Test
    public void testGetUserName() {
        assertEquals("Paolo Rossi", U1.getUserName());
        assertEquals("Giovanni Verdi", U2.getUserName());
    }

    @Test
    public void testUser() {
        User u1 = new User(U1);
        assertEquals(U1.getUserName(), u1.getUserName());
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
